package ar.edu.uns.cs.ed.tdas.tdagrafo;

import java.util.Iterator;

import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidEdgeException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidPositionException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidVertexException;
import ar.edu.uns.cs.ed.tdas.tdalista.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;

public class GrafoNDMatrizAdyacente<V,E> implements Graph<V,E>{
    protected PositionList<Vertex<V>> listaDeVertices;
    protected PositionList<Edge<E>> listaDeArcos;
    protected ArcoMatriz<E,V>[][] matrizArcos;
    protected int sig;

    public GrafoNDMatrizAdyacente(){
        listaDeArcos= new ListaDoblementeEnlazada<>();
        listaDeVertices= new ListaDoblementeEnlazada<>();
        matrizArcos= new ArcoMatriz[100][100];
        sig=0;
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
        ArcoMatriz<E,V> a= checkEdge(e);
        VerticeMatriz<V,E> result=null;
        if(a.getAdyacente1()==v1 || a.getAdyacente2()==v1){
            if(a.getAdyacente1()== v1){
                result= a.getAdyacente2();
            }
            if(a.getAdyacente2()== v1){
                result = a.getAdyacente1();
            } 
        }
        else{
            throw new InvalidEdgeException("se intento hacer opposite con un arco invalido");
        }

        return result;

    }

    public Vertex<V>[] endvertices(Edge<E> e) {
        ArcoMatriz<E,V> a= checkEdge(e);
        Vertex<V>[] arreglode2= (Vertex<V>[])new Vertex[2];
        arreglode2[0]= a.getAdyacente1();
        arreglode2[1]= a.getAdyacente2();

        return arreglode2;
    }
    
    public boolean areAdjacent(Vertex<V> v, Vertex<V> w) {
        VerticeMatriz<V,E> v1= checkVertex(v);
        VerticeMatriz<V,E> v2= checkVertex(w);
        boolean result = false;
        if(matrizArcos[v1.getIndiceMatriz()][v2.getIndiceMatriz()]!= null){
            result=true;
        }
        return result;
    }

    public V replace(Vertex<V> v, V x) {
        VerticeMatriz<V,E> v1= checkVertex(v);
        V rotuloviejo= v1.element();
        v1.setelement(x);
        return rotuloviejo;
    }
    
    public E replace(Edge<E> e, E x) {
        ArcoMatriz<E,V> ar= checkEdge(e);
        E rta= ar.element();
        ar.setElem(x);
        return rta;
    }

    public Vertex<V> insertVertex(V x) {
        VerticeMatriz<V,E> nuevo= new VerticeMatriz<>(x);
        if(listaDeVertices.isEmpty()){ 
            nuevo.setIndiceMatriz(0);
        }
        else{
            VerticeMatriz<V,E> ulti= (VerticeMatriz<V,E>) listaDeVertices.last().element();
            if(ulti.getIndiceMatriz()==99){
                int num = matrizArcos.length;
                ArcoMatriz<E,V>[][] matriz = new ArcoMatriz[num*2][num*2];
                for(int i=0; i< matrizArcos.length;i++){
                    for(int f=0; f< matrizArcos[i].length; f++){
                        matriz[i][f]=matrizArcos[i][f];
                    }
                }
                matrizArcos = matriz;
            }
            nuevo.setIndiceMatriz(ulti.getIndiceMatriz()+1);

        }
        listaDeVertices.addLast(nuevo);
        nuevo.setPositionInLista(listaDeVertices.last());
        return nuevo;
        
    }

    public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E e) {
        VerticeMatriz<V,E> v1= checkVertex(v);
        VerticeMatriz<V,E> v2= checkVertex(w);
        ArcoMatriz<E,V> ar = new ArcoMatriz<>(e, v1, v2);
        matrizArcos[v1.getIndiceMatriz()][v2.getIndiceMatriz()]= ar;
        matrizArcos[v2.getIndiceMatriz()][v1.getIndiceMatriz()]= ar;
        listaDeArcos.addLast(ar);
        v1.getArcosAdyacentes().addLast(ar);
        v2.getArcosAdyacentes().addLast(ar);
        ar.setPositionInLista(listaDeArcos.last());
        return ar;

    }
    
    public V removeVertex(Vertex<V> v) {
        VerticeMatriz<V,E> v1= checkVertex(v);
        ArcoMatriz<E,V> arcocomun= null;
        for(int i=0; i< matrizArcos[v1.getIndiceMatriz()].length; i++){
            arcocomun= matrizArcos[v1.getIndiceMatriz()][i];
            if(arcocomun!=null){
                //remover lista arcos
                listaDeArcos.remove(arcocomun.getPosisionLista());

                //remover arco del vertice opuesto
                //busco vertex op
                VerticeMatriz<V,E> vOp;
                if(arcocomun.getAdyacente1()!= v1){
                    vOp= arcocomun.getAdyacente1();
                }
                else{
                    vOp= arcocomun.getAdyacente2();
                }
                // remuevo arco de vertex op
                Iterator<Position<Edge<E>>> it= vOp.getArcosAdyacentes().positions().iterator();
                while(it.hasNext()){
                    Position<Edge<E>> posa=it.next();
                    if(posa.element()==arcocomun){
                        vOp.getArcosAdyacentes().remove(posa);
                    }
                }
                //quito el arco de mi matriz de arcos
                matrizArcos[v1.getIndiceMatriz()][i]=null;
                matrizArcos[i][v1.getIndiceMatriz()]=null;
            }
        }
        listaDeVertices.remove(v1.getPositionInLista());
        return v1.element();
    }
    

    public E removeEdge(Edge<E> e) {
        //checkeo
        ArcoMatriz<E,V> ar= checkEdge(e);
        //"elimino de matriz"
        matrizArcos[ar.getAdyacente1().getIndiceMatriz()][ar.getAdyacente2().getIndiceMatriz()]= null;
        matrizArcos[ar.getAdyacente2().getIndiceMatriz()][ar.getAdyacente1().getIndiceMatriz()]= null;
        //saco los iteradores para eliminar de la lista de arcos de los vertices
        Iterator<Position<Edge<E>>> itad1 = ar.getAdyacente1().getArcosAdyacentes().positions().iterator();
        boolean noencontre1= true;
        Iterator<Position<Edge<E>>> itad2 = ar.getAdyacente2().getArcosAdyacentes().positions().iterator();
        boolean noencontre2= true;
        //elimino de la lista de vertices
        while(itad1.hasNext() && noencontre1){
            Position<Edge<E>> pos1 =itad1.next();
            if(ar==pos1.element()){
                ar.getAdyacente1().getArcosAdyacentes().remove(pos1);
                noencontre1=false;
            }
        }
        while(itad2.hasNext() && noencontre2){
            Position<Edge<E>> pos2 =itad2.next();
            if(ar==pos2.element()){
                ar.getAdyacente2().getArcosAdyacentes().remove(pos2);
                noencontre2= false;
            }
        }
        //elimino lista general
        listaDeArcos.remove(ar.getPosisionLista());
        return ar.element();
        
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

    private ArcoMatriz<E, V> checkEdge(Edge<E> e) {
		ArcoMatriz<E, V> resultado = null;
        if(e==null){
            throw new InvalidEdgeException(" e es nulo");
        }
		if( listaDeArcos.size() == 0) { throw new InvalidPositionException(null); }
		try {
			resultado = (ArcoMatriz<E,V>) e;
		} catch( ClassCastException h ) {
			throw new InvalidPositionException(null);
		}	
		return resultado;
	}

    
}
