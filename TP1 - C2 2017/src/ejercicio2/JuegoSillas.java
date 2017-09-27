package ejercicio2;

import java.util.LinkedList;
import java.util.Random;

public class JuegoSillas {

	private Random _random;
	private TableroSillas _tablero;
	private int cant_sillas, cant_max_jugadores, cant_min_jugadores, cant_jugadores;
	
	public JuegoSillas(int cant_sillas){
		
		this.cant_sillas = cant_sillas;
		this.cant_min_jugadores = this.cant_sillas+1;
		this.cant_max_jugadores = this.cant_sillas*2 + 6;
		_random = new Random();
		_tablero = new TableroSillas(cant_max_jugadores, this.cant_sillas);
		
	}
	
	public void asignarPersonas(){
		
		cant_jugadores = _random.nextInt(cant_max_jugadores - (cant_min_jugadores -1) ) + cant_min_jugadores;
		_tablero.asignarJugadores(cant_jugadores);
		
	}
	
	public void jugar(){
		
		_tablero.reubicarJugadores();
		_tablero.quitarSilla();
		
	}

	public int cantidadSillasActivas() {
		return _tablero.sillasRestantes();
	}
	
	public LinkedList<Jugador> getGanadores(){
		return _tablero.getJugadoresRestantes();
	}
	
	public String toString(){
		return "Estado ultimo turno: { Sillas en el turno: " + (_tablero.sillasRestantes() + 1) + "; Jugadores que consiguieron silla: " + _tablero.getJugadoresRestantes() + "} ";
	}
	
}
