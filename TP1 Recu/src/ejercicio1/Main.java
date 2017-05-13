package ejercicio1;

public class Main {

	public static void main(String[] args) {
		
		CashFlow c1 = new CashFlow();
		
		c1.agregarRegistro(new Registro(-100));
		c1.agregarRegistro(new Registro(-100));
		c1.agregarRegistro(new Registro(-100));
		c1.agregarRegistro(new Registro(-100));
		c1.agregarRegistro(new Registro(1000));
		
		System.out.println("C2 original: " + c1);
		
		c1.obtenerSaldoPositivoSwapeandoPocasVeces();
		
		System.out.println("C2 Swapeado: " + c1);

	}

}
