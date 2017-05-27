package br.ufrn.imd.rollercoaster;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.rollercoaster.model.MontanhaRussa;
import br.ufrn.imd.rollercoaster.model.ParqueDiversoes;
import br.ufrn.imd.rollercoaster.model.Passageiro;
import br.ufrn.imd.rollercoaster.model.Visitante;
import br.ufrn.imd.rollercoaster.util.Notes;

public class Main {
	
	// Definição de constantes.
	private static final int QUANTIDADE_VISITANTES_PADRAO = 40;
	private static final int INDICE_VISITANTES = 0;
	private static final int CAPACIDADE_CARRO_PADRAO = 10;
	private static final int INDICE_CAPACIDADE_CARRO = 1;
	private static final int QUANTIDADE_PASSEIOS_PADRAO = 5;
	private static final int INDICE_LIMITE_PASSEIOS = 2;
	
	private static final int DISTANCIA_TRILHA = 5;
	
	
	
	public static void main(String args[]){
				
		Notes.print(Main.class, Mensagens.MAIN_PROGRAMA_INICIADO);
			
		List<Integer> argumentos = receberArgs(args);
		
		int CAPACIDADE_CARRO = argumentos.get(INDICE_CAPACIDADE_CARRO);
		int QUANTIDADE_PASSAGEIROS =  argumentos.get(INDICE_VISITANTES);
		int QUANTIDADE_PASSEIOS_POR_DIA =  argumentos.get(INDICE_LIMITE_PASSEIOS);
		
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
		
		parqueDiversoes.printRelatorio();

	}
	
	public static List<Integer> receberArgs (String args[]) {
		List<Integer> lista = new ArrayList<Integer>(3);
				
		//Valores padrões.
		lista.add(INDICE_VISITANTES, QUANTIDADE_VISITANTES_PADRAO);
		lista.add(INDICE_CAPACIDADE_CARRO, CAPACIDADE_CARRO_PADRAO);
		lista.add(INDICE_LIMITE_PASSEIOS, QUANTIDADE_PASSEIOS_PADRAO);
		
		try {
			if(args.length > 0) {
				lista.add(INDICE_VISITANTES, Integer.parseInt(args[0]));
				if (args.length > 1) {
					lista.add(INDICE_CAPACIDADE_CARRO, Integer.parseInt(args[1]));
					if (args.length > 2) {
						lista.add(INDICE_LIMITE_PASSEIOS, Integer.parseInt(args[2]));
					}
				} 
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}

}
