package br.ufrn.imd.rollercoaster.model;

import br.ufrn.imd.rollercoaster.Mensagens;
import br.ufrn.imd.rollercoaster.util.Notes;

public class MontanhaRussa {

	private Carro carro;
	private Trilha trilha;
	private int qtdPasseiosLimite;
	private int qtdPasseios;
	private boolean aberto;
	
	public MontanhaRussa(int capacidadecarro, int distanciaTrilha, int qtdPasseiosLimite){
		this.carro = new Carro(capacidadecarro, this);
		this.trilha = new Trilha(distanciaTrilha);
		this.qtdPasseiosLimite = qtdPasseiosLimite;
		this.qtdPasseios = 0;
	}
	
	public void init(){
		this.carro.start();
		setAberto(true);
	}
	
	public void tentarBrincar(Passageiro passageiro) {
		Notes.print(this, Mensagens.MONTANHARUSSA_CHEGADA_PASSAGEIRO, passageiro.toString());
		passageiro.board(carro);
		Notes.print(this, Mensagens.MONTANHARUSSA_PASSAGEIRO_SAINDO, passageiro.toString());
	}
	
	public void someQtdPasseios() {
		qtdPasseios+=1;
		Notes.print(this, Mensagens.MONTANHARUSSA_QTD_PASSEIO, qtdPasseios, qtdPasseiosLimite);
		if(qtdPasseios == qtdPasseiosLimite){
			setAberto(false);
			Notes.print(this, Mensagens.MONTANHARUSSA_LIMITE);
			carro.liberarFila();
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
}
