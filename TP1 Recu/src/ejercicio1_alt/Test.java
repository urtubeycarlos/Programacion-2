package ejercicio1_alt;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CashFlow c1 = new CashFlow();
		
	
		c1.agregarRegistro(new Registro(-100));
		c1.agregarRegistro(new Registro(100));
		c1.agregarRegistro(new Registro(100));
		c1.agregarRegistro(new Registro(100));
		
		//c1.forzarInvariante();
		System.out.println("C1 original " + c1);

//		HashSet<CashFlow> permutaciones = c1.generarPermutaciones();
//		System.out.println(permutaciones.size());
//		for(CashFlow c:permutaciones)
//			System.out.println(c);
		
		c1.minimizarDistanciaAcumulada();
		
		
//		CashFlow c2 = new CashFlow(); // no cumplira el invariante
//		// para ningun orden posible
//		
//		c2.agregarRegistro(new Registro(100));
//		c2.agregarRegistro(new Registro(-100));
//		c2.agregarRegistro(new Registro(100));
//		c2.agregarRegistro(new Registro(-101));
//		
//		c2.forzarInvariante();
//		System.out.println(c2);
			
				
	}

}
