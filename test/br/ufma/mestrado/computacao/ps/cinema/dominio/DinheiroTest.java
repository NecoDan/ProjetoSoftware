package br.ufma.mestrado.computacao.ps.cinema.dominio;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import br.ufma.mestrado.computacao.ps.cinema.dominio.Dinheiro;

public class DinheiroTest {

	@Test
	public void testarValorEmReaisSomenteComCedulas50() {
		Dinheiro dinheiro = new Dinheiro();
		dinheiro.setQtdCedulas50(3);

		BigDecimal totalEsperado = new BigDecimal(150);

		Assert.assertEquals(totalEsperado, dinheiro.valorEmReais());
	}

	@Test
	public void testarValorEmReaisSomenteComCedulas10() {
		Dinheiro dinheiro = new Dinheiro();
		dinheiro.setQtdCedulas10(6);

		BigDecimal totalEsperado = new BigDecimal(60);

		Assert.assertEquals(totalEsperado, dinheiro.valorEmReais());
	}

	@Test
	public void testarValorEmReaisSomenteComCedulas5() {
		Dinheiro dinheiro = new Dinheiro();
		dinheiro.setQtdCedulas5(5);

		BigDecimal totalEsperado = new BigDecimal(25);

		Assert.assertEquals(totalEsperado, dinheiro.valorEmReais());
	}

	@Test
	public void testarValorEmReaisComCedulasDiversas() {
		Dinheiro dinheiro = new Dinheiro();
		dinheiro.setQtdCedulas50(2);
		dinheiro.setQtdCedulas10(3);
		dinheiro.setQtdCedulas5(1);

		BigDecimal totalEsperado = new BigDecimal(135);

		Assert.assertEquals(totalEsperado, dinheiro.valorEmReais());
	}

	@Test
	public void testarValorEmReaisSomenteComMoedasDe1Real() {
		Dinheiro dinheiro = new Dinheiro();
		dinheiro.setQtdMoedas1Real(3);

		BigDecimal totalEsperado = new BigDecimal(3);

		Assert.assertEquals(totalEsperado, dinheiro.valorEmReais());
	}

	@Test
	public void testarValorEmReaisSomenteComMoedasDe50Centavos() {
		Dinheiro dinheiro = new Dinheiro();
		dinheiro.setQtdMoedas50Centavos(7);

		BigDecimal totalEsperado = new BigDecimal(3.5);

		Assert.assertEquals(totalEsperado, dinheiro.valorEmReais());
	}

	@Test
	public void testarValorEmReaisComMoedasDiversas() {
		Dinheiro dinheiro = new Dinheiro();
		dinheiro.setQtdMoedas1Real(8);
		dinheiro.setQtdMoedas50Centavos(5);

		BigDecimal totalEsperado = new BigDecimal(10.5);

		Assert.assertEquals(totalEsperado, dinheiro.valorEmReais());
	}

	@Test
	public void testarValorEmReaisComCedulasEMoedasDiversas() {
		Dinheiro dinheiro = new Dinheiro();
		dinheiro.setQtdCedulas50(2);
		dinheiro.setQtdCedulas10(3);
		dinheiro.setQtdCedulas5(1);
		dinheiro.setQtdMoedas1Real(8);
		dinheiro.setQtdMoedas50Centavos(5);

		BigDecimal totalEsperado = new BigDecimal(145.5);

		Assert.assertEquals(totalEsperado, dinheiro.valorEmReais());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testarSetQuantidadeNegativaDeCedulas50Reais() {
		Dinheiro dinheiro = new Dinheiro();
		dinheiro.setQtdCedulas50(-2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testarSetQuantidadeNegativaDeCedulas10Reais() {
		Dinheiro dinheiro = new Dinheiro();
		dinheiro.setQtdCedulas10(-3);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testarSetQuantidadeNegativaDeCedulas5Reais() {
		Dinheiro dinheiro = new Dinheiro();
		dinheiro.setQtdCedulas5(-2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testarSetQuantidadeNegativaDeMoedas1Real() {
		Dinheiro dinheiro = new Dinheiro();
		dinheiro.setQtdMoedas1Real(-10);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testarSetQuantidadeNegativaDeMoedas50Centavos() {
		Dinheiro dinheiro = new Dinheiro();
		dinheiro.setQtdMoedas50Centavos(-5);
	}

}
