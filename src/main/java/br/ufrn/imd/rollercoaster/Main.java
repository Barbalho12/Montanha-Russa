package br.ufrn.imd.rollercoaster;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.rollercoaster.util.RandInt;

public class Main {
	
	public static void main(String args[]){
		
		
		int CAPACIDADE_CARRO = 20;
		int DISTANCIA_TRILHA =  10;
		
		
		
		MontanhaRussa montanhaRussa = new MontanhaRussa(CAPACIDADE_CARRO, DISTANCIA_TRILHA);
		
		ParqueDiversoes parque = new ParqueDiversoes(montanhaRussa);
		

		
		List<Passageiro> passageiros = new ArrayList<Passageiro>();
		for (int i = 0; i < 50; i++) {
			Passageiro passageiro = new Passageiro(i, parque);
			passageiros.add(passageiro);
		}
		
		RandInt randInt = new RandInt(200, 3000);

		for (Passageiro passageiro : passageiros) {
			try {
				/**/
				Thread.sleep(randInt.rand());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			passageiro.start();
		}
		

		

	}

}
