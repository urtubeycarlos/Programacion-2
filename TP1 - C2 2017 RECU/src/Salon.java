public class Salon
{
	
	int[] sillas;
	int cantSillas;
	int Personas;
	
	Salon(int cantidadsillas)
	{
		this.cantSillas=cantidadsillas;
		this.Personas=cantidadsillas*2;
		this.sillas= new int[Personas];
	}
}
