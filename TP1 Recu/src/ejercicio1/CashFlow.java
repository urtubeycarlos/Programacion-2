package ejercicio1;

import java.util.ArrayList;

public class CashFlow {
	
	public ArrayList<Registro> registros;
	private Integer saldo;	//saldo gral
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
		
		// por construccion fechaReal viene vacio, por las dudas pregunto
		if (saldo < 0 && r.fechaReal != null)
			throw new RuntimeException("saldo negativo!: " + saldo);
		
	}

	private void agregarRegistros(ArrayList<Registro> listaRegistros){
		for(Registro r:listaRegistros)
			agregarRegistro(r);
	}


	public void minimizarDistanciaAcumulada(){
		ArrayList<CashFlow> posiblesSoluciones = generarPermutaciones();
		CashFlow mejorCashFlow = buscarCashFlowOptimo(posiblesSoluciones);
		registros = mejorCashFlow.registros;
		setearFechasCorrectas();
		System.out.println(registros);
	}

	
	private void setearFechasCorrectas() {
		for(int i=0; i<registros.size(); i++){
			Registro r = registros.get(i);
			int temp = r.fecha;
			r.fechaReal = temp;
			r.fecha = i;
		}
	}

	private ArrayList<CashFlow> generarPermutaciones(){
	
		ArrayList<ArrayList<Registro>> permutacionesRegistro = new ArrayList<ArrayList<Registro>>();
		permutacionesRegistro.add(new ArrayList<Registro>());
		
		for(int i=0; i<registros.size(); i++){
			
			ArrayList<ArrayList<Registro>> current = new ArrayList<ArrayList<Registro>>();
			
			for(ArrayList<Registro> arrayRegistro:permutacionesRegistro){
				
				for(int j=0; j<arrayRegistro.size()+1; j++){
					arrayRegistro.add(j, registros.get(i));
					ArrayList<Registro> temp = new ArrayList<Registro>(arrayRegistro);
					current.add(temp);
					arrayRegistro.remove(j);
				}
				
				permutacionesRegistro = new ArrayList<ArrayList<Registro>>(current);
			}
		}
		
		ArrayList<CashFlow> resultado = new ArrayList<CashFlow>();
		for(ArrayList<Registro> permutacion:permutacionesRegistro){
			CashFlow aux = new CashFlow();
			aux.agregarRegistros(permutacion);
			resultado.add(aux);
		}
		
		System.out.println("Resultado permutaciones: " + resultado);
		return resultado;
		
	}

	private CashFlow buscarCashFlowOptimo(ArrayList<CashFlow> listaCashFlows){
	
		CashFlow elMejorHastaAhora = null;
		int mejorDistAcumulada = 0;
		
		for(CashFlow c:listaCashFlows){
			if( elMejorHastaAhora == null ){
				elMejorHastaAhora = c;
				mejorDistAcumulada = calcDistanciaAcumulada(c.registros);
			} else {
				boolean condicion1 = !seAcumulaSaldoNegativo(c.registros);
				boolean condicion2 = calcDistanciaAcumulada(c.registros) <= mejorDistAcumulada;
				if( condicion1 && condicion2 ){
					elMejorHastaAhora = c;
					mejorDistAcumulada = calcDistanciaAcumulada(c.registros);
				}
			}	
		}
		
		System.out.println("El mejor:  " + elMejorHastaAhora);
		return elMejorHastaAhora;
	}

	private int calcDistanciaAcumulada(ArrayList<Registro> listaRegistros){
		int acum = 0;
	
		for(int i=0; i<listaRegistros.size(); i++){
			acum = acum + ( listaRegistros.get(i).fecha - registros.get(i).fecha );
		}
		
		return acum;
	}

	private boolean seAcumulaSaldoNegativo(ArrayList<Registro> listaRegistros){
		int acum = 0;
		for(int i=0; i<listaRegistros.size(); i++){
			acum = acum - listaRegistros.get(i).importe;
			if( acum < 0 )
				return true;
		} return false;
	}
	
	@Override
	public String toString(){
		return registros.toString();
	}

}
