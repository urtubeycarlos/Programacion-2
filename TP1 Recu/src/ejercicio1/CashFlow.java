package ejercicio1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CashFlow {
	
	public ArrayList<Registro> registros;
	private Integer saldo;
	private static Integer cantReg;
	
	public CashFlow(){
		saldo = 0;
		cantReg = 0;
		registros = new ArrayList<Registro>();
	}

	public void agregarRegistro(Registro r){
		
		r.fecha = cantReg;
		cantReg++;
		
		registros.add(r);	
		
		saldo = saldo + r.importe;
		
	}
	
	public void obtenerSaldoPositivoSwapeandoPocasVeces(){
		
		if( !sePuedeConseguirSaldoPositivo() ) //Checkea si en algun momento se puede obtener saldo positivo. Complejidad O(n)
			throw new RuntimeException("Nunca se va a poder conseguir saldo positivo");
		
		//------------------ Bloque principal -----------------------------//
		
		for(int i=registros.size(); i>0; i--){ //Recorre la lista de registros de atras para adelante para hacer mas intuitiva
											   //la generacion de sublistas. En el peor de los casos hace n iteraciones.
			while ( seAcumulaSaldoNegativo( registros.subList(0, i) ) ){ //Vamos generando sublistas desde 0 hasta i.          <- El peor de los casos acá seria que tengamos un solo monto positivo grande a lo ultimo que tengamos que mover hacia adelante
																		//iteramos n veces (en el peor de los casos) una (n-1) sucesiones
				for(int j=0; j<i; j++){	//Recorre la sublistal, itera n veces (en el peor de los casos) sucesiones (n-1) otra vez <- El peor de los casos acá seria que tengamos un solo monto positivo grande a lo ultimo que tengamos que mover hacia adelante
					if( registros.get(j).importe < 0 && registros.get(j+1).importe > 0 ){ //Si se encuentra un saldo pos al lado de uno neg entonces...
						Collections.swap(registros, j, j+1); //...swapea, en un ArrayList es O(1), se descarta.
						break; //Corta el ciclo para que no haya problemas con el indice
					}
				}
			}
		}
		
		//La formula final quedaria:           n * n * 2(n-1)
		//                                    ----------------
		//                                      	  2
		
		// Seria una sumatoria de Gauss doble. La primera n seria por el primer ciclo, la segunda n por el while
		// 2(n-1) porque hacemos una sucesion de (n-1) dos veces, una para hacer el metodo seAcumulaSaldoNegativo y otra para recorrer la sublista
		// Divido 2 por la definicón la sumatoria de Gauss.
		
		// Entonces...
		// Los 2 se cancelan: ---->    n * n * (n-1)
		// Distributiva: ----> n * (n^2 - 1n)
		// Distributiva (again): -----> n^3 - 1n^2
		
		// En notacion O grande quedaría el mayor de los exponentes: -----> O(n^3)
		
		
		//Total bloque principal O(n^3)
		
		//------------------ Bloque principal -----------------------------//
		
		
		setearFechasCorrectas(); //Setea las fechas correctas. O(n)
		
		// Total final: -----> O(n) + O(n^3) + O(n) = O(n^3)
		// (Cuando se suman las O's queda la mas grande)
		
	}
	
	private void setearFechasCorrectas() {
		//Recorre los registros y les setea la fecha que les correspondia antes de ser swapeados.
		//Complejidad O(n), recorre n registros una vez.
		for(int i=0; i<registros.size(); i++){
			Registro r = registros.get(i);
			int temp = r.fecha;
			r.fechaReal = temp;
			r.fecha = i;
		}
	}
	
	public boolean seAcumulaSaldoNegativo(List<Registro> list){
		int acum = 0;
		for(Registro r:list){
			acum += r.importe;
			if( acum<0 )
				return true;
		} return false;
	}
	
	public int obtenerSaldoNegAcumulado(){
		int acum_neg = 0;
		int acum_aux = 0;
		for(Registro r:registros){
			acum_aux += r.importe;
			if( acum_aux < 0 ){
				acum_neg += acum_aux;
				acum_aux = 0;
			}
		} return acum_neg;
	}

	private boolean sePuedeConseguirSaldoPositivo(){
		//Complejidad O(n). Recorre n registros una vez.
		//Operaciones aritmeticas tienen complejidad O(1)
		int acum_pos = 0;
		int acum_neg = 0;
		for(Registro r:registros){
			if( r.importe > 0)
				acum_pos += r.importe;
			if ( r.importe < 0)
				acum_neg += r.importe;
		}

		if( acum_pos+acum_neg >= 0 )
			return true;
		return false;
		
	}
	
	
	
	@Override
	public String toString(){
		return registros.toString();
	}

}
