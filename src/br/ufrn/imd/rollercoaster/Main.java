package br.ufrn.imd.rollercoaster;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.rollercoaster.model.MontanhaRussa;
import br.ufrn.imd.rollercoaster.model.ParqueDiversoes;
import br.ufrn.imd.rollercoaster.model.Passageiro;
import br.ufrn.imd.rollercoaster.model.Visitante;
import br.ufrn.imd.rollercoaster.util.Notes;

public class Main {

	public static void main(String args[]){
		
		Notes.print(Main.class, Mensagens.MAIN_PROGRAMA_INICIADO);
		
		int CAPACIDADE_CARRO = 10;
		int DISTANCIA_TRILHA = 5;
		int QUANTIDADE_PASSAGEIROS =  40;
		int QUANTIDADE_PASSEIOS_POR_DIA =  10;
		
		MontanhaRussa montanhaRussa = new MontanhaRussa(CAPACIDADE_CARRO, DISTANCIA_TRILHA, QUANTIDADE_PASSEIOS_POR_DIA);
		
		ParqueDiversoes parqueDiversoes = new ParqueDiversoes(montanhaRussa);
		
		//Criação de passageiros
		List<Visitante> visitantes = new ArrayList<Visitante>();
		for (int i = 0; i < QUANTIDADE_PASSAGEIROS; i++) {
			
			/*Os visitantes terão ciclo de vida de passageiro da motanha russa*/
			Passageiro passageiro = new Passageiro(i, parqueDiversoes);
			visitantes.add(passageiro);
		}
		
		parqueDiversoes.init(visitantes);

	}

}
