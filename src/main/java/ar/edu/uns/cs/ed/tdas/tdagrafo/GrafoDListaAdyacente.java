package ar.edu.uns.cs.ed.tdas.tdagrafo;

import ar.edu.uns.cs.ed.tdas.excepciones.InvalidEdgeException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidPositionException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidVertexException;
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
        VerticeDlista<V,E> nuev = checkVertex(v);
        PositionList<Edge<E>> listaincidentes= new ListaDoblementeEnlazada<>();
        for(ArcoDLista<E,V> e: nuev.GetListaArcos()){
            if(e.getOrigen()!= nuev && e.getDestino()==nuev){
                listaincidentes.addLast(e);
            }
        }
        return listaincidentes;
    }

    public Iterable<Edge<E>> succesorEdges(Vertex<V> v) {
        VerticeDlista<V,E> v1= checkVertex(v);
        PositionList<Edge<E>> listaincidentes= new ListaDoblementeEnlazada<>();
        for(ArcoDLista<E,V> e: nuev.GetListaArcos()){
            for(ArcoDLista<E,V> f: nuev.GetListaArcos()){
                
            }
        }
        return listaincidentes;
    }

    public Vertex<V> opposite(Vertex<V> v, Edge<E> e) {
        VerticeDlista<V,E> v1= checkVertex(v);
        ArcoDLista<E,V> a= checkEdge(e);
        VerticeDlista<V,E> rta = null;
        if(a.getAdyacente1()==v1 || a.getAdyacente2()==v1){
            if(a.getAdyacente1()==v1){
                rta= a.getAdyacente2();
            }
            else{
                rta= a.getAdyacente1();
            }
        }

        return rta;

    }

    public Vertex<V>[] endvertices(Edge<E> e) {
        ArcoDLista<E,V> a= checkEdge(e);
        Vertex<V>[] arreglode2= new Vertex[2];
        arreglode2[0]= a.getAdyacente1();
        arreglode2[1]= a.getAdyacente2();
        return arreglode2;
    }

    public boolean areAdjacent(Vertex<V> v, Vertex<V> w) {
        VerticeDlista<V,E> v1= checkVertex(v);
        VerticeDlista<V,E> v2= checkVertex(w);
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
        VerticeDlista<V,E> v1= checkVertex(v);
        V rotuloviejo= v1.element();
        v1.setRotulo(x);
        return rotuloviejo;
    }

    public E replace(Edge<E> e, E x){
        ArcoDLista<E,V> e1 = checkEdge(e);
        E rotuloviejo = e1.element();
        e1.setElem(x);
        return rotuloviejo;
    }

    public Vertex<V> insertVertex(V x) {
        VerticeDlista<V,E> nuevo= new VerticeDlista<>(x);
        listaDeVertices.addLast(nuevo);
        nuevo.setPositionInLista(listaDeVertices.last());
        return nuevo;
    }

    public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E e) {
        VerticeDlista<V,E> v1= checkVertex(v);
        VerticeDlista<V,E> v2= checkVertex(w);
        ArcoDLista<E,V> anuevo= new ArcoDLista<>(e, v1, v2);
        listaDeArcos.addLast(anuevo);
        anuevo.setPositionInLista(listaDeArcos.last());
        anuevo.setAdyacente1(v1);
        anuevo.setAdyacente2(v2);
        return anuevo;
    }

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
    private VerticeDlista<V, E> checkVertex(Vertex<V> v) {
		VerticeDlista<V, E> resultado = null;
        if(v==null){
            throw new InvalidVertexException(" V es nulo");
        }
		if( listaDeVertices.size() == 0) { throw new InvalidPositionException(null); }
		try {
			resultado = (VerticeDlista<V, E>)v;
		} catch( ClassCastException e ) {
			throw new InvalidPositionException(null);
		}	
		return resultado;
	}
    
    private ArcoDLista<E, V> checkEdge(Edge<E> e) {
		ArcoDLista<E, V> resultado = null;
        if(e==null){
            throw new InvalidEdgeException(" e es nulo");
        }
		if( listaDeArcos.size() == 0) { throw new InvalidPositionException(null); }
		try {
			resultado = (ArcoDLista<E,V>) e;
		} catch( ClassCastException h ) {
			throw new InvalidPositionException(null);
		}	
		return resultado;
	}
    
}
