package ejercicio2;

import java.util.HashSet;

public class PPTLS {

	private HashSet<Objeto> _objetos;
	
	public PPTLS(){
		_objetos = new HashSet<Objeto>();
	}
	
	public void agregarObjeto(String nombre_objeto){
		_objetos.add(new Objeto(nombre_objeto));
	}
	
	public void agregarObjetoParecido(String objeto_nuevo, String objeto_al_que_se_parece){
		
		Objeto obj_al_que_se_parece = getObjeto(objeto_al_que_se_parece);
		if( obj_al_que_se_parece == null )
			throw new IllegalArgumentException("No se puede agregar el objeto " + objeto_nuevo + " sin antes agregar " + objeto_al_que_se_parece);
		
		obj_al_que_se_parece.agregarParecido(objeto_nuevo);
		
	}
	
	public void agregarRegla(String objeto1, String objeto2){
		
		if( objeto1.equals(objeto2) )
			throw new IllegalArgumentException("No se puede definir una regla entre elementos iguales");
		
		Objeto objetoTemp1 = getObjeto(objeto1);
		Objeto objetoTemp2 = getObjeto(objeto2);
		
		
		if( objetoTemp1 == null || objetoTemp2 == null )
			throw new IllegalArgumentException("No se puede agregar la regla sin antes agregar el objeto: " + ((objetoTemp1 == null)? objeto1:objeto2) );
		
		if( objetoTemp1.esParecido(objeto2) )
			throw new IllegalArgumentException("No se puede definir una regla entre elementos parecidos");
		
		if( objetoTemp2.leGanoA(objetoTemp1) )
			throw new IllegalArgumentException("No se puede agregar una regla redundante. " + objeto1 + " <-> " +objeto2);
		
		objetoTemp1.leGanaA(objetoTemp2);
		
	}
	
	private Objeto getObjeto(String nombre_objeto){
		
		Objeto ret = null;
		
		for(Objeto objs:_objetos){
			if( objs.nombre.equals(nombre_objeto) || objs.esParecido(nombre_objeto) )
				ret = objs;
		}
		
		return ret;
	}
	
	public String jugar(String objeto1, String objeto2){
		
		if( _objetos.size() <=1 )
			throw new RuntimeException("No se puede jugar sin agregar 2 o mas elementos");
		
		if( objeto1.equals(objeto2) )
			throw new IllegalArgumentException("No se puede jugar con dos elementos iguales");
			
		Objeto objetoTemp1 = getObjeto(objeto1);
		Objeto objetoTemp2 = getObjeto(objeto2);
		
		if( objetoTemp1 == null || objetoTemp2 == null )
			throw new IllegalArgumentException("No se puede jugar sin antes agregar el objeto: " + ((objetoTemp1 == null)? objeto1:objeto2) );
		
		if( objetoTemp1.esParecido(objeto2) )
			throw new IllegalArgumentException("No se puede jugar con dos elementos parecidos");
		
		if( objetoTemp1.leGanoA(objetoTemp2) ) {
			return objeto1 + " le gana a " + objeto2 + "!";
		} else if( objetoTemp2.leGanoA(objetoTemp1) ) {
			return objeto2 + " le gana a " + objeto1 + "!";
		} else {
			throw new RuntimeException("No existe una regla definida para " + objeto1 + " y " + objeto2);
		}
		
	}
	
}
