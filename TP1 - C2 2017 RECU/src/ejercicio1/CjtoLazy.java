package ejercicio1;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.LinkedList;

public class CjtoLazy<T>
{
	private Set<T> elems;
	private LinkedList<Operacion> ops;

	public CjtoLazy() 
	{
		elems = new HashSet<>();
		ops = new LinkedList<>(); //cola de operaciones***************
	}

	public void agregar(T elem) 
	{
		ops.add(new Agregar(elem));
	}

	public void union(CjtoLazy<T> cjto) 
	{
		ops.add(new Union(cjto));
	}

	public void interseccion(CjtoLazy<T> cjto) 
	{
		ops.add(new Interseccion(cjto));
	}
	
	public void resta(CjtoLazy<T> cjto) 
	{
		ops.add(new Resta(cjto));
	}
	
	
	public Iterator<T> iterador()
	{
		recalcular();
		return elems.iterator();
		
	}

	public int size()
	{
		recalcular();
		return elems.size();
	}

	public boolean pertenece(T elem)
	{
		recalcular();
		return elems.contains(elem);
	}

	//
	// Metodos privados.
	//

	private void recalcular() 
	{
		while (!ops.isEmpty()) {
			ops.pollFirst().aplicar();
		}
	}
	
	//
	// Abstraccion Operacion.
	//

	private abstract class Operacion
	{
		abstract void aplicar();
	}

	private class Agregar extends Operacion	
	{
		private T elem;
		
		Agregar(T x) {
			elem = x;
		}

		@Override
		void aplicar()
		{
			elems.add(elem);
		}
	}
	private class Resta extends Operacion
	{
		
		private CjtoLazy<T> cjto;
		public Resta(CjtoLazy<T> c)
		{
			cjto = c;
		}
		
		@Override
		public void aplicar() 
		{
			cjto.recalcular();
			elems.removeIf( elem -> cjto.elems.contains(elem) );
		}
	}

	private class Interseccion extends Operacion
	{
		private CjtoLazy<T> cjto;
		
		public Interseccion(CjtoLazy<T> c) {
			cjto = c;
		}

		@Override
		public void aplicar() 
		{
			cjto.recalcular();
			
			// java 8
			elems.removeIf(x -> !cjto.elems.contains(x));		
			
			// java 7
//			Iterator<T> elementosIT = elems.iterator();
//			T elem;
//			
//			while (elementosIT.hasNext())
//			{
//				elem = elementosIT.next();
//				
//				if (!cjto.elems.contains(elem))
//				{
//					elementosIT.remove();
//				}		
//			}
		}
	}

	private class Union extends Operacion
	{
		private CjtoLazy<T> cjto;
		
		public Union(CjtoLazy<T> c) 
		{
			cjto = c;
		}

		@Override
		public void aplicar()
		{
			cjto.recalcular();
			elems.addAll(cjto.elems);
		}
	}
	// debe usar StringBuilder y (iteradores propios o de Set)
	@Override
	public String toString() 
	{
		StringBuilder sb= new StringBuilder();
		sb.append("CjtoLazy [");

		Iterator<T> i = this.iterador();
		while( i.hasNext() )
			sb.append( i.next().toString() ).append( i.hasNext()? ", ":"");
		 //Si tengo un siguiente agrego una coma con un espacio, sino NADA.
		sb.append("]");
		
		return sb.toString();
	}
	
	
	public static void main(String[] args)
	{
		CjtoLazy<String> cjto = new CjtoLazy<>();
		cjto.agregar("a");
		cjto.agregar("b");
		cjto.size();
		System.out.println(cjto);
	}
	
}

