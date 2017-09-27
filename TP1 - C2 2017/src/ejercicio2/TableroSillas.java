package ejercicio2;

import java.util.LinkedList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class TableroSillas {

	private Jugador[] _tablero;
	private List<Jugador> auxiliar;
	private int sillas_restantes;
	
	public TableroSillas(int tam_tablero, int cant_sillas){
		
		_tablero = new Jugador[tam_tablero];
		sillas_restantes = cant_sillas;
		
	}
	
	public void asignarJugadores(int cant_jugadores){
		
		for(int i=0; i<cant_jugadores; i++)
			_tablero[i] = new Jugador(i);
		
		auxiliar = Arrays.asList(_tablero);
		Collections.shuffle(auxiliar);
		_tablero = (Jugador[]) auxiliar.toArray();
		
	}
	
	public void quitarSilla(){
		sillas_restantes--;
	}
	
	public int sillasRestantes(){
		return sillas_restantes;
	}
	
	public int jugadoresRestantes(){
		return getJugadoresRestantes().size();
	}
	
	public LinkedList<Jugador> getJugadoresRestantes(){
		LinkedList<Jugador> ret = new LinkedList<Jugador>();
		for(Jugador j:_tablero) if( j != null )
			ret.add(j);
		return ret;
	}
	
	public void reubicarJugadores(){

		auxiliar = Arrays.asList(_tablero);
		Collections.shuffle(auxiliar);
		_tablero = (Jugador[]) auxiliar.toArray();
		Arrays.fill(_tablero, _tablero.length-6, _tablero.length, null);

		int cont = 0;
		for( int i=0; i<_tablero.length; i++ ){
			if( _tablero[i] != null )
				cont++;
			if( cont > sillasRestantes() )
				_tablero[i] = null;
				
		}
		
	}
	
}
