package br.ufma.mestrado.computacao.ps.cinema.negocio;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import br.ufma.mestrado.computacao.ps.cinema.dominio.Filme;
import br.ufma.mestrado.computacao.ps.cinema.dominio.Pagamento;
import br.ufma.mestrado.computacao.ps.cinema.dominio.Sala;
import br.ufma.mestrado.computacao.ps.cinema.dominio.Sessao;

public class ManutencacaoNegocioTest {

	private Filme filme;
	private ManutencacaoNegocio manutencacaoNegocio = new ManutencacaoNegocio();
	private Sessao sessao;

	@Before
	public void setUp() {
		filme = new Filme();
		filme.setAno(2013);
		filme.setClassificacao(0);
		filme.setDuracao(110);
		filme.setEmCartazAte(Calendar.getInstance().getTime());
		filme.setNome("Se Beber Não Case");
		filme.setSinopse("bla bla bla");

		Sala sala = new Sala();
		sala.setNome("Sala Teste");
		sala.setQuantidadeAssentos(100);
		sala.setQuantidadeFileiras(10);

		sessao = new Sessao();
		sessao.setFilme(filme);
		sessao.setPrecoIngresso(new BigDecimal(15.50));
		sessao.setSala(sala);
	}

	@Test
	public void testarRecuperarPagamentos() {
		Calendar dataInicio = Calendar.getInstance();
		dataInicio.set(2013, Calendar.JANUARY, 1);

		Calendar dataFim = Calendar.getInstance();
		dataFim.set(2013, Calendar.DECEMBER, 31);

		List<Pagamento> pagamentos = manutencacaoNegocio.recuperarPagamentos(dataInicio, dataFim);

		for (Pagamento pagamento : pagamentos) {
			Logger.getLogger("ManutencaoNegocioTest").log(Level.INFO, "Pagamento: " + pagamento.getId());
		}
	}

	@Test
	public void testarSalvarFilmeEmCartaz() throws Exception {
		Calendar amanha = Calendar.getInstance();
		amanha.add(Calendar.DAY_OF_MONTH, 1);
		filme.setEmCartazAte(amanha.getTime());
		manutencacaoNegocio.salvar(filme);

		Assert.assertNotNull(filme.getId());
	}

	@Test(expected = Exception.class)
	public void testarSalvarFilmeQueNaoEstaEmCartaz() throws Exception {
		Calendar ontem = Calendar.getInstance();
		ontem.add(Calendar.DAY_OF_MONTH, -1);
		filme.setEmCartazAte(ontem.getTime());
		manutencacaoNegocio.salvar(filme);
	}

	@Test
	public void testarSalvarSessaoNaoIniciada() throws Exception {
		Calendar inicio = Calendar.getInstance();
		inicio.add(Calendar.HOUR_OF_DAY, 1);
		inicio.add(Calendar.MINUTE, 30);
		sessao.setInicio(inicio.getTime());

		Calendar fim = Calendar.getInstance();
		fim.add(Calendar.HOUR_OF_DAY, 2);
		fim.add(Calendar.MINUTE, 20);
		sessao.setFim(fim.getTime());

		manutencacaoNegocio.salvar(sessao);

		Assert.assertNotNull(sessao.getId());
	}

	@Test(expected = Exception.class)
	public void testarSalvarSessaoJahIniciada() throws Exception {
		Calendar inicio = Calendar.getInstance();
		inicio.add(Calendar.HOUR_OF_DAY, -1);
		inicio.add(Calendar.MINUTE, 30);
		sessao.setInicio(inicio.getTime());

		Calendar fim = Calendar.getInstance();
		fim.add(Calendar.HOUR_OF_DAY, 1);
		fim.add(Calendar.MINUTE, 20);
		sessao.setFim(fim.getTime());

		manutencacaoNegocio.salvar(sessao);
	}

	@Test(expected = Exception.class)
	public void testarSalvarSessaoJahTerminada() throws Exception {
		Calendar inicio = Calendar.getInstance();
		inicio.add(Calendar.HOUR_OF_DAY, -2);
		inicio.add(Calendar.MINUTE, 30);
		sessao.setInicio(inicio.getTime());

		Calendar fim = Calendar.getInstance();
		fim.add(Calendar.HOUR_OF_DAY, -1);
		fim.add(Calendar.MINUTE, 20);
		sessao.setFim(fim.getTime());

		manutencacaoNegocio.salvar(sessao);
	}
}
