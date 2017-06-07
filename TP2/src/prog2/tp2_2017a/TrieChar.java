package prog2.tp2_2017a;

import java.util.List;

public class TrieChar<V> {
	
	private Nodo<V> raiz;
	private Alfabeto<Character> alf;

	public TrieChar(Alfabeto<Character> alf) {
		this.alf = alf;
		this.raiz = new Nodo<V>( alf.tam() );
	}

	/**
	 * Agrega una cadena a la estructura, asociándole un determinado valor.
	 * Si la clave ya existía, se reemplaza su valor asociado.
	 */
	public void agregar(String clave, V valor) {
		agregar(clave, valor, raiz);
	}

	private void agregar(String clave, V valor, Nodo<V> actual){
		if ( clave.length() == 0 ){
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
		return null;
	}

}
