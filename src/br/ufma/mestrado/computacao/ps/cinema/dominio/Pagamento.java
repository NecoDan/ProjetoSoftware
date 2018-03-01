package br.ufma.mestrado.computacao.ps.cinema.dominio;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PAGAMENTO")
public final class Pagamento {

	@Column(name = "DATA")
	private Calendar data;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne
	@JoinColumn(name = "ID_INGRESSO")
	private Ingresso ingresso;

	@Column(name = "NUMERO_CARTAO")
	private String numeroCartao;

	@ManyToOne
	@JoinColumn(name = "ID_TIPO_PAGAMENTO")
	private TipoPagamento tipo;

	private transient Dinheiro trocoEmDinheiro;

	@Column(name = "VALOR_PAGO")
	private BigDecimal valorPago;

	@Column(name = "VALOR_TROCO")
	private BigDecimal valorTroco;

	/* ############################################### Outros Métodos ############################################### */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pagamento other = (Pagamento) obj;
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

	/* ################################################ Gets e Sets ################################################# */

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Ingresso getIngresso() {
		return ingresso;
	}

	public void setIngresso(Ingresso ingresso) {
		this.ingresso = ingresso;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public TipoPagamento getTipo() {
		return tipo;
	}

	public void setTipo(TipoPagamento tipo) {
		this.tipo = tipo;
	}

	public Dinheiro getTrocoEmDinheiro() {
		return trocoEmDinheiro;
	}

	public void setTrocoEmDinheiro(Dinheiro trocoEmDinheiro) {
		this.trocoEmDinheiro = trocoEmDinheiro;

		if (trocoEmDinheiro != null) {
			this.valorTroco = trocoEmDinheiro.valorEmReais();
		}
	}

	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	public BigDecimal getValorTroco() {
		return valorTroco;
	}

	public void setValorTroco(BigDecimal valorTroco) {
		this.valorTroco = valorTroco;
	}

}
