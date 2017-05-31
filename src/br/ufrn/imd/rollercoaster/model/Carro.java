package br.ufrn.imd.rollercoaster.model;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

import br.ufrn.imd.rollercoaster.Mensagens;
import br.ufrn.imd.rollercoaster.util.Notes;

public class Carro extends Thread{

	private MontanhaRussa montanhaRussaREF;
	private List<Passageiro> passageiros;
	private int capacidade;
//	private Semaphore semaphore;
//	private Semaphore entrando;
	private int qtdPasseios;
	private int qtdPasseiosLimite;
	private boolean ligado;
	private int qtdPassageiros;
	private LockFila bloquedor;

	public Carro(int capacidade, MontanhaRussa montanhaRussaREF, int qtdPasseiosLimite) {
		this.setCapacidade(capacidade);
		this.passageiros = new ArrayList<Passageiro>();
		this.montanhaRussaREF = montanhaRussaREF;
		this.qtdPasseiosLimite = qtdPasseiosLimite;
		this.qtdPasseios = 0;
		this.qtdPassageiros = 0;
//		semaphore = new Semaphore(capacidade);
//		entrando = new Semaphore(1);
//		fecharCarro(); //Esperar load para o primeiro passsageiro entrar no carro
//		barreira = new Barreira();
		bloquedor= new LockFila();
	}
	
	public void run() {
		load(); //Passageiros podem entrar
		while ( isLigado()) { //enquanto limite de passeios ok
			if (carroCheio()) { //Se carro tiver lotado
				iniciarPasseio(); //Passeia pela trilha ... conluido passeio
				unload(); //Passageiros podem sair
				load(); //novos passageiros podem entrar
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			System.out.println(carroCheio());
		}
	}
	
	public void load() {
		if (isLigado()){
			Notes.print(this, "load.");
		}
		qtdPassageiros = 0;
//		semaphore.release(capacidade);
//		if(carroCheio())
		
		
//		for (int i = 0; i < capacidade; i++) {
//			bloquedor.desploquear();
//		}
		bloquedor.desploquear(capacidade);
	}

	public void unload() {
		Notes.print(this, "unload.");

		Iterator<Passageiro> itPassageiros = passageiros.iterator();
		while (itPassageiros.hasNext()) {
			itPassageiros.next().unboard();
			itPassageiros.remove();
		}
	}
	
	public void iniciarPasseio(){
		someQtdPasseios();
		Passeio passeio = new Passeio(montanhaRussaREF.getTrilha());
		passeio.run();
		Notes.print(this, Mensagens.CARRO_QTD_PASSEIO, qtdPasseios, qtdPasseiosLimite);
	}
	
	public void someQtdPasseios() {
		qtdPasseios+=1;
		
		if(qtdPasseios == qtdPasseiosLimite){
			setLigado(false);
			Notes.print(this, Mensagens.CARRO_LIMITE);
			bloquedor.desploquearTodos();
		}
	}
	
	public boolean carroCheio(){
		//Todos as pessoas estão no carro, nem entrando nem esperando para entrar.
//		return semaphore.availablePermits() == 0 && !entrando.hasQueuedThreads() && entrando.availablePermits() != 0;
		return qtdPassageiros == capacidade;
	}
	
	public synchronized void entrar(Passageiro passageiro){
		passageiros.add(passageiro);
		Notes.print(this, Mensagens.CARRO_PASSAGEIRO_EMBARCOU, passageiro.toString());
		Notes.print(this, Mensagens.CARRO_LOTACAO, passageiros.size(), capacidade, passageiros.toString());
		++qtdPassageiros;
	}

	public boolean embarcar(Passageiro passageiro) {
		try {
//			if (semaphore.availablePermits() == 0){
//				Notes.print(this, Mensagens.CARRO_FILA_ESPERA,(semaphore.getQueueLength() + 1));
//			}
//			
			if(carroCheio()){
				Notes.print(this, Mensagens.CARRO_FILA_ESPERA,(bloquedor.getTamanhoLista() + 1));
				bloquedor.bloquear(passageiro);
			}
//			semaphore.acquire();
			
			if(isLigado()){
				
				entrar(passageiro);

//				entrando.acquire();
//				
//				passageiros.add(passageiro);
//				Notes.print(this, Mensagens.CARRO_PASSAGEIRO_EMBARCOU, passageiro.toString());
//				Notes.print(this, Mensagens.CARRO_LOTACAO, passageiros.size(), capacidade, passageiros.toString());
//				++qtdPassageiros;
//				entrando.release();
				
			}else{
				Notes.print(this, Mensagens.CARRO_NO_EMBARCOU, passageiro.toString());
				return false;
			}
			
		} catch (ConcurrentModificationException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		return true;
	}
	
//	public void fecharCarro(){
//		try {
////			semaphore.acquire(capacidade);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//			System.exit(0);
//		}
//	}
	
//	public void liberarFila(){
//		bloquedor.desploquearTodos();
//	}

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

	public boolean isLigado() {
		return ligado;
	}

	public void setLigado(boolean ligado) {
		this.ligado = ligado;
	}
	
	public int getQtdPasseios() {
		return qtdPasseios;
	}

	public int getQtdPasseiosLimite() {
		return qtdPasseiosLimite;
	}
	public void setQtdPasseiosLimite(int qtdPasseiosLimite) {
		this.qtdPasseiosLimite = qtdPasseiosLimite;
	}

}
