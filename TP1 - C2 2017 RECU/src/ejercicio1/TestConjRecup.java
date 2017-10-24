package ejercicio1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestConjRecup 
{
	
	private CjtoLazy<Integer> c0 , c1, c2 , c3, c4;
	@Before
	public void setUp() {
	c0 = new CjtoLazy<>();
	c1 = new CjtoLazy<>();
	c2 = new CjtoLazy<>();
	c3 = new CjtoLazy<>();
	c4 = new CjtoLazy<>();
	int n = 100000;
	for (int i =0; i < 100000; i++){
	c1.agregar(i);
	c2.agregar(n + i);
	c3.agregar(i);
	c4.agregar(n + i);
	}
	}

	@Test
	public void stress_O_1() {
		long start = System.currentTimeMillis();
		c1.interseccion(c2); //O(1)
		//System.out.println((System.currentTimeMillis() - start));
		// falla si no es O(1)
		assertTrue((System.currentTimeMillis() - start) < 10 );
		}
		@Test
		public void Consistencia1() {
		long start = System.currentTimeMillis();
		c1.interseccion(c2); //O(1)
		c3.interseccion(c4);
		
		c0.union(c1); //O(1)
		c0.union(c3); // vacio
		boolean aux = c0.pertenece(11711); // No es O(1)!
		//System.out.println(aux + " " + (System.currentTimeMillis() - start));
		// falla si se aplican mal las operaciones
		assertTrue(!aux);
		}
		@Test
		public void Consistencia2() {
		c1.resta(c3);
		boolean aux = c1.pertenece(11711); // No es O(1)!
		// falla si se aplican mal las operaciones
		assertTrue(!aux);
		}

}
