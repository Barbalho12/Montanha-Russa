package br.ufrn.imd.rollercoaster;

import java.util.ArrayList;
import java.util.List;

public class Main {
	
	private static final String TAG = "["+Main.class.getSimpleName().toUpperCase()+"] ";
	
	public static void main(String args[]){
		
		Notes.print(TAG + "Programa Iniciado.");
		
		int CAPACIDADE_CARRO = 4;
		int DISTANCIA_TRILHA = 4;
		int QUANTIDADE_PASSAGEIROS =  6;
		int QUANTIDADE_PASSEIOS_POR_DIA =  2;
		
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
