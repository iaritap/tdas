package ar.edu.uns.cs.ed.tdas.tdagrafo;

import ar.edu.uns.cs.ed.tdas.Position;

public class ArcoDLista<E,V> implements Edge<E>{
    protected E element;
	protected Position<Edge<E>> posicion;
	protected VerticeDlista<V, E> origen;
	protected VerticeDlista<V, E> destino;

    public ArcoDLista(E e, VerticeDlista<V, E> v1, VerticeDlista<V, E> v2) {
		element = e;
		origen = v1;
		destino = v2;
	}

    public void setElem(E p){
        element=p;
    }

    public E element() {
        return element;
    } 

    public void setPositionInLista(Position<Edge<E>> last) {
		posicion = last;
		
	}

    public Position<Edge<E>> getPosisionLista(){
        return posicion;
    }

    public void setOrigen(VerticeDlista<V, E> v){
        origen=v;
    }

    public void setDestino(VerticeDlista<V, E> v){
        destino=v;
    }

    public VerticeDlista<V,E> getOrigen(){
        return origen;
    }

    public VerticeDlista<V,E> getDestino(){
        return destino;
    }

}
