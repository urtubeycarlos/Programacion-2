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
		Character digito_actual = clave.charAt(0);
		return obtener( clave.substring(1, clave.length()), actual.hijo( alf.indice(digito_actual) ));
		
	}

	/**
	 * Devuelve una lista con todos los valores cuyas claves
	 * empiezan por un determinado prefijo.
	 */
	public List<V> busqueda(String prefijo) {
		
		List<V> ret = new ArrayList<V>();
		boolean existePrefijo = false;
		
		for( String clave:claves )
			existePrefijo = existePrefijo || clave.substring(0, prefijo.length()).equals(prefijo);
		
		if( !existePrefijo )
			return ret;
		
		Nodo<V> nivel = raiz;
		for(char caracter:prefijo.toCharArray())
			nivel = nivel.hijo( alf.indice(caracter) );
		
		busqueda(nivel, ret);
		
		return ret;
	}
	
	private void busqueda(Nodo<V> nodo, List<V> lista){
		 
		if(nodo.val != null)
			lista.add( nodo.val );
		
		for( int i=0; i<alf.tam(); i++ ) if ( nodo.hijo(i) != null )
			busqueda(nodo.hijo(i), lista);
		
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
