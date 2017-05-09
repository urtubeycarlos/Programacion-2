package ejercicio2;

import java.util.HashSet;

public class Objeto {

	public final String nombre;
	private HashSet<Objeto> _leGanaA;
	private HashSet<String> _parecidos;
	
	public Objeto(String nombre_objeto){
		nombre = nombre_objeto;
		_leGanaA = new HashSet<Objeto>();
		_parecidos = new HashSet<String>();
	}
	
	public void leGanaA(Objeto obj){
		if( !leGanoA(obj) )
			_leGanaA.add(obj);
	}

	
	public boolean leGanoA(Objeto obj){
		for( Objeto contrincante:_leGanaA )
			if( contrincante.nombre.equals(obj.nombre) )
				return true;
		return false;
	}
	
	public void agregarParecido(String obj_parecido){
		_parecidos.add(obj_parecido);
	}

	public boolean esParecido(String obj){
		return _parecidos.contains(obj);
	}
	
}
