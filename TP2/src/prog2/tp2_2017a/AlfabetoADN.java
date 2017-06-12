package prog2.tp2_2017a;

public class AlfabetoADN implements Alfabeto<Character> {

	private enum Base{A, C, G, T};
	
	@Override
	public int tam() {
		return 4;
	}

	@Override
	public int indice(Character c) {
		return Base.valueOf( c.toString() ).ordinal();
	}

	@Override
	public Character obtenerCaracter(int indice) {
		if( indice > tam() || indice < 0 )
			throw new IllegalArgumentException("Indice fuera de rango!. indice = " + indice);
		return Base.values()[indice].name().charAt(0);
	}

	@Override
	public boolean equals(Object o){
		
		if( o == null )
			return false;
		if( !(o instanceof AlfabetoADN) )
			return false;
		
		AlfabetoADN a2 = (AlfabetoADN) o;
		
		if( this.tam() != a2.tam() )
			return false;
		boolean acum = true;
		for(int i=0; i<this.tam(); i++)
			acum = acum && this.obtenerCaracter(i).equals( a2.obtenerCaracter(i) );
		return acum;
	}
	
}
