package ar.edu.uns.cs.ed.tdas.tdadiccionario;

import java.util.Iterator;

import ar.edu.uns.cs.ed.tdas.Entrada;
import ar.edu.uns.cs.ed.tdas.Entry;
import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidEntryException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidKeyException;
import ar.edu.uns.cs.ed.tdas.tdalista.DNode;
import ar.edu.uns.cs.ed.tdas.tdalista.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;

public class DiccionarioHashAbierto<K,V> implements Dictionary<K,V> {
    protected int cantidad;
    protected int cubetas;
    protected PositionList<Entry<K,V>> [] arreglo;

    public DiccionarioHashAbierto(){
        cantidad =0;
        cubetas = 17;
        arreglo= new ListaDoblementeEnlazada[cubetas];
        for(int i=0; i< this.cubetas; i++){
            arreglo[i]= new ListaDoblementeEnlazada<Entry<K,V>>();
        }
    }

    public int size() {
        return cantidad;
    }

    public boolean isEmpty() {
        return cantidad ==0;
    }

    public Entry<K, V> find(K key) {
        if(key==null){
            throw new InvalidKeyException("se hizo find con una key nula");
        }
        else{
            int cubeta= hash(key);
            Iterator<Entry<K,V>> ite= arreglo[cubeta].iterator();
            Entry<K,V> resultado= null;
            boolean encontre= false;
            while(ite.hasNext()&& !encontre){
               Entry<K,V> entrada= ite.next(); 
               K clave= entrada.getKey();
               if(clave.equals(key)){
                encontre=true;
                resultado= entrada;
               }
            }
            return resultado;
        }
    }

    private int hash(K key){
        return (key.hashCode() % cubetas);
    }

    public Iterable<Entry<K, V>> findAll(K key) {
        if(key==null){
            throw new InvalidKeyException("se hizo find con una key nula");
        }
        else{
            int cubeta= hash(key);
            PositionList<Entry<K,V>> resultado= new ListaDoblementeEnlazada<Entry<K,V>>();
            for(Entry<K,V> e: arreglo[cubeta]){
                if(key.equals(e.getKey()) ){
                    resultado.addLast(e);
                }
            }
            
            return resultado;
        }
    }

    public Entry<K, V> insert(K key, V value) {
        if(key==null){
            throw new InvalidKeyException("se intento insertar con clave nula");
        }
        else{
            Iterable<Entry<K,V>> listaentradas= this.findAll(key);
            boolean encontre= false;
            int cubeta= hash(key);
            Entry<K,V> enuevo= new Entrada(key,value);
            for(Entry<K,V> e : listaentradas){
                if(e.getValue().equals(value)&&e.getKey().equals(key)){//
                    encontre= true;
                }
            }
            if(!encontre){
                arreglo[cubeta].addLast(enuevo);
                cantidad ++;
            }

            return enuevo;
        }
    }

    public Entry<K, V> remove(Entry<K, V> e) {
        if(e==null || find(e.getKey())== null){
            throw new InvalidEntryException("se intento insertar con clave nula");
        }
        else{
            int cubeta= hash(e.getKey());
            Iterator<Position<Entry<K,V>>> ite= arreglo[cubeta].positions().iterator();
            boolean encontre= false;
            K clave = e.getKey();
            V value = e.getValue();
            while(ite.hasNext() && !encontre){
               Position<Entry<K,V>> posicionEntrada= ite.next(); 
               if(clave.equals(posicionEntrada.element().getKey()) && value.equals(posicionEntrada.element().getValue())){
                encontre=true;
                arreglo[cubeta].remove(posicionEntrada);
                cantidad --;
               }
            }
            return e;

        }
    }

    public Iterable<Entry<K, V>> entries() {
        PositionList<Entry<K,V>> listaEntradasDiccionario= new ListaDoblementeEnlazada<>();
        for(int i= 0; i< cubetas;i++){
            for(Entry<K,V> e: arreglo[i]){
                listaEntradasDiccionario.addLast(e);
            }
        }
        return listaEntradasDiccionario;
    }
    
}
