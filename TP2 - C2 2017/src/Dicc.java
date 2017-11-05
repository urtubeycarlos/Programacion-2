/*
 * Interfaz de Dicc<C,S>
 * Deben implementar todos estos métodos y el contructor para cada implementación
 * 
 */

import java.util.Iterator;

public interface Dicc<C, S> {
	public S obtener(C clave);						//si la clave no existe devuelve exception
	public void definir(C clave, S significado);	//si la clave existe, piso el significado
	public boolean pertenece(C clave);
	public void eliminar(C clave);					//si la clave no existe no hace nada (o sea, no tira exception)
	Iterator<C> iterator();

	//equals, sobreescribir de Object
	//toString, sobreescribir de Object

	/*IREP:
	 * -No hay claves repetidas.
	 * -Dos diccionarios son iguales si tienen exactamente las mismas claves y si para clave.
	 * tiene el mismo significado.
	 * -Si se define una clave que ya existe, se sobreescribe el significado anterior por el nuevo.
	 */

}
