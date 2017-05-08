package ejercicio1_alt;

import java.util.ArrayList;
import java.util.Arrays;

public class AlgoritmoPermutaciones {

	public static int factorial(int n){
		if( n<=1 ){
			return 1;
		}
		return n * factorial(n-1);
	}
	
	
	public static ArrayList<ArrayList<Integer>> permutacionesSinRepeticiones(ArrayList<Integer> in){

		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		res.add(new ArrayList<Integer>());
		
		for(int i=0; i<in.size(); i++){
			
			ArrayList<ArrayList<Integer>> actual = new ArrayList<ArrayList<Integer>>();
			
			for(ArrayList<Integer> permutacion:res){
				
				for(int j=0; j<permutacion.size()+1; j++){
					permutacion.add(j, in.get(i));
					ArrayList<Integer> temp = new ArrayList<Integer>(permutacion);
					actual.add(temp);
					permutacion.remove(j);
				}

				res = new ArrayList<ArrayList<Integer>>(actual);
				
			}
			
		}
		
		
		return res;
		
	}
	
	
	public static void main(String[] args){
		
		Integer[] numeros = new Integer[]{1, 2, 3, 4};
		ArrayList<Integer> lista = new ArrayList<Integer>();
		lista.addAll( Arrays.asList(numeros) );
		System.out.println(lista);

		ArrayList<ArrayList<Integer>> res = permutacionesSinRepeticiones(lista);
		System.out.println("Tamanio res: " + res.size() + "  ;  Factorial lista.size() : " + factorial(lista.size()) );
		System.out.println(res);
	}
	
}
