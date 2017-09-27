package ejercicio1;
import java.util.LinkedList;

public class ConjLazy<T> {
	
	private LinkedList<T> _conjunto;	
	private LinkedList<ConjLazy<T>> _auxUnion;
	private LinkedList<ConjLazy<T>> _auxIntersec;
	
	public ConjLazy(){
		_conjunto = new LinkedList<T>();
		_auxUnion = new LinkedList<ConjLazy<T>>();
		_auxIntersec = new LinkedList<ConjLazy<T>>();
	}
	
	public void agregar(T elem){
		_conjunto.add(elem);
	}
	
	public void union(ConjLazy<T> conj){
		_auxUnion.add(conj);
	}
	
	public void interseccion(ConjLazy<T> conj){
		_auxIntersec.add(conj);
	}
	
	public int tamaño(){
		consolidarDatos();
		return _conjunto.size();
	}
	
	public boolean pertenece(T elem){
		consolidarDatos();
		return _conjunto.contains(elem);
	}
	
	public LinkedList<T> getConjunto(){
		return _conjunto;
	}
	
	private void consolidarDatos(){
		
		LinkedList<T> _conjuntoNuevo = new LinkedList<T>();

		//Elimina los repetidos
		_conjunto.forEach( elem -> {
			if( !_conjuntoNuevo.contains(elem) )
				_conjuntoNuevo.add(elem);
		});

		//Si hay conjuntos para unir entonces los recorre y une todos, y 
		//finalmente los une con el conjunto que ya tenemos.
		if( !_auxUnion.isEmpty() ){
			
			LinkedList<T> _conjuntoUnion = new LinkedList<T>();
			_auxUnion.forEach( conj -> {
				_conjuntoUnion.addAll( conj.getConjunto() );
			});
			
			_conjuntoUnion.forEach( elem -> {
				if( !_conjuntoNuevo.contains(elem) )
					_conjuntoNuevo.add(elem);
			});
			
		}

		
		//Si hay conjuntos para intersecar entonces los recorre y une todos, y 
		//finalmente hace interseccion con el conjunto que ya tenemos.
		if( !_auxIntersec.isEmpty() ){
			
			LinkedList<T> _conjInterseccion = new LinkedList<T>();
			_auxIntersec.forEach( conj -> {
				_conjInterseccion.addAll( conj.getConjunto() );
			});
			
			_conjuntoNuevo.retainAll(_conjInterseccion );
			
		}
		
		_conjunto = _conjuntoNuevo;
		
	}
}
