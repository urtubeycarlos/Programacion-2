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
		
		if( !sePuedeConseguirSaldoPositivo() ) //O(n)
			throw new RuntimeException("Nunca se va a poder conseguir saldo positivo");
		
		int saldoNegAcumulado = obtenerSaldoNegAcumulado(); 
		while( saldoNegAcumulado < 0 ){ 
			
			for(int i=0; i<registros.size()-1; i++){
				
				if( ( registros.get(i).importe < 0 ) && !( registros.get(i+1).importe < 0 ) ){
					saldoNegAcumulado += ( registros.get(i+1).importe - ( registros.get(i).importe + registros.get(i+1).importe ));
					Collections.swap(registros, i, i+1);
					break;
				}
			}
			
		}
		
		setearFechasCorrectas();
		
	}
	
	public void obtenerSaldoPositivoSwapeandoPocasVeces2(){
		
		if( !sePuedeConseguirSaldoPositivo() ) //O(n)
			throw new RuntimeException("Nunca se va a poder conseguir saldo positivo");
		
		for(int i=registros.size(); i>0; i--){
			if ( seAcumulaSaldoNegativo( registros.subList(0, i) ) ){
				for(int j=0; j<i; j++){
					if( registros.get(j).importe < 0 && registros.get(j+1).importe > 0 ){
						Collections.swap(registros, j, j+1);
						break;
					}
				}
			}
		}
		
		setearFechasCorrectas();
		
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
