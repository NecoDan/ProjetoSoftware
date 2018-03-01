package br.ufma.mestrado.computacao.ps.cinema.visao.manutencao;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.ufma.mestrado.computacao.ps.cinema.dominio.Filme;
import br.ufma.mestrado.computacao.ps.cinema.negocio.ManutencacaoNegocio;

@ManagedBean
@SessionScoped
public class CadastroFilmesBean {

	private Filme filme;

	private List<Filme> filmesCadastrados;

	private ManutencacaoNegocio manutencacaoNegocio;

	public CadastroFilmesBean() {
		manutencacaoNegocio = new ManutencacaoNegocio();
		novo();
	}

	public String editar() {
		return "";
	}

	public final String novo() {
		filme = new Filme();
		filmesCadastrados = manutencacaoNegocio.recuperarTodosOsFilmesCadastrados();
		return "";
	}

	public String salvar() {
		try {
			manutencacaoNegocio.salvar(filme);

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Filme salvo com sucesso.", ""));

			return novo();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi possível salvar o filme. Causa: "
							+ e.getMessage(), e.getMessage()));

			return "";
		}
	}

	/* ################################################ Gets e Sets ################################################# */

	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}

	public List<Filme> getFilmesCadastrados() {
		return filmesCadastrados;
	}

	public int getQuantidadeFilmesCadastrados() {
		return filmesCadastrados.size();
	}
}