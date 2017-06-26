
public class ListaDoblementeEnlazada<E> {

	private Nodo<E> inicio;
	private Nodo<E> fin;

	public void agregarAlPrincipio(E e){
		
		if( estaVacia() ) {
			inicio = new Nodo<E>(e);
			fin = inicio;
		} else {
			Nodo<E> aux = new Nodo<E>(e, null, inicio);
			inicio.anterior = aux;
			inicio = aux;
		}
		
	}
	
	public void agregarAlFinal(E e){
		if( estaVacia() ){
			inicio = new Nodo<E>(e);
			fin = inicio;
		} else {
			Nodo<E> aux = new Nodo<E>(e, fin, null);
			fin.siguiente = aux;
			fin = aux;
		}
	}
	
	public boolean estaVacia(){
		return inicio == null;
	}
	
	
	@Override
	public String toString(){
		String ret = "Lista: {";
		Nodo<E> n = inicio;
		while( n != null ){
			ret += n.toString() + ", ";
			n = n.siguiente;
		}
		
		ret = ret.substring(0, ret.length()-2);
		ret = ret.concat("}");
		return ret;
	}

	private class Nodo<E> {
		E elem;
		Nodo<E> anterior;
		Nodo<E> siguiente;
		
		public Nodo(E elem){
			this.elem = elem;
		}
		
		public Nodo(E elem, Nodo<E> ant, Nodo<E> sig){
			this.elem = elem;
			this.anterior = ant;
			this.siguiente = sig;
		}
		
		@Override
		public String toString(){
			return elem.toString();
		}
	}

	public static void main(String[] args){
		ListaDoblementeEnlazada<Integer> lista = new ListaDoblementeEnlazada<Integer>();
		lista.agregarAlPrincipio(3);
		lista.agregarAlPrincipio(4);
		lista.agregarAlFinal(2);
		System.out.println(lista);
	}
	
}
