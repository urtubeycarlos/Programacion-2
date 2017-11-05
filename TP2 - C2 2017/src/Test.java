import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import org.junit.Before;

public class Test {

	private Dicc1<Integer, String> dicc1IS1, dicc1IS2, dicc1IS3;
	private Dicc2<Integer, String> dicc2IS1, dicc2IS2, dicc2IS3;	
	private HashSet <Integer> clavesPosta;

	@Before
	public void setUp() {
		dicc1IS1 = new Dicc1<Integer, String>();
		dicc1IS2 = new Dicc1<Integer, String>();
		dicc1IS3 = new Dicc1<Integer, String>();
		dicc2IS1 = new Dicc2<Integer, String>();
		dicc2IS2 = new Dicc2<Integer, String>();
		dicc2IS3 = new Dicc2<Integer, String>();
		
		
		dicc1IS1.definir(2, "Esto es un dos");
		dicc1IS1.definir(4, "Esto es un cuatro");
		dicc1IS1.definir(1, "Esto es un uno"); 

		dicc2IS1.definir(1, "Esto es un uno"); 
		dicc2IS1.definir(2, "Esto es un dos");
		dicc2IS1.definir(4, "Esto es un cuatro");
		
		//igual que dicc1IS1 pero en otro orden 
		dicc1IS2.definir(2, "Esto es un dos");
		dicc1IS2.definir(1, "Esto es un uno"); 
		dicc1IS2.definir(4, "Esto es un cuatro");

		//igual que dicc2IS2 pero en otro orden 
		dicc2IS2.definir(1, "Esto es un uno"); 
		dicc2IS2.definir(4, "Esto es un cuatro");
		dicc2IS2.definir(2, "Esto es un dos");

		//Diccionario de control para el eliminar, sin el 4 
		dicc1IS3.definir(1, "Esto es un uno"); 
		dicc1IS3.definir(2, "Esto es un dos");		
		//Diccionario de control para el eliminar, sin el 4 
		dicc2IS3.definir(2, "Esto es un dos");
		dicc2IS3.definir(1, "Esto es un uno"); 		
		
		//set con las claves que definimos, para testear que pertenezcan a los diccionarios
		clavesPosta = new HashSet<Integer>();
		clavesPosta.add(1);
		clavesPosta.add(2);
		clavesPosta.add(4);
		
	}
	
	@org.junit.Test
	public void testDiccObtener() {
		assertEquals("Esto es un dos", dicc1IS1.obtener(2));
		assertEquals("Esto es un dos", dicc1IS2.obtener(2));
		assertEquals("Esto es un cuatro",dicc2IS2.obtener(4));
		assertEquals("Esto es un uno", dicc2IS2.obtener(1));
	}
	
	@org.junit.Test
	public void testDiccDefinir() {//Para ver que pise los datos
		dicc1IS1.definir(2, "Esto es un NUEVO dos");
		dicc2IS1.definir(4, "Esto es un NUEVO cuatro");
		assertEquals("Esto es un NUEVO dos", dicc1IS1.obtener(2));
		assertEquals("Esto es un NUEVO cuatro",dicc2IS1.obtener(4));
	}
	
	@org.junit.Test
	public void testDiccPertenece() {
		assertTrue(dicc1IS1.pertenece(2));
		assertTrue(dicc1IS2.pertenece(4));
		assertFalse(dicc1IS1.pertenece(6));
		assertFalse(dicc1IS2.pertenece(6));
		assertTrue(dicc2IS1.pertenece(2));
		assertTrue(dicc2IS2.pertenece(4));
		assertFalse(dicc2IS1.pertenece(6));
		assertFalse(dicc2IS2.pertenece(6));
	}
	
	//veamos que los iteradores no manden fruta:
	// - Que devuelvan todas las claves
	// - Que lo hagan una sola vez
	@org.junit.Test
	public void testDiccIterator() {
		Iterator<Integer> itDicc1IS1 = dicc1IS1.iterator();
		Iterator<Integer> itDicc2IS1 = dicc2IS1.iterator();

		//voy a usar arraylist para ver si tienen claves repetidas o alguna cosa asi
		ArrayList<Integer> claves1 = new ArrayList<Integer>();
		ArrayList<Integer> claves2 = new ArrayList<Integer>();

		while(itDicc1IS1.hasNext()){
			claves1.add(itDicc1IS1.next());
		}
		while(itDicc2IS1.hasNext()){
			claves2.add(itDicc2IS1.next());
		}
		
		//tienen que tener 1,2,4, y visceversa
		assertTrue(clavesPosta.containsAll(claves1));
		assertTrue(claves1.containsAll(clavesPosta));
		assertTrue(clavesPosta.containsAll(claves2));		
		assertTrue(claves2.containsAll(clavesPosta));		

		
		//finalmente veamos que no tienen repetidos
		HashSet <Integer> claves1Set = new HashSet<Integer>(claves1);
		HashSet <Integer> claves2Set = new HashSet<Integer>(claves2);

		assertTrue(claves1Set.size()==claves1.size());
		assertTrue(claves2Set.size()==claves2.size());			
	}
	
	@org.junit.Test
	/*
	 * Miramos los equals, entre las diferentes implementaciones y en las mismas.
	 * Buscando que esté bien la abstracción y que no estén confiando en el orden de la estructura
	 * de representación interna
	 */
	public void testDiccEquals() {
		assertTrue(dicc1IS1.equals(dicc1IS2)); 
		assertTrue(dicc2IS1.equals(dicc2IS2)); 
	}
	
	
	//igualdad observacional entre dos diccionarios de diferentes implementaciones
	//dos diccionarios son iguales sii:
	// - Tienen las mismas claves
	// - Para cada clave la definicion es la misma
	@org.junit.Test
	public void testDiccEqualsObs() {
		//para buscar 
		HashSet <Integer> claves1 = new HashSet<Integer>();
		HashSet <Integer> claves2 = new HashSet<Integer>();
		
		Iterator<Integer> itDicc1IS1 = dicc1IS1.iterator();
		Iterator<Integer> itDicc2IS1 = dicc2IS1.iterator();

		while(itDicc1IS1.hasNext()){
			claves1.add(itDicc1IS1.next());
		}
		while(itDicc2IS1.hasNext()){
			claves2.add(itDicc2IS1.next());
		}
		//claves iguales
		assertTrue(claves1.equals(claves2)); 

		//mismas definiciones
		boolean res = true;
		for(Integer clave : claves1){
			res = res && dicc1IS1.obtener(clave).equals(dicc2IS1.obtener(clave)); 
		}
		
		assertTrue(res); 		

	}
	
	@org.junit.Test
	public void testDiccEliminar() {
		dicc1IS1.eliminar(4);
		dicc2IS1.eliminar(4);

		assertTrue(dicc1IS1.equals(dicc1IS3)); 
		assertTrue(dicc2IS1.equals(dicc2IS3)); 

	}

}
