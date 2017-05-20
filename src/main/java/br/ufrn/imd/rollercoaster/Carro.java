package br.ufrn.imd.rollercoaster;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.plaf.basic.BasicTreeUI.TreeHomeAction;

public class Carro extends Thread implements Runnable{
	
	private MontanhaRussa montanhaRussaREF;
	private List<Passageiro> passageiros;
	private int capacidade;
	private int qtdPassageiros;
	private boolean emMovimento;
	
	public Carro(){
		this.setEmMovimento(false);
		this.setCapacidade(0);
		this.passageiros = new ArrayList<Passageiro>();
		qtdPassageiros = 0;
	}

	public Carro(int capacidade, MontanhaRussa montanhaRussaREF){

		this.setEmMovimento(false);
		this.setCapacidade(capacidade);
		this.passageiros = new ArrayList<Passageiro>();
		qtdPassageiros = 0;
		this.montanhaRussaREF = montanhaRussaREF;
	}
	
	public void run() {
		
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			System.out.println(qtdPassageiros +" "+capacidade+" "+montanhaRussaREF.getFilaEmbarque().size());
			if(qtdPassageiros == capacidade){
//				System.out.println("AQUI 3");
//				montanhaRussaREF.ativarNovoEmbarque();
				setEmMovimento(true);
				Passeio passeio = new Passeio(montanhaRussaREF.getTrilha());
				passeio.start();
				try {
					passeio.join();
					unload();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				setEmMovimento(false);
			}
			
		}
	}
	
	public void embarcar(Passageiro passageiro){
		passageiros.add(passageiro);
		qtdPassageiros++;
//		try {
//			 synchronized (passageiro) {
//		         while (isEmMovimento())
//		        	 passageiro.wait();
//		     }
//
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}
//	
//	public void removePassageiros(){
//		
//
//	}
	
	public boolean contemPassageiro(Passageiro passageiro){
		return passageiros.contains(passageiro);
	}
	
	public void load(){
		try {
			passageiros.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}

	public void unload(){
		Iterator<Passageiro> itPassageiros = passageiros.iterator();
		while (itPassageiros.hasNext()) {
			Passageiro p = itPassageiros.next();
			p.unboard();
			itPassageiros.remove();
			qtdPassageiros--;
		}
//		passageiros.notify();
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	public boolean isEmMovimento() {
		return emMovimento;
	}

	public void setEmMovimento(boolean emMovimento) {
		this.emMovimento = emMovimento;
	}

	public int getQtdPassageiros() {
		return qtdPassageiros;
	}

	public void setQtdPassageiros(int qtdPassageiros) {
		this.qtdPassageiros = qtdPassageiros;
	}
}
