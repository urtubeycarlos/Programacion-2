package ejercicio2;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class JuegoTest {
	
	Juego juego;
	
	@Before
	public void iniciar(){
		juego = new Juego();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testReglaVacia(){
		juego.agregarRegla("Tijera", "Piedra");
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testReglaIguales(){
		
		juego.agregarElemento("Piedra");
		juego.agregarRegla("Piedra", "Piedra");
		
	}

	@Test(expected = IllegalArgumentException.class)
	public void testReglaElemInexistente(){
		
		juego.agregarElemento("Piedra");
		juego.agregarRegla("Tijera", "Piedra");

	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testReglasRedundantes(){
	
		juego.agregarElemento("Papel");
		juego.agregarElemento("Tijera");
		
		juego.agregarRegla("Papel", "Tijera");
		juego.agregarRegla("Tijera", "Papel");
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testReglasIndefinidas(){
		
		juego.agregarElemento("Piedra");
		juego.agregarElemento("Papel");
		juego.agregarElemento("Tijera");
		
		juego.agregarRegla("Piedra", "Tijera");
		juego.agregarRegla("Tijera", "Papel");
		
		juego.jugar("Tijera", "Papel");
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
		assertEquals(new Integer(2), juego.jugar("Tijera", "Piedra"));
		
	}
	
}
