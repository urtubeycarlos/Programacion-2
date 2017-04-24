package ejercicio2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ConjuntoRelaciones {

	HashMap<String, HashSet<String>> _relaciones;
	// HashMap<"le gana a...", HashSet<"...estos">>
	
	public ConjuntoRelaciones(){
		_relaciones = new HashMap<String, HashSet<String>>();
	}
	
	/**
	 * @param elemento Nombre del nuevo elemento que se va a agregar
	 * @return boolean true si puedo agregar correctamente, caso contrario false.
	 */
	public void agregarElemento(String elemento){
		_relaciones.putIfAbsent(elemento, new HashSet<String>());
	}
	
	/**
	 * @param subordinante El nombre del elemento que va a ganar cuando se checkee esta regla.
	 * @param subordinado El nombre del elemento que va a perder cuando se checkee esta regla
	 * @return true si se agrego la regla correctamente, false si no se la pudo agregar.
	 * @throws IllegalArgumentException Si alguno de los elementos no se encuentra en el map de reglas.
	 */
	public void agregarRelacion(String subordinante, String subordinado){
		checkearElemento(subordinante, "agregar una relacion");
		checkearElemento(subordinado, "agregar una relacion");
		_relaciones.get(subordinante).add(subordinado);
	}
	
	/**
	 * @param elem1 El primer elemento de la jugaada
	 * @param elem2 El segundo elemento de la jugada
	 * @return Integer. 0 si la regla no esta definida, 1 si elem1 tiene como subordinado al 2, 2 si elem2 tiene como subordinado al 1.
	 * @throws IllegalArgumentException Si alguno de los elementos no se encuentra en el map de reglas.
	 */
	public Integer checkearRelacion(String elem1, String elem2){
		checkearElemento(elem1, "checkear una regla");
		checkearElemento(elem2, "checkear una regla");
		if( _relaciones.get(elem1).contains(elem2) )
			return 1;
		if( _relaciones.get(elem2).contains(elem1))
			return 2;
		return 0;
	}

	/**
	 * @param elem El elemento a checkear.
	 * @param accion La accion que se va a realizar. Para poder mostrar el mensaje correspondiente si se lanza la excepcion.
	 */
	private void checkearElemento(String elem, String accion){
		if( !_relaciones.containsKey(elem) )
			throw new IllegalArgumentException("No se puede " + accion + " sin agregar el elemento '" + elem + "' primero");
	}
	
	public Set<String> getSubordinados(String elem){
		return _relaciones.get(elem);
	}
	
	public Set<String> getElementos(){
		return _relaciones.keySet();
	}
	
}
