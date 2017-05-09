package ejercicio1;

public class Registro {

	Integer importe;
	Integer fecha;
	Integer fechaReal;

	public Registro( Integer importe){
		this.importe = importe;
	}

	public Registro copiarRegistro(){
		Registro ret = new Registro(importe.intValue());
		ret.fecha = this.fecha.intValue();
		return ret;
	}

	@Override
	public String toString(){
		return "{ Fecha: " + fecha  + ";  Fecha real: " + fechaReal + ";  Importe: " + importe + " }" ;
	}

}
