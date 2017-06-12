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
		return (char) (indice + 'a');
	}
	
	@Override
	public boolean equals(Object o){
		Palabras p2 = (Palabras) o;
		if( p2.tam() != this.tam()  )
			return false;
		boolean acum = true;
		for(int i=0; i<this.tam(); i++)
			acum &= this.obtenerCaracter(i).equals( p2.obtenerCaracter(i) );
		return acum;
	}

}
