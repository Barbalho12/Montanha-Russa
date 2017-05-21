package br.ufrn.imd.rollercoaster;

import java.util.ArrayList;
import java.util.List;

public class MontanhaRussa {
	
	private final String TAG = "["+this.getClass().getSimpleName().toUpperCase()+"] ";
	
	private Carro carro;
	private Trilha trilha;
	private int qtdPasseiosLimite;
	private int qtdPasseios;
	
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
		Notes.print(TAG + passageiro.getID()+ " está chegando na Montanha Russa.");
		passageiro.board(carro);
		Notes.print(TAG + passageiro.getID()+ " saindo da Montanha Russa.");
	}
	
	public List<Passageiro> getFilaEmbarque() {
		return filaEmbarque;
	}

	public void setFilaEmbarque(List<Passageiro> filaEmbarque) {
		this.filaEmbarque = filaEmbarque;
	}

	public void someQtdPasseios() {
		qtdPasseios+=1;
		Notes.print(TAG + qtdPasseios + " passeios realizados.");
	}
	
	public int getQtdPasseios() {
		return qtdPasseios;
	}
}
