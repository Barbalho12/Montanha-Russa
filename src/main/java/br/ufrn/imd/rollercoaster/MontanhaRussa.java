package br.ufrn.imd.rollercoaster;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MontanhaRussa {
	private Carro carro;
	private Trilha trilha;
	private int qtdPasseiosLimite;
	
	private List<Passageiro> filaEmbarque;
	
	public MontanhaRussa(int capacidadecarro, int distanciaTrilha){
		filaEmbarque = new ArrayList<Passageiro>();
		carro = new Carro(capacidadecarro, this);
		trilha = new Trilha(distanciaTrilha);
		carro.start();
	}
	
	public int getQtdPasseiosLimite() {
		return qtdPasseiosLimite;
	}
	public void setQtdPasseiosLimite(int qtdPasseiosLimite) {
		this.qtdPasseiosLimite = qtdPasseiosLimite;
	}
	public Trilha getTrilha() {
		return trilha;
	}
	public void setTrilha(Trilha trilha) {
		this.trilha = trilha;
	}
	public Carro getCarro() {
		return carro;
	}
	public void setCarro(Carro carro) {
		this.carro = carro;
	}
	
	public void tentarBrincar(Passageiro passageiro) {
		System.out.println(ParqueDiversoes.GLOBAL_TIME + " [MONTANHA_RUSSA] "+passageiro.getID()+ " está na fila da Montanha Russa.");
		filaEmbarque.add(passageiro);
		
		
//		try {
//			 synchronized (passageiro) {
//		         while (!((filaEmbarque.size() >= carro.getCapacidade()) && !carro.isEmMovimento())){
//		        	 passageiro.wait();
//		         }
//		        	 
//		     }
//
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
//		System.out.println((filaEmbarque.size() == carro.getCapacidade()) +" - "+ (!carro.isEmMovimento()));
		synchronized (passageiro) {
			if(!carro.isEmMovimento()){
				if(filaEmbarque.size() >= carro.getCapacidade() && carro.getQtdPassageiros() == 0){
					
					System.out.println(ParqueDiversoes.GLOBAL_TIME + " [MONTANHA_RUSSA] Ativar Novo Embarque.");
					
					Iterator<Passageiro> itFilaEmbarque = filaEmbarque.iterator();
					while (itFilaEmbarque.hasNext() && (carro.getQtdPassageiros() < carro.getCapacidade())) {
//						System.out.println("AQUI 0");
						itFilaEmbarque.next().board(carro);
//						System.out.println("AQUI 1");
						itFilaEmbarque.remove();
//						System.out.println("AQUI 2");
					}
				}
			}
		}

//		passageiro.stop();
		try {
			synchronized (passageiro) {
				while (filaEmbarque.contains(passageiro) || carro.contemPassageiro(passageiro)) {
					passageiro.wait();
				}
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public List<Passageiro> getFilaEmbarque() {
		return filaEmbarque;
	}

	public void setFilaEmbarque(List<Passageiro> filaEmbarque) {
		this.filaEmbarque = filaEmbarque;
	}

	public synchronized void ativarNovoEmbarque(){
//		System.out.println(ParqueDiversoes.GLOBAL_TIME + " [MONTANHA_RUSSA] Ativar Novo Embarque.");
//		//o carro permite a entrada de exatamente C passageiros;
//		for (int i = 0; i < carro.getCapacidade(); i++) {
//			filaEmbarque.get(i).board(carro);
//		}
//		
//		for (int i = 0; i < carro.getCapacidade(); i++) {
//			filaEmbarque.remove(i);
//		}
	}
	

}
