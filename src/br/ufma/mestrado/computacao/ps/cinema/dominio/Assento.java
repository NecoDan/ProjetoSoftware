package br.ufma.mestrado.computacao.ps.cinema.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ASSENTO")
public final class Assento {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "NUM_FILEIRA")
	private int numeroFileira;

	private transient boolean ocupado;

	@Column(name = "POS_FILEIRA")
	private int posicaoFileira;

	@ManyToOne
	@JoinColumn(name = "ID_SALA")
	private Sala sala;

	public Assento() {
		super();
	}

	public Assento(Assento assento) {
		id = assento.getId();
		numeroFileira = assento.getNumeroFileira();
		posicaoFileira = assento.getPosicaoFileira();
		sala = assento.getSala();
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
		Assento other = (Assento) obj;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumeroFileira() {
		return numeroFileira;
	}

	public void setNumeroFileira(int numeroFileira) {
		this.numeroFileira = numeroFileira;
	}

	public boolean isOcupado() {
		return ocupado;
	}

	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}

	public int getPosicaoFileira() {
		return posicaoFileira;
	}

	public void setPosicaoFileira(int posicaoFileira) {
		this.posicaoFileira = posicaoFileira;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}
}
