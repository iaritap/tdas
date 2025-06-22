package ar.edu.uns.cs.ed.tdas.tdagrafo;

import ar.edu.uns.cs.ed.tdas.tdalista.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;

public class Arco<V> implements Edge<V> {
    protected V peso;
    protected PositionList<Vertex<V>> verticesunidos;

    public Arco(V p){
        peso=p;
        verticesunidos= new ListaDoblementeEnlazada<>();
    }

    public void setElem(V p){
        peso=p;
    }

    public V element() {
        return peso;
    } 

    public void setVertices(Vertex<V> v){
        verticesunidos.addLast(v);
    }

    public PositionList<Vertex<V>> getVertices(){
        return verticesunidos;
    }

}
