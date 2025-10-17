package ar.edu.uns.cs.ed.tdas.tdagrafo;

import ar.edu.uns.cs.ed.tdas.Position;

public class ArcoMatriz<E,V> implements Edge<E> {
    protected E element;
	protected Position<Edge<E>> posicion;
	protected VerticeMatriz<V, E> adyacente1;
	protected VerticeMatriz<V, E> adyacente2;

    public ArcoMatriz(E e, VerticeMatriz<V, E> v1, VerticeMatriz<V, E> v2) {
		element = e;
		adyacente1 = v1;
		adyacente2 = v2;
		v1.agregarAdyacente(this);
		v2.agregarAdyacente(this);
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

    public void setAdyacente1(VerticeMatriz<V, E> v){
        adyacente1=v;
    }

    public void setAdyacente2(VerticeMatriz<V, E> v){
        adyacente2=v;
    }

    public VerticeMatriz<V,E> getAdyacente1(){
        return adyacente1;
    }

    public VerticeMatriz<V,E> getAdyacente2(){
        return adyacente2;
    }
}
