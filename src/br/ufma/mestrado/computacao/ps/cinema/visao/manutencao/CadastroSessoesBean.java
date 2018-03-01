package br.ufma.mestrado.computacao.ps.cinema.visao.manutencao;

import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.ufma.mestrado.computacao.ps.cinema.dominio.Filme;
import br.ufma.mestrado.computacao.ps.cinema.dominio.Sala;
import br.ufma.mestrado.computacao.ps.cinema.dominio.Sessao;
import br.ufma.mestrado.computacao.ps.cinema.negocio.ManutencacaoNegocio;

@ManagedBean
@ViewScoped
public class CadastroSessoesBean {

	private Filme filme;

	private List<Sala> salasCadastradas;

	private Sessao sessao;

	private List<Sessao> sessoesCadastradas;

	private ManutencacaoNegocio manutencacaoNegocio;

	public CadastroSessoesBean() {
		manutencacaoNegocio = new ManutencacaoNegocio();

		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		int idFilme = Integer.parseInt(params.get("idFilme"));
		String nomeFilme = params.get("nomeFilme");

		filme = new Filme();
		filme.setId(idFilme);
		filme.setNome(nomeFilme);

		novo();

		salasCadastradas = manutencacaoNegocio.recuperarTodasAsSalas();
	}

	public String editar() {
		return "";
	}

	public final String novo() {
		sessao = new Sessao();
		sessao.setFilme(filme);
		sessao.setSala(new Sala());

		sessoesCadastradas = manutencacaoNegocio.recuperarSessoes(filme);

		return "";
	}

	public String salvar() {
		try {
			manutencacaoNegocio.salvar(sessao);

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sessão salva com sucesso.", ""));

			return novo();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi possível salvar a sessão. Causa: "
							+ e.getMessage(), e.getMessage()));

			return "";
		}
	}

	/* ################################################ Gets e Sets ################################################# */

	public Filme getFilme() {
		return filme;
	}

	public List<Sala> getSalasCadastradas() {
		return salasCadastradas;
	}

	public Sessao getSessao() {
		return sessao;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}

	public List<Sessao> getSessoesCadastradas() {
		return sessoesCadastradas;
	}

}
