package br.ufrn.imd.rollercoaster;

import java.util.List;

import br.ufrn.imd.rollercoaster.util.RandInt;

public class ParqueDiversoes{
	
	@SuppressWarnings("unused")
	private final String TAG = "["+this.getClass().getSimpleName().toUpperCase()+"] ";
	
	private MontanhaRussa montanhaRussa;
	private List<Passageiro> visitantes;

	public ParqueDiversoes(MontanhaRussa montanhaRussa){
		this.montanhaRussa = montanhaRussa;
	}
	
	public void init(List<Passageiro> visitantes){
		this.visitantes = visitantes;
		
			try {
				if(montanhaRussa.getQtdPasseiosLimite() > this.visitantes.size()){
					throw new Exception("O numero de visitantes é insuficiente para brincar na Montanha Russa!");
				}else{
					montanhaRussa.init();
					
					//Tempo de variação em que um passageiro entra no parque (millisegundos)
					RandInt randInt = new RandInt(200, 3000);
					
					//Inicialização de passageiros
					for (Passageiro passageiro : visitantes) {
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
	}

	public MontanhaRussa getMontanhaRussa() {
		return montanhaRussa;
	}

	public void setMontanhaRussa(MontanhaRussa montanhaRussa) {
		this.montanhaRussa = montanhaRussa;
	}

}
