package ejercicio2;

public class Test 
{
	public static void main(String[] args)
	{
		JuegoSillas js = new JuegoSillas(4);// 4 sillas
		// asigna de manera aleatoria
		js.asignarPersonas();
		while ( js.cantSillasActivas()>0 )
		{//muestra las personas y sillas activas

			System.out.println( js );
			js.jugar();
		}
		
		System.out.println( js );
	}

}


