package prog2.tp2_2017a;

public interface Alfabeto<T>
{
	/**
	 * Devuelve k: número de símbolos en el alfabeto.
	 */
	int tam();

	/**
	 * Devuelve el índice correspondiente a un símbolo.
	 *
	 * Debe tener complejidad O(1). Lanza RuntimeException
	 * si el símbolo no es válido.
	 */
	int indice(T elem);
}
