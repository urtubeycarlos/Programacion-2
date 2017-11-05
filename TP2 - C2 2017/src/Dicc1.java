
import java.util.List;
import java.util.LinkedList;
import java.util.TreeSet;
//import java.util.Comparator;
import java.util.Iterator;

public class Dicc1<C, S> implements Dicc<C, S> {

	TreeSet<TuplaDicc<C, S>> _diccionario;
	
	public Dicc1(){
		
		_diccionario = new TreeSet<TuplaDicc<C, S>>( (t1, t2) -> t1.hashCode() - t2.hashCode() );
		
//		_diccionario = new TreeSet<TuplaDicc<C, S>>( new Comparator<TuplaDicc<C, S>>() {
//
//			@Override
//			public int compare(TuplaDicc<C, S> t1, TuplaDicc<C, S> t2) {
//				return t1.hashCode() - t2.hashCode();
//			}
//			
//		});
		
	}
	
	@Override
	public S obtener(C clave) {
		checkearEntrada(clave, "clave");
		for( TuplaDicc<C, S> t:_diccionario )
			if( t.e1.equals(clave) )
				return t.e2;
		return null;
	}

	@Override
	public void definir(C clave, S significado) {
		checkearEntrada(clave, "clave");
		checkearEntrada(significado, "significado");
		eliminar(clave);
		_diccionario.add( new TuplaDicc<C, S>(clave, significado) );
	}


	@Override
	public void eliminar(C clave) {
		checkearEntrada(clave, "clave");
		_diccionario.removeIf( t -> t.e1.equals(clave) );
	}
	
	@Override
	public boolean pertenece(C clave) {
		checkearEntrada(clave, "clave");
		return _diccionario.contains( new TuplaDicc<C, S>(clave, null) );
	}
	
	private boolean pertenece(C clave, S significado){
		if( pertenece(clave) )
			for( TuplaDicc<C, S> t:_diccionario )
				if( t.e2.equals(significado) )
					return true;
		return false;
	}
	
	private void checkearEntrada(Object entrada, String tipo) {
		if( entrada == null )
			throw new IllegalArgumentException(tipo + " no puede ser null");
	}

	@Override
	public Iterator<C> iterator() {
		List<C> claves = new LinkedList<C>();
		_diccionario.forEach( t -> claves.add(t.e1) );
		return claves.iterator();
	}
	
	@Override
	public boolean equals(Object o){
		
		if( o == null )
			return false;
		
		if( !(o instanceof Dicc) )
			return false;
		
		boolean ret = true;
		
		@SuppressWarnings("unchecked")
		Dicc<C, S> d2 = ( Dicc<C, S> ) o;
			
		Iterator<C> iter = d2.iterator();
		while( iter.hasNext() ){
			C aux = iter.next();
			ret = ret && pertenece( aux, d2.obtener(aux) );
		}
		
		return ret;
	}

	@Override
	public String toString(){
		return _diccionario.toString();
	}
	
	public static void main(String[] args){
		
		Dicc1<Integer, String> dic1 = new Dicc1<>();
		
		dic1.definir(1, "cuatro");
		dic1.definir(1, "uno");
		dic1.definir(2, "dos");
		
	}
}
