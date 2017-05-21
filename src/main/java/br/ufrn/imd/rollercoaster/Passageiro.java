package br.ufrn.imd.rollercoaster;

import java.util.concurrent.Semaphore;

import br.ufrn.imd.rollercoaster.util.RandInt;

public class Passageiro extends Thread implements Runnable {
	
	private final String TAG = "["+this.getClass().getSimpleName().toUpperCase()+"] ";

	private final static int MAX_SEG_PASSEIO_PARQUE = 10;
	private final static int MIN_SEG_PASSEIO_PARQUE = 4;

	private RandInt randInt;
	private int id;
	ParqueDiversoes parqueDiversoesREF;
	private Semaphore parado;

	public Passageiro(int id, ParqueDiversoes parqueDiversoesREF) {
		this.randInt = new RandInt(MIN_SEG_PASSEIO_PARQUE, MAX_SEG_PASSEIO_PARQUE);
		this.parqueDiversoesREF = parqueDiversoesREF;
		setId(id);
		this.parado = new Semaphore(0);
	}


	public void run() {
		Notes.print(TAG + id + " Chegou ao Parque de diversões.");
		MontanhaRussa montanhaRussaREF = parqueDiversoesREF.getMontanhaRussa();
		while (parqueDiversoesREF.getMontanhaRussa().isAberto()) {
			montanhaRussaREF.tentarBrincar(this);
			passearNoParque();
		}
		Notes.print(TAG + id + " Indo Embora.");
	}

	public void board(Carro carro) {
		Notes.print(TAG + id + " try Board.");
		carro.embarcar(this);
		try {
			this.parado.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public synchronized void unboard() {
		Notes.print(TAG + id + " Unboard.");
		this.parado.release();
	}

	private void passearNoParque() {
		try {
			int tempo_seg = randInt.rand();
			Notes.print(TAG + id + " Passeando No Parque por "+tempo_seg+" segundos.");
			Thread.sleep(tempo_seg * 1000);
			Notes.print(TAG + id + " Passeio No Parque concluído.");
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public int getID() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return  " ["+id+"] ";
	}
	
}
