package br.ufma.mestrado.computacao.ps.cinema.visao.operacao;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.ufma.mestrado.computacao.ps.cinema.dominio.Filme;
import br.ufma.mestrado.computacao.ps.cinema.negocio.CinemaNegocio;

@ManagedBean
@SessionScoped
public class SessoesBean {

	private Filme filmeEscolhido;

	private CinemaNegocio cinemaNegocio = new CinemaNegocio();

	public Filme getFilmeEscolhido() {
		return filmeEscolhido;
	}

	public void setFilmeEscolhido(Filme filmeEscolhido) {
		this.filmeEscolhido = filmeEscolhido;
		cinemaNegocio.carregarAssentos(filmeEscolhido);
	}

}
