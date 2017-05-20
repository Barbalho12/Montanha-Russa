package br.ufrn.imd.rollercoaster;

import java.util.Random;

import br.ufrn.imd.rollercoaster.util.RandInt;

public class Passageiro extends Thread implements Runnable {

//	private static Random rand;
	private final static int MAX_SEG_PASSEIO_PARQUE = 10;
	private final static int MIN_SEG_PASSEIO_PARQUE = 4;
//	public boolean paused = false;
	private boolean parado = false;
	private RandInt randInt;
	

	private int id;
	ParqueDiversoes parqueDiversoesREF;

	// public Passageiro(){
	// rand = new Random();
	// }

	public Passageiro(int id, ParqueDiversoes parqueDiversoesREF) {
//		rand = new Random();
		randInt = new RandInt(MIN_SEG_PASSEIO_PARQUE, MAX_SEG_PASSEIO_PARQUE);
		this.parqueDiversoesREF = parqueDiversoesREF;
		setId(id);
//		System.out.println(ParqueDiversoes.GLOBAL_TIME + " [PASSAGEIRO] " + id + " criado.");
	}

//	public void esperar() {
//		paused = true;
//	}

	public void run() {
		System.out.println(ParqueDiversoes.GLOBAL_TIME + " [PASSAGEIRO] " + id + " Chegou ao Parque de diversões.");
		MontanhaRussa montanhaRussaREF = parqueDiversoesREF.getMontanhaRussa();
		while (true) {
//			if( ! parado ){

				montanhaRussaREF.tentarBrincar(this);
				passearNoParque();
//			}
		}
	}

	public void board(Carro carro) {
		System.out.println(ParqueDiversoes.GLOBAL_TIME + " [PASSAGEIRO] " + id + " Board.");
		carro.embarcar(this);
//		paused = false;
	}

	public synchronized void unboard() {
		System.out.println(ParqueDiversoes.GLOBAL_TIME + " [PASSAGEIRO] " + id + " Unboard.");
		notify();
		
		System.out.println(this.getState().toString());
	}

	private void passearNoParque() {
		try {
			int tempo_seg = randInt.rand();
			System.out.println(ParqueDiversoes.GLOBAL_TIME + " [PASSAGEIRO] " + id + " Passeando No Parque por "+tempo_seg+" segundos.");
			Thread.sleep(tempo_seg*1000);
			System.out.println(ParqueDiversoes.GLOBAL_TIME + " [PASSAGEIRO] " + id + " Passeio No Parque concluído.");
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

//	private static int rand() {
//		int randdom = rand.nextInt((MAX_SEG_PASSEIO_PARQUE - MIN_SEG_PASSEIO_PARQUE) + 1) + MIN_SEG_PASSEIO_PARQUE;
//		return randdom;
//	}
	
	public boolean isParado() {
		return parado;
	}

	public void setParado(boolean parado) {
		this.parado = parado;
	}
}
