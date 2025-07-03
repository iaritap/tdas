package ar.edu.uns.cs.ed.tdas.tdaarbolbinario;

import java.util.Iterator;

import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.excepciones.BoundaryViolationException;
import ar.edu.uns.cs.ed.tdas.excepciones.EmptyTreeException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidOperationException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidPositionException;
import ar.edu.uns.cs.ed.tdas.tdaarbol.Tree;
import ar.edu.uns.cs.ed.tdas.tdaarbol.TreeNodo;
import ar.edu.uns.cs.ed.tdas.tdalista.ListaDoblementeEnlazada;
import ar.edu.uns.cs.ed.tdas.tdalista.PositionList;

public class BTTDA<E> implements BinaryTree<E> {

    protected int size;
    BTNodo<E> root;

    public BTTDA(){
        size=0;
        root=null;
    }

    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public Iterator<E> iterator() {
        PositionList<E> resultado = new ListaDoblementeEnlazada<E>();
		preordenElementos(root, resultado);
		return resultado.iterator();
        
    }

    @Override
    public Iterable<Position<E>> positions() {
        PositionList<Position<E>> resultado = new ListaDoblementeEnlazada<Position<E>>();
		preordenPositions(root, resultado);
		return resultado;
    }

    public E replace(Position<E> v, E e) {
        BTNodo<E> nodo = checkPosition(v);
		E resultado = nodo.element();
		nodo.setElem(e);
		return resultado;
    }

    public Position<E> root() {
        if(isEmpty()){
            throw new EmptyTreeException("se intento hacer root con arbol vacio");
        }
        else{
            return root;
        }
    }

    @Override
    public Position<E> parent(Position<E> v) {
        BTNodo<E> p = checkPosition(v);
        if(isRoot(p)){
            throw new BoundaryViolationException("se intento hacer padre a una raiz");
        }
        else{
            return p.getPadre();
        }

    }

    public Iterable<Position<E>> children(Position<E> v) {
        BTNodo<E> nodo = checkPosition(v);
		PositionList<Position<E>> resultado = new ListaDoblementeEnlazada<Position<E>>();
        if(nodo.getHijoIzq()!= null)
            resultado.addLast(nodo.getHijoIzq());
        if(nodo.getHijoDere()!= null)
		    resultado.addLast(nodo.getHijoDere());
 		return resultado;
    }

    public boolean isInternal(Position<E> v) {
        BTNodo<E> p= checkPosition(v);
        boolean resultado= true;
        if(p.getHijoDere()== null && p.getHijoIzq()==null){
            resultado= false;
        }
        return resultado;
    }

    public boolean isExternal(Position<E> v) {
        BTNodo<E> p= checkPosition(v);
        boolean resultado= false;
        if(p.getHijoDere()== null && p.getHijoIzq()==null){
            resultado= true;
        }
        return resultado;
    }

    public boolean isRoot(Position<E> v) {
        BTNodo<E> raiz = checkPosition(v);
        return root== raiz;
    }

    public void createRoot(E e) {
        if(root!=null){
            throw new InvalidOperationException("el arbol ya tiene raiz");
        }
        else{
            size++;
            root = new BTNodo<>(e, null, null, null);//ver
        }
    }

    public Position<E> addFirstChild(Position<E> p, E e) {
        BTNodo<E> n= checkPosition(p);
        if(isEmpty()){
            throw new InvalidPositionException("se intento addafter con arbol vacio.");
        }
        BTNodo<E> hijo= null;
        if(n.getHijoDere()== null && n.getHijoIzq()==null){
            hijo = new BTNodo<E>(e, null, null, null);
            n.setHijoIzq(hijo);
            size++;
        }
        return hijo;
        
    }

    @Override
    public Position<E> addLastChild(Position<E> p, E e) {
        BTNodo<E> n= checkPosition(p);
        if(isEmpty()){
            throw new InvalidPositionException("se intento addafter con arbol vacio.");
        }
        BTNodo<E> hijo= null;
        if(n.getHijoDere()== null){
            hijo = new BTNodo<E>(e, null, null, null);
            n.setHijoIzq(hijo);
            size++;
        }
        return hijo;
    }

    public Position<E> addBefore(Position<E> p, Position<E> rb, E e) {
        BTNodo<E> padre = checkPosition(p);
		BTNodo<E> hermano = checkPosition(rb);
        if(isEmpty()){
            throw new InvalidPositionException("se intento addafter con arbol vacio.");
        }
		if(hermano.getPadre()!= padre ) {
            throw new InvalidPositionException("El padre no es el verdadero padre");}
		BTNodo<E> nuevo= null;
		if(padre.getHijoIzq()== null && padre.getHijoDere()==hermano){
            nuevo = new BTNodo<E>(e, null, null, padre);
            padre.setHijoDere(nuevo);
            size++;
        }
		return nuevo;
    }

    public Position<E> addAfter(Position<E> p, Position<E> lb, E e) {
        BTNodo<E> padre = checkPosition(p);
		BTNodo<E> hermano = checkPosition(lb);
        if(isEmpty()){
            throw new InvalidPositionException("se intento addafter con arbol vacio.");
        }
		if(hermano.getPadre()!= padre ) {
            throw new InvalidPositionException("El padre no es el verdadero padre");}
		BTNodo<E> nuevo= null;
		if(padre.getHijoIzq()== hermano && padre.getHijoDere()==null){
            nuevo = new BTNodo<E>(e, null, null, padre);
            padre.setHijoDere(nuevo);
            size++;
        }
		return nuevo;
    }

    public void removeExternalNode(Position<E> p) {
        BTNodo<E> n= checkPosition(p);
        if(isExternal(n)){
            if(n.getPadre().getHijoDere()== n){
                n.getPadre().setHijoDere(null);
            }
            if(n.getPadre().getHijoIzq()==n){
                n.getPadre().setHijoIzq(null);
            }
            size--;
        }
    }

    public void removeInternalNode(Position<E> p) {
        BTNodo<E> n= checkPosition(p);
        if(isEmpty()){
            throw new InvalidPositionException("se intento removeinternal con arbol vacio");
        }
        if(!isInternal(n)){
            throw new InvalidPositionException("se intento removeinternal sin ser nodo interno");
        }
        if(isRoot(n)){
            if(size!=2){
                throw new InvalidPositionException("se intento removeinternal con root sin un unico hijo");
            }
            if(size==2){
                if(n.getHijoDere()!= null){
                    root=n.getHijoDere();
                }
                if(n.getHijoIzq()!= null){
                    root=n.getHijoIzq();
                }
                n.setPadre(null);
                size--;
            }
        }
        else{
            
        }
    }

    public void removeNode(Position<E> p) {
        BTNodo<E> n= checkPosition(p);
        if(isExternal(n)){
            removeExternalNode(n);
        }
        else{
            removeInternalNode(n);
        }
    }

    //propios de binary tree

    public Position<E> left(Position<E> v) {
        BTNodo<E> n= checkPosition(v);
        if(n.getHijoIzq()==null){
            throw new BoundaryViolationException("se intento hacer left de una pos que no tiene hijo izq");
        }
        else{
            BTNodo<E> hijoIzq = n.getHijoIzq();
            return hijoIzq;
        }
    }

    public Position<E> right(Position<E> v) {
        BTNodo<E> n= checkPosition(v);
        if(n.getHijoDere()==null){
            throw new BoundaryViolationException("se intento hacer right de una pos que no tiene hijo dere");
        }
        else{
            BTNodo<E> hijoDere = n.getHijoDere();
            return hijoDere;
        }
    }

    public boolean hasLeft(Position<E> v) {
        BTNodo<E> n= checkPosition(v);
        return n.getHijoIzq()!= null;
    }

    public boolean hasRight(Position<E> v) {
        BTNodo<E> n= checkPosition(v);
        return n.getHijoDere()!= null;
    }

    public Position<E> addLeft(Position<E> v, E r) {
        BTNodo<E> n= checkPosition(v);
        if(isEmpty()){
            throw new InvalidPositionException("se intento hacer addleft con arbol vacio");
        }
        if(n.getHijoIzq()!=null){
            throw new InvalidOperationException("se intento hacer addleft y v ya tenia hijo izq");
        }
        else{
            BTNodo<E> hijonew = new BTNodo<E>(r, null, null, n);
            n.setHijoIzq(hijonew);
            size++;
            return hijonew;
        }

    }

    public Position<E> addRight(Position<E> v, E r) {
        BTNodo<E> n= checkPosition(v);
        if(isEmpty()){
            throw new InvalidPositionException("se intento hacer addright con arbol vacio");
        }
        if(n.getHijoDere()!=null){
            throw new InvalidOperationException("se intento hacer addright y v ya tenia hijo dere");
        }
        else{
            BTNodo<E> hijonew = new BTNodo<E>(r, null, null, n);
            n.setHijoDere(hijonew);
            size++;
            return hijonew;
        }
    } 

    public void attach(Position<E> r, BinaryTree<E> T1, BinaryTree<E> T2) {
        BTNodo<E> n= checkPosition(r);
        if( isEmpty() ){
            throw new InvalidOperationException("se intento hacer attach con arbol vacio");
        }
        if( isInternal(n) ){
            throw new InvalidPositionException("se intento hacer attach con V que no es hoja");
        }
        else{
            if(!T1.isEmpty()){
                BTNodo<E> raizclon= new BTNodo<E>(T1.root().element(), null, null, null);
                BTNodo<E> raizOri=(BTNodo<E>) T1.root();
                if(T1.hasLeft(raizOri)){
                    preordenClonT(raizOri.getHijoIzq(), raizclon, T1, true);
                }
                if(T1.hasRight(raizOri)){
                    preordenClonT(raizOri.getHijoDere(), raizclon, T1, false);
                }
                n.setHijoIzq(raizclon);
                size+= T1.size();
            }
            if(!T2.isEmpty()){
                BTNodo<E> raizclon2= new BTNodo<E>(T2.root().element(), null, null, null);
                BTNodo<E> raizOr=(BTNodo<E>) T2.root();
                if(T1.hasLeft(raizOr)){
                    preordenClonT(raizOr.getHijoIzq(), raizclon2, T2, true);
                }
                if(T1.hasRight(raizOr)){
                    preordenClonT(raizOr.getHijoDere(), raizclon2, T2, false);
                }
                n.setHijoIzq(raizclon2);
                size+= T2.size();
            }
        }
    }

    //metodos privados
    private BTNodo<E> checkPosition(Position<E> v) {
		BTNodo<E> resultado = null;
		if( v == null) {throw new InvalidPositionException("Posicion es nula");}
		if( this.isEmpty()) {throw new InvalidPositionException("El árbol está vacío");}
		try {
			resultado = (BTNodo<E>)v;
		} catch( ClassCastException e ) {
			throw new InvalidPositionException("La posición no es del tipo correcto");
		}
		return resultado;
	}

    protected void preordenPositions( BTNodo<E> nodo, PositionList<Position<E>> lista ) {
		if(nodo!=null){
            lista.addLast(nodo);
            preordenPositions(nodo.getHijoIzq(), lista);
            preordenPositions(nodo.getHijoDere(), lista);
        }
	}

    protected void preordenClonT( BTNodo<E> hijoOriginal, BTNodo<E> padreclon,BinaryTree<E> t, boolean esZurdo ) {
        BTNodo<E> hijoclonado= new BTNodo<E>(hijoOriginal.element(), null, null, padreclon);
        if(esZurdo){
            padreclon.setHijoIzq(hijoclonado);
        }
        else{
            padreclon.setHijoDere(hijoclonado);
        }

        if(t.hasLeft(hijoOriginal)){
            preordenClonT(hijoOriginal.getHijoIzq(), hijoclonado, t, true);
        }
        if(t.hasRight(hijoOriginal)){
            preordenClonT(hijoOriginal.getHijoDere(), hijoclonado, t, false);
        } 
	}

    private void preordenElementos( BTNodo<E> nodo, PositionList<E> lista ) {
        if(nodo!=null){
            lista.addLast(nodo.element()); //Visitar nodo
            preordenElementos(nodo.getHijoIzq(), lista);
            preordenElementos(nodo.getHijoDere(), lista);
        }
	}
    
}
