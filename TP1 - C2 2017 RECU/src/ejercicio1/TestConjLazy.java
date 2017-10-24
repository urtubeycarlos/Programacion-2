package ejercicio1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestConjLazy
{
	
	
	private CjtoLazy<String> ab, bc, cd, cjto;

	@Before
	public void setUp() 
	{
		ab = new CjtoLazy<>();
		bc = new CjtoLazy<>();
		cd = new CjtoLazy<>();
		cjto = new CjtoLazy<>();

		ab.agregar("A");
		ab.agregar("B");

		bc.agregar("B");
		bc.agregar("C");

		cd.agregar("C");
		cd.agregar("D");
	}


	@Test
	public void ejemploConsigna()
	{
		cjto.union(ab);
		cjto.union(bc);
		cjto.interseccion(cd);

		System.out.println(cjto.size());
		System.out.println(cjto.pertenece("C"));
		System.out.println(cjto.pertenece("A"));
		System.out.println(cjto.pertenece("B"));

		
		assertEquals(1, cjto.size());
		assertTrue(cjto.pertenece("C"));
		assertFalse(cjto.pertenece("D"));
		assertFalse(cjto.pertenece("A"));
		assertFalse(cjto.pertenece("B"));
	}
	
	@Test
	public void agregar()
	{
		cjto.agregar("B");
		cjto.union(ab);
		cjto.agregar("C");
		
		assertEquals(3, cjto.size());
		assertTrue(cjto.pertenece("A"));
		assertTrue(cjto.pertenece("B"));
		assertTrue(cjto.pertenece("C"));
	}
	
	@Test
	public void interseccionVacia() 
	{
		cjto.agregar("C");
		cjto.interseccion(ab);
		
		assertEquals(0, cjto.size());
		assertFalse(cjto.pertenece("C"));
	}
		
	@Test
	public void interseccionNoVacia()
	{
		cjto.interseccion(ab);
		cjto.agregar("C");

		assertEquals(1, cjto.size());
		assertTrue(cjto.pertenece("C"));
	}
	
	@Test
	public void propagarRecalcular()
	{
		ab.union(cd);
		ab.agregar("E");

		cjto.union(bc);
		cjto.agregar("E");
		
		cjto.interseccion(ab);
		
		assertEquals(3, cjto.size());
		assertTrue(cjto.pertenece("B"));
		assertTrue(cjto.pertenece("C"));
		assertTrue(cjto.pertenece("E"));
	}
}
