package ar.edu.uns.cs.ed.tdas.tdagrafo;

import ar.edu.uns.cs.ed.tdas.excepciones.InvalidPositionException;
import ar.edu.uns.cs.ed.tdas.tdalista.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;

public class GrafoListaAdyacente<V,E> implements Graph<V,E> {

    protected PositionList<Vertex<V>> listaDeVertices;
    protected PositionList<Edge<E>> listaDeArcos;

    public GrafoListaAdyacente(){
        listaDeVertices= new ListaDoblementeEnlazada<>();
        listaDeArcos= new ListaDoblementeEnlazada<>();
    }

    public Iterable<Vertex<V>> vertices() {
        return listaDeVertices;
    }

    public Iterable<Edge<E>> edges() {
        return listaDeArcos;
    }

    public Iterable<Edge<E>> incidentEdges(Vertex<V> v) {
        Vertice<V,E> n= checkVertex(v);
        PositionList<Edge<E>> ListaArcosIncidentesDeV = new ListaDoblementeEnlazada<>();
        for(Edge<E> e: listaDeArcos){
            Arco<E,V> a= (Arco<E,V>) e;
            if(a.getAdyacente1()== n || a.getAdyacente2()==n){
                ListaArcosIncidentesDeV.addLast(e);
            }
        }
        return ListaArcosIncidentesDeV;
    }

    public Vertex<V> opposite(Vertex<V> v, Edge<E> e) {
        Vertice<V,E> n = checkVertex(v);

        return null;

    }

    @Override
    public Vertex<V>[] endvertices(Edge<E> e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'endvertices'");
    }

    @Override
    public boolean areAdjacent(Vertex<V> v, Vertex<V> w) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'areAdjacent'");
    }

    @Override
    public V replace(Vertex<V> v, V x) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'replace'");
    }

    @Override
    public Vertex<V> insertVertex(V x) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertVertex'");
    }

    @Override
    public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertEdge'");
    }

    @Override
    public V removeVertex(Vertex<V> v) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeVertex'");
    }

    @Override
    public E removeEdge(Edge<E> e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeEdge'");
    }
    
    //metodos privados
    private Vertice<V, E> checkVertex(Vertex<V> v) {
		Vertice<V, E> resultado = null;
		if( listaDeVertices.size() == 0) { throw new InvalidPositionException(null); }
		try {
			resultado = (Vertice<V, E>)v;
		} catch( ClassCastException e ) {
			throw new InvalidPositionException(null);
		}	
		return resultado;
	}
    
    private Arco<E, V> checkEdge(Edge<E> e) {
		Arco<E, V> resultado = null;
		if( listaDeArcos.size() == 0) { throw new InvalidPositionException(null); }
		try {
			resultado = (Arco<E,V>) e;
		} catch( ClassCastException h ) {
			throw new InvalidPositionException(null);
		}	
		return resultado;
	}
}
