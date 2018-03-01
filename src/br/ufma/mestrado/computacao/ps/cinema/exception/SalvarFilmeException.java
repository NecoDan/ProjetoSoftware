package br.ufma.mestrado.computacao.ps.cinema.exception;

public class SalvarFilmeException extends Exception {

	private static final long serialVersionUID = -8239148831324403152L;

	public SalvarFilmeException() {
		super("Somente filmes em cartaz podem ser salvos");
	}
}
