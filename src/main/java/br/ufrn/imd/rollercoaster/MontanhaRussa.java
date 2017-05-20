package br.ufrn.imd.rollercoaster;

import java.util.ArrayList;
import java.util.List;

public class MontanhaRussa {
	private Carro carro;
	private Trilha trilha;
	private int qtdPasseiosLimite;
	
	private List<Passageiro> filaEmbarque;
	
	public MontanhaRussa(int capacidadecarro, int distanciaTrilha){
		filaEmbarque = new ArrayList<Passageiro>();
		carro = new Carro(capacidadecarro, this);
		trilha = new Trilha(distanciaTrilha);
		carro.start();
	}
	
	public int getQtdPasseiosLimite() {
		return qtdPasseiosLimite;
	}
	public void setQtdPasseiosLimite(int qtdPasseiosLimite) {
		this.qtdPasseiosLimite = qtdPasseiosLimite;
	}
	public Trilha getTrilha() {
		return trilha;
	}
	public void setTrilha(Trilha trilha) {
		this.trilha = trilha;
	}
	public Carro getCarro() {
		return carro;
	}
	public void setCarro(Carro carro) {
		this.carro = carro;
	}
	
	public void tentarBrincar(Passageiro passageiro) {
		Notes.print("[MONTANHA_RUSSA] "+passageiro.getID()+ " está chegando na Montanha Russa.");
		passageiro.board(carro);
		Notes.print("[MONTANHA_RUSSA] "+passageiro.getID()+ " saindo da Montanha Russa.");
	}
	
	public List<Passageiro> getFilaEmbarque() {
		return filaEmbarque;
	}

	public void setFilaEmbarque(List<Passageiro> filaEmbarque) {
		this.filaEmbarque = filaEmbarque;
	}

}
