package br.ufrn.imd.rollercoaster.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.ufrn.imd.rollercoaster.Mensagens;
import br.ufrn.imd.rollercoaster.util.Notes;

public class Carro extends Thread {

	private MontanhaRussa montanhaRussaREF;
	private List<Passageiro> passageiros;
	private int capacidade;
	private int qtdPasseios;
	private int qtdPasseiosLimite;
	private boolean ligado;
	private int qtdPassageiros;
	private LockLista bloquedor;

	public Carro(int capacidade, MontanhaRussa montanhaRussaREF, int qtdPasseiosLimite) {
		this.setCapacidade(capacidade);
		this.passageiros = new ArrayList<Passageiro>();
		this.montanhaRussaREF = montanhaRussaREF;
		this.qtdPasseiosLimite = qtdPasseiosLimite;
		this.qtdPasseios = 0;
		this.qtdPassageiros = 0;
		bloquedor = new LockLista();
	}

	/**
	 * Inicia Thread
	 */
	public void run() {
		load(); // Passageiros podem entrar

		while (isLigado()) { // enquanto limite de passeios ok
			if (carroCheio()) { // Se carro tiver lotado
				iniciarPasseio(); // Passeia pela trilha ... conluido passeio
				unload(); // Passageiros podem sair
				load(); // novos passageiros podem entrar
			}
		}
		verificacaooFinal();
	}


	/**
	 * Entrada de passageiros permitida
	 */
	public void load() {
		if (isLigado()) {
			Notes.print(this, "load.");
		}
		qtdPassageiros = 0;

		bloquedor.desploquear(capacidade);
	}

	/**
	 * Saída de passageiros permitida
	 */
	public void unload() {
		Notes.print(this, "unload.");

		Iterator<Passageiro> itPassageiros = passageiros.iterator();
		while (itPassageiros.hasNext()) {
			itPassageiros.next().unboard();
			itPassageiros.remove();
		}
	}

	/**
	 * Realiza passeio no carro pelo trilho
	 */
	public void iniciarPasseio() {
		someQtdPasseios();
		Passeio passeio = new Passeio(montanhaRussaREF.getTrilha());
		passeio.run();
		Notes.print(this, Mensagens.CARRO_QTD_PASSEIO, qtdPasseios, qtdPasseiosLimite);
	}

	/**
	 * Incrementa o numero de passeios, verificando o limite
	 */
	public void someQtdPasseios() {
		qtdPasseios += 1;

		if (qtdPasseios == qtdPasseiosLimite) {
			setLigado(false);
			Notes.print(this, Mensagens.CARRO_LIMITE);
			bloquedor.desploquearTodos();
		}
	}

	/**
	 * @return Verdadeiro se o carro está cheio, falso caso contrário
	 */
	public boolean carroCheio() {
		return qtdPassageiros == capacidade;
	}

	/**
	 * Ato de passageiro entrar no carro, um por vez
	 * @param passageiro
	 * @throws Exception 
	 */
	public synchronized void entrar(Passageiro passageiro) throws Exception {
		if (!isLigado() || carroCheio()) {	
			throw new Exception("Carro cheio ou desligado");
		}
		passageiros.add(passageiro);
		Notes.print(this, Mensagens.CARRO_PASSAGEIRO_EMBARCOU, passageiro.toString());
		Notes.print(this, Mensagens.CARRO_LOTACAO, passageiros.size(), capacidade, passageiros.toString());
		++qtdPassageiros;
	}
	
	

	/**
	 * Processo de embarcar, tenta entrar no carro, ou fica na fila esperando.
	 * @param passageiro
	 * @return se conseguiu embarcar, falso caso não tenha conseguido
	 */
	public boolean embarcar(Passageiro passageiro) {
		try {

		
			if (carroCheio() || bloquedor.getTamanhoLista()>0) {
				Notes.print(this, Mensagens.CARRO_FILA_ESPERA, (bloquedor.getTamanhoLista() + 1));
				bloquedor.bloquear(passageiro);
			}
			entrar(passageiro);

		} catch (Exception e) {
			Notes.print(this, Mensagens.CARRO_NO_EMBARCOU, passageiro.toString());
			return false;
		}

		return true;
	}
	
	/**
	 * verifica se ainda tem alguma pessoa esperando e manda ir embora 
	 */
	private void verificacaooFinal() {
		for(Visitante v : montanhaRussaREF.getParqueREF().getVisitantes()){
			synchronized (v) {
				v.notify();
			}
		}
	}
	
	/**
	 * Tempo de espera ocioso
	 * @param milisegundos
	 */
	private void delay(int milisegundos) {
		try {
			Thread.sleep(milisegundos);
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

	public boolean isLigado() {
		delay(5);
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
