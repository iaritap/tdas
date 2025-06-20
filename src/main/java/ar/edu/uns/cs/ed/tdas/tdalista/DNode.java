package ar.edu.uns.cs.ed.tdas.tdalista;

import ar.edu.uns.cs.ed.tdas.Position;

public class DNode<E> implements Position<E>{
    protected E elemento;
    protected DNode<E> next;
    protected DNode<E> before;

    public DNode(E elem){
        elemento = elem;
        before = null;
        next = null;
    }

    public DNode(E elem, DNode<E> bef, DNode<E> ne){
        elemento = elem;
        before = bef;
        next = ne;
    }
	
	public void setElemento( E _elemento ) {
		elemento = _elemento;
	}
	
	public DNode<E> getSiguiente() {
		return next;
	}
	
	public void setSiguiente( DNode<E> _next ) {
		next = _next;
	}

	public void setAnterior( DNode<E> _anterior){
		before= _anterior;
	}
	public DNode<E> getAnterior(){
		return before;
	}

    public E element() {
        return elemento;
    }
    
}
