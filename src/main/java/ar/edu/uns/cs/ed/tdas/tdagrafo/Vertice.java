package ar.edu.uns.cs.ed.tdas.tdagrafo;

import java.util.Iterator;

import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.tdalista.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;

public class Vertice<V> implements Vertex<V> {
    protected V elem;
    protected PositionList<Edge<V>> listaArcosIncidentes;
    public Vertice(V e){
        listaArcosIncidentes = new ListaDoblementeEnlazada<>();
        elem=e;
    }

    public V element() {
        return elem;
    }

    public void setElem(V e){
        elem= e;
    }

    public void setArcoincidente(Edge<V> a){
        if(a!= null){
            listaArcosIncidentes.addLast(a);
        }
    }

    public PositionList<Edge<V>> getArcosIncidentes(){
        return listaArcosIncidentes;
    } 

}
