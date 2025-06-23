package ar.edu.uns.cs.ed.tdas.tdagrafo;

import ar.edu.uns.cs.ed.tdas.tdalista.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;

public class GrafoDListaAdyacente<V,E> implements GraphD<V,E> {
    protected PositionList<Vertex<V>> listaDeVertices;
    protected PositionList<Edge<E>> listaDeArcos;

    public GrafoDListaAdyacente(){
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
        
    }

    @Override
    public Iterable<Edge<E>> succesorEdges(Vertex<V> v) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'succesorEdges'");
    }

    @Override
    public Vertex<V> opposite(Vertex<V> v, Edge<E> e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'opposite'");
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
    
}
