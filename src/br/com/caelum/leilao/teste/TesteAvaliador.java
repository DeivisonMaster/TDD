package br.com.caelum.leilao.teste;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class TesteAvaliador {
	private Avaliador leiloeiro;
	private Usuario joao;
	private Usuario jose;
	private Usuario maria;

	@Before // JUnit executa o metodo criaAvaliador antes do uso
	public void criaAvaliador() {
		this.leiloeiro = new Avaliador();
		this.joao = new Usuario("joao");
		this.jose = new Usuario("jose");
		this.maria = new Usuario("maria");
	}

	@Test
	public void deveEntenderLancesEmOrdemCrescente() {

		// 1° cenario

		Leilao leilao = new Leilao("Playstation 4");
		leilao.propoe(new Lance(joao, 250.0));
		leilao.propoe(new Lance(jose, 300.0));
		leilao.propoe(new Lance(maria, 400.0));

		// 2° ação
		leiloeiro.avalia(leilao);

		// 3° validação
		double maiorEsperado = 400;
		double menorEsperado = 250;

		assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);
	}

	@Test
	public void deveEntenderLeilaoComApenasUmLance() {
		Usuario joao = new Usuario("joao");

		Leilao leilao = new Leilao("PS4 Novo");
		leilao.propoe(new Lance(joao, 1000.0));

		leiloeiro.avalia(leilao);

		assertEquals(1000.0, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(1000.0, leiloeiro.getMenorLance(), 0.00001);
	}

	@Test
	public void deveEncontrarOsTresMaioresLances() {
		Usuario joao = new Usuario("joao");
		Usuario maria = new Usuario("maria");

//		Leilao leilao = new Leilao("PS3 Novo");
//		leilao.propoe(new Lance(joao, 100.0));
//		leilao.propoe(new Lance(maria, 200.0));
//		leilao.propoe(new Lance(joao, 300.0));
//		leilao.propoe(new Lance(maria, 400.0));

		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 novo")
				.lance(joao, 100.0)
				.lance(maria, 200.0)
				.lance(joao, 300.0)
				.lance(maria, 400.0)
				.constroi();
		
		leiloeiro.avalia(leilao);

		List<Lance> maiores = leiloeiro.getTresMaiores();
		assertEquals(3, maiores.size());

		// verifica o conteudo da lista
		assertEquals(400.0, maiores.get(0).getValor(), 0.00001);
		assertEquals(300.0, maiores.get(1).getValor(), 0.00001);
		assertEquals(200.0, maiores.get(2).getValor(), 0.00001);
	}
	
	
	@Test(expected=RuntimeException.class)
	public void naoDeveAvaliarLeiloesSemNenhumLanceDado(){
		Leilao leilao = new CriadorDeLeilao().para("Playstation 4 novo").constroi();
		
		leiloeiro.avalia(leilao);
	}
}
