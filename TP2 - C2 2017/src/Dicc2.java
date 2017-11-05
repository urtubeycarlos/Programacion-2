import java.util.HashSet;
import java.util.Iterator;
//import java.util.LinkedHashSet;
import java.util.PriorityQueue;
import java.util.Set;
//import java.util.LinkedList;

public class Dicc2<C, S> implements Dicc<C, S> {

	PriorityQueue<TuplaDicc<C, S>> _diccionario;
//	LinkedList<TuplaDicc<C, S>> _diccionario;
//	LinkedHashSet<Tupla<C, S>> _diccionario;
	
	public Dicc2(){
		_diccionario = new PriorityQueue<TuplaDicc<C, S>>( (t1, t2) -> t1.hashCode() - t2.hashCode() );
//		_diccionario = new LinkedList<TuplaDicc<C, S>>();
//		_diccionario = new LinkedHashSet<Tupla<C, S>>();
	}
	
	@Override
	public S obtener(C clave) {
		for( Tupla<C, S> t:_diccionario )
			if( t.e1.equals(clave) )
				return t.e2;
		return null;
	}

	@Override
	public void definir(C clave, S significado) {
		eliminar(clave);
		_diccionario.add( new TuplaDicc<C, S>(clave, significado) );
	}

	@Override
	public boolean pertenece(C clave) {
		for( Tupla<C, S> t:_diccionario )
			if( t.e1.equals(clave) )
				return true;
		return false;

	}
	
	public boolean pertenece(C clave, S significado) {
		if( pertenece(clave) )
			for( Tupla<C, S> t:_diccionario )
				if( t.e2.equals(significado) )
					return true;
		return false;
	}

	@Override
	public void eliminar(C clave) {
		_diccionario.removeIf( t -> t.e1.equals(clave) );
	}

	@Override
	public Iterator<C> iterator() {
		Set<C> claves = new HashSet<C>();
		_diccionario.forEach( t -> claves.add(t.e1) );
		return claves.iterator();
	}
	
	@Override
	public boolean equals(Object o){
		
		boolean ret = true;
		
		if( !(o instanceof Dicc) )
			ret = false;
		
		@SuppressWarnings("unchecked")
		Dicc<C, S> d2 = ( Dicc<C, S> ) o;
			
		Iterator<C> iter = d2.iterator();
		while( iter.hasNext() ){
			C aux = iter.next();
			ret = ret && pertenece(aux, d2.obtener(aux));
		}
		
		return ret;
	}
	
	@Override
	public String toString(){
		return _diccionario.toString();
	}

}
