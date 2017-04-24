package ejercicio2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Carlos Urtubey
 */
public class ArbolRelaciones {

	HashMap<String, HashSet<String>> _relaciones;

	/**
	 * Representa un arbol de relaciones entre distintas entidades o elementos representados por un String.<br/>
	 * Un Arbol de Relaciones sigue la siguiente invariante:<br/>
	 *   -> Antes de agregar una relacion se debe agregar ambas entidades o elementos que van a formar parte de
	 *   	esa relacion.<br/>
	 *   -> La transitividad es valida, es decir, una elemento o entidad puede "relacionarse consigo mismo".<br/>
	 *   -> Las relaciones son asimetricas, es decir, el subordinado Y del elemento X no puede ser a la vez 
	 *      subordinante de X debido a que se sigue un rango jerarquico con estructura de arbol.<br/>
	 * 	 -> Un elemento X puede tener un conjunto vacio de relaciones. Debido a que se sigue una estructura de arbol
	 *      un elemento podria ser una "rama" y no tener ningun subordinado pero si subordinante.
	 * 
	 */
	
	public ArbolRelaciones(){
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
	 * @param subordinante El nombre del elemento que va a ser el subordinante.
	 * @param subordinado El nombre del elemento que va a ser el subordinado.
	 * @throws IllegalArgumentException Si alguno de los elementos no se encuentra en el map de elementos para establer relacion.
	 */
	public void agregarRelacion(String subordinante, String subordinado){
		checkearElemento(subordinante, "agregar una relacion");
		checkearElemento(subordinado, "agregar una relacion");
		checkearRedundancias(subordinante, subordinado);
		_relaciones.get(subordinante).add(subordinado);
	}
	
	/**
	 * @param elem1 El primer elemento sobre el cual se checkeara la relacion.
	 * @param elem2 El segundo elemento sobre el cual se checkeara la relacion.
	 * @return Integer. 0 si la relacion no esta definida, 1 si elem1 tiene como subordinado al 2, 2 si elem2 tiene como subordinado al 1.
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
	
	/**
	 * @param subordinante El elemento subordinante en la relacion.
	 * @param subordinado El elemento subordinado en la relacion.
	 */
	private void checkearRedundancias(String subordinante, String subordinado){
		if( getSubordinados(subordinado).contains(subordinante) )
			throw new IllegalArgumentException("No se admite definir relaciones redundantes: " + subordinante + " <-> " + subordinado);
	}
	
	/**
	 * @param elem El elemento del cual se van a obtener los subordinados.
	 * @return Set<String> Conjunto de elementos subordinados.
	 */
	public Set<String> getSubordinados(String elem){
		return _relaciones.get(elem);
	}
	
	/**
	 * @return Set<String> Conjunto de elementos en el arbol de relaciones.
	 */
	public Set<String> getElementos(){
		return _relaciones.keySet();
	}
	
}
