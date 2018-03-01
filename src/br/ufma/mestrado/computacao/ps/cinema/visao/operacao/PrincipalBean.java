package br.ufma.mestrado.computacao.ps.cinema.visao.operacao;

import java.util.List;

import javax.faces.bean.ManagedBean;

import br.ufma.mestrado.computacao.ps.cinema.dominio.Filme;
import br.ufma.mestrado.computacao.ps.cinema.negocio.CinemaNegocio;

@ManagedBean
public class PrincipalBean {

	private List<Filme> filmesEmExibicao;
	private CinemaNegocio filmeNegocio = new CinemaNegocio();

	public PrincipalBean() {
		filmesEmExibicao = filmeNegocio.recuperarFilmesEmExibicao();
	}

	public List<Filme> getFilmesEmExibicao() {
		return filmesEmExibicao;
	}
}
