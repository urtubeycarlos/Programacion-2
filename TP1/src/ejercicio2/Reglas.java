package ejercicio2;

import java.util.HashMap;
import java.util.HashSet;

public class Reglas {

	HashMap<String, HashSet<String>> _reglas;
	// HashMap<"le gana a...", HashSet<"...estos">>
	
	public Reglas(){
		_reglas = new HashMap<String, HashSet<String>>();
	}
	
	/**
	 * @param elemento Nombre del nuevo elemento que se va a agregar
	 * @return boolean true si puedo agregar correctamente, caso contrario false.
	 */
	public boolean agregarElemento(String elemento){
		return _reglas.putIfAbsent(elemento, new HashSet<String>()) != null;
	}
	
	/**
	 * @param ganador El nombre del elemento que va a ganar cuando se checkee esta regla.
	 * @param perdedor El nombre del elemento que va a perder cuando se checkee esta regla
	 * @return true si se agrego la regla correctamente, false si no se la pudo agregar.
	 */
	public boolean agregarRegla(String ganador, String perdedor){
		checkearElemento(ganador, "agregar una regla");
		checkearElemento(perdedor, "agregar una regla");
		return _reglas.get(ganador).add(perdedor);
	}
	
	/**
	 * @param elem1 El primer elemento de la jugaada
	 * @param elem2 El segundo elemento de la jugada
	 * @return Integer. 0 si la regla no esta definida, 1 si gano el elemento 1, 2 si gano el elemento 2.
	 * @throws IllegalArgumentException Si alguno de los elementos no se encuentra en la lista de reglas.
	 */
	public Integer checkearRegla(String elem1, String elem2){
		checkearElemento(elem1, "checkear una regla");
		checkearElemento(elem2, "checkear una regla");
		if( _reglas.get(elem1).contains(elem2) )
			return 1;
		if( _reglas.get(elem2).contains(elem1))
			return 2;
		return 0;
	}

	/**
	 * @param elem El elemento a checkear.
	 * @param accion La accion que se va a realizar. Para poder mostrar el mensaje correspondiente si se lanza la excepcion.
	 */
	private void checkearElemento(String elem, String accion){
		if( !_reglas.containsKey(elem) )
			throw new IllegalArgumentException("No se puede " + accion + " sin agregar el elemento '" + elem + "' primero");
	}
	
}
