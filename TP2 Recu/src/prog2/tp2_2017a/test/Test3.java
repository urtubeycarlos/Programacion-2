package prog2.tp2_2017a.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import prog2.tp2_2017a.Digitos;
import prog2.tp2_2017a.TrieChar;

public class Test3 {

	private TrieChar<String> t;
	
	@Before
	public void setUp() {
		t = new TrieChar<String>( new Digitos() );
		t.agregar("123", "Juan");
		t.agregar("456", "Carlos");
		t.agregar("124", "Pedro");
	}

	@Test
	public void agregarTest() {
		assertEquals("Juan", t.obtener("123"));
	}
	
	@Test
	public void eliminarTest(){
		t.eliminar("456");
		assertNull( t.obtener("456") );
	}

	@Test
	public void buscarTest(){
		
		String[] valores = new String[]{"Juan", "Pedro"};
		List<String> esperados = new ArrayList<String>();
		esperados.addAll( Arrays.asList(valores) );
		
		assertEquals(esperados, t.busqueda("12"));
	}
	
}
