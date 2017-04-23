package ejercicio2;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class JuegoTest {

	Juego juego;
	
	@Before
	public void initialize(){
		juego = new Juego();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testReglaIguales(){
		juego.agregarElemento("Piedra");
		juego.agregarRegla("Piedra", "Piedra");
	}
	
	@Test
	public void testJuegoNormal1() {
		
		juego.agregarElemento("Piedra");
		juego.agregarElemento("Tijera");
		juego.agregarElemento("Papel");
		
		juego.agregarRegla("Piedra", "Tijera");
		juego.agregarRegla("Tijera", "Papel");
		juego.agregarRegla("Papel", "Piedra");
		
		assertEquals(new Integer(1), juego.jugar("Tijera", "Papel"));
		
	}
	
}
