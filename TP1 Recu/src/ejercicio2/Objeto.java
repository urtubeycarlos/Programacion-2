package ejercicio2;

import java.util.HashSet;

public class Objeto {

	public final String nombre;
	private HashSet<Objeto> _leGanaA;
	private HashSet<Objeto> _similares;
	
	public Objeto(String nombre_objeto){
		nombre = nombre_objeto;
		_leGanaA = new HashSet<Objeto>();
		_similares = new HashSet<Objeto>();
	}
	
	public void leGanaA(Objeto obj){
		if( !leGanoA(obj) )
			_leGanaA.add(obj);
	}

	public void agregarParecido(Objeto obj){
		if( !existeParecido(obj) )	
			_similares.add(obj);
	}
	
	public boolean leGanoA(Objeto obj){
		for( Objeto contrincante:_leGanaA )
			if( contrincante.nombre.equals(obj.nombre) )
				return true;
		return false;
	}
	
	private boolean existeParecido(Objeto obj){
		for( Objeto similar:_similares )
			if( similar.nombre.equals(obj.nombre) )
				return true;
		return false;
	}
	
}
