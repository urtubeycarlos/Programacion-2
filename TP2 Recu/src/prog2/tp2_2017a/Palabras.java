package prog2.tp2_2017a;

public class Palabras implements Alfabeto<Character> {

	@Override
	public int tam() {
		return 27;
	}

	@Override
	public int indice(Character c) {
		if( c == 'ñ' )
			return tam()-1;
		if (c >= 'a' && c <= 'z')
			return c - 'a';
		throw new RuntimeException("Caracter no válido: " + c);
	}

	@Override
	public Character obtenerCaracter(int indice) {
		if( indice == tam()-1 )
			return 'ñ';
		if( indice>=0 && indice < tam() )
			return (char) (indice + 'a');
		throw new RuntimeException("Indice no válido: " + indice);
	}
	
	@Override
	public boolean equals(Object o){
		
		if( o == null )
			return false;
		
		if( !(o instanceof Palabras) )
			return false;
		
		Palabras p2 = (Palabras) o;
		if( p2.tam() != this.tam()  )
			return false;
		
		boolean acum = true;
		for(int i=0; i<this.tam(); i++)
			acum = acum && this.obtenerCaracter(i).equals( p2.obtenerCaracter(i) );
		return acum;
	}

}
