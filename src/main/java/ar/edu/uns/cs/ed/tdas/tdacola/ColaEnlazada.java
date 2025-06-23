package ar.edu.uns.cs.ed.tdas.tdacola;

import ar.edu.uns.cs.ed.tdas.excepciones.EmptyQueueException;

public class ColaEnlazada<E> implements Queue<E>{
    protected E[] cola;
    protected int capacidad= 1000;
    protected int size= 0;

    public ColaEnlazada(){
        cola= (E[]) new Object[capacidad];
        size=0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public E front(){
        if(!isEmpty()){
            return cola[0];
        }
        else{
            EmptyQueueException e= new EmptyQueueException("se intento hacer front con cola vacia");
            throw e;
        }
    }

    public void enqueue(E elem){
        if(size!= capacidad){
            cola[size]= elem;
            size++;
        }
        else{
            E[] colanueva = (E[]) new Object[capacidad*2];
            capacidad= capacidad*2;

            for(int i=0; i<size; i++){
                colanueva[i]=cola[i];
            }

            cola= colanueva;
            cola[size]=elem;
        }
    }

    public E dequeue(){
        if(!isEmpty()){
            E rta = cola[0];
            cola[0]= null;
            size--;
            for(int i=0; i<size; i++){
                cola[i]= cola[i+1];
            }
            return rta;
        }
        else{
            EmptyQueueException e = new EmptyQueueException("se intento hacer dequeue con cola vacia");
            throw e;
        }
        
    }

}
