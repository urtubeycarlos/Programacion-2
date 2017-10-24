package ejercicio2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class JuegoSillas {
	
	private Random _random;
	private Salon _salon;
	private int _cant_personas_max, _cant_sillas, _cant_personas_min;
	
	JuegoSillas(int sillas){
		
		_cant_sillas = sillas;
		_cant_personas_min = sillas+1;
		_cant_personas_max = sillas*2;
		_random = new Random();
		_salon = new Salon(_cant_sillas);
		
	}
	
	public void asignarPersonas(){
		
		int cant_personas = _random.nextInt(this._cant_personas_max-this._cant_personas_min-1)+this._cant_personas_min;
		for( int i=0; i<cant_personas; i++ )
			_salon.setPersona(i, new Persona(i)); 
		
	}
	
	public void jugar(){
		if( _salon.cantPersonas() > 1 ){
			reubicarPersonas();
			limpiarPerdedores();
			_salon.quitarSilla();
		}
	}
	
	public int cantSillasActivas(){
		if( _salon.cantPersonas() <= 1 )
			return 0;
		return _salon.sillasRestantes();
	}

	private void reubicarPersonas(){
		
		List<Persona> ganadores = _salon.getPersonas();
		_salon.reacomodarSalon();
		
		Collections.shuffle(ganadores);
		Persona[] personas = new Persona[ _salon.sillasRestantes()*2 ];
		
		for( int i=0; i<ganadores.size(); i++ )
			personas[i] = ganadores.get(i);
	
		List<Persona> aux = Arrays.asList(personas); //Convierto arreglo a lista
		Collections.shuffle( aux ); //Lo mezclo
		personas = (Persona[]) aux.toArray(); //Convierto de nuevo a arreglo
	
		for( int i=0; i<personas.length; i++ )
			_salon.setPersona(i, personas[i]);

	}
	
	private void limpiarPerdedores(){
		
		List<Integer> posiciones_ocupadas = _salon.getPosicionesOcupadas();
		for( Integer posicion:posiciones_ocupadas )
			if( posicion%2 != 0 )
				_salon.setPersona(posicion, null);
		
		while( _salon.cantPersonas() > _salon.sillasRestantes() ){
			
			posiciones_ocupadas = _salon.getPosicionesOcupadas();
			Collections.shuffle(posiciones_ocupadas);
			_salon.setPersona(posiciones_ocupadas.get(0), null);
			
		}
		
	}
	
	
	@Override
	public String toString(){
		if( _salon.cantPersonas() == 1 )
			return "Ganador: " + _salon.getPersonas();
		if( _salon.sillasRestantes()>=1 && _salon.cantPersonas()==0 )
			return "No hay ganador";
		return _salon.toString();
	}
	
}
