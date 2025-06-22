package ar.edu.uns.cs.ed.tdas.tdagrafo;

import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.tdalista.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;

public class Arco<E,V> implements Edge<E> {
    protected E element;
	protected Position<Arco<E,V>> posicion;
	protected Vertice<V, E> adyacente1;
	protected Vertice<V, E> adyacente2;

    public Arco(E e, Vertice<V, E> v1, Vertice<V, E> v2) {
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

    public void setPositionInLista(Position<Arco<E, V>> last) {
		posicion = last;
		
	}

    public Position<Arco<E, V>> gePosisionLista(){
        return posicion;
    }

    public void setAdyacente1(Vertice<V, E> v){
        adyacente1=v;
    }

    public void setAdyacente2(Vertice<V, E> v){
        adyacente2=v;
    }

    public Vertice<V,E> getAdyacente1(){
        return adyacente1;
    }

    public Vertice<V,E> getAdyacente2(){
        return adyacente2;
    }
}
