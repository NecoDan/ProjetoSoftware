package br.ufma.mestrado.computacao.ps.cinema.negocio;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ufma.mestrado.computacao.ps.cinema.dominio.Assento;
import br.ufma.mestrado.computacao.ps.cinema.dominio.Dinheiro;
import br.ufma.mestrado.computacao.ps.cinema.dominio.Ingresso;
import br.ufma.mestrado.computacao.ps.cinema.dominio.Maquina;
import br.ufma.mestrado.computacao.ps.cinema.dominio.Pagamento;
import br.ufma.mestrado.computacao.ps.cinema.dominio.Sessao;
import br.ufma.mestrado.computacao.ps.cinema.dominio.TipoPagamento;
import br.ufma.mestrado.computacao.ps.cinema.negocio.CinemaNegocio;
import br.ufma.mestrado.computacao.ps.cinema.util.HibernateUtil;

public class CinemaNegocioTest {

	private Session session;

	private CinemaNegocio cinemaNegocio = new CinemaNegocio();

	private Maquina maquina;

	@Before
	public void setUp() {
		session = HibernateUtil.getInstance().getSession();
		maquina = new Maquina();
		maquina.setId(1);
		maquina.setQtdImpressoes(10);
	}

	@After
	public void tearDown() {
		session.close();
	}

	@Test
	public void pagarIngressoComCartao() throws Exception {
		String cartao = "123456";

		Assento assento = new Assento();
		assento.setId(1);
		Sessao sessao = new Sessao();
		sessao.setId(1);

		Ingresso ingresso = new Ingresso(assento, sessao);
		ingresso.setPreco(new BigDecimal(27.50));

		Pagamento pagamento = cinemaNegocio.pagarIngressoComCartao(maquina, cartao, ingresso, true);

		Assert.assertNotNull(pagamento);
		Assert.assertNotNull(pagamento.getId());
	}

	@Test(expected = Exception.class)
	public void pagarIngressoComCartaoEMaquinaSemPapel() throws Exception {
		String cartao = "123456";

		Assento assento = new Assento();
		assento.setId(1);
		Sessao sessao = new Sessao();
		sessao.setId(1);

		Ingresso ingresso = new Ingresso(assento, sessao);
		ingresso.setPreco(new BigDecimal(27.50));

		maquina.setQtdImpressoes(0);

		cinemaNegocio.pagarIngressoComCartao(maquina, cartao, ingresso, true);
	}

	@Test
	public void pagarIngressoComDinheiro() throws Exception {
		Assento assento = new Assento();
		assento.setId(1);
		Sessao sessao = new Sessao();
		sessao.setId(1);

		Ingresso ingresso = new Ingresso(assento, sessao);
		ingresso.setPreco(new BigDecimal(27.50));

		Dinheiro dinheiro = new Dinheiro();
		dinheiro.setQtdCedulas10(2);
		dinheiro.setQtdCedulas5(1);
		dinheiro.setQtdMoedas1Real(2);
		dinheiro.setQtdMoedas50Centavos(1);

		Pagamento pagamento = cinemaNegocio.pagarIngressoComDinheiro(maquina, ingresso, dinheiro);

		Assert.assertNotNull(pagamento);
		Assert.assertNotNull(pagamento.getId());
		Assert.assertNotNull(pagamento.getValorTroco());
		Assert.assertTrue(pagamento.getValorTroco().compareTo(BigDecimal.ZERO) == 0);
	}

	@Test(expected = Exception.class)
	public void pagarIngressoComDinheiroMaquinaSemTroco() throws Exception {
		Assento assento = new Assento();
		assento.setId(1);
		Sessao sessao = new Sessao();
		sessao.setId(1);

		Ingresso ingresso = new Ingresso(assento, sessao);
		ingresso.setPreco(new BigDecimal(27.50));

		Dinheiro dinheiro = new Dinheiro();
		dinheiro.setQtdCedulas10(3);

		Maquina maquina = new Maquina();
		maquina.setId(1);

		cinemaNegocio.pagarIngressoComDinheiro(maquina, ingresso, dinheiro);
	}

	@Test(expected = Exception.class)
	public void pagarIngressoComDinheiroInsuficiente() throws Exception {
		Assento assento = new Assento();
		assento.setId(1);
		Sessao sessao = new Sessao();
		sessao.setId(1);

		Ingresso ingresso = new Ingresso(assento, sessao);
		ingresso.setPreco(new BigDecimal(2));

		Dinheiro dinheiro = new Dinheiro();
		dinheiro.setQtdCedulas10(1);

		Maquina maquina = new Maquina();
		maquina.setId(1);

		cinemaNegocio.pagarIngressoComDinheiro(maquina, ingresso, dinheiro);
	}

	@Test(expected = Exception.class)
	public void pagarIngressoComDinheiroEMaquinaSemPapel() throws Exception {
		Assento assento = new Assento();
		assento.setId(1);
		Sessao sessao = new Sessao();
		sessao.setId(1);

		Ingresso ingresso = new Ingresso(assento, sessao);
		ingresso.setPreco(new BigDecimal(27.50));

		Dinheiro dinheiro = new Dinheiro();
		dinheiro.setQtdCedulas10(2);
		dinheiro.setQtdCedulas5(1);
		dinheiro.setQtdMoedas1Real(2);
		dinheiro.setQtdMoedas50Centavos(1);

		maquina.setQtdImpressoes(0);

		cinemaNegocio.pagarIngressoComDinheiro(maquina, ingresso, dinheiro);
	}

	@Test
	public void testarRecuperarTiposPagamento() {
		List<TipoPagamento> tipos = cinemaNegocio.recuperarTiposPagamento();
		Assert.assertTrue(tipos.size() > 0);
	}

}
