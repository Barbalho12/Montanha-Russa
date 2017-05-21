package br.ufrn.imd.rollercoaster.model;

import java.util.List;

import br.ufrn.imd.rollercoaster.util.Notes;
import br.ufrn.imd.rollercoaster.util.RandInt;

public class ParqueDiversoes{
	
	private final String TAG = "["+this.getClass().getSimpleName().toUpperCase()+"]\t";
	
	private MontanhaRussa montanhaRussa;
	private List<Visitante> visitantes;

	public ParqueDiversoes(MontanhaRussa montanhaRussa){
		this.montanhaRussa = montanhaRussa;
	}
	
	public void init(List<Visitante> visitantes){
		this.visitantes = visitantes;
		Notes.print(TAG+"O Parque de diversões está aberto.");
			try {
				if(montanhaRussa.getCarro().getCapacidade() > this.visitantes.size()){
					throw new Exception("O numero de visitantes é insuficiente para brincar na Montanha Russa!");
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
			Notes.print(TAG+"O Parque de diversões encerrou suas atividades.");
	}

	public MontanhaRussa getMontanhaRussa() {
		return montanhaRussa;
	}

	public void setMontanhaRussa(MontanhaRussa montanhaRussa) {
		this.montanhaRussa = montanhaRussa;
	}

}
