package br.ufrn.imd.rollercoaster;

public class Passeio extends Thread implements Runnable{
	
	private double tempo;
	
	private double distanciaPercorrida;
	
	private Trilha trilha;
	
	private double velocidade;

	public Passeio(Trilha trilha){
		tempo = 0;
		distanciaPercorrida = 0;
		this.trilha = trilha;
		velocidade= 0;
	}

	public double getTempo() {
		return tempo;
	}

	public void setTempo(double tempo) {
		this.tempo = tempo;
	}

	public Trilha getTrilha() {
		return trilha;
	}

	public void setTrilha(Trilha trilha) {
		this.trilha = trilha;
	}

	public double getVelocidade() {
		return velocidade;
	}

	public void setVelocidade(double velocidade) {
		this.velocidade = velocidade;
	}

	public void run() {
		System.out.println(ParqueDiversoes.GLOBAL_TIME + " [PASSEIO] passeando...");
		while (distanciaPercorrida < trilha.getDistancia()) {
			double percent = (distanciaPercorrida*(1.0) / trilha.getDistancia())*100.0;
			distanciaPercorrida+=1;
			System.out.println(ParqueDiversoes.GLOBAL_TIME + " [PASSEIO] passeando na Montanha Russa ("+percent+"%)");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}


	
}
