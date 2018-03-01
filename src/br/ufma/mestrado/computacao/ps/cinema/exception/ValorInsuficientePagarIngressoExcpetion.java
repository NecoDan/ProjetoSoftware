package br.ufma.mestrado.computacao.ps.cinema.exception;

public class ValorInsuficientePagarIngressoExcpetion extends Exception {

	private static final long serialVersionUID = -2798799674084950865L;

	public ValorInsuficientePagarIngressoExcpetion() {
		super("Valor insuficiente para pagar o ingresso.");
	}

}
