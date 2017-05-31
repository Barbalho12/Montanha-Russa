package br.ufrn.imd.rollercoaster.model;

import java.util.List;

import br.ufrn.imd.rollercoaster.Mensagens;
import br.ufrn.imd.rollercoaster.util.Notes;
import br.ufrn.imd.rollercoaster.util.RandInt;

public class ParqueDiversoes{

	private MontanhaRussa montanhaRussa;
	private List<Visitante> visitantes;
	private long temporizador;
	
	public ParqueDiversoes(){
		
	}

	public List<Visitante> getVisitantes() {
		return visitantes;
	}

	public void setVisitantes(List<Visitante> visitantes) {
		this.visitantes = visitantes;
	}

	public ParqueDiversoes(MontanhaRussa montanhaRussa){
		this.montanhaRussa = montanhaRussa;
		
	}
	
	public void init(List<Visitante> visitantes){
		this.visitantes = visitantes;
		
			try {
				if(montanhaRussa.getCarro().getCapacidade() > this.visitantes.size()){
					throw new Exception(Mensagens.PARQUEDIVERSOES_NUMERO_VISITANTES_EXCPETION);
				}else{
					Notes.print(this, Mensagens.PARQUEDIVERSOES_ABERTO);
					
					montanhaRussa.init();
					temporizador = System.currentTimeMillis(); 
					
					//Tempo de variação em que um passageiro entra no parque (millisegundos)
					RandInt randInt = new RandInt(200, 1000);

					//Inicialização de passageiros
					for (Visitante passageiro : visitantes) {
						try {
							Thread.sleep(randInt.rand());
						} catch (InterruptedException e) {
							e.printStackTrace();
							System.exit(0);
						}
						passageiro.start();
					}
				}
				
			} catch (Exception e) {
				System.err.println(e.getMessage());
				System.exit(1);
			}
			
			for (Visitante visitante : visitantes) {
				try {
					visitante.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
					System.exit(0);
				}
			}
			temporizador = System.currentTimeMillis() - temporizador;
			Notes.print(this, Mensagens.PARQUEDIVERSOES_FECHADO);
			
			
	}
	
	public void printRelatorio(){
		System.out.println("----------------Relatório Final-----------------");
		Notes.print(this, Mensagens.PARQUEDIVERSOES_TEMPO_ABERTO, (temporizador / 1000));
		Notes.print(this, Mensagens.PARQUEDIVERSOES_QTD_VISITANTES, visitantes.size());
		System.out.println("....");
		Notes.print(this, Mensagens.PARQUEDIVERSOES_LIMITE_PASSEIOS, montanhaRussa.getQtdPasseiosLimite());
		System.out.println("....");
		Notes.print(this, Mensagens.PARQUEDIVERSOES_LIMITE_CARRO, montanhaRussa.getCarro().getCapacidade());
		for (Visitante visitante : visitantes) {
			System.out.println("....");
			visitante.gerarRelatorio();
		}
		System.out.println("-------------------------");
	}

	public MontanhaRussa getMontanhaRussa() {
		return montanhaRussa;
	}

	public void setMontanhaRussa(MontanhaRussa montanhaRussa) {
		this.montanhaRussa = montanhaRussa;
	}

}
