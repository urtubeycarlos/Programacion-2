package ejercicio1;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;


public class Test1 {
   ConjLazy<Integer>  c0;
   ConjLazy<Integer>  c1;
   ConjLazy<Integer>  c2;
   ConjLazy<Integer> c3;
   ConjLazy<Integer> c4;

  
  @Before
  public void setUp() {
    
    c0 = new ConjLazy<Integer>();
    c1 = new ConjLazy<Integer>();
    c2 = new ConjLazy<Integer>();
    c3 = new ConjLazy<Integer>();
    c4 = new ConjLazy<Integer>();

    
    c1.agregar(1);
    c2.agregar(2);
    c3.agregar(3);
    c4.agregar(4);
    
    c1.interseccion(c2);  //  vacio
    c3.interseccion(c4);  // vacio
    
    c0.union(c1);// vacio
    c0.union(c3);// vacio
    
  }
  
// falla cuando no aplican las operaciones recusivamente

  @Test
  public void test1() {
    assertEquals(0, c0.tamaño());

  }
}
  
