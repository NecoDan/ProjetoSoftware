package br.ufma.mestrado.computacao.ps.cinema.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TIPO_PAGAMENTO")
public class TipoPagamento {

	public static final int DINHEIRO = 1;
	public static final int CARTAO_CREDITO = 2;
	public static final int CARTAO_DEBITO = 3;

	@Column(name = "DESCRICAO")
	private String descricao;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/* ################################################ Construtores ################################################ */

	public TipoPagamento() {
	}

	public TipoPagamento(int id) {
		this.id = id;
	}

	public TipoPagamento(String descricao) {
		this.descricao = descricao;
	}

	public TipoPagamento(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
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
		TipoPagamento other = (TipoPagamento) obj;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
