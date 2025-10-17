package ar.edu.uns.cs.ed.tdas.tdacolaconprioridad;

import java.util.Comparator;

import ar.edu.uns.cs.ed.tdas.Entrada;
import ar.edu.uns.cs.ed.tdas.Entry;
import ar.edu.uns.cs.ed.tdas.excepciones.EmptyPriorityQueueException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidKeyException;

public class ColaConPriodidadHEAP<K,V> implements PriorityQueue<K,V> {
    protected Entrada<K,V>[] arregloelementos;
    protected int cant;
    protected Comparator<K> comp;

    public ColaConPriodidadHEAP(Comparator<K> c){
        arregloelementos= (Entrada<K,V>[]) new Entrada[1000];
        comp= c;
        cant=0;
    }

    public int size() {
        return cant;
    }

    public boolean isEmpty() {
        return cant==0;
    }

    public Entry<K, V> min() {
        if(cant==0){
            throw new EmptyPriorityQueueException("la cola con prioridad esta vacia");
        }
        Entrada<K,V> min= arregloelementos[1];

        for(int i=0; i<cant;i++){
            if(comp.compare(arregloelementos[i+1].getKey(), min.getKey())<0){
                min=arregloelementos[i+1];
            }

        }
        return min;
    }

    public Entry<K, V> insert(K key, V value) {
        if(key==null){
            throw new InvalidKeyException("clave invalida");
        }
        if(cant+1==arregloelementos.length){
            Entrada<K,V>[] arreglonuevo= (Entrada<K,V>[]) new Entrada[arregloelementos.length*2];
            for(int i=0;i<cant; i++){
                arreglonuevo[i+1]= arregloelementos[i+1];
            }
            arregloelementos=arreglonuevo;
        }
        //creo la entrada
        Entrada<K,V> enuev= new Entrada<K,V>(key, value);
        //pongo la entrada al final del "arbol"
        cant++;
        arregloelementos[cant]=enuev;
        //armo auxiliares 
        int aux=cant;
        boolean subir = true;
        //comienza a intercalar dependiendo la prioridad
        while(aux>0 && subir){
            int padre= aux/2;
            //comparo prioridad con el padre
            if(padre!=0 && comp.compare(arregloelementos[aux].getKey(), arregloelementos[padre].getKey())>0){
                //intercambio si tiene mayor prioridad
                Entrada<K,V> temporal = arregloelementos[padre];
                arregloelementos[padre]=arregloelementos[aux];
                arregloelementos[aux]=temporal;
                aux=padre;//la pos nueva para poder controlar con los demas la prioridad
            }
            else{
                subir=false;
            }

        }
        
        return enuev;
    }

    public Entry<K, V> removeMin() {
        /* eliminar max prioridad???
        //se saca el elem de la raiz del arbol
        Entrada<K,V> raiz= arregloelementos[1];
        //se pone el ultimo elem en la raiz
        arregloelementos[1]=arregloelementos[cant];
        arregloelementos[cant]=null;
        //pongo auxiliares
        int aux = 1;
        boolean bajar=true;
        //comienzo ciclo
        while(aux<cant && bajar){
            int hijoizq=2*aux;
            int hijodere=2*aux+1;
            int menor= aux;
            if(comp.compare(arregloelementos[hijoizq].getKey(), arregloelementos[hijodere].getKey())<0){

            }
        } */
        if(cant==0){
            throw new EmptyPriorityQueueException("cola con prioridad vacia");
        }
        //encuentro el minimo
        Entry<K,V> min= min();
        boolean encontre = false;
        int poseli=0;
        //saco la posicion en el arreglo
        for(int i=0; i<cant && !encontre; i++){
            if(arregloelementos[i+1]==min){
                poseli=i+1;
                encontre=true;
            }
        }
        //comienzo a modificar arbol para que no me quede mal el orden de prioridad 
        if(poseli==cant){//si es el ultimo que ingreso, solo lo elimino
            arregloelementos[poseli]=null;
            cant--;
        }
        else{//si no es el ultimo que ingreso
            arregloelementos[poseli]=arregloelementos[cant];
            arregloelementos[cant]=null;
            cant--;
        } 
        return min;

    }
    
}
