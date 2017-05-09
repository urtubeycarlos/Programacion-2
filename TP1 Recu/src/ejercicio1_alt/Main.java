package ejercicio1_alt;

public class Main {

	public static void main(String[] args) {
		
		CashFlow c1 = new CashFlow();
		
		c1.agregarRegistro(new Registro(-100));
		c1.agregarRegistro(new Registro(100));
		c1.agregarRegistro(new Registro(100));
		c1.agregarRegistro(new Registro(100));
		
		System.out.println("C1 original: " + c1);
		
		c1.minimizarDistanciaAcumulada();
		
		System.out.println("CashFlow mas optimo: " + c1);
				
	}

}
