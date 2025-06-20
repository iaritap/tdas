package ar.edu.uns.cs.ed.tdas.tdaarbol;

import java.util.Iterator;

import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.excepciones.BoundaryViolationException;
import ar.edu.uns.cs.ed.tdas.excepciones.EmptyTreeException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidOperationException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidPositionException;
import ar.edu.uns.cs.ed.tdas.tdalista.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;

public class TreeTDA<E> implements Tree<E>{
    protected int size;
    protected TreeNodo<E> root;

    public TreeTDA(){
        size= 0;
        root=null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size ==0;
    }

    public Iterator<E> iterator() {
        PositionList<E> resultado = new ListaDoblementeEnlazada<E>();
		preordenElementos(root, resultado);
		return resultado.iterator();
    }

    public Iterable<Position<E>> positions() {
        PositionList<Position<E>> resultado = new ListaDoblementeEnlazada<Position<E>>();
		preordenPositions(root, resultado);
		return resultado;
    }

    public E replace(Position<E> v, E e) {
        TreeNodo<E> nodo = checkPosition(v);
		E resultado = nodo.element();
		nodo.setElement(e);
		return resultado;
    }

    public Position<E> root() {
        if(this.isEmpty()) {throw new EmptyTreeException("El árbol está vacío");}
        return root;
    }

    public Position<E> parent(Position<E> v) {
        TreeNodo<E> nodo = checkPosition(v);
        if(v== root){
            throw new BoundaryViolationException("re intento hacer padre de la raiz");
        }
		return nodo.padre();
    }

    public Iterable<Position<E>> children(Position<E> v) {
        TreeNodo<E> nodo = checkPosition(v);
		PositionList<Position<E>> resultado = new ListaDoblementeEnlazada<Position<E>>();
		for(TreeNodo<E> n : nodo.hijos()) {
			resultado.addLast(n);
		}
 		return resultado;
    }

    public boolean isInternal(Position<E> v) {
        TreeNodo<E> nodo = checkPosition(v);
		return !nodo.hijos().isEmpty();
    }

    public boolean isExternal(Position<E> v) {
        TreeNodo<E> nodo = checkPosition(v);
		return nodo.hijos().isEmpty();
    }

    public boolean isRoot(Position<E> v) {
        TreeNodo<E> nodo = checkPosition(v);
		return root == nodo;
    }

    public void createRoot(E e) {
        if(this.root != null) {throw new InvalidOperationException("El árbol ya tiene una raíz");}
		else {
			this.root = new TreeNodo<E>(e, null);
			this.size++;
		}
    }

    public Position<E> addFirstChild(Position<E> p, E e) {
        TreeNodo<E> nodo = checkPosition(p);
		TreeNodo<E> hijo = new TreeNodo<E>(e, nodo);
		nodo.hijos().addFirst(hijo);
        size ++;
		return hijo;
    }

    public Position<E> addLastChild(Position<E> p, E e) {
        TreeNodo<E> nodo = checkPosition(p);
		TreeNodo<E> hijo = new TreeNodo<E>(e, nodo);
		nodo.hijos().addLast(hijo);
        size++;
		return hijo;
    }

    public Position<E> addBefore(Position<E> p, Position<E> rb, E e) {
        TreeNodo<E> padre = checkPosition(p);
		TreeNodo<E> hder = checkPosition(rb);
		if(hder.padre()!= padre ) {throw new InvalidPositionException("El padre no es el verdadero padre");}
		TreeNodo<E> nuevo = new TreeNodo<E>(e, padre);
		Iterator<Position<TreeNodo<E>>> ite = padre.hijos().positions().iterator();
		Position<TreeNodo<E>> posBuscada = null;
		while(ite.hasNext()&&posBuscada==null) {
			Position<TreeNodo<E>> pos = ite.next();
			if(pos.element() == hder ) {posBuscada = pos;}
		}
		padre.hijos().addBefore(posBuscada, nuevo);
		size++;
		return nuevo;
    }

    public Position<E> addAfter(Position<E> p, Position<E> lb, E e) {
        TreeNodo<E> padre = checkPosition(p);
		TreeNodo<E> hder = checkPosition(lb);
		if(hder.padre()!= padre ) {throw new InvalidPositionException("El padre no es el verdadero padre");}
		TreeNodo<E> nuevo = new TreeNodo<E>(e, padre);
		Iterator<Position<TreeNodo<E>>> ite = padre.hijos().positions().iterator();
		Position<TreeNodo<E>> posBuscada = null;
		while(ite.hasNext()&&posBuscada==null) {
			Position<TreeNodo<E>> pos = ite.next();
			if(pos.element() == hder ) {posBuscada = pos;}
		}
		padre.hijos().addAfter(posBuscada, nuevo);
		size++;
		return nuevo;
    }

    public void removeExternalNode(Position<E> p) {
        TreeNodo<E> nodo = checkPosition(p);		
		if(this.isInternal(nodo)) {throw new InvalidPositionException("El nodo no es externo");}
		if(this.root == nodo) {
			//el nodo externo es la raiz
			this.root = null;
			size = 0;
		} else {
			Iterator<Position<TreeNodo<E>>> ite = nodo.padre().hijos().positions().iterator();
			Position<TreeNodo<E>> posBuscada = null;
			while(ite.hasNext()&&posBuscada==null) {
				Position<TreeNodo<E>> pos = ite.next();
				if(pos.element() == nodo ) {posBuscada = pos;}
			}
			nodo.padre().hijos().remove(posBuscada);
			size--;
		}
    }

    public void removeInternalNode(Position<E> p) {
        TreeNodo<E> nodo = checkPosition(p);
		if( this.isExternal(nodo)) {throw new InvalidPositionException("No es un nodo interno");}
		if(this.root == nodo ) {
            if(root.hijos().size()==0){
                throw new BoundaryViolationException("re intento hacer removeinternal de la raiz sin hijos");
            }//ver
            else{
                if(this.root.hijos().size()>1){
                    throw new InvalidPositionException("No puedo eliminar la raiz porque tiene muchos hijos");
                }
                else{
                    root = this.root.hijos().first().element();
                    root.setPadre(null);
                    size--;
                }
            }
		} 
        else {
			Iterator<Position<TreeNodo<E>>> ite = nodo.padre().hijos().positions().iterator();
			Position<TreeNodo<E>> posBuscada = null;
			while(ite.hasNext()&&posBuscada==null) {
				Position<TreeNodo<E>> pos = ite.next();
				if(pos.element() == nodo ) {posBuscada = pos;}
			}
			//posBuscada es la posicion en la lista del padre, del nodo que quiero eliminar
			for(TreeNodo<E> hijo : nodo.hijos()) {
				nodo.padre().hijos().addBefore(posBuscada, hijo);
				hijo.setPadre(nodo.padre());
			}
			nodo.padre().hijos().remove(posBuscada);
			nodo.setPadre(null);
			size--;
		}
    }

    public void removeNode(Position<E> p) {
        TreeNodo<E> nodo = checkPosition(p);
		if(this.isInternal(nodo)) {this.removeInternalNode(p);}
		else {this.removeExternalNode(p);}
    }


    
    //metodos privados
    private void preordenElementos( TreeNodo<E> nodo, PositionList<E> lista ) {
        if(nodo!=null){
            lista.addLast(nodo.element()); //Visitar nodo
            for(TreeNodo<E> n : nodo.hijos()) {
                preordenElementos(n, lista);
            }
        }
	}

    private void posordenElementos( TreeNodo<E> nodo, PositionList<E> lista ) {
        if(nodo!=null){
            for(TreeNodo<E> n : nodo.hijos()) {
                posordenElementos(n, lista);
            }
            lista.addLast(nodo.element()); //Visitar nodo
        }
	}

    protected void preordenPositions( TreeNodo<E> nodo, PositionList<Position<E>> lista ) {
		if(nodo!=null){
            lista.addLast(nodo);
            for(TreeNodo<E> n : nodo.hijos()) {
                preordenPositions(n, lista);
            }
        }
	}
    private TreeNodo<E> checkPosition(Position<E> v) {
		TreeNodo<E> resultado = null;
		if( v == null) {throw new InvalidPositionException("Posicion es nula");}
		if( this.isEmpty()) {throw new InvalidPositionException("El árbol está vacío");}
		try {
			resultado = (TreeNodo<E>)v;
		} catch( ClassCastException e ) {
			throw new InvalidPositionException("La posición no es del tipo correcto");
		}
		return resultado;
	}
    
}
