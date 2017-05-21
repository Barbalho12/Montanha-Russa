package br.ufrn.imd.rollercoaster;

import br.ufrn.imd.rollercoaster.util.RandInt;

public class Passageiro extends Visitante{
	
	private final String TAG = "["+this.getClass().getSimpleName().toUpperCase()+"]\t";

	private final static int MAX_SEG_PASSEIO_PARQUE = 10;
	private final static int MIN_SEG_PASSEIO_PARQUE = 4;

	public Passageiro(int id, ParqueDiversoes parqueDiversoesREF) {
		super(id, parqueDiversoesREF);
		
		/*Tempo de duração de passeios*/
		setRandInt(new RandInt(MIN_SEG_PASSEIO_PARQUE, MAX_SEG_PASSEIO_PARQUE));
	}
	
	@Override
	public void action() {
		getParqueDiversoesREF().getMontanhaRussa().tentarBrincar(this);
		passearNoParque();
	}

	public void board(Carro carro) {
		Notes.print(TAG + toString() + " try Board.");
		if(carro.embarcar(this)){
			parar();
		}else{
			continuar();
		}
	}

	public void unboard() {
		Notes.print(TAG + toString() + " Unboard.");
		continuar();
	}

}
