package br.ufrn.imd.rollercoaster;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.rollercoaster.util.RandInt;

public class Main {
	
	public static void main(String args[]){
		Notes.print("[MAIN] Programa Iniciado.");
		
		int CAPACIDADE_CARRO = 1;
		int DISTANCIA_TRILHA = 2;
		int QUANTIDADE_PASSAGEIROS =  1;
		
		
		MontanhaRussa montanhaRussa = new MontanhaRussa(CAPACIDADE_CARRO, DISTANCIA_TRILHA);
		
		ParqueDiversoes parque = new ParqueDiversoes(montanhaRussa);
		

		//Criação de passageiros
		List<Passageiro> passageiros = new ArrayList<Passageiro>();
		for (int i = 0; i < QUANTIDADE_PASSAGEIROS; i++) {
			Passageiro passageiro = new Passageiro(i, parque);
			passageiros.add(passageiro);
		}
		
		//Tempo de variação em que um passageiro entra no parque (millisegundos)
		RandInt randInt = new RandInt(200, 3000);

		//Inicialização de passageiros
		for (Passageiro passageiro : passageiros) {
			try {
				Thread.sleep(randInt.rand());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			passageiro.start();
		}

	}

}
