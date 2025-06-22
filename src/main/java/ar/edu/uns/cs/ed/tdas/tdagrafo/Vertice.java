package ar.edu.uns.cs.ed.tdas.tdagrafo;

import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.tdalista.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;

public class Vertice<V,E> implements Vertex<V> {
    protected V element;
	protected Position<Vertice<V, E>> posicion;
	protected PositionList<Edge<E>> listaAdyacencia;

    public Vertice( V elemento ) {
		element = elemento;
	    listaAdyacencia = new ListaDoblementeEnlazada<Edge<E>>();
	}

    public V element() {
        return element;
    }

    public void setRotulo(V e){
        element= e;
    }

    public void setPositionInLista(Position<Vertice<V, E>> last){
		posicion = last;
	}

    public void agregarAdyacente(Arco<E, V> arco) {
		listaAdyacencia.addLast(arco);
	}
}
