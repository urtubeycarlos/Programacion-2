package ejercicio2;

public class Juego {

	private Reglas _reglas;
	
	public Juego(){
		_reglas = new Reglas();
	}
	
	public void agregarElemento(String elemento){
		elemento = elemento.toLowerCase();
		_reglas.agregarElemento(elemento);
	}
	
	public void agregarRegla(String ganador, String perdedor){
		ganador = ganador.toLowerCase();
		perdedor = perdedor.toLowerCase();
		checkearEntrada(ganador, perdedor, "agregar una regla");
		_reglas.agregarRegla(ganador, perdedor);
	}
	
	public Integer jugar(String elem1, String elem2){
		elem1 = elem1.toLowerCase(); 
		elem2 = elem2.toLowerCase();
		checkearEntrada(elem1, elem2, "jugar");
		return _reglas.checkearRegla(elem1, elem2);
	}
	
	public void checkearEntrada(String elem1, String elem2, String accion){
		if( elem1.equals(elem2) )
			throw new IllegalArgumentException("No se puede " + accion + " con dos elementos iguales");
	}
	
}
