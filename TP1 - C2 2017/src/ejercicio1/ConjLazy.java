package ejercicio1;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class ConjLazy<T> {
	
	private enum Operacion { UNION, INTERSECCION, AGREGAR };
	private HashSet<T> _elems;
	private Queue<ConjLazy<T>> _tareasPendientes;
	private Queue<Operacion> _tiposTareas;
	
	public ConjLazy(){
		// ".add( elem )" en LinkedList tiene complejidad O(1)
		_elems = new HashSet<T>();
		_tareasPendientes = new LinkedList<ConjLazy<T>>();
		_tiposTareas = new LinkedList<Operacion>();
	}
	
	public ConjLazy(HashSet<T> elemsIniciales){
		_elems = elemsIniciales;
		_tareasPendientes = new LinkedList<ConjLazy<T>>();
		_tiposTareas = new LinkedList<Operacion>();
	}
	
	public void agregar(T elem){
		if( _tareasPendientes.isEmpty() )
			_elems.add(elem);
		else {
			HashSet<T> aux = new HashSet<T>();
			aux.add(elem);
			_tareasPendientes.add( new ConjLazy<T>(aux) );
			_tiposTareas.add(Operacion.AGREGAR);
		}
	}
	
	public void union(ConjLazy<T> conj){
		_tareasPendientes.add(conj);
		_tiposTareas.add(Operacion.UNION);
	}
	
	public void interseccion(ConjLazy<T> conj){
		_tareasPendientes.add(conj);
		_tiposTareas.add(Operacion.INTERSECCION);
	}
	
	public int tamaño(){
		consolidarDatos();
		return _elems.size();
	}
	
	public boolean pertenece(T elem){
		consolidarDatos();
		return _elems.contains(elem);
	}
	
	public HashSet<T> getConjunto(){
		//Devuelve una copia de los elementos de nuestro conjunto. 
		//Es una buena practica para no modificar por error un conjunto que no queremos modificar.
		return new HashSet<T>(_elems);
	}
	
	//TODO: ¿Como establecemos prioridad entre varias uniones e intersecciones a la vez sin joder la complejidad? 
	private void consolidarDatos(){
		
		ConjLazy<T> proximaTarea = _tareasPendientes.poll();
		Operacion proximaTipoOperacion = _tiposTareas.poll();
		
		while( proximaTarea != null ) {
			
			proximaTarea.tamaño();
			
			if( proximaTipoOperacion == Operacion.INTERSECCION )
				_elems.retainAll( proximaTarea.getConjunto() );
			else
				_elems.addAll( proximaTarea.getConjunto() );
			
			proximaTarea = _tareasPendientes.poll();
			proximaTipoOperacion = _tiposTareas.poll();
		
		}
		
	}

	@Override
	public String toString(){
		return _elems.toString();
	}
}
