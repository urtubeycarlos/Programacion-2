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
	
	
	@Test(expected=IllegalArgumentException.class)
	public void reglaConInexistenteTest(){
		
		juego.agregarObjeto("piedra");
		juego.agregarObjeto("papel");
		
		juego.agregarRegla("tijera", "papel");
		
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void reglaIgualesTest(){
		
		juego.agregarObjeto("piedra");
		juego.agregarObjeto("papel");
		juego.agregarObjeto("tijera");
		
		juego.agregarRegla("piedra", "piedra");
		
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void reglaEntreParecidosTest(){
		
		juego.agregarObjeto("piedra");
		juego.agregarObjeto("papel");
		juego.agregarObjeto("tijera");
		
		juego.agregarObjetoParecido("piedra roja", "piedra");
		
		juego.agregarRegla("piedra", "piedra roja");
		
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void reglaRedundanteTest(){
		
		juego.agregarObjeto("tijera");
		juego.agregarObjeto("piedra");
		
		juego.agregarRegla("tijera", "piedra");
		juego.agregarRegla("piedra", "tijera");
	
	}

	
	@Test(expected=IllegalArgumentException.class)
	public void jugadaIgualesTest(){
		
		juego.agregarObjeto("piedra");
		juego.agregarObjeto("papel");
		juego.agregarObjeto("tijera");
		
		juego.jugar("piedra", "piedra");
	
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void jugadaConInexistenteTest(){
		
		juego.agregarObjeto("piedra");
		juego.agregarObjeto("papel");
		
		juego.jugar("tijera", "papel");
	}
	
	@Test(expected=RuntimeException.class)
	public void jugadaReglaIndefinidaTest(){
		
		juego.agregarObjeto("piedra");
		juego.agregarObjeto("papel");
		
		juego.jugar("piedra", "papel");
		
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

	
	@Test(expected=IllegalArgumentException.class)
	public void faltaElementoOriginalTest(){
		
		juego.agregarObjeto("piedra");
		juego.agregarObjeto("tijera");
		
		juego.agregarObjetoParecido("papel a4", "papel");
		
	}
	

	@Test(expected=IllegalArgumentException.class)
	public void jugadaElementosParecidosIguales(){
		
		juego.agregarObjeto("piedra");
		juego.agregarObjeto("papel");
		
		juego.agregarObjetoParecido("papel a4", "papel");
		
		juego.jugar("papel a4", "papel a4");
		
	}

	
	@Test(expected=IllegalArgumentException.class)
	public void jugadaElementosParecidosNoIguales(){
		
		juego.agregarObjeto("piedra");
		juego.agregarObjeto("papel");
		
		juego.agregarObjetoParecido("papel a4", "papel");
		juego.agregarObjetoParecido("papel a5", "papel");
		
		juego.jugar("papel a4", "papel a5");
		
	}
	
	
	@Test
	public void agregarReglasParecidosInvertidas(){
		
		juego.agregarObjeto("piedra");
		juego.agregarObjeto("papel");
		
		juego.agregarRegla("papel", "piedra");
		
		juego.agregarObjetoParecido("papel a4", "papel");
		
		assertEquals("papel a4 le gana a piedra!", juego.jugar("papel a4", "piedra") );
	}
	
	
	@Test
	public void juegoNormalConParecidosTest(){
		
		juego.agregarObjeto("piedra");
		juego.agregarObjeto("papel");
		juego.agregarObjeto("tijera");
		
		juego.agregarObjetoParecido("papel a4", "papel");
		juego.agregarObjetoParecido("papel a5", "papel");
		juego.agregarObjetoParecido("piedra roja", "piedra");
		
		juego.agregarRegla("piedra", "tijera");
		juego.agregarRegla("papel", "piedra");
		juego.agregarRegla("tijera", "papel");
		
		assertEquals("tijera le gana a papel!", juego.jugar("papel", "tijera"));
		assertEquals("tijera le gana a papel a4!", juego.jugar("papel a4", "tijera"));
		assertEquals("papel a4 le gana a piedra roja!", juego.jugar("papel a4", "piedra roja"));
		assertEquals("papel a5 le gana a piedra roja!", juego.jugar("papel a5", "piedra roja"));
		
	}
}
