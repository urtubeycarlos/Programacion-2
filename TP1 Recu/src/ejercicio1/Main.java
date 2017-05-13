package ejercicio1;

public class Main {

	public static void main(String[] args) {

//		CashFlow c1 = new CashFlow();
//		
//		c1.agregarRegistro(new Registro(-100));
//		c1.agregarRegistro(new Registro(100));
//		c1.agregarRegistro(new Registro(-100));
//		c1.agregarRegistro(new Registro(100));
//		
//		System.out.println("C1 original: " + c1);
//		
//		c1.obtenerSaldoPositivoSwapeandoPocasVeces();
//		
//		System.out.println("C1 Swapeado: " + c1);
		
		
		
		
		CashFlow c2 = new CashFlow();
		
		c2.agregarRegistro(new Registro(-100));
		c2.agregarRegistro(new Registro(-100));
		c2.agregarRegistro(new Registro(-100));
		c2.agregarRegistro(new Registro(-100));
		c2.agregarRegistro(new Registro(1000));
		
		System.out.println("C2 original: " + c2);
		
		c2.obtenerSaldoPositivoSwapeandoPocasVeces();
		
		System.out.println("C2 Swapeado: " + c2);

	}

}
