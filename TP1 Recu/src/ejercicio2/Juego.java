package ejercicio2;

/**
 * @author Carlos Urtubey
 */
public class Juego {

	private ArbolRelaciones _gestorReglas;
	
	public Juego(){
		_gestorReglas = new ArbolRelaciones();
	}
	
	public void agregarElemento(String elemento){
		elemento = elemento.toLowerCase();
		_gestorReglas.agregarElemento(elemento);
	}
	
	public void agregarRegla(String ganador, String perdedor){
		ganador = ganador.toLowerCase();
		perdedor = perdedor.toLowerCase();
		checkearIgualdad(ganador, perdedor, "agregar una regla");
		_gestorReglas.agregarRelacion(ganador, perdedor);
	}
	
	public Integer jugar(String elem1, String elem2){
		elem1 = elem1.toLowerCase(); 
		elem2 = elem2.toLowerCase();
		checkearIgualdad(elem1, elem2, "jugar");
		checkearReglasIndefinidas();
		return _gestorReglas.checkearRelacion(elem1, elem2);
	}
	
	private void checkearIgualdad(String elem1, String elem2, String accion){
		if( elem1.equals(elem2) )
			throw new IllegalArgumentException("No se puede " + accion + " con dos elementos iguales");
	}
	
	private void checkearReglasIndefinidas(){
		for( String elem:_gestorReglas.getElementos() )
			if ( _gestorReglas.getSubordinados(elem).isEmpty() )
				throw new IllegalArgumentException("Faltan definidir reglas para: " + elem);
	}
}
