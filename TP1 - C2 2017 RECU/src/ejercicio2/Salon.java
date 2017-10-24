package ejercicio2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Salon {
	
	private Integer _cantSillas;
	private Persona[] _posiciones;
	
	Salon(int cantSillas){
		_cantSillas = cantSillas;
		_posiciones = new Persona[cantSillas*2];
	}
	
	public void setPersona(int i, Persona p){
		_posiciones[i] = p;
	}
	
	public List<Persona> getPersonas(){
		List<Persona> ret = new LinkedList<Persona>();
		for( Persona p:_posiciones )
			if( p != null )
				ret.add(p);
		return ret;
	}
	
	public int cantPersonas(){
		return getPersonas().size();
	}
	
	public int sillasRestantes(){
		return _cantSillas;
	}
	
	public void quitarSilla(){
		
		if( sillasRestantes() == 0 )
			throw new RuntimeException("No se puede quitar mas sillas. cant sillas = 0");
		_cantSillas--;
		
	}
	
	public void reacomodarSalon(){
		_posiciones = new Persona[ sillasRestantes()*2 ];
	}
	
	public List<Integer> getPosicionesOcupadas(){

		List<Integer> ret = new LinkedList<Integer>(); 
		for (int i = 0; i < _posiciones.length; i++)
			if( _posiciones[i] != null )
				ret.add(i);
		return ret;
	}
	
	@Override
	public String toString(){
		return Arrays.toString(_posiciones);
	}
	
//	@Override
//	public String toString(){
//		
//		StringBuilder sb = new StringBuilder();
//		sb.append( "Tablero: { \n" );
//
//		String[][] stringTablero = new String[ _posiciones.length ][ _posiciones[0].length ];
//		
//		for( int i=0; i<_posiciones.length; i++ ) {
//			for( int j=0; j<_posiciones[i].length; j++ ){
//
//				if( _posiciones[i][j] == null ){
//					if( i == 1 && j > 0 && j < _posiciones[i].length-1 )
//						stringTablero[i][j] = "S";
//					else 
//						stringTablero[i][j] = "P";
//				} else {
//					stringTablero[i][j] = _posiciones[i][j].toString();
//				}
//			}
//		}
//				
//		for( String[] linea:stringTablero )
//			sb.append("\t").append( Arrays.toString( linea ) ).append("\n");
//		sb.append( "};" );
//		return sb.toString();
//		
//	}
	
	
}