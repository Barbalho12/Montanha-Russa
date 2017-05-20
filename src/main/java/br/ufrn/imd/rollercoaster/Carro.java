package br.ufrn.imd.rollercoaster;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Carro extends Thread implements Runnable {

	private MontanhaRussa montanhaRussaREF;
	private List<Passageiro> passageiros;
	private int capacidade;
	private Semaphore semaphore;
	private Semaphore embarque;

	public Carro() {
		this.setCapacidade(0);
		this.passageiros = new ArrayList<Passageiro>();
	}

	public Carro(int capacidade, MontanhaRussa montanhaRussaREF) {
		this.setCapacidade(capacidade);
		this.passageiros = new ArrayList<Passageiro>();
		this.montanhaRussaREF = montanhaRussaREF;
		semaphore = new Semaphore(capacidade);
		embarque = new Semaphore(1);
	}

	public void run() {
		load();
		while (true) {
			delay();
			if (getQtdPassageiros() == capacidade) {
				Passeio passeio = new Passeio(montanhaRussaREF.getTrilha());
				passeio.start();
				try {
					passeio.join();
					unload();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void delay() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	public void embarcar(Passageiro passageiro) {

		try {
			embarque.acquire();
			if (passageiros.size() == capacidade)
				Notes.print("[CARRO] Fila de espera anterior: " + semaphore.getQueueLength() + ".");
			semaphore.acquire();
			passageiros.add(passageiro);
			Notes.print("[CARRO] " + passageiro.getID() + " embarcou no carro.");
			Notes.print("[CARRO] Lotação do carro: " + passageiros.size() + ".");
			embarque.release();
//			Notes.print("[CARRO] aeww");
			synchronized (passageiro) {
				while (contemPassageiro(passageiro)) {
					passageiro.wait();
				}
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean contemPassageiro(Passageiro passageiro) {
		return passageiros.contains(passageiro);
	}

	public void load() {
		Notes.print("[CARRO] load.");

		embarque.release();

	}

	public void unload() {
		Notes.print("[CARRO] unload.");
		try {

			embarque.acquire();
			Iterator<Passageiro> itPassageiros = passageiros.iterator();
			while (itPassageiros.hasNext()) {
				itPassageiros.next().unboard();
				semaphore.release();
			}
			passageiros.clear();
			
			load();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	public int getQtdPassageiros() {
		return passageiros.size();
	}

}
