package br.ufrn.imd.rollercoaster.model;

import br.ufrn.imd.rollercoaster.Mensagens;
import br.ufrn.imd.rollercoaster.util.Notes;

public class Passageiro extends Visitante{
	
	private int qtdPasseios;
	private MontanhaRussa montanhaRussaREF;

	public Passageiro(int id, ParqueDiversoes parqueDiversoesREF) {
		super(id, parqueDiversoesREF);
		this.setQtdPasseios(0);
		this.montanhaRussaREF = parqueDiversoesREF.getMontanhaRussa();
	}
	
	@Override
	public void action() {
		getParqueDiversoesREF().getMontanhaRussa().tentarBrincar(this);
		passearNoParque();
	}
	
	@Override
	public boolean condicaoPermanencia() {
		return montanhaRussaREF.getCarro().isLigado();
	}

	public void board(Carro carro) {
		Notes.print(this, Mensagens.PASSAGEIRO_BOARD, toString());
		if(carro.embarcar(this)){
			parar();
			setQtdPasseios(getQtdPasseios() + 1);
		}else{
//			Notes.print(this, Mensagens.PASSAGEIRO_BOARD, toString());
			continuar();
		}
	}

	public void unboard() {
		Notes.print(this, Mensagens.PASSAGEIRO_UNBOARD, toString());
		continuar();
	}
	
	public void gerarRelatorio(){
		super.gerarRelatorio();
		Notes.print(this, Mensagens.PASSAGEIRO_RELATORIO, toString(), qtdPasseios);
	}

	public int getQtdPasseios() {
		return qtdPasseios;
	}

	public void setQtdPasseios(int qtdPasseios) {
		this.qtdPasseios = qtdPasseios;
	}

	

}
