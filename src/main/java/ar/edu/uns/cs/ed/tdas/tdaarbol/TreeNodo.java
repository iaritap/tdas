package ar.edu.uns.cs.ed.tdas.tdaarbol;

import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.tdalista.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;

public class TreeNodo<E> implements Position<E> {
    protected E elem;
    protected TreeNodo<E> padre;
    protected PositionList<TreeNodo<E>> hijos;

    public TreeNodo(E e, TreeNodo<E> p){
        elem= e;
        padre= p;
        hijos=new ListaDoblementeEnlazada<TreeNodo<E>>();
    }

    public TreeNodo<E> padre(){
        return padre;
    }

    public PositionList<TreeNodo<E>> hijos(){
        return hijos;
    }

    public void setPadre(TreeNodo<E> p){
        padre= p;
    }

    public void setElement( E e ){
        elem= e;
    }
    
    public E element() {
        return elem;
    }
    
}
