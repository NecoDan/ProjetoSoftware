package br.ufma.mestrado.computacao.ps.cinema.visao.manutencao;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.ufma.mestrado.computacao.ps.cinema.dominio.Maquina;
import br.ufma.mestrado.computacao.ps.cinema.negocio.ManutencacaoNegocio;

@ManagedBean
@ViewScoped
public class RecarregarMaquinaBean {

	private ManutencacaoNegocio manutencacaoNegocio;

	private Maquina maquina;

	private int qtdImpressoes;

	public RecarregarMaquinaBean() {
		manutencacaoNegocio = new ManutencacaoNegocio();
		maquina = manutencacaoNegocio.recuperarMaquina(1);
	}

	public String recarregar() {
		try {
			manutencacaoNegocio.recarregarMaquina(maquina, qtdImpressoes);

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Máquina recarregada com sucesso.", ""));

			return "inicio";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi possível recarregar a máquina. Causa: "
							+ e.getMessage(), e.getMessage()));

			return "";
		}
	}

	/* ################################################ Gets e Sets ################################################# */

	public Maquina getMaquina() {
		return maquina;
	}

	public int getQtdImpressoes() {
		return qtdImpressoes;
	}

	public void setQtdImpressoes(int qtdImpressoes) {
		this.qtdImpressoes = qtdImpressoes;
	}

}
