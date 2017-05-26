package br.ufrn.imd.rollercoaster.model;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.rollercoaster.util.Notes;

public class MontanhaRussa {
	
	private final String TAG = "["+this.getClass().getSimpleName().toUpperCase()+"]\t";
	
	private Carro carro;
	private Trilha trilha;
	private int qtdPasseiosLimite;
	private int qtdPasseios;
	private boolean aberto;
	
	private List<Passageiro> filaEmbarque;
	
	public MontanhaRussa(int capacidadecarro, int distanciaTrilha, int qtdPasseiosLimite){
		this.filaEmbarque = new ArrayList<Passageiro>();
		this.carro = new Carro(capacidadecarro, this);
		this.trilha = new Trilha(distanciaTrilha);
		this.qtdPasseiosLimite = qtdPasseiosLimite;
		this.qtdPasseios = 0;
	}
	
	public void init(){
		this.carro.start();
		setAberto(true);
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
		Notes.print(TAG + passageiro.toString()+ " está chegando na Montanha Russa.");
		passageiro.board(carro);
		Notes.print(TAG + passageiro.toString()+ " saindo da Montanha Russa.");
	}
	
	public List<Passageiro> getFilaEmbarque() {
		return filaEmbarque;
	}

	public void setFilaEmbarque(List<Passageiro> filaEmbarque) {
		this.filaEmbarque = filaEmbarque;
	}

	public void someQtdPasseios() {
		qtdPasseios+=1;
		Notes.print(TAG + qtdPasseios+"/"+qtdPasseiosLimite + " passeios iniciados.");
		if(qtdPasseios == qtdPasseiosLimite){
			setAberto(false);
			Notes.print(TAG + "O limite de passeios foi atingido.");
		}
	}
	
	public int getQtdPasseios() {
		return qtdPasseios;
	}

	public boolean isAberto() {
		return aberto;
	}

	public void setAberto(boolean aberto) {
		this.aberto = aberto;
	}
}
