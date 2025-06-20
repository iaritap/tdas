package ar.edu.uns.cs.ed.tdas.tdapila;

import ar.edu.uns.cs.ed.tdas.excepciones.EmptyStackException;

public class PilaConLista<E> implements Stack<E> {
    protected E[] pila;
    protected int capacidad= 1000;
    protected int size;
    
    public PilaConLista(){
        pila= (E[]) new Object[capacidad];
        size= 0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public E top(){
        if(!this.isEmpty()){
            return pila[size-1];
        }
        else {
            EmptyStackException e= new EmptyStackException("se intento hacer top con pila vacia");
            throw e;
        }
        
    }

    public void push(E elem){
        if(size!= capacidad){
            pila[size]= elem;
            size++;
        }
        else{
            E[] pilanueva= (E[]) new Object[capacidad*2];
            capacidad= capacidad*2;

            for(int i=0; i<size; i++){
                pilanueva[i]=pila[i];
            }

            pila=pilanueva;
            pila[size]= elem;

        }
    }

    public E pop(){
        if(!this.isEmpty()){
            E rta= pila[size-1];
            pila[size-1]=null;
            size--;
            return rta;
        }
        else{
            EmptyStackException e= new ar.edu.uns.cs.ed.tdas.excepciones.EmptyStackException("se intento hacer pop con pila vacia");
            throw e;
        }
    }
}
