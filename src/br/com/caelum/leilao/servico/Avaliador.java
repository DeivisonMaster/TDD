package br.com.caelum.leilao.servico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

public class Avaliador {
	private double maiorLance = Double.NEGATIVE_INFINITY;
	private double menorLance = Double.POSITIVE_INFINITY;
	private List<Lance> maiores;

	public void avalia(Leilao leilao){
		
		if(leilao.getLances().size() == 0){
			throw new RuntimeException("Não é possivel avaliar leilões sem lances");
		}
		
		for(Lance lance : leilao.getLances()){
			if(lance.getValor() > maiorLance){
				maiorLance = lance.getValor();
			}
			if(lance.getValor() < menorLance){
				menorLance = lance.getValor();
			}
		}
		
		// Calcular 3 maiores lances de um leilão
		maiores = new ArrayList<Lance>(leilao.getLances());
		Collections.sort(maiores, new Comparator<Lance>() {

			public int compare(Lance o1, Lance o2) {
				if(o1.getValor() < o2.getValor()){
					return 1;
				}
				else if(o1.getValor() > o2.getValor()){
					return -1;
				}
				return 0;
			}
		});
		
		// inicia do primeiro item e busca 3 itens da lista
		maiores = maiores.subList(0, maiores.size() > 3 ? 3 : maiores.size());
	}
	
	public List<Lance> getTresMaiores() {
		return maiores;
	}
	
	public double getMaiorLance() {
		return maiorLance;
	}
	
	public double getMenorLance() {
		return menorLance;
	}
}
