package ar.edu.uns.cs.ed.tdas;

import ar.edu.uns.cs.ed.tdas.excepciones.InvalidKeyException;

public class Entrada<K,V> implements Entry<K, V>{
    K key;
    V value;
    public Entrada(K k, V v){
        key = k;
        value= v;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V v){
        if(v==null){
            throw new InvalidKeyException("se intento hacer setValue con una clave nula"); 
        }
        else{
            value= v;
        }
    }

    public void setKey(K k){
        if(k==null){
            throw new InvalidKeyException("se intento hacer setkey con una clave nula"); 
        }
        else{
            key= k;
        }
    }
}
