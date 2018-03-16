package br.com.caelum.leilao.builder;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class CriadorDeLeilao {
	private Leilao leilao;
	private Lance lance;

	public CriadorDeLeilao para(String descricao) {
		this.leilao = new Leilao(descricao);
		return this;
	}

	public CriadorDeLeilao lance(Usuario usuario, double valorLance) {
		this.lance = new Lance(usuario, valorLance);
		this.leilao.propoe(lance);
		
		return this;
	}

	public Leilao constroi() {
		return leilao;
	}
	
}
