package ejercicio2;

public class Main {

	public static void main(String[] args) {

		JuegoSillas js = new JuegoSillas(4);
		js.asignarPersonas();
		
		
		while( js.cantidadSillasActivas() > 0 ){
			
			js.jugar();
			System.out.println( js );
			
		}
		
	}

}
