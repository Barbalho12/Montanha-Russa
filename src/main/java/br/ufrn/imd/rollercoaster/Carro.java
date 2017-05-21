package br.ufrn.imd.rollercoaster;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Carro extends Thread implements Runnable {
	
	private final String TAG = "["+this.getClass().getSimpleName().toUpperCase()+"] ";

	private MontanhaRussa montanhaRussaREF;
	private List<Passageiro> passageiros;
	private int capacidade;
	private Semaphore semaphore;

	public Carro() {
		this.setCapacidade(0);
		this.passageiros = new ArrayList<Passageiro>();
	}

	public Carro(int capacidade, MontanhaRussa montanhaRussaREF) {
		this.setCapacidade(capacidade);
		this.passageiros = new ArrayList<Passageiro>();
		this.montanhaRussaREF = montanhaRussaREF;
		semaphore = new Semaphore(capacidade);
	}

	public void run() {
		while ( montanhaRussaREF.getQtdPasseios() < montanhaRussaREF.getQtdPasseiosLimite()) {
			if (semaphore.availablePermits() == 0) {
				Passeio passeio = new Passeio(montanhaRussaREF.getTrilha());
				passeio.start();
				try {
					passeio.join();
					montanhaRussaREF.someQtdPasseios();
					unload();
				} catch (InterruptedException e) {
					e.printStackTrace();
					System.exit(0);
				}
			}
		}
	}

	public void embarcar(Passageiro passageiro) {
		try {
			if (semaphore.availablePermits() == 0)
				Notes.print(TAG+"Fila de espera: " + (semaphore.getQueueLength() + 1) + ".");

			semaphore.acquire();

			passageiros.add(passageiro);

			Notes.print(TAG + passageiro.getID() + " embarcou no carro.");
			Notes.print(TAG + "Lotação do carro: " + passageiros.size() + ". " + passageiros.toString());

		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public boolean contemPassageiro(Passageiro passageiro) {
		return passageiros.contains(passageiro);
	}

	public void load() /* throws Exception */ {
		Notes.print(TAG + "load.");
		semaphore.release(capacidade);
	}

	public void unload() {
		Notes.print(TAG + "unload.");

		Iterator<Passageiro> itPassageiros = passageiros.iterator();
		while (itPassageiros.hasNext()) {
			itPassageiros.next().unboard();
			itPassageiros.remove();
		}

		load();
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
