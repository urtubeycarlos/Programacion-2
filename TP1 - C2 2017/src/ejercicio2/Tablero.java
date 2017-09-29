package ejercicio2;

import java.util.Arrays;
import java.util.LinkedList;

public class Tablero {
	
	private Persona[][] _matrizPosiciones;
	
	Tablero(int cantSillas){
		this._matrizPosiciones = new Persona[3][cantSillas+2];
		for( Persona[] fila:_matrizPosiciones )//
			Arrays.fill(fila, null);
	}
	
	public void setPersona(int x, int y, Persona p){
		_matrizPosiciones[x][y] = p;
	}
	
	public LinkedList<Persona> getPersonas(){
		LinkedList<Persona> ret = new LinkedList<Persona>();
		for( Persona[] fila:_matrizPosiciones )
		for( Persona p:fila )
			if( p != null )
				ret.add(p);
		return ret;
	}
	
	public int cantPersonas(){
		return getPersonas().size();
	}
	
	public int sillasRestantes(){
		return _matrizPosiciones[0].length - 2;
	}
	
	public void quitarSilla(){
		
		if( sillasRestantes() == 0 )
			throw new RuntimeException("No se puede quitar mas sillas. cant sillas = 0");
		
		Persona[][] nuevaMatriz = new Persona[3][ _matrizPosiciones[0].length-1 ];
		for( Persona[] fila:nuevaMatriz )
			Arrays.fill(fila, null);
		_matrizPosiciones = nuevaMatriz;
		
	}
	
	public LinkedList<Tupla> getPosicionesOcupadas(){

		LinkedList<Tupla> ret = new LinkedList<Tupla>();
		for( int i=0; i<_matrizPosiciones.length; i++ )
		for( int j=0; j<_matrizPosiciones[i].length; j++ )
			if( _matrizPosiciones[i][j] != null )
				ret.add( new Tupla(i, j) );
		return ret;
		
	}
	
	@Override
	public String toString(){
		
		StringBuilder sb = new StringBuilder();
		sb.append( "Tablero: { \n" );

		String[][] stringTablero = new String[ _matrizPosiciones.length ][ _matrizPosiciones[0].length ];
		
		for( int i=0; i<_matrizPosiciones.length; i++ ) {
			for( int j=0; j<_matrizPosiciones[i].length; j++ ){

				if( _matrizPosiciones[i][j] == null ){
					if( i == 1 && j > 0 && j < _matrizPosiciones[i].length-1 )
						stringTablero[i][j] = "S";
					else 
						stringTablero[i][j] = "P";
				} else {
					stringTablero[i][j] = _matrizPosiciones[i][j].toString();
				}
			}
		}
				
		for( String[] linea:stringTablero )
			sb.append("\t").append( Arrays.toString( linea ) ).append("\n");
		sb.append( "};" );
		return sb.toString();
		
	}
	
}