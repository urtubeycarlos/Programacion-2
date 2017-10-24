package ejercicio2;

public class Tupla {

	final int x;
	final int y;
	
	Tupla(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString(){
		return "(" + x + ", " + y + ")";
	}
	
}
