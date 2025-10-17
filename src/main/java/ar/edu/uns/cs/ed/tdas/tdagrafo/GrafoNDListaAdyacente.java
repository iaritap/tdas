package ar.edu.uns.cs.ed.tdas.tdagrafo;

import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidEdgeException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidPositionException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidVertexException;
import ar.edu.uns.cs.ed.tdas.tdalista.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;

public class GrafoNDListaAdyacente<V,E> implements Graph<V,E> {

    protected PositionList<Vertex<V>> listaDeVertices;
    protected PositionList<Edge<E>> listaDeArcos;

    public GrafoNDListaAdyacente(){
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
        return n.getArcosAdyacentes();
    }

    public Vertex<V> opposite(Vertex<V> v, Edge<E> e) {
        Vertice<V,E> n = checkVertex(v);
        Arco<E,V> a= checkEdge(e);
        Vertex<V> result= null;
        if(a.getAdyacente1()!= n && a.getAdyacente2()!= n){
            throw new InvalidEdgeException("se intento hacer opposite con un arco invalido");
        }
        if(a.getAdyacente1()== n){
            result= a.getAdyacente2();
        }
        if(a.getAdyacente2()== n){
            result = a.getAdyacente1();
        }
        return result;

    }

    public Vertex<V>[] endvertices(Edge<E> e) {
        Arco<E,V> a= checkEdge(e);
        Vertex<V>[] arreglode2= (Vertex<V>[])new Vertex[2];
        arreglode2[0]= a.getAdyacente1();
        arreglode2[1]= a.getAdyacente2();

        return arreglode2;
    }

    public boolean areAdjacent(Vertex<V> v, Vertex<V> w) {
        Vertice<V,E> v1= checkVertex(v);
        Vertice<V,E> v2= checkVertex(w);
        boolean result= false;
        for(Edge<E> e: v1.getArcosAdyacentes()){
            for(Edge<E> e2: v2.getArcosAdyacentes()){
                if(e==e2){
                    result=true;
                }
            }
        }
        return result;
    }

    public V replace(Vertex<V> v, V x) {
        Vertice<V,E> v1= checkVertex(v);
        V rotuloviejo= v1.element();
        v1.setRotulo(x);
        return rotuloviejo;
    }

    public E replace(Edge<E> e, E x) {
        Arco<E,V> a= checkEdge(e);
        E rotuloviejo= a.element();
        a.setElem(x);
        return rotuloviejo;
    }

    public Vertex<V> insertVertex(V x) {
        Vertice<V,E> nuevo= new Vertice<V,E>(x);
        listaDeVertices.addLast(nuevo);
        nuevo.setPositionInLista(listaDeVertices.last());
        return nuevo;
    }

    public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E e) {
        Vertice<V,E> v1= checkVertex(v);
        Vertice<V,E> v2= checkVertex(w);
        Arco<E,V> anuevo= new Arco<>(e, v1, v2);
        listaDeArcos.addLast(anuevo);
        anuevo.setPositionInLista(listaDeArcos.last());
        v1.agregarAdyacente(anuevo);
        v2.agregarAdyacente(anuevo);

        return anuevo;
    }

    public V removeVertex(Vertex<V> v) {
        Vertice<V,E> v1= checkVertex(v);
        V e = null;
        if(!listaDeVertices.isEmpty()){
            e= v1.element();
            listaDeVertices.remove(v1.getPositionInLista());
            for(Edge<E> edges: v1.getArcosAdyacentes()){
                Arco<E,V> aDev1 = (Arco<E,V>) edges;
                if(!listaDeArcos.isEmpty()){
                    listaDeArcos.remove(aDev1.getPosisionLista());
                }
            }
        }
        return e; 
    }

    public E removeEdge(Edge<E> e) {
        Arco<E,V> a= checkEdge(e);
        E elem= a.element();
        Vertice<V,E> v1= a.getAdyacente1();
        Vertice<V,E> v2= a.getAdyacente2();

        listaDeArcos.remove(a.getPosisionLista());

        for(Position<Edge<E>> posEdge : v1.getArcosAdyacentes().positions()){
            if(posEdge.element()==a){
                v1.getArcosAdyacentes().remove(posEdge);
            }
        }

        for(Position<Edge<E>> posEdge : v2.getArcosAdyacentes().positions()){
            if(posEdge.element()==a){
                v2.getArcosAdyacentes().remove(posEdge);
            }
        }
        return elem;

    }
    
    //metodos privados
    private Vertice<V, E> checkVertex(Vertex<V> v) {
		Vertice<V, E> resultado = null;
        if(v==null){
            throw new InvalidVertexException(" V es nulo");
        }
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
