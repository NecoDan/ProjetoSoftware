package br.ufma.mestrado.computacao.ps.cinema.dominio;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.ufma.mestrado.computacao.ps.cinema.exception.QuantidadeNotasOuMoedasInsuficienteException;

/**
 * Classe de domínio que representa a máquina de ingressos de cinema
 * 
 */
@Entity
@Table(name = "MAQUINA")
public final class Maquina {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "QTD_CEDULAS_5_REAIS")
	private int qtdCedulas5;

	@Column(name = "QTD_CEDULAS_10_REAIS")
	private int qtdCedulas10;

	@Column(name = "QTD_CEDULAS_50_REAIS")
	private int qtdCedulas50;

	@Column(name = "QTD_IMPRESSOES")
	private int qtdImpressoes;

	@Column(name = "QTD_MOEDAS_1_REAL")
	private int qtdMoedas1Real;

	@Column(name = "QTD_MOEDAS_50_CENTAVOS")
	private int qtdMoedas50Centavos;

	/* ############################################### Outros Métodos ############################################### */

	public void colocarDinheiro(Dinheiro dinheiro) {
		this.qtdCedulas50 += dinheiro.getQtdCedulas50();
		this.qtdCedulas10 += dinheiro.getQtdCedulas10();
		this.qtdCedulas5 += dinheiro.getQtdCedulas5();

		this.qtdMoedas1Real += dinheiro.getQtdMoedas1Real();
		this.qtdMoedas50Centavos += dinheiro.getQtdMoedas50Centavos();
	}

	public Dinheiro colocarDinheiro(Dinheiro quantiaFornecida, BigDecimal quantiaNecessaria) throws Exception {

		Dinheiro dinheiroTrocado = new Dinheiro();

		// Verificando se precisa de troco

		if (quantiaFornecida.valorEmReais().compareTo(quantiaNecessaria) >= 0) {
			BigDecimal troco = quantiaFornecida.valorEmReais().subtract(quantiaNecessaria);

			troco = SeparadorDinheiro.separarCedulas50Reais(this, troco, dinheiroTrocado);
			troco = SeparadorDinheiro.separarCedulas10Reais(this, troco, dinheiroTrocado);
			troco = SeparadorDinheiro.separarCedulas5Reais(this, troco, dinheiroTrocado);
			troco = SeparadorDinheiro.separarMoedas1Real(this, troco, dinheiroTrocado);
			troco = SeparadorDinheiro.separarMoedas50Centavos(this, troco, dinheiroTrocado);

			if (troco.compareTo(BigDecimal.ZERO) > 0) {
				throw new Exception("Não existe dinheiro suficiente para dar o troco");
			}
		}

		colocarDinheiro(quantiaFornecida);
		retirarDinheiro(dinheiroTrocado);

		return dinheiroTrocado;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Maquina other = (Maquina) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	public void imprimirIngresso() throws Exception {
		if (!podeImprimir()) {
			throw new Exception("Não foi possível imprimir o ingresso, pois a Impressora está sem papel");
		}

		qtdImpressoes--;
	}

	public boolean podeImprimir() {
		return qtdImpressoes > 0;
	}

	public void recarregar(int quantidadeImpressoes) throws IllegalArgumentException {
		if (quantidadeImpressoes <= 0) {
			throw new IllegalArgumentException("A quantidade de impressões a adicionar deve ser maior que zero");
		}

		this.qtdImpressoes += quantidadeImpressoes;
	}

	public void retirarDinheiro(Dinheiro dinheiro) throws Exception {
		if (this.qtdCedulas50 < dinheiro.getQtdCedulas50()) {
			throw new QuantidadeNotasOuMoedasInsuficienteException("Quantidade de notas de R$ 50,00 insuficiente");
		}

		if (this.qtdCedulas10 < dinheiro.getQtdCedulas10()) {
			throw new QuantidadeNotasOuMoedasInsuficienteException("Quantidade de notas de R$ 10,00 insuficiente");
		}

		if (this.qtdCedulas5 < dinheiro.getQtdCedulas5()) {
			throw new QuantidadeNotasOuMoedasInsuficienteException("Quantidade de notas de R$ 5,00 insuficiente");
		}

		if (this.qtdMoedas1Real < dinheiro.getQtdMoedas1Real()) {
			throw new QuantidadeNotasOuMoedasInsuficienteException("Quantidade de moedas de R$ 1,00 insuficiente");
		}

		if (this.qtdMoedas50Centavos < dinheiro.getQtdMoedas50Centavos()) {
			throw new QuantidadeNotasOuMoedasInsuficienteException("Quantidade de moedas de R$ 0,50 insuficiente");
		}

		this.qtdCedulas50 -= dinheiro.getQtdCedulas50();
		this.qtdCedulas10 -= dinheiro.getQtdCedulas10();
		this.qtdCedulas5 -= dinheiro.getQtdCedulas5();

		this.qtdMoedas1Real -= dinheiro.getQtdMoedas1Real();
		this.qtdMoedas50Centavos -= dinheiro.getQtdMoedas50Centavos();
	}

	/* ################################################ Gets e Sets ################################################# */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQtdCedulas5() {
		return qtdCedulas5;
	}

	public void setQtdCedulas5(int qtdCedulas5) {
		this.qtdCedulas5 = qtdCedulas5;
	}

	public int getQtdCedulas10() {
		return qtdCedulas10;
	}

	public void setQtdCedulas10(int qtdCedulas10) {
		this.qtdCedulas10 = qtdCedulas10;
	}

	public int getQtdCedulas50() {
		return qtdCedulas50;
	}

	public void setQtdCedulas50(int qtdCedulas50) {
		this.qtdCedulas50 = qtdCedulas50;
	}

	public int getQtdImpressoes() {
		return qtdImpressoes;
	}

	public void setQtdImpressoes(int qtdImpressoes) {
		this.qtdImpressoes = qtdImpressoes;
	}

	public int getQtdMoedas50Centavos() {
		return qtdMoedas50Centavos;
	}

	public void setQtdMoedas50Centavos(int qtdMoedas50Centavos) {
		this.qtdMoedas50Centavos = qtdMoedas50Centavos;
	}

	public int getQtdMoedas1Real() {
		return qtdMoedas1Real;
	}

	public void setQtdMoedas1Real(int qtdMoedas1Real) {
		this.qtdMoedas1Real = qtdMoedas1Real;
	}

	public BigDecimal getTotalEmReais() {
		BigDecimal total = BigDecimal.ZERO;

		total = total.add(new BigDecimal(qtdCedulas50 * 50));
		total = total.add(new BigDecimal(qtdCedulas10 * 10));
		total = total.add(new BigDecimal(qtdCedulas5 * 5));

		total = total.add(new BigDecimal(qtdMoedas1Real * 1));
		total = total.add(new BigDecimal(((double) qtdMoedas50Centavos * 50) / 100));

		return total;
	}

}
