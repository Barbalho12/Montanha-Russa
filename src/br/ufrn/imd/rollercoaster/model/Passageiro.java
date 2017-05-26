package br.ufrn.imd.rollercoaster.model;

import br.ufrn.imd.rollercoaster.Mensagens;
import br.ufrn.imd.rollercoaster.util.Notes;
import br.ufrn.imd.rollercoaster.util.RandInt;

public class Passageiro extends Visitante{
	
	private final static int MAX_SEG_PASSEIO_PARQUE = 10;
	private final static int MIN_SEG_PASSEIO_PARQUE = 4;
	private int qtdPasseios;

	public Passageiro(int id, ParqueDiversoes parqueDiversoesREF) {
		super(id, parqueDiversoesREF);
		this.setQtdPasseios(0);
		/*Tempo de duração de passeios*/
		setRandInt(new RandInt(MIN_SEG_PASSEIO_PARQUE, MAX_SEG_PASSEIO_PARQUE));
	}
	
	@Override
	public void action() {
		getParqueDiversoesREF().getMontanhaRussa().tentarBrincar(this);
		passearNoParque();
	}

	public void board(Carro carro) {
		Notes.print(this, Mensagens.PASSAGEIRO_BOARD, toString());
		if(carro.embarcar(this)){
			parar();
			setQtdPasseios(getQtdPasseios() + 1);
		}else{
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
