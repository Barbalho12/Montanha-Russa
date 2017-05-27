package br.ufrn.imd.rollercoaster.model;

import br.ufrn.imd.rollercoaster.Mensagens;
import br.ufrn.imd.rollercoaster.util.Notes;

public class MontanhaRussa {

	private Carro carro;
	private Trilha trilha;
	
	public MontanhaRussa(int capacidadecarro, int distanciaTrilha, int qtdPasseiosLimite){
		this.carro = new Carro(capacidadecarro, this, qtdPasseiosLimite);
		this.trilha = new Trilha(distanciaTrilha);
	}
	
	public void init(){
		carro.setLigado(true);
		this.carro.start();
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
}
