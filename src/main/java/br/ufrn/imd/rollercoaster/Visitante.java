package br.ufrn.imd.rollercoaster;
import java.util.concurrent.Semaphore;

import br.ufrn.imd.rollercoaster.util.RandInt;

public abstract class Visitante {

	private final String TAG = "["+this.getClass().getSimpleName().toUpperCase()+"] ";
	
	private RandInt randInt;
	private int id;
	ParqueDiversoes parqueDiversoesREF;
	private Semaphore parado;

	public Visitante(int id, ParqueDiversoes parqueDiversoesREF) {
		this.parqueDiversoesREF = parqueDiversoesREF;
		this.id = id;
		this.parado = new Semaphore(0);
	}


	public void run() {
		Notes.print(TAG + id + " Chegou ao Parque de diversões.");
		while (true) {
			action();
		}
	}
	
	public void parar(){
		try {
			parado.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void continuar(){
		parado.release();
	}
	
	public abstract void action();

	@SuppressWarnings("unused")
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
