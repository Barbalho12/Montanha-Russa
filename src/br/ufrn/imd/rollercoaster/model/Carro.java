package br.ufrn.imd.rollercoaster.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Semaphore;

import br.ufrn.imd.rollercoaster.util.Notes;

public class Carro extends Thread{
	
	private final String TAG = "["+this.getClass().getSimpleName().toUpperCase()+"]\t";

	private MontanhaRussa montanhaRussaREF;
	private List<Passageiro> passageiros;
	private int capacidade;
	private Semaphore semaphore;

	public Carro(int capacidade, MontanhaRussa montanhaRussaREF) {
		this.setCapacidade(capacidade);
		this.passageiros = new ArrayList<Passageiro>();
		this.montanhaRussaREF = montanhaRussaREF;
		semaphore = new Semaphore(capacidade);
		FecharCarro();
	}
	
	public void run() {
		load();
		while ( montanhaRussaREF.isAberto()) {
			if (carroCheio()) {
				iniciarPasseio();
				unload();
				load();
			}
		}
		semaphore.release(semaphore.getQueueLength());
	}
	
	public void load() {
		if (montanhaRussaREF.isAberto()){
			Notes.print(TAG + "load.");
		}
		semaphore.release(capacidade);
	}

	public void unload() {
		Notes.print(TAG + "unload.");

		Iterator<Passageiro> itPassageiros = passageiros.iterator();
		while (itPassageiros.hasNext()) {
			itPassageiros.next().unboard();
			itPassageiros.remove();
		}
	}
	
	public void iniciarPasseio(){
		montanhaRussaREF.someQtdPasseios();
		Passeio passeio = new Passeio(montanhaRussaREF.getTrilha());
		passeio.start();
		try {
			passeio.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public boolean carroCheio(){
		return semaphore.availablePermits() == 0;
	}

	public boolean embarcar(Passageiro passageiro) {
		try {
			if (semaphore.availablePermits() == 0){
				Notes.print(TAG+"Fila de espera: " + (semaphore.getQueueLength() + 1) + ".");
			}
			
			semaphore.acquire();
			
			if(montanhaRussaREF.isAberto()){

				passageiros.add(passageiro);

				Notes.print(TAG + passageiro.toString() + " embarcou no carro.");
				Notes.print(TAG + "Lotação do carro: " + passageiros.size()+"/"+capacidade + ". " + passageiros.toString());
			}else{
				Notes.print(TAG + passageiro.toString() + " Não conseguiu embarcar.");
				return false;
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		return true;
	}
	
	public void FecharCarro(){
		try {
			semaphore.acquire(capacidade);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public boolean contemPassageiro(Passageiro passageiro) {
		return passageiros.contains(passageiro);
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
