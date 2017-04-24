package ejercicio2;

public class Juego {

	private ConjuntoRelaciones _reglas;
	
	public Juego(){
		_reglas = new ConjuntoRelaciones();
	}
	
	public void agregarElemento(String elemento){
		elemento = elemento.toLowerCase();
		_reglas.agregarElemento(elemento);
	}
	
	public void agregarRegla(String ganador, String perdedor){
		ganador = ganador.toLowerCase();
		perdedor = perdedor.toLowerCase();
		checkearIgualdad(ganador, perdedor, "agregar una regla");
		checkearRedundancias(ganador, perdedor);
		_reglas.agregarRelacion(ganador, perdedor);
	}
	
	public Integer jugar(String elem1, String elem2){
		elem1 = elem1.toLowerCase(); 
		elem2 = elem2.toLowerCase();
		checkearIgualdad(elem1, elem2, "jugar");
		checkearReglasIndefinidas();
		return _reglas.checkearRelacion(elem1, elem2);
	}
	
	private void checkearIgualdad(String elem1, String elem2, String accion){
		if( elem1.equals(elem2) )
			throw new IllegalArgumentException("No se puede " + accion + " con dos elementos iguales");
	}
	
	private void checkearRedundancias(String ganador, String perdedor){
		if( _reglas.getSubordinados(perdedor).contains(ganador) )
			throw new IllegalArgumentException("No se admite definir reglas redundantes: " + ganador + " <-> " + perdedor);
	}
	
	private void checkearReglasIndefinidas(){
		for( String elem:_reglas.getElementos() )
			if ( _reglas.getSubordinados(elem).isEmpty() )
				throw new IllegalArgumentException("Faltan definidir reglas para: " + elem);
	}
}
