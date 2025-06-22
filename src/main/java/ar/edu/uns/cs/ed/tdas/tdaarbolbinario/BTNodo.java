package ar.edu.uns.cs.ed.tdas.tdaarbolbinario;

import ar.edu.uns.cs.ed.tdas.Position;

public class BTNodo<E> implements Position<E> {
    protected E elem;
    protected BTNodo<E> hijoIzq;
    protected BTNodo<E> hijoDere;
    protected BTNodo<E> padre;

    public BTNodo(E e, BTNodo<E> hi, BTNodo<E> hd, BTNodo<E> p){
        elem =e;
        hijoIzq=hi;
        hijoDere=hd;
        padre=p;
    }

    
    public E element(){
        return elem;
    }

    public void setElem(E elem) {
        this.elem = elem;
    }


    public BTNodo<E> getHijoIzq() {
        return hijoIzq;
    }


    public void setHijoIzq(BTNodo<E> hijoIzq) {
        this.hijoIzq = hijoIzq;
    }


    public BTNodo<E> getHijoDere() {
        return hijoDere;
    }


    public void setHijoDere(BTNodo<E> hijoDere) {
        this.hijoDere = hijoDere;
    }


    public BTNodo<E> getPadre() {
        return padre;
    }


    public void setPadre(BTNodo<E> padre) {
        this.padre = padre;
    }
    
}
