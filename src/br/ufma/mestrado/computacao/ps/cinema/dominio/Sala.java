package br.ufma.mestrado.computacao.ps.cinema.dominio;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "SALA")
public final class Sala {

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "sala")
	private List<Assento> assentos;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "NOME")
	private String nome;

	@Column(name = "QTD_ASSENTOS")
	private int quantidadeAssentos;

	@Column(name = "QTD_FILEIRAS")
	private int quantidadeFileiras;

	/* ################################################ Construtores ################################################ */

	public Sala() {
	}

	public Sala(String nome, int quantidadeAssentos, int quantidadeFileiras) {
		this.nome = nome;
		this.quantidadeAssentos = quantidadeAssentos;
		this.quantidadeFileiras = quantidadeFileiras;
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
		Sala other = (Sala) obj;
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

	@Override
	public String toString() {
		return nome;
	}

	/* ################################################ Gets e Sets ################################################# */

	public List<Assento> getAssentos() {
		return assentos;
	}

	public void setAssentos(List<Assento> assentos) {
		this.assentos = assentos;
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

	public int getQuantidadeAssentos() {
		return quantidadeAssentos;
	}

	public void setQuantidadeAssentos(int quantidadeAssentos) {
		this.quantidadeAssentos = quantidadeAssentos;
	}

	public int getQuantidadeFileiras() {
		return quantidadeFileiras;
	}

	public void setQuantidadeFileiras(int quantidadeFileiras) {
		this.quantidadeFileiras = quantidadeFileiras;
	}
}
