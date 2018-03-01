package br.ufma.mestrado.computacao.ps.cinema.dominio;

import java.math.BigDecimal;

public final class SeparadorDinheiro {

	private SeparadorDinheiro() {
	}

	public static BigDecimal separarCedulas50Reais(Maquina maquina, BigDecimal quantia, Dinheiro dinheiroTrocado) {
		if (maquina.getQtdCedulas50() <= 0) {
			return quantia;
		}

		// Separando as cedulas de R$ 50,00

		BigDecimal[] divisorResto = quantia.divideAndRemainder(new BigDecimal(50));
		int qtdCedulas50 = divisorResto[0].intValue();

		if (maquina.getQtdCedulas50() < qtdCedulas50) {
			qtdCedulas50 -= maquina.getQtdCedulas50();
			quantia = quantia.subtract(new BigDecimal(qtdCedulas50 * 50));
		} else {
			quantia = divisorResto[1];
		}

		dinheiroTrocado.setQtdCedulas50(qtdCedulas50);

		return quantia;
	}

	public static BigDecimal separarCedulas10Reais(Maquina maquina, BigDecimal quantia, Dinheiro dinheiroTrocado) {
		if (maquina.getQtdCedulas10() <= 0) {
			return quantia;
		}

		// Separando as cedulas de R$ 10,00

		BigDecimal[] divisorResto = quantia.divideAndRemainder(new BigDecimal(10));
		int qtdCedulas10 = divisorResto[0].intValue();

		if (maquina.getQtdCedulas10() < qtdCedulas10) {
			qtdCedulas10 -= maquina.getQtdCedulas10();
			quantia = quantia.subtract(new BigDecimal(qtdCedulas10 * 10));
		} else {
			quantia = divisorResto[1];
		}

		dinheiroTrocado.setQtdCedulas10(qtdCedulas10);

		return quantia;
	}

	public static BigDecimal separarCedulas5Reais(Maquina maquina, BigDecimal quantia, Dinheiro dinheiroTrocado) {
		if (maquina.getQtdCedulas5() <= 0) {
			return quantia;
		}

		// Separando as cedulas de R$ 5,00

		BigDecimal[] divisorResto = quantia.divideAndRemainder(new BigDecimal(5));
		int qtdCedulas5 = divisorResto[0].intValue();

		if (maquina.getQtdCedulas5() < qtdCedulas5) {
			qtdCedulas5 -= maquina.getQtdCedulas5();
			quantia = quantia.subtract(new BigDecimal(qtdCedulas5 * 5));
		} else {
			quantia = divisorResto[1];
		}

		dinheiroTrocado.setQtdCedulas5(qtdCedulas5);

		return quantia;
	}

	public static BigDecimal separarMoedas1Real(Maquina maquina, BigDecimal quantia, Dinheiro dinheiroTrocado) {
		if (maquina.getQtdMoedas1Real() <= 0) {
			return quantia;
		}

		// Separando as moedas de R$ 1,00

		BigDecimal[] divisorResto = quantia.divideAndRemainder(new BigDecimal(1));
		int qtdMoedas1Real = divisorResto[0].intValue();

		if (maquina.getQtdMoedas1Real() < qtdMoedas1Real) {
			qtdMoedas1Real -= maquina.getQtdMoedas1Real();
			quantia = quantia.subtract(new BigDecimal(qtdMoedas1Real * 1));
		} else {
			quantia = divisorResto[1];
		}

		dinheiroTrocado.setQtdMoedas1Real(qtdMoedas1Real);

		return quantia;
	}

	public static BigDecimal separarMoedas50Centavos(Maquina maquina, BigDecimal quantia, Dinheiro dinheiroTrocado) {
		if (maquina.getQtdMoedas50Centavos() <= 0) {
			return quantia;
		}

		// Separando as moedas de R$ 0,50

		BigDecimal[] divisorResto = quantia.divideAndRemainder(new BigDecimal(0.5));
		int qtdMoedas50Centavos = divisorResto[0].intValue();

		if (maquina.getQtdMoedas50Centavos() < qtdMoedas50Centavos) {
			qtdMoedas50Centavos -= maquina.getQtdMoedas50Centavos();
			double valor = ((double) qtdMoedas50Centavos * 50) / 100;
			quantia = quantia.subtract(new BigDecimal(qtdMoedas50Centavos * valor));
		} else {
			quantia = divisorResto[1];
		}

		dinheiroTrocado.setQtdMoedas50Centavos(qtdMoedas50Centavos);

		return quantia;
	}

}
