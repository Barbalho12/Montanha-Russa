package br.ufrn.imd.rollercoaster.model;

import java.util.List;

import br.ufrn.imd.rollercoaster.Mensagens;
import br.ufrn.imd.rollercoaster.util.Notes;
import br.ufrn.imd.rollercoaster.util.RandInt;

public class ParqueDiversoes{

	private MontanhaRussa montanhaRussa;
	private List<Visitante> visitantes;

	public ParqueDiversoes(MontanhaRussa montanhaRussa){
		this.montanhaRussa = montanhaRussa;
	}
	
	public void init(List<Visitante> visitantes){
		this.visitantes = visitantes;
		Notes.print(this, Mensagens.PARQUEDIVERSOES_ABERTO);
			try {
				if(montanhaRussa.getCarro().getCapacidade() > this.visitantes.size()){
					throw new Exception(Mensagens.PARQUEDIVERSOES_NUMERO_VISITANTES_EXCPETION);
				}else{
					montanhaRussa.init();
					
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
				e.printStackTrace();
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
			Notes.print(this, Mensagens.PARQUEDIVERSOES_FECHADO);
	}

	public MontanhaRussa getMontanhaRussa() {
		return montanhaRussa;
	}

	public void setMontanhaRussa(MontanhaRussa montanhaRussa) {
		this.montanhaRussa = montanhaRussa;
	}

}
