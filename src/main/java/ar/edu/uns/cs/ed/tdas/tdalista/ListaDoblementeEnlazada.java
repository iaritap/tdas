package ar.edu.uns.cs.ed.tdas.tdalista;

import java.util.Iterator;

import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.excepciones.BoundaryViolationException;
import ar.edu.uns.cs.ed.tdas.excepciones.EmptyListException;
import ar.edu.uns.cs.ed.tdas.excepciones.InvalidPositionException;

public class ListaDoblementeEnlazada<E> implements PositionList<E> {
    protected int cantidad;
	protected DNode<E> lista;
	protected DNode<E> ultimo;

    public ListaDoblementeEnlazada(){
        cantidad= 0;
        lista= new DNode(null);
        ultimo= new DNode(null);
        lista.setSiguiente(ultimo);
        ultimo.setAnterior(lista);

    }

    public int size() {
        return cantidad;
    }

    public boolean isEmpty() {
        return cantidad==0;
    }

    public Position<E> first() {
        if(isEmpty()){
            throw new EmptyListException("Se intento first de lista vacia");
        }
        return lista.getSiguiente();
    }

    public Position<E> last() {
        if(isEmpty()){
            throw new EmptyListException("Se intento last de lista vacia");
        }
        return ultimo.getAnterior();
    }

    public Position<E> next(Position<E> p) {
        if(isEmpty()){
            throw new InvalidPositionException ("Se intento next de lista vacia");
        }
        else{
           if(p== ultimo.getAnterior()){
            throw new BoundaryViolationException("se intento next con el ultimo elemento de la lista elemento");
           }
           else{
            DNode<E> h = (DNode<E>) checkPosition(p);
            return  h.getSiguiente();
           }
        }
        
    }

    public Position<E> prev(Position<E> p) {
        if(isEmpty()){
            throw new InvalidPositionException ("Se intento last de lista vacia");
        }
        else{
           if(p==lista.getSiguiente()){
            throw new BoundaryViolationException("se intento last con 1 solo elemento");
           }
           else{
            DNode<E> h = (DNode<E>) checkPosition(p);
            return  h.getAnterior();
           }
        }
    }

    public void addFirst(E element) {
        agregarEnElMedio(lista, lista.getSiguiente(), element);
    }

    public void addLast(E element) {
        agregarEnElMedio(ultimo.getAnterior(), ultimo, element);
    }

    public void addAfter(Position<E> p, E element) {
        if(isEmpty()){
            throw new InvalidPositionException("se intento addafter con lista vacia");
        }
        DNode<E> anterior = checkPosition(p);
		agregarEnElMedio(anterior, anterior.getSiguiente(), element);
    }

    public void addBefore(Position<E> p, E element) {
        if(isEmpty()){
            throw new InvalidPositionException("se intento addafter con lista vacia");
        }
        
        DNode<E> siguiente = checkPosition(p);
		agregarEnElMedio(siguiente.getAnterior(), siguiente , element); 
    }

    public E remove(Position<E> p) {
        if(isEmpty()){
            throw new InvalidPositionException ("Se intento remove de lista vacia");
        }
        else{
            DNode<E> eliminar= checkPosition(p);
            eliminar.getAnterior().setSiguiente(eliminar.getSiguiente());
            eliminar.getSiguiente().setAnterior(eliminar.getAnterior());
            E elem = eliminar.element();
            p = null;
            cantidad --;
            return elem;
        }
    }

    public E set(Position<E> p, E elem) {
        if(isEmpty()){
            throw new InvalidPositionException ("Se intento hacer set de lista vacia");
        }
        else{
            DNode<E> pos = checkPosition(p);
            E elemViejo = p.element();
            pos.setElemento(elem);
            return elemViejo;
        }
    }

    public Iterator<E> iterator() {
        return new IteradorListaDC<E>(this);

    }

    public Iterable<Position<E>> positions() {
        PositionList<Position<E>> p1= new ListaDoblementeEnlazada<Position<E>>();
        if(cantidad !=0){
            DNode<E> n = lista.getSiguiente();
            while (n != ultimo){
                p1.addLast(n);
                n=n.getSiguiente();
            }
        }
        return p1;
    }

    protected void agregarEnElMedio(DNode<E> anterior, DNode<E> siguiente, E elemento) {
		DNode<E> nuevoNodo = new DNode(elemento);
		nuevoNodo.setSiguiente(siguiente);
		nuevoNodo.getSiguiente().setAnterior(nuevoNodo);
		anterior.setSiguiente(nuevoNodo);
		nuevoNodo.setAnterior(anterior);
		this.cantidad++;
	}

    private DNode<E> checkPosition(Position<E> p) throws InvalidPositionException {
		DNode<E> n;
		if( p == null ) {
			throw new InvalidPositionException("Posición Nula. Posición Inválida");
		}
		if(this.isEmpty()) {
			throw new EmptyListException("No puede operar sobre una lista vacía");
		}
		try {
		     n= (DNode<E>) p;
		} catch( ClassCastException e ) { 
		throw new InvalidPositionException("Posicion invalida");  }
		return n;
	}

   /*  // agregados por el tp
    public void modificarElementos(E e1, E e2){
        if(isEmpty()){
            throw new InvalidPositionException ("Se intento modificarElementos de lista vacia");
        }
        else{
            DNode<E> elem1= new DNode<E>(e1,lista.getSiguiente(),lista.getSiguiente().getSiguiente());
            DNode<E> elem2= new DNode<E>(e2,ultimo.getAnterior().getAnterior(), ultimo.getAnterior());
            ultimo.getAnterior().getAnterior().setSiguiente(elem2);
            ultimo.getAnterior().setAnterior(elem2);
            lista.getSiguiente().getSiguiente().setAnterior(elem1);
            lista.getSiguiente().setSiguiente(elem1);
        }
    }

    //ejercio 3
    public boolean seEncuentra(PositionList<E> listita, E elem){
        boolean se= false;
        E e= null;
        Iterator<E> it= listita.iterator();
        while(it.hasNext() && !se){
            e = it.next();
            if( e == elem){
                se= true;
            }
        }
        return  se;
    }

    public int cantSeEncuentra(PositionList<E> listita, E elem){
        int contador= 0;
        for(E elemento: listita){
            if(elemento==elem){
                contador ++;
            }
        }
        return contador;
    }

    public boolean seEncuentraNVeces(PositionList<E> listita, E x, int n){
        boolean senv= false;
        int contador= 0;
        Iterator<E> it= listita.iterator();
        while(it.hasNext() && !senv){
            if(it.next()== x)
                contador++;
            if(contador>=n)
                senv= true;
        }
        return  senv;
    }

    //ejercicio 4
    public PositionList<E> nuevaListaRepetida (PositionList<E> listita ){
        PositionList<E> listarepe= ListaDoblementeEnlazada<E>();
        for(E elem: listita){
            listarepe.addLast(elem);
            listarepe.addLast(elem);
        }
        return listarepe;
    }

    //ejercicio 5 
    public Iterable<E> elimineElemDeL1(PositionList<E> l1, PositionList<E> l2){
        PositionList<E> listaDeELiminados = new ListaDoblementeEnlazada<E>();
        Iterable<Position<E>> it= l2.positions();
        PositionList<Position<E>> posicioneseliminadas= new ListaDoblementeEnlazada<Position<E>>();
        for(Position<E> p: it){
            for(E elemt: l1){
                if(elemt== p.element()){
                    posicioneseliminadas.addLast(p);
                    listaDeELiminados.addLast(elemt);
                }
            }
        }

        for(Position<E> p2: posicioneseliminadas){
            l2.remove(p2);
        }

        return listaDeELiminados;
    }

    //ejercicio 6
    public PositionList<E> intercalarListas(PositionList<E> l1, PositionList<E> l2){
        PositionList<E> listaresult= new ListaDoblementeEnlazada<E>();
        Iterator<E> it1= l1.iterator();
        Iterator<E> it2= l2.iterator();
        while(it1.hasNext() || it2.hasNext()){
            if(it1.hasNext()){
                listaresult.addLast(it1.next());
            }
            if(it2.hasNext()){
                listaresult.addLast(it2.next());
            }
        }
        return listaresult;
    }

    public PositionList<Integer> intercalarListaEnteros(PositionList<Integer> l1, PositionList<Integer> l2){
        PositionList<Integer> listaresult= new ListaDoblementeEnlazada<Integer>();
        Iterator<Integer> it1= l1.iterator();
        Iterator<Integer> it2= l2.iterator();
        int elem1;
        int elem2;
        while(it1.hasNext() || it2.hasNext()){
            if(it2.hasNext()&& it1.hasNext()){
                elem1= it1.next();
                elem2= it2.next();
                if(elem1== elem2){
                    listaresult.addLast(elem2);
                }
                else{
                    if(elem1>elem2){
                        listaresult.addLast(elem2);
                        listaresult.addLast(elem1);
                    }
                    else{
                        listaresult.addLast(elem1);
                        listaresult.addLast(elem2);
                    }
                }
            }
            else{
                if(it1.hasNext()){
                    listaresult.addLast(it1.next());
                }
                else{
                    listaresult.addLast(it2.next());
                }
            }
            
        }
        return listaresult;
    }

    //ejercicio 7
    public void eliminar(PositionList<E> l1, PositionList<E> l2){
        PositionList<E> reversoL2= new ListaDoblementeEnlazada<E>();
        Iterable<Position<E>> it= l1.positions();
        PositionList<Position<E>> posicioneseliminadas= new ListaDoblementeEnlazada<Position<E>>();
        for(Position<E> p: it){
            for(E elemt: l2){
                if(elemt== p.element()){
                    posicioneseliminadas.addLast(p);
                }
            }
        }   

        for(Position<E> p2: posicioneseliminadas){
            l1.remove(p2);
        }
        for(E e: l2){
            reversoL2.addFirst(e);
        }

        for(E e: reversoL2){
            l1.addLast(e);
        }

    } */
}
