package ejercicio1;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ConjLazyTest {

	ConjLazy<String> c1;
	ConjLazy<String> c2;
	ConjLazy<String> c3;
	
	@Before
	public void setUp(){
		
		c1 = new ConjLazy<>();
		c1.agregar("A");
		c1.agregar("B");
		c1.agregar("C");
		
		c2 = new ConjLazy<>();
		c2.agregar("C");
		c2.agregar("D");
		c2.agregar("E");
		
		c3 = new ConjLazy<String>();
		c3.agregar("B");
		c3.agregar("F");
		
	}
	
	@Test
	public void agregarTest(){
		assertEquals(3, c1.tamaño());
	}
	
	@Test
	public void unionTest() {
		
		c1.union(c2);
		c1.union(c3);
		
		assertEquals(true, c1.pertenece("D"));
		assertEquals(true, c1.pertenece("F"));
		assertEquals(6, c1.tamaño());
		
	}
	
	//FIXME: Revisar test.
	@Test
	public void interseccionTest(){
		
		// c1 = [A, B, C]
		// c2 = [C, D, E]
		// c3 = [B, F]
		c1.interseccion(c2);
		// c1 intersec c2 = C
		c1.interseccion(c3);
		// c1 = [C]
		// c3 = [B, F]
		// c1 intersec c3 = []
		
		//¿Está bien el test?
		
		assertTrue( c1.pertenece("C") );
		assertTrue( c1.pertenece("B") );
		assertEquals(2, c1.tamaño());
		
	}

}
