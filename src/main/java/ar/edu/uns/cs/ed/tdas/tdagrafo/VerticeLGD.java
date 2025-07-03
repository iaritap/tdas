package ar.edu.uns.cs.ed.tdas.tdagrafo;

import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.tdalista.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;

public class VerticeLGD<V,E> implements Vertex<V> {
    protected V element;
	protected Position<Vertex<V>> posicion;
	protected PositionList<Edge<E>> listaIncidentes;
    protected PositionList<Edge<E>> listaEmergentes;

    public VerticeLGD( V elemento ) {
		element = elemento;
	    listaI = new ListaDoblementeEnlazada<Edge<E>>();
	}

    public V element() {
        return element;
    }

    public void setRotulo(V e){
        element= e;
    }

    public void setPositionInLista(Position<Vertex<V>> last){
		posicion = last;
	}

    public Position<Vertex<V>> getPositionInLista(){
		return posicion;
	}

    public void agregarAdyacente(Arco<E, V> arco) {
		listaAdyacencia.addLast(arco);
	}

    public PositionList<Edge<E>> getArcosAdyacentes(){
        return listaAdyacencia;
    }
}
