package br.ufrn.imd.rollercoaster;

public class Trilha {
	
	@SuppressWarnings("unused")
	private final String TAG = "["+this.getClass().getSimpleName().toUpperCase()+"] ";
	
	private double distancia;

	public Trilha(double distancia){
		setDistancia(distancia);
	}

	public double getDistancia() {
		return distancia;
	}

	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}

}
