package ejercicio2;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class JuegoTest {

	private PPTLS juego;
	
	@Before
	public void init(){
		juego = new PPTLS();
	}
	
	@Test(expected=RuntimeException.class)
	public void juegoVacioTest(){
		juego.jugar("piedra", "tijera");
	}
	
	@Test(expected=RuntimeException.class)
	public void faltaElementoTest(){
		juego.agregarObjeto("piedra");
		juego.agregarObjeto("tijera");
		juego.jugar("tijera", "papel");
	}
	
	@Test(expected=RuntimeException.class)
	public void reglaIndefinidaTest(){
		juego.agregarObjeto("piedra");
		juego.agregarObjeto("papel");
		juego.jugar("piedra", "papel");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void jugadaIgualesTest(){
		juego.agregarObjeto("piedra");
		juego.agregarObjeto("papel");
		juego.agregarObjeto("tijera");
		
		juego.jugar("piedra", "piedra");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void reglaRedundanteTest(){
		juego.agregarObjeto("tijera");
		juego.agregarObjeto("piedra");
		
		juego.agregarRegla("tijera", "piedra");
		juego.agregarRegla("piedra", "tijera");
	}
	
	@Test
	public void juegoNormalTest() {
		juego.agregarObjeto("piedra");
		juego.agregarObjeto("papel");
		juego.agregarObjeto("tijera");
		
		juego.agregarRegla("piedra", "tijera");
		juego.agregarRegla("papel", "piedra");
		juego.agregarRegla("tijera", "papel");
		
		assertEquals("piedra le gana a tijera!", juego.jugar("piedra", "tijera"));
		assertEquals("papel le gana a piedra!", juego.jugar("papel", "piedra"));
		assertEquals("tijera le gana a papel!", juego.jugar("papel", "tijera"));
		
	}

	
	@Test
	public void juegoNormalConSimilaresTest(){
		juego.agregarObjeto("piedra");
		juego.agregarObjeto("papel");
		juego.agregarObjeto("tijera");
		juego.agregarObjetoParecido("papel a4", "papel");
		
		juego.agregarRegla("piedra", "tijera");
		juego.agregarRegla("papel", "piedra");
		juego.agregarRegla("tijera", "papel");
		assertEquals("tijera le gana a papel!", juego.jugar("papel", "tijera"));
		assertEquals("tijera le gana a papel a4!", juego.jugar("papel a4", "tijera"));
	}
}
