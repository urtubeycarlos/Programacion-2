package prog2.tp2_2017a.test;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import prog2.tp2_2017a.AlfabetoADN ;
import prog2.tp2_2017a.TrieChar;

public class TestADN {

	private List<String> busq;
	private TrieChar<String> cadenasADN;

	@Before
	public void inicializar() {
		cadenasADN = new TrieChar<>(new AlfabetoADN());
	}

	@Test
	public void vacio() {
		assertNull(cadenasADN.obtener("AAA"));
		assertTrue(cadenasADN.busqueda("AC").isEmpty());
		assertTrue(cadenasADN.busqueda("ACCG").isEmpty());
	}

	@Test
	public void ejemploConsigna() {
		cadenasADN.agregar("AAA", "s1");
		cadenasADN.agregar("ACGT", "s2");
		cadenasADN.agregar("TAC", "s3");

		assertEquals("s1", cadenasADN.obtener("AAA"));
		assertEquals("s3", cadenasADN.obtener("TAC"));

		busq = cadenasADN.busqueda("A");
		assertEquals(2, busq.size());
		assertTrue(busq.contains("s1"));
		assertTrue(busq.contains("s2"));
		
		System.out.println(cadenasADN.toString());
	}

	@Test
	public void buscarTodos() {
		cadenasADN.agregar("ATC", "s4");
		cadenasADN.agregar("CAT", "s5");
		cadenasADN.agregar("AGG", "s6");
		cadenasADN.agregar("GAT", "s7");

		busq = cadenasADN.busqueda("");

		assertEquals(4, busq.size());
		assertTrue(busq.contains("s4"));
		assertTrue(busq.contains("s5"));
		assertTrue(busq.contains("s6"));
		assertTrue(busq.contains("s7"));

		assertNull(cadenasADN.obtener("GGGG"));
	}

}
