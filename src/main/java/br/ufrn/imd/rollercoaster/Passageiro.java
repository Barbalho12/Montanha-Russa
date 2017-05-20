package br.ufrn.imd.rollercoaster;

import br.ufrn.imd.rollercoaster.util.RandInt;

public class Passageiro extends Thread implements Runnable {

	private final static int MAX_SEG_PASSEIO_PARQUE = 10;
	private final static int MIN_SEG_PASSEIO_PARQUE = 4;

	private RandInt randInt;
	

	private int id;
	ParqueDiversoes parqueDiversoesREF;

	public Passageiro(int id, ParqueDiversoes parqueDiversoesREF) {
		randInt = new RandInt(MIN_SEG_PASSEIO_PARQUE, MAX_SEG_PASSEIO_PARQUE);
		this.parqueDiversoesREF = parqueDiversoesREF;
		setId(id);
	}


	public void run() {
		Notes.print("[PASSAGEIRO] " + id + " Chegou ao Parque de diversões.");
		MontanhaRussa montanhaRussaREF = parqueDiversoesREF.getMontanhaRussa();
		while (true) {
//			delay();
			montanhaRussaREF.tentarBrincar(this);
			passearNoParque();
		}
	}

	public void board(Carro carro) {
		Notes.print("[PASSAGEIRO] " + id + " try Board.");
		carro.embarcar(this);
		
	}

	public synchronized void unboard() {
		notify();
		Notes.print("[PASSAGEIRO] " + id + " Unboard.");
		
	}

	private void passearNoParque() {
		try {
			int tempo_seg = randInt.rand();
			Notes.print("[PASSAGEIRO] " + id + " Passeando No Parque por "+tempo_seg+" segundos.");
			Thread.sleep(tempo_seg * 1000);
			Notes.print("[PASSAGEIRO] " + id + " Passeio No Parque concluído.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public int getID() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	private void delay() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

}
