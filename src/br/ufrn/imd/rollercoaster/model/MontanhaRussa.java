package br.ufrn.imd.rollercoaster.model;

import br.ufrn.imd.rollercoaster.Mensagens;
import br.ufrn.imd.rollercoaster.util.Notes;

public class MontanhaRussa {

	private Carro carro;
	private Trilha trilha;
	private ParqueDiversoes parqueREF;
	
	public MontanhaRussa(int capacidadecarro, int distanciaTrilha, int qtdPasseiosLimite, ParqueDiversoes parqueREF){
		this.carro = new Carro(capacidadecarro, this, qtdPasseiosLimite);
		this.trilha = new Trilha(distanciaTrilha);
		this.setParqueREF(parqueREF);
	}
	
	public void init(){
		this.carro.start();
		carro.setLigado(true);
	}
	
	public void tentarBrincar(Passageiro passageiro) {
		Notes.print(this, Mensagens.MONTANHARUSSA_CHEGADA_PASSAGEIRO, passageiro.toString());
		passageiro.board(carro);
		Notes.print(this, Mensagens.MONTANHARUSSA_PASSAGEIRO_SAINDO, passageiro.toString());
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
	
	public int getQtdPasseios() {
		return carro.getQtdPasseios();
	}

	public int getQtdPasseiosLimite() {
		return carro.getQtdPasseiosLimite();
	}

	public ParqueDiversoes getParqueREF() {
		return parqueREF;
	}

	public void setParqueREF(ParqueDiversoes parqueREF) {
		this.parqueREF = parqueREF;
	}
}
