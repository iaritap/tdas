package ar.edu.uns.cs.ed.tdas.tdalista;
import java.util.Iterator;

import ar.edu.uns.cs.ed.tdas.Position;
import ar.edu.uns.cs.ed.tdas.excepciones.BoundaryViolationException;

public class IteradorListaDC<E> implements Iterator<E> {

    protected PositionList<E> lista;
    protected Position<E> cursor;

    public IteradorListaDC(PositionList<E> list){
        lista= list;
        if(!lista.isEmpty()){
            cursor = list.first();
        }
        else{
            cursor= null;
        }
    }

    
    public boolean hasNext() {
        return cursor!=null;
    }

    public E next() {
        if(cursor== null){
            throw new BoundaryViolationException("no hay siguiente elem en iterador");
        }
        E elem= cursor.element();
        if(cursor == lista.last()){
            cursor= null;
        }
        else{
            cursor= lista.next(cursor);
        }
        
        return elem;
    }
    
}
