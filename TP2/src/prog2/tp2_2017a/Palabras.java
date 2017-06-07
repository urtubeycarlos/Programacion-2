package prog2.tp2_2017a;

public class Palabras implements Alfabeto<Character> {

	@Override
	public int tam() {
		return 27;
	}

	@Override
	public int indice(Character c) {
		//FIXME: Agregar el caso de la ñ.
		if( c == 'ñ' )
			return tam()-1;
		if (c >= 'a' && c <= 'z')
			return c - 'a';
		throw new RuntimeException("Caracter no válido: " + c);
	}

}
