package ejercicio2;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class JuegoSillas {
	
	private Random random;
	private Tablero _tablero;
	private int cant_personas_Max, cant_sillas, cant_personas_min;
	
	JuegoSillas(int sillas){
		
		this.cant_sillas = sillas;
		this.cant_personas_min = sillas;
		this.cant_personas_Max = sillas*2+6;
		random = new Random();
		this._tablero = new Tablero(this.cant_sillas);
		
	}
	
	public void asignarPersonas(){
		
		int personas = random.nextInt(this.cant_personas_Max-this.cant_personas_min-1)+this.cant_personas_min;
		LinkedList<Tupla> posiblesPosiciones = generarPosiblesPosiciones();
		Collections.shuffle( posiblesPosiciones );
		
		for( int i=0; i<personas; i++ ){
			Tupla aux = posiblesPosiciones.get(i);
			_tablero.setPersona( aux.x , aux.y, new Persona(i) );
		}
		
	}
	
	public void jugar(){
		if( _tablero.cantPersonas() > 1 ){
			limpiarPerdedores();
			reubicarPersonas();
		}
	}
	
	public int cantSillasActivas(){
		if( _tablero.cantPersonas() <= 1 )
			return 0;
		return _tablero.sillasRestantes();
	}
	
	private void limpiarPerdedores(){
		
		for( int i=0; i<3; i++ ){
			_tablero.setPersona(i, 0, null);
			_tablero.setPersona(i, _tablero.sillasRestantes()+1, null);
		}
		
		LinkedList<Tupla> posiciones_ocupadas = _tablero.getPosicionesOcupadas();
		Collections.shuffle( posiciones_ocupadas );
		
		int i = 0;
		while( _tablero.cantPersonas() > _tablero.sillasRestantes() ){
			Tupla aux = posiciones_ocupadas.get(i++);
			_tablero.setPersona(aux.x, aux.y, null);
		}
		
	}
	
	private void reubicarPersonas(){
		
		LinkedList<Persona> ganadores = _tablero.getPersonas();
		
		_tablero.quitarSilla();
		
		LinkedList<Tupla> posiblesPosiciones = generarPosiblesPosiciones();
		Collections.shuffle( posiblesPosiciones );

		for( int i=0; i<ganadores.size(); i++ ){
			Tupla pos = posiblesPosiciones.get(i);
			_tablero.setPersona(pos.x, pos.y, ganadores.get(i));
		}
		
	}
	
	private LinkedList<Tupla> generarPosiblesPosiciones(){
		
		LinkedList<Tupla> ret = new LinkedList<Tupla>();
		for( int i=0; i<3; i++)
		for( int j=0; j<_tablero.sillasRestantes()+2; j++)
			ret.add( new Tupla(i, j) );
		ret.removeIf( t -> (t.x == 1 && t.y > 0 && t.y<_tablero.sillasRestantes()+1) );
		return ret;
		
	}
	
	@Override
	public String toString(){
		
		if( _tablero.cantPersonas() == 1 )
			return "Ganador: " + _tablero.getPersonas();
		if( _tablero.sillasRestantes() >=1 && _tablero.cantPersonas() == 0 )
			return "No hay ganador";
		return _tablero.toString();
		
	}
	
}
