package ar.edu.uns.cs.ed.tdas.tdagrafo;

import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.tdalista.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;

public class VerticeDlista<V,E> implements Vertex<V> {
    protected V element;
	protected Position<Vertex<V>> posicion;
	protected PositionList<ArcoDLista<E,V>> listaArcos;

    public VerticeDlista(V e){
        element= e;
        listaArcos= new ListaDoblementeEnlazada<>();
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

    public PositionList<ArcoDLista<E,V>> GetListaArcos(){
        return listaArcos;
    }

    public void setListaArcos(Edge<E> e){
        ArcoDLista<E,V> a= ( ArcoDLista<E,V>) e;
        ite
        listaArcos.addLast(a);
    }
    
}
