package ex_ejercicio1;
import java.util.LinkedList;

public class ConjLazy<T> {
	
	private LinkedList<T> _conjunto;
	private LinkedList<T> _addsPendientes;
	private LinkedList<ConjLazy<T>> _unionesPendientes;
	private LinkedList<ConjLazy<T>> _interseccionesPendientes;
	
	public ConjLazy(){
		// ".add( elem )" en LinkedList tiene complejidad O(1)
		_conjunto = new LinkedList<T>();
		_addsPendientes = new LinkedList<T>();
		_unionesPendientes = new LinkedList<ConjLazy<T>>();
		_interseccionesPendientes = new LinkedList<ConjLazy<T>>();
	}
	
	public void agregar(T elem){
		if( hayTareasPendientes() ) //Si hay pendiente alguna intersec o union, entonces agrega en el elemento a una cola de espera.
			_addsPendientes.add(elem);
		else
			_conjunto.add(elem); //Caso contrario lo agrega directamente.
	}
	
	private boolean hayTareasPendientes(){
		// ".isEmpty()" en LinkedList tiene complejidad O(1). Porque, if ( head == null ) return true;
		return !_unionesPendientes.isEmpty() || !_interseccionesPendientes.isEmpty();
	}
	
	public void union(ConjLazy<T> conj){
		_unionesPendientes.add(conj);
	}
	
	public void interseccion(ConjLazy<T> conj){
		_interseccionesPendientes.add(conj);
	}
	
	public int tamaño(){
		consolidarDatos();
		return _conjunto.size();
	}
	
	public boolean pertenece(T elem){
		consolidarDatos();
		return _conjunto.contains(elem);
	}
	
	@SuppressWarnings("unchecked")
	public LinkedList<T> getConjunto(){
		//Devuelve una copia de los elementos de nuestro conjunto. 
		//Es una buena practica para no modificar por error un conjunto que no queremos modificar.
		return (LinkedList<T>) _conjunto.clone();
	}
	
	//TODO: ¿Como establecemos prioridad entre varias uniones e intersecciones a la vez sin joder la complejidad? 
	private void consolidarDatos(){
		
		//Crea un auxiliar _conjuntoNuevo con el que vamos a trabajar para consolidar datos y luego cambiar los punteros.
		LinkedList<T> _conjuntoNuevo = new LinkedList<T>();
		_conjuntoNuevo.addAll(_conjunto); //Agrega al auxiliar todos los elementos que ya tenemos.

		//Si hay conjuntos para unir entonces hace la union uno por uno.
		if( !_unionesPendientes.isEmpty() ){
			
			_unionesPendientes.forEach( conj -> {
				conj.tamaño(); //Llamamos a ".tamaño()" del conjunto que queremos unir por si tiene tareas pendientes.
				_conjuntoNuevo.addAll( conj.getConjunto() );
			});
			
			_unionesPendientes.clear();
			
		}
		
		//Si hay conjuntos para intersecar entonces hace la interseccion uno por uno.
		if( !_interseccionesPendientes.isEmpty() ){
			
			_interseccionesPendientes.forEach( conj -> {
				conj.tamaño(); //Llamamos a ".tamaño()" del conjunto que queremos intersecar por si tiene tareas pendientes.
				_conjuntoNuevo.retainAll( conj.getConjunto() );
			});
			
			_interseccionesPendientes.clear();
			
		}
		
		//Agregamos a nuestro conjuntos todos los elementos que estaban en cola y luego vaciamos la cola.
		_conjuntoNuevo.addAll(_addsPendientes);
		_addsPendientes.clear();
		
		//Elimina repetidos.
		_conjuntoNuevo.removeIf( elem -> _conjuntoNuevo.indexOf(elem) - _conjuntoNuevo.lastIndexOf(elem) != 0 );
		
		//	".removeIf( condicion )" quita un elemento de la lista si se cumple la condición pasada como parametro. 
		//
		//					^
		//					|
		//
		//Si obtenemos el primer y el ultimo indice donde aparece un elem y la resta da 0 entonces quiere decir que encontramos
		//el mismo indice, porque i - i = 0, por lo tanto el elemento no se repite. Caso contrario la resta da distinto de 0
		//y eso quiere decir que el elemento se repite. De pasar esto ultimo lo quitamos de la lista.

		_conjunto = _conjuntoNuevo;	//Acomodamos los punteros.
		
	}

	@Override
	public String toString(){
		return _conjunto.toString();
	}
}
