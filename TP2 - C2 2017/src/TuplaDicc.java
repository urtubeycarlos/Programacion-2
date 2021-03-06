public class TuplaDicc<T1, T2> extends Tupla<T1, T2> {
	public TuplaDicc(T1 e1, T2 e2) {
		super(e1, e2);
	}

	@Override
	public boolean equals(Object t2) {
		boolean ret = true;
		if (!(t2 instanceof Tupla))
			ret = false;
		else {
			// es importante no asumir <T1,T2> para t2,pues podria ser <T3,T4>
			@SuppressWarnings("unchecked")
			Tupla<T1, T2> t3 = ( Tupla<T1, T2> ) t2;
			ret = ret && t3.e1.equals(e1); // && t3.e2.equals(e2)
		}
		return ret;
	}

	@Override
	public int hashCode() {
		return e1.hashCode();
	}
}