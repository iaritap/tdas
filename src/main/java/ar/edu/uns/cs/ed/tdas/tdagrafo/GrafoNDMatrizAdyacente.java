package ar.edu.uns.cs.ed.tdas.tdagrafo;

import ar.edu.uns.cs.ed.tdas.excepciones.InvalidEdgeException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidPositionException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidVertexException;
import ar.edu.uns.cs.ed.tdas.tdalista.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;

public class GrafoNDMatrizAdyacente<V,E> implements Graph<V,E>{
    protected PositionList<Vertex<V>> listaDeVertices;
    protected PositionList<Edge<E>> listaDeArcos;
    protected Arco<V,E>[][] matrizArcos;

    public GrafoNDMatrizAdyacente(){
        listaDeArcos= new ListaDoblementeEnlazada<>();
        listaDeVertices= new ListaDoblementeEnlazada<>();

    }

    public Iterable<Vertex<V>> vertices() {
        return listaDeVertices;
    }
    
    public Iterable<Edge<E>> edges() {
        return listaDeArcos;
    }

    public Iterable<Edge<E>> incidentEdges(Vertex<V> v) {
        VerticeMatriz<V,E> v1 = checkVertex(v);
        return v1.getArcosAdyacentes();
    }

    public Vertex<V> opposite(Vertex<V> v, Edge<E> e) {
        VerticeMatriz<V,E> v1= checkVertex(v);
        Arco<E,V> a= checkEdge(e);

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
    private VerticeMatriz<V, E> checkVertex(Vertex<V> v) {
		VerticeMatriz<V, E> resultado = null;
        if(v==null){
            throw new InvalidVertexException(" V es nulo");
        }
		if( listaDeVertices.size() == 0) { throw new InvalidPositionException(null); }
		try {
			resultado = (VerticeMatriz<V, E>)v;
		} catch( ClassCastException e ) {
			throw new InvalidPositionException(null);
		}	
		return resultado;
	}
    
    private Arco<E, V> checkEdge(Edge<E> e) {
		Arco<E, V> resultado = null;
        if(e==null){
            throw new InvalidEdgeException(" e es nulo");
        }
		if( listaDeArcos.size() == 0) { throw new InvalidPositionException(null); }
		try {
			resultado = (Arco<E,V>) e;
		} catch( ClassCastException h ) {
			throw new InvalidPositionException(null);
		}	
		return resultado;
	}
}
