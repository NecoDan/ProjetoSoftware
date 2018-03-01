package br.ufma.mestrado.computacao.ps.cinema.dominio;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "SESSAO")
public final class Sessao {

	private transient List<Assento> assentos;

	@ManyToOne
	@JoinColumn(name = "ID_FILME")
	private Filme filme;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FIM")
	private Date fim;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INICIO")
	private Date inicio;

	@Column(name = "PRECO_INGRESSO")
	private BigDecimal precoIngresso;

	@ManyToOne
	@JoinColumn(name = "ID_SALA")
	private Sala sala;

	/* ############################################### Outros Métodos ############################################### */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sessao other = (Sessao) obj;
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

	public boolean naoIniciou() {
		Date dataAtual = Calendar.getInstance().getTime();
		return inicio.after(dataAtual);
	}

	/* ################################################ Gets e Sets ################################################# */

	public List<Assento> getAssentos() {
		return assentos;
	}

	public void setAssentos(List<Assento> assentos) {
		this.assentos = assentos;
	}

	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}

	public Date getFim() {
		return fim;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public BigDecimal getPrecoIngresso() {
		return precoIngresso;
	}

	public void setPrecoIngresso(BigDecimal precoIngresso) throws IllegalArgumentException {
		if (precoIngresso.doubleValue() < 0.0) {
			throw new IllegalArgumentException("O preço do ingresso deve ser maior ou igual a zero");
		}

		this.precoIngresso = precoIngresso;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}
}
