package prog2.tp2_2017a;

/**
 * Alfabeto de ejemplo.
 */
public class Digitos implements Alfabeto<Character>
{
	@Override
	public int tam() { return 10; }

	@Override
	public int indice(Character c) {
		// Se implementa bas�ndose en el valor ASCII de char.
		// Los caracteres '0' a '9' son adyacentes en la tabla
		// ASCII, por lo que sus valores son contiguos.
		if (c >= '0' && c <= '9')
			return c - '0';

		throw new RuntimeException("digito no v�lido: " + c);
	}
	

	@Override
	public Character obtenerCaracter(int indice) {
		if( indice>=0 && indice < tam() )
			return (char) (indice + '0');
		throw new RuntimeException("indice no v�lido: " + indice);
		
	}
	
	@Override
	public boolean equals(Object o){ 
		
		if( o == null )
			return false;
		if( !(o instanceof Digitos) )
			return false;
		
		Digitos d2 = (Digitos) o;
		if( d2.tam() != this.tam()  )
			return false;
		
		boolean acum = true;
		for(int i=0; i<this.tam(); i++)
			acum = acum && this.obtenerCaracter(i).equals( d2.obtenerCaracter(i) );
		return acum;
	}

}
