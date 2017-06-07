package prog2.tp2_2017a;

import java.util.List;

public class TrieChar<V>
{
	private Nodo<V> raiz;
	private Alfabeto<Character> alf;

	public TrieChar(Alfabeto<Character> alf) {
		this.alf = alf;
	}

	/**
	 * Agrega una cadena a la estructura, asociándole un determinado valor.
	 *
	 * Si la clave ya existía, se reemplaza su valor asociado.
	 */
	public void agregar(String clave, V valor) {
	}

	/**
	 * Devuelve el valor asociado a una clave, o null si no existe.
	 */
	public V obtener(String clave) {
		return null;
	}

	/**
	 * Devuelve una lista con todos los valores cuyas claves
	 * empiezan por un determinado prefijo.
	 */
	public List<V> busqueda(String prefijo) {
		return null;
	}
}
