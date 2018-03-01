package br.ufma.mestrado.computacao.ps.cinema.dominio;

import java.math.BigDecimal;

public final class Dinheiro {

	private int qtdCedulas50;

	private int qtdCedulas10;

	private int qtdCedulas5;

	private int qtdMoedas1Real;

	private int qtdMoedas50Centavos;

	/* ############################################### Outros Métodos ############################################### */

	public BigDecimal valorEmReais() {
		BigDecimal total = BigDecimal.ZERO;

		total = total.add(new BigDecimal(qtdCedulas50 * 50));
		total = total.add(new BigDecimal(qtdCedulas10 * 10));
		total = total.add(new BigDecimal(qtdCedulas5 * 5));

		total = total.add(new BigDecimal(qtdMoedas1Real * 1));
		total = total.add(new BigDecimal(((double) qtdMoedas50Centavos * 50) / 100));

		return total;
	}

	/* ################################################ Gets e Sets ################################################# */

	public int getQtdCedulas50() {
		return qtdCedulas50;
	}

	public void setQtdCedulas50(int qtdCedulas50) {
		if (qtdCedulas50 < 0) {
			throw new IllegalArgumentException("Quantidade de Cédulas de R$ 50,00 inválida");
		}

		this.qtdCedulas50 = qtdCedulas50;
	}

	public int getQtdCedulas10() {
		return qtdCedulas10;
	}

	public void setQtdCedulas10(int qtdCedulas10) {
		if (qtdCedulas10 < 0) {
			throw new IllegalArgumentException("Quantidade de cédulas de R$ 10,00 inválida");
		}

		this.qtdCedulas10 = qtdCedulas10;
	}

	public int getQtdCedulas5() {
		return qtdCedulas5;
	}

	public void setQtdCedulas5(int qtdCedulas5) {
		if (qtdCedulas5 < 0) {
			throw new IllegalArgumentException("Quantidade de cédulas de R$ 5,00 inválida");
		}

		this.qtdCedulas5 = qtdCedulas5;
	}

	public int getQtdMoedas1Real() {
		return qtdMoedas1Real;
	}

	public void setQtdMoedas1Real(int qtdMoedas1Real) {
		if (qtdMoedas1Real < 0) {
			throw new IllegalArgumentException("Quantidade de moedas de R$ 1,00 inválida");
		}

		this.qtdMoedas1Real = qtdMoedas1Real;
	}

	public int getQtdMoedas50Centavos() {
		return qtdMoedas50Centavos;
	}

	public void setQtdMoedas50Centavos(int qtdMoedas50Centavos) {
		if (qtdMoedas50Centavos < 0) {
			throw new IllegalArgumentException("Quantidade de moedas de R$ 0,50 inválida");
		}

		this.qtdMoedas50Centavos = qtdMoedas50Centavos;
	}

}
