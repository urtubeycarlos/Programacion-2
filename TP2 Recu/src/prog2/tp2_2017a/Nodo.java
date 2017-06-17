package prog2.tp2_2017a;

/**
 * Nodo de un trie.
 *
 * El número de hijos se decide en el constructor.
 */
class Nodo<V>
{
	V val;
	private Nodo<V> hijos[];

	@SuppressWarnings("unchecked")
	Nodo(int tam) {
		hijos = new Nodo[tam];
	}

	Nodo<V> hijo(int i) {
		return hijos[i];
	}

	void setHijo(int i, Nodo<V> hijo) {
		hijos[i] = hijo;
	}
}
