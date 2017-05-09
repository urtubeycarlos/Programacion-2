package ejercicio1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class PruebaSwapeo {

	
	public static void main(String[] args){
	
		Integer[] numeros = new Integer[]{1, 2, 3, 4};
		
		ArrayList<Integer> lista = new ArrayList<Integer>();
		lista.addAll( Arrays.asList(numeros) );
		
		Collections.swap(lista, 1, 2);
		
		//Lo de arriba es equivalente a esto:
//		Integer elem = lista.remove(2);
//		lista.add( lista.indexOf(2), elem );
		
		System.out.println(lista);
	}
	
}
