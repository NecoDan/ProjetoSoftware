package br.ufma.mestrado.computacao.ps.cinema.dominio;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

public class MaquinaTest {

	@Test
	public void testarTotalEmReaisSomenteComCedulas50() {
		Maquina maquina = new Maquina();
		maquina.setQtdCedulas50(3);

		BigDecimal totalEsperado = new BigDecimal(150);

		Assert.assertEquals(totalEsperado, maquina.getTotalEmReais());
	}

	@Test
	public void testarTotalEmReaisSomenteComCedulas10() {
		Maquina maquina = new Maquina();
		maquina.setQtdCedulas10(6);

		BigDecimal totalEsperado = new BigDecimal(60);

		Assert.assertEquals(totalEsperado, maquina.getTotalEmReais());
	}

	@Test
	public void testarTotalEmReaisSomenteComCedulas5() {
		Maquina maquina = new Maquina();
		maquina.setQtdCedulas5(5);

		BigDecimal totalEsperado = new BigDecimal(25);

		Assert.assertEquals(totalEsperado, maquina.getTotalEmReais());
	}

	@Test
	public void testarTotalEmReaisComCedulasDiversas() {
		Maquina maquina = new Maquina();
		maquina.setQtdCedulas50(2);
		maquina.setQtdCedulas10(3);
		maquina.setQtdCedulas5(1);

		BigDecimal totalEsperado = new BigDecimal(135);

		Assert.assertEquals(totalEsperado, maquina.getTotalEmReais());
	}

	@Test
	public void testarTotalEmReaisSomenteComMoedasDe1Real() {
		Maquina maquina = new Maquina();
		maquina.setQtdMoedas1Real(3);

		BigDecimal totalEsperado = new BigDecimal(3);

		Assert.assertEquals(totalEsperado, maquina.getTotalEmReais());
	}

	@Test
	public void testarTotalEmReaisSomenteComMoedasDe50Centavos() {
		Maquina maquina = new Maquina();
		maquina.setQtdMoedas50Centavos(7);

		BigDecimal totalEsperado = new BigDecimal(3.5);

		Assert.assertEquals(totalEsperado, maquina.getTotalEmReais());
	}

	@Test
	public void testarTotalEmReaisComMoedasDiversas() {
		Maquina maquina = new Maquina();
		maquina.setQtdMoedas1Real(8);
		maquina.setQtdMoedas50Centavos(5);

		BigDecimal totalEsperado = new BigDecimal(10.5);

		Assert.assertEquals(totalEsperado, maquina.getTotalEmReais());
	}

	@Test
	public void testarTotalEmReaisComCedulasEMoedasDiversas() {
		Maquina maquina = new Maquina();
		maquina.setQtdCedulas50(2);
		maquina.setQtdCedulas10(3);
		maquina.setQtdCedulas5(1);
		maquina.setQtdMoedas1Real(8);
		maquina.setQtdMoedas50Centavos(5);

		BigDecimal totalEsperado = new BigDecimal(145.5);

		Assert.assertEquals(totalEsperado, maquina.getTotalEmReais());
	}

	@Test(expected = Exception.class)
	public void testarImpressaoDeIngressoSemQtdImpressoes() throws Exception {
		Maquina maquina = new Maquina();
		maquina.setQtdImpressoes(0);

		maquina.imprimirIngresso();

		Assert.assertEquals(0, maquina.getQtdImpressoes());
	}

	@Test
	public void testarImpressaoDeIngressoComQtdImpressoes() throws Exception {
		Maquina maquina = new Maquina();
		maquina.setQtdImpressoes(2);

		maquina.imprimirIngresso();

		Assert.assertEquals(1, maquina.getQtdImpressoes());
	}

	@Test
	public void testarSeImpressoraPodeImprimir() throws Exception {
		Maquina maquina = new Maquina();
		maquina.setQtdImpressoes(1);

		Assert.assertTrue(maquina.podeImprimir());

		maquina.imprimirIngresso();

		Assert.assertFalse(maquina.podeImprimir());
	}

	@Test
	public void testarSeImpressoraNaoPodeImprimir() {
		Maquina maquina = new Maquina();
		maquina.setQtdImpressoes(0);

		Assert.assertFalse(maquina.podeImprimir());
	}

	@Test(expected = Exception.class)
	public void testarRetiradaDeDinheiroComQuantidadeNotasDe50ReaisInsuficiente() throws Exception {
		Maquina maquina = new Maquina();
		maquina.setQtdCedulas50(2);

		Dinheiro dinheiro = new Dinheiro();
		dinheiro.setQtdCedulas50(3);

		maquina.retirarDinheiro(dinheiro);
	}

	@Test
	public void testarRetiradaDeDinheiroComQuantidadeNotasDe50ReaisSuficiente() throws Exception {
		Maquina maquina = new Maquina();
		maquina.setQtdCedulas50(2);

		Dinheiro dinheiro = new Dinheiro();
		dinheiro.setQtdCedulas50(2);

		maquina.retirarDinheiro(dinheiro);

		Assert.assertEquals(0, maquina.getQtdCedulas50());
	}

	@Test
	public void testarRetiradaDeDinheiroComQuantidadeNotasDe50ReaisMaisQueSuficiente() throws Exception {
		Maquina maquina = new Maquina();
		maquina.setQtdCedulas50(6);

		Dinheiro dinheiro = new Dinheiro();
		dinheiro.setQtdCedulas50(3);

		maquina.retirarDinheiro(dinheiro);

		Assert.assertEquals(3, maquina.getQtdCedulas50());
	}

	@Test(expected = Exception.class)
	public void testarRetiradaDeDinheiroComQuantidadeNotasDe10ReaisInsuficiente() throws Exception {
		Maquina maquina = new Maquina();
		maquina.setQtdCedulas10(1);

		Dinheiro dinheiro = new Dinheiro();
		dinheiro.setQtdCedulas10(5);

		maquina.retirarDinheiro(dinheiro);
	}

	@Test
	public void testarRetiradaDeDinheiroComQuantidadeNotasDe10ReaisSuficiente() throws Exception {
		Maquina maquina = new Maquina();
		maquina.setQtdCedulas10(1);

		Dinheiro dinheiro = new Dinheiro();
		dinheiro.setQtdCedulas10(1);

		maquina.retirarDinheiro(dinheiro);

		Assert.assertEquals(0, maquina.getQtdCedulas10());
	}

	@Test
	public void testarRetiradaDeDinheiroComQuantidadeNotasDe10ReaisMaisQueSuficiente() throws Exception {
		Maquina maquina = new Maquina();
		maquina.setQtdCedulas10(8);

		Dinheiro dinheiro = new Dinheiro();
		dinheiro.setQtdCedulas10(1);

		maquina.retirarDinheiro(dinheiro);

		Assert.assertEquals(7, maquina.getQtdCedulas10());
	}

	@Test(expected = Exception.class)
	public void testarRetiradaDeDinheiroComQuantidadeNotasDe5ReaisInsuficiente() throws Exception {
		Maquina maquina = new Maquina();
		maquina.setQtdCedulas5(4);

		Dinheiro dinheiro = new Dinheiro();
		dinheiro.setQtdCedulas5(8);

		maquina.retirarDinheiro(dinheiro);
	}

	@Test
	public void testarRetiradaDeDinheiroComQuantidadeNotasDe5ReaisSuficiente() throws Exception {
		Maquina maquina = new Maquina();
		maquina.setQtdCedulas5(6);

		Dinheiro dinheiro = new Dinheiro();
		dinheiro.setQtdCedulas5(6);

		maquina.retirarDinheiro(dinheiro);

		Assert.assertEquals(0, maquina.getQtdCedulas5());
	}

	@Test
	public void testarRetiradaDeDinheiroComQuantidadeNotasDe5ReaisMaisQueSuficiente() throws Exception {
		Maquina maquina = new Maquina();
		maquina.setQtdCedulas5(8);

		Dinheiro dinheiro = new Dinheiro();
		dinheiro.setQtdCedulas5(6);

		maquina.retirarDinheiro(dinheiro);

		Assert.assertEquals(2, maquina.getQtdCedulas5());
	}

	@Test(expected = Exception.class)
	public void testarRetiradaDeDinheiroComQuantidadeMoedasDe1RealInsuficiente() throws Exception {
		Maquina maquina = new Maquina();
		maquina.setQtdMoedas1Real(7);

		Dinheiro dinheiro = new Dinheiro();
		dinheiro.setQtdCedulas5(6);

		maquina.retirarDinheiro(dinheiro);
	}

	@Test
	public void testarRetiradaDeDinheiroComQuantidadeMoedasDe1RealSuficiente() throws Exception {
		Maquina maquina = new Maquina();
		maquina.setQtdMoedas1Real(7);

		Dinheiro dinheiro = new Dinheiro();
		dinheiro.setQtdMoedas1Real(7);

		maquina.retirarDinheiro(dinheiro);

		Assert.assertEquals(0, maquina.getQtdMoedas1Real());
	}

	@Test
	public void testarRetiradaDeDinheiroComQuantidadeMoedasDe1RealMaisQueSuficiente() throws Exception {
		Maquina maquina = new Maquina();
		maquina.setQtdMoedas1Real(10);

		Dinheiro dinheiro = new Dinheiro();
		dinheiro.setQtdMoedas1Real(8);

		maquina.retirarDinheiro(dinheiro);

		Assert.assertEquals(2, maquina.getQtdMoedas1Real());
	}

	@Test(expected = Exception.class)
	public void testarRetiradaDeDinheiroComQuantidadeMoedasDe50CentavosInsuficiente() throws Exception {
		Maquina maquina = new Maquina();
		maquina.setQtdMoedas50Centavos(5);

		Dinheiro dinheiro = new Dinheiro();
		dinheiro.setQtdCedulas5(6);

		maquina.retirarDinheiro(dinheiro);
	}

	@Test
	public void testarRetiradaDeDinheiroComQuantidadeMoedasDe50CentavosSuficiente() throws Exception {
		Maquina maquina = new Maquina();
		maquina.setQtdMoedas50Centavos(4);

		Dinheiro dinheiro = new Dinheiro();
		dinheiro.setQtdMoedas50Centavos(4);

		maquina.retirarDinheiro(dinheiro);

		Assert.assertEquals(0, maquina.getQtdMoedas50Centavos());
	}

	@Test
	public void testarRetiradaDeDinheiroComQuantidadeMoedasDe50CentavosMaisQueSuficiente() throws Exception {
		Maquina maquina = new Maquina();
		maquina.setQtdMoedas50Centavos(10);

		Dinheiro dinheiro = new Dinheiro();
		dinheiro.setQtdMoedas50Centavos(4);

		maquina.retirarDinheiro(dinheiro);

		Assert.assertEquals(6, maquina.getQtdMoedas50Centavos());
	}

	@Test
	public void testarColocarCedulasDe50ReaisNaMaquina() {
		Maquina maquina = new Maquina();
		maquina.setQtdCedulas50(0);

		Dinheiro dinheiro = new Dinheiro();
		dinheiro.setQtdCedulas50(2);

		maquina.colocarDinheiro(dinheiro);

		Assert.assertEquals(2, maquina.getQtdCedulas50());
	}

	@Test
	public void testarColocarCedulasDe10ReaisNaMaquina() {
		Maquina maquina = new Maquina();
		maquina.setQtdCedulas10(0);

		Dinheiro dinheiro = new Dinheiro();
		dinheiro.setQtdCedulas10(5);

		maquina.colocarDinheiro(dinheiro);

		Assert.assertEquals(5, maquina.getQtdCedulas10());
	}

	@Test
	public void testarColocarCedulasDe5ReaisNaMaquina() {
		Maquina maquina = new Maquina();
		maquina.setQtdCedulas5(0);

		Dinheiro dinheiro = new Dinheiro();
		dinheiro.setQtdCedulas5(8);

		maquina.colocarDinheiro(dinheiro);

		Assert.assertEquals(8, maquina.getQtdCedulas5());
	}

	@Test
	public void testarColocarMoeadasDe1RealNaMaquina() {
		Maquina maquina = new Maquina();
		maquina.setQtdMoedas1Real(0);

		Dinheiro dinheiro = new Dinheiro();
		dinheiro.setQtdMoedas1Real(50);

		maquina.colocarDinheiro(dinheiro);

		Assert.assertEquals(50, maquina.getQtdMoedas1Real());
	}

	@Test
	public void testarColocarMoeadasDe50CentavosNaMaquina() {
		Maquina maquina = new Maquina();
		maquina.setQtdMoedas50Centavos(0);

		Dinheiro dinheiro = new Dinheiro();
		dinheiro.setQtdMoedas50Centavos(100);

		maquina.colocarDinheiro(dinheiro);

		Assert.assertEquals(100, maquina.getQtdMoedas50Centavos());
	}

	@Test
	public void testarColocarCedulasEMoedasNaMaquina() {
		Maquina maquina = new Maquina();
		maquina.setQtdCedulas10(0);
		maquina.setQtdMoedas1Real(0);
		maquina.setQtdMoedas50Centavos(0);

		Dinheiro dinheiro = new Dinheiro();
		dinheiro.setQtdCedulas10(2);
		dinheiro.setQtdMoedas1Real(3);
		dinheiro.setQtdMoedas50Centavos(5);

		maquina.colocarDinheiro(dinheiro);

		Assert.assertEquals(2, maquina.getQtdCedulas10());
		Assert.assertEquals(3, maquina.getQtdMoedas1Real());
		Assert.assertEquals(5, maquina.getQtdMoedas50Centavos());
	}

	@Test
	public void testarColocarDinheiroQuandoNaoPrecisaDeTroco() throws Exception {
		Maquina maquina = new Maquina();
		maquina.setQtdCedulas50(2);
		maquina.setQtdCedulas10(1);
		maquina.setQtdCedulas5(3);
		maquina.setQtdMoedas1Real(5);
		maquina.setQtdMoedas50Centavos(7);

		Dinheiro quantiaFornecida = new Dinheiro();
		quantiaFornecida.setQtdCedulas10(2);
		quantiaFornecida.setQtdMoedas50Centavos(3);

		BigDecimal quantiaNecessaria = new BigDecimal(21.50);

		Dinheiro troco = maquina.colocarDinheiro(quantiaFornecida, quantiaNecessaria);

		// Verificando o valor do troco

		Assert.assertTrue(troco.getQtdCedulas50() == 0);
		Assert.assertTrue(troco.getQtdCedulas10() == 0);
		Assert.assertTrue(troco.getQtdCedulas5() == 0);

		Assert.assertTrue(troco.getQtdMoedas1Real() == 0);
		Assert.assertTrue(troco.getQtdMoedas50Centavos() == 0);
	}

	@Test
	public void testarColocarDinheiroQuandoPrecisaDeTroco() throws Exception {
		Maquina maquina = new Maquina();
		maquina.setQtdCedulas50(2);
		maquina.setQtdCedulas10(1);
		maquina.setQtdCedulas5(3);
		maquina.setQtdMoedas1Real(5);
		maquina.setQtdMoedas50Centavos(7);

		Dinheiro quantiaFornecida = new Dinheiro();
		quantiaFornecida.setQtdCedulas10(3);

		BigDecimal quantiaNecessaria = new BigDecimal(21.50);

		Dinheiro troco = maquina.colocarDinheiro(quantiaFornecida, quantiaNecessaria);

		// Verificando o valor do troco

		Assert.assertTrue(troco.getQtdCedulas5() == 1);
		Assert.assertTrue(troco.getQtdMoedas1Real() == 3);
		Assert.assertTrue(troco.getQtdMoedas50Centavos() == 1);
	}

	@Test(expected = Exception.class)
	public void testarColocarDinheiroQuandoPrecisaDeTrocoMasNaoTem() throws Exception {
		Maquina maquina = new Maquina();
		maquina.setQtdCedulas50(2);
		maquina.setQtdCedulas10(1);
		maquina.setQtdCedulas5(3);
		maquina.setQtdMoedas1Real(5);

		Dinheiro quantiaFornecida = new Dinheiro();
		quantiaFornecida.setQtdCedulas10(3);

		BigDecimal quantiaNecessaria = new BigDecimal(21.50);

		maquina.colocarDinheiro(quantiaFornecida, quantiaNecessaria);
	}

	/* ###################################### Testes do módulo de Administração ##################################### */

	@Test(expected = Exception.class)
	public void testarRecarregarQuantidadeDeImpressoesNegativa() {
		Maquina maquina = new Maquina();
		maquina.recarregar(-10);
	}

	@Test(expected = Exception.class)
	public void testarRecarregarQuantidadeDeImpressoesZerada() {
		Maquina maquina = new Maquina();
		maquina.recarregar(0);
	}

	@Test
	public void testarRecarregarQuantidadeDeImpressoesPositiva() {
		Maquina maquina = new Maquina();
		maquina.setQtdImpressoes(0);
		maquina.recarregar(10);

		Assert.assertEquals(10, maquina.getQtdImpressoes());
	}

}
