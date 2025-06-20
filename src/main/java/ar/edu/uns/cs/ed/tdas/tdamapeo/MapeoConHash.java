package ar.edu.uns.cs.ed.tdas.tdamapeo; // porque me pone ese error y no me marca los otros?
import ar.edu.uns.cs.ed.tdas.Entry;
import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.Entrada;
import java.util.Iterator;
import ar.edu.uns.cs.ed.tdas.excepciones.*;

import ar.edu.uns.cs.ed.tdas.tdalista.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;
import ar.edu.uns.cs.ed.tdas.tdamapeo.Map; //no se que toque no me deja editar nada
// sera por github? los repositorios?

public class MapeoConHash<K,V> implements Map<K,V> {
    protected int cantidad;
    protected int cubetas;
    protected PositionList<Entry<K,V>> [] arreglo;

    public MapeoConHash(){
        cantidad=0;
        cubetas=11;
        arreglo= new ListaDoblementeEnlazada[cubetas];
        for(int i=0; i< this.cubetas; i++){
            arreglo[i]= new ListaDoblementeEnlazada<Entry<K,V>>();
        }
    }

    public int size(){
        return cantidad;
    }

    public boolean isEmpty(){
        return cantidad==0;
    }

    public V get(K key){
        if(key== null){
            throw new InvalidKeyException("se intento hacer get con clave nula");   
        }
        else{
            int cubeta= hash(key);
            Iterator<Entry<K,V>> ite= arreglo[cubeta].iterator();
            V resultado= null;
            boolean encontre= false;
            while(ite.hasNext()&& !encontre){
               Entry<K,V> entrada= ite.next(); 
               K clave= entrada.getKey();
               if(clave== key){
                encontre=true;
                resultado= entrada.getValue();
               }
            }
            return resultado;
        }
        
    }

    private int hash(K key){
        return (key.hashCode() % cubetas);
    }

    public V put(K key, V value){
      if(key== null){
            throw new InvalidKeyException("se intento hacer put con clave nula");   
        }
        else{
            int cubeta= hash(key);
            Iterator<Entry<K,V>> ite= arreglo[cubeta].iterator();
            V resultado= null;
            boolean encontre= false;
            K clave= null;
            Entrada<K,V> entrada= null;
            while(ite.hasNext() && !encontre){
               entrada= (Entrada<K,V>)ite.next(); 
               clave= entrada.getKey();
               if(clave.equals(key)){
                encontre=true;
                resultado= entrada.getValue();
                entrada.setValue(value); 
               }
            }
            if(!encontre){
            Entry<K,V> nueva= new Entrada<K,V>(key, value);
            arreglo[cubeta].addLast(nueva);
            cantidad ++;
            }
            
            return resultado;
        } 
        
    }

    public V remove(K key){
        if(key== null){
            throw new InvalidKeyException("se intento hacer put con clave nula");   
        }
        else{
            int cubeta= hash(key);
            V resultado= null;
            Iterator<Position<Entry<K,V>>> ite= arreglo[cubeta].positions().iterator();
            boolean encontre= false;
            while(ite.hasNext() && !encontre){
               Position<Entry<K,V>> posicionEntrada= ite.next(); 
               K clave= posicionEntrada.element().getKey();
               if(clave.equals(key)){
                encontre=true;
                resultado= posicionEntrada.element().getValue();
                arreglo[cubeta].remove(posicionEntrada);
                cantidad--;
               }
            }  
            return resultado;
        }

        
    }

    public Iterable<K> keys(){
        PositionList<K> itk= new ListaDoblementeEnlazada<K>();
        for(int i=0; i<cubetas; i++){
            for(Entry<K,V> entrada: arreglo[i]){
                itk.addLast(entrada.getKey());
            }
        }

        return itk;
    }

    public Iterable<V> values(){
        PositionList<V> itv= new ListaDoblementeEnlazada<V>();
        for(int i=0; i<cubetas; i++){
            for(Entry<K,V> entrada: arreglo[i]){
                itv.addLast(entrada.getValue());
            }
        }

        return itv;
    }

    public Iterable<Entry<K,V>> entries(){
        PositionList<Entry<K,V>> ite= new ListaDoblementeEnlazada<Entry<K,V>>();
        for(int i=0; i<cubetas; i++){
            for(Entry<K,V> entrada: arreglo[i]){
                ite.addLast(entrada);
            }
        }

        return ite;
    }
}