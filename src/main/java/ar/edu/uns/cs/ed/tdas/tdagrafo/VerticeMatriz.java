package ar.edu.uns.cs.ed.tdas.tdagrafo;

import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.tdalista.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;

public class VerticeMatriz<V,E> implements Vertex<V> {
    protected V element;
	protected Position<Vertex<V>> posicion;
	protected PositionList<Edge<E>> listaAdyacencia;
    protected int indiceMatriz;

    public VerticeMatriz( V elemento ) {
		element = elemento;
	    listaAdyacencia = new ListaDoblementeEnlazada<Edge<E>>();
        indiceMatriz=-1;
        posicion=null;
	}

    public V element() {
        return element;
    }

    public void setelement(V e){
        element= e;
    }

    public int getIndiceMatriz(){
        return indiceMatriz;
    }

    public void setIndiceMatriz(int i){
        indiceMatriz=i;
    }

    public void setPositionInLista(Position<Vertex<V>> last){
		posicion = last;
	}

    public Position<Vertex<V>> getPositionInLista(){
		return posicion;
	}

    public void agregarAdyacente(ArcoMatriz<E, V> arco) {
		listaAdyacencia.addLast(arco);
	}

    public PositionList<Edge<E>> getArcosAdyacentes(){
        return listaAdyacencia;
    }
}
