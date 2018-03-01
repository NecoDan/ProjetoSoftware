package br.ufma.mestrado.computacao.ps.cinema.dominio;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "INGRESSO")
public final class Ingresso {

	@ManyToOne
	@JoinColumn(name = "ID_ASSENTO")
	private Assento assento;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "PRECO")
	private BigDecimal preco;

	@ManyToOne
	@JoinColumn(name = "ID_SESSAO")
	private Sessao sessao;

	public Ingresso() {
		super();
	}

	public Ingresso(Assento assento, Sessao sessao) {
		this.assento = assento;
		this.sessao = sessao;

		if (sessao != null) {
			preco = sessao.getPrecoIngresso();
		}
	}

	/* ############################################### Outros Métodos ############################################### */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ingresso other = (Ingresso) obj;
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

	public Assento getAssento() {
		return assento;
	}

	public void setAssento(Assento assento) {
		this.assento = assento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Sessao getSessao() {
		return sessao;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}
}
