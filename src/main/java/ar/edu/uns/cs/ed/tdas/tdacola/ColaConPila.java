package ar.edu.uns.cs.ed.tdas.tdacola;
import java.util.Stack;

import ar.edu.uns.cs.ed.tdas.excepciones.EmptyQueueException;

public class ColaConPila<E> implements Queue<E>{
    Stack<E> cola = new Stack<E>();
    public int size(){
        return cola.size();
    }

    public boolean isEmpty(){
        return cola.isEmpty();
    }

    public E front(){
        if(!isEmpty()){
            return cola.get(0);
        }
        else{
            EmptyQueueException e= new EmptyQueueException("se intento hacer front con cola vacia");
            throw e;
        }
    }

    public void enqueue(E elem){
        cola.add(elem);
    }

    public E dequeue(){
        if(!isEmpty()){
            Stack<E> colanueva= new Stack<E>();
            E elem_eliminado = cola.get(0);
            for(int i=1; i<size(); i++){
                colanueva.add(cola.get(i));
            }
            cola= colanueva;

            return elem_eliminado;
        }
        else{
            EmptyQueueException e= new EmptyQueueException("se intento hacer front con cola vacia");
            throw e;
        }
    }
}
