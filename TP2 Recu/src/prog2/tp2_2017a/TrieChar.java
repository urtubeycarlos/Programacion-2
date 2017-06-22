package prog2.tp2_2017a;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class TrieChar<V> {
	
	private Nodo<V> raiz;
	private Alfabeto<Character> alf;
	private HashSet<String> claves;

	public TrieChar(Alfabeto<Character> alf) {
		this.alf = alf;
		this.raiz = new Nodo<V>( alf.tam() );
		this.claves = new HashSet<String>();
	}

	/**
	 * Agrega una cadena a la estructura, asociándole un determinado valor.
	 * Si la clave ya existía, se reemplaza su valor asociado.
	 */
	public void agregar(String clave, V valor) {
		claves.add(clave);
		agregar(clave, valor, raiz);
	}

	private void agregar(String clave, V valor, Nodo<V> actual){
		if ( clave.equals("") ){
			actual.val = valor;
		} else {
			Character digito_actual = clave.charAt(0);
			
			Nodo<V> hijo_actual = actual.hijo( alf.indice(digito_actual) );
			if( hijo_actual == null ){
				actual.setHijo( alf.indice(digito_actual) , new Nodo<V>( alf.tam() ));
				hijo_actual = actual.hijo( alf.indice(digito_actual) );
			}

			agregar( clave.substring(1, clave.length()), valor, hijo_actual);
		}
	}
	
	
	public void eliminar(String clave){
		if( claves.contains(clave) ){
			agregar(clave, null, raiz);
			claves.remove(clave);
		}
	}
	
//	Alternativo	
//	
//	public void eliminar(String clave){
//		if( clave.contains(clave) ){
//			eliminar(clave, raiz);
//			claves.remove(clave);
//		}
//	}
//	
//	public void eliminar(String clave, Nodo<V> nodo){
//		if( clave.equals("") )
//			nodo.val = null;
//		else
//			eliminar(clave.substring(1), nodo.hijo( alf.indice(clave.charAt(0)) ));
//	}
	
	
	/**
	 * Devuelve el valor asociado a una clave, o null si no existe.
	 */
	public V obtener(String clave) {
		if( !claves.contains(clave) )
			return null;
		return obtener(clave, raiz);
	}
	
	private V obtener(String clave, Nodo<V> actual){
		if( clave.equals("") )
			return actual.val;
		return obtener( clave.substring(1), actual.hijo( alf.indice(clave.charAt(0)) ));
	}

	/**
	 * Devuelve una lista con todos los valores cuyas claves
	 * empiezan por un determinado prefijo.
	 */
	public List<V> busqueda(String prefijo) {
		
		List<V> ret = new ArrayList<V>();
		busqueda(prefijo, raiz, ret);
		return ret;
		
	}

	private void busqueda(String prefijo, Nodo<V> nodo, List<V> lista){
		
		if( prefijo.equals("") ){
			
			if(nodo.val != null)
				lista.add( nodo.val );
			
			for( int i=0; i<alf.tam(); i++ ) if ( nodo.hijo(i) != null )
				busqueda(prefijo, nodo.hijo(i), lista);
			
		} else {
			
			Nodo<V> hijo = nodo.hijo( alf.indice(prefijo.charAt(0)) );
			if( hijo != null )
				busqueda(prefijo.substring(1), hijo, lista);
			
		}
		
	}
	
	@Override
	public boolean equals(Object o){

		if( o == null )
			return false;
		
		if( !(o instanceof TrieChar) )
			return false;
			
		@SuppressWarnings("unchecked")
		TrieChar<V> t2 = (TrieChar<V>) o;
		
		if( !this.alf.equals( t2.alf ) )
			return false;
		if( this.claves.size() != t2.claves.size() || !this.claves.equals( t2.claves ) )
			return false;
		
		for( String clave:claves )
			if( !this.obtener(clave).equals( t2.obtener(clave) ) )
				return false;
		
		return true;
		
	}
	
	@Override
	public String toString(){
		String ret = "TrieChar: { ";
		for(String clave:claves)
			ret = ret + clave + " = " + this.obtener(clave).toString() + ", ";
		ret = ret.substring(0, ret.length()-2) + " }";
		return ret;
	}
	
}
