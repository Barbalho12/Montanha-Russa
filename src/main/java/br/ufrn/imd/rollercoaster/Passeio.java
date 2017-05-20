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
		Notes.print("[PASSEIO] Iniciando passeio pela trilha.");
		while (distanciaPercorrida < trilha.getDistancia()) {
			double percent = (distanciaPercorrida*(1.0) / trilha.getDistancia())*100.0;
			distanciaPercorrida += 1;
			Notes.print("[PASSEIO] passeando na Montanha Russa ("+percent+"%)");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Notes.print("[PASSEIO] Fim do passeio ("+100+"%)");
	}


	
}
