package ejercicio1_alt;

public class Registro {

	int importe;
	int fecha;
	int fechaReal;

	public Registro( Integer importe){
		this.importe = importe;
	}
	
	@Override
	public String toString(){
		return "{ Fecha: " + fecha  + ";  Fecha real: " + fechaReal + ";  Importe: " + importe + " }" ;
	}


}
