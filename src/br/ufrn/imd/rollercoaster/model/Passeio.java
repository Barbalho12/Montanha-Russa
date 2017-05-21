package br.ufrn.imd.rollercoaster.model;

import br.ufrn.imd.rollercoaster.util.Notes;

public class Passeio extends Thread{
	
	private final String TAG = "["+this.getClass().getSimpleName().toUpperCase()+"]\t";
	
	private double tempo;
	
	private double distanciaPercorrida;
	
	private Trilha trilha;
	
	private double velocidade;

	public Passeio(Trilha trilha){
		this.tempo = 0;
		this.distanciaPercorrida = 0;
		this.trilha = trilha;
		this.velocidade= 0;
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
		Notes.print(TAG + "Iniciando passeio pela trilha.");
		while (distanciaPercorrida < trilha.getDistancia()) {
			double percent = (distanciaPercorrida*(1.0) / trilha.getDistancia())*100.0;
			distanciaPercorrida += 1;
			Notes.print(TAG + "passeando na Montanha Russa ("+percent+"%)");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
		Notes.print(TAG + "Fim do passeio ("+100+"%)");
	}

}
