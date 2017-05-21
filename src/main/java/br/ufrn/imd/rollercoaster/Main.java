package br.ufrn.imd.rollercoaster;

import java.util.ArrayList;
import java.util.List;

public class Main {
	
	private static final String TAG = "["+Main.class.getSimpleName().toUpperCase()+"] ";
	
	public static void main(String args[]){
		
		Notes.print(TAG + "Programa Iniciado.");
		
		int CAPACIDADE_CARRO = 15;
		int DISTANCIA_TRILHA = 5;
		int QUANTIDADE_PASSAGEIROS =  100;
		int QUANTIDADE_PASSEIOS_POR_DIA =  50;
		
		MontanhaRussa montanhaRussa = new MontanhaRussa(CAPACIDADE_CARRO, DISTANCIA_TRILHA, QUANTIDADE_PASSEIOS_POR_DIA);
		
		
		ParqueDiversoes parqueDiversoes = new ParqueDiversoes(montanhaRussa);
		
		//Criação de passageiros
		List<Passageiro> passageiros = new ArrayList<Passageiro>();
		for (int i = 0; i < QUANTIDADE_PASSAGEIROS; i++) {
			Passageiro passageiro = new Passageiro(i, parqueDiversoes);
			passageiros.add(passageiro);
		}
		
		
		parqueDiversoes.init(passageiros);
	
		
		

	}

}
