package br.ufma.mestrado.computacao.ps.cinema.dominio;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import br.ufma.mestrado.computacao.ps.cinema.dominio.Sessao;

public class SessaoTest {

	@Test
	public void testarSeSessaoJahIniciou() {
		Calendar dataInicio = Calendar.getInstance();
		// Diminuindo em uma hora o inicio da sessao a partir da data/hora atual
		dataInicio.add(Calendar.HOUR, -1);

		Sessao sessao = new Sessao();
		sessao.setInicio(dataInicio.getTime());

		Assert.assertFalse(sessao.naoIniciou());
	}

	@Test
	public void testarSeSessaoAindaNaoIniciou() {
		Calendar dataInicio = Calendar.getInstance();
		// Aumentando em uma hora o inicio da sessao a partir da data/hora atual
		dataInicio.add(Calendar.HOUR, 1);

		Sessao sessao = new Sessao();
		sessao.setInicio(dataInicio.getTime());

		Assert.assertTrue(sessao.naoIniciou());
	}
}
