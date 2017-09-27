package ejercicio1;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class Test1 {
	
		ConjLazy<String> c1 ;
		ConjLazy<String> c2 ;
		ConjLazy<String> c3;
		
		@Before
		public void setUp() {
			c1 = new ConjLazy<String>();
			c2 = new ConjLazy<String>();
			c3 = new ConjLazy<String>();
			c1.agregar("A");
			c1.agregar("B");
			c2.agregar("B");
			c2.agregar("C");
			c3.agregar("C");
			c3.agregar("D");
			c1.union(c2);
			c1.interseccion(c3);
		}
		
		@Test
		public void test1() {
			assertEquals(true, c1.pertenece("C"));
			assertEquals(false, c1.pertenece("D"));
		}
	
}
