package br.ufma.mestrado.computacao.ps.cinema.dominio;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "FILME")
public final class Filme {

	@Column(name = "ANO")
	private int ano;

	@Column(name = "CLASSIFICACAO")
	private int classificacao;

	@Column(name = "DURACAO_EM_MIN")
	private int duracao;

	@Temporal(TemporalType.DATE)
	@Column(name = "EM_CARTAZ_ATE")
	private Date emCartazAte;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "NOME")
	private String nome;

	@Transient
	private List<Sessao> sessoes;

	@Column(name = "SINOPSE")
	private String sinopse;

	/* ############################################### Outros Métodos ############################################### */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Filme other = (Filme) obj;
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

	public boolean isEmCartaz() {
		Date dataAtual = Calendar.getInstance().getTime();
		return emCartazAte.after(dataAtual);
	}

	@Override
	public String toString() {
		return nome;
	}

	/* ################################################ Gets e Sets ################################################# */

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		if (ano != 0 && ano < 1900) {
			throw new IllegalArgumentException("O ano do filme deve ser maior o igual a 1900.");
		}
		this.ano = ano;
	}

	public int getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(int classificacao) {
		this.classificacao = classificacao;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		if (duracao < 0) {
			throw new IllegalArgumentException("A duração do filme deve ser maior que zero.");
		}

		this.duracao = duracao;
	}

	public Date getEmCartazAte() {
		return emCartazAte;
	}

	public void setEmCartazAte(Date emCartazAte) {
		this.emCartazAte = emCartazAte;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Sessao> getSessoes() {
		return sessoes;
	}

	public void setSessoes(List<Sessao> sessoes) {
		this.sessoes = sessoes;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

}
