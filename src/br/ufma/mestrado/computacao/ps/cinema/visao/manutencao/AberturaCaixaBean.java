package br.ufma.mestrado.computacao.ps.cinema.visao.manutencao;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.ufma.mestrado.computacao.ps.cinema.dominio.Dinheiro;
import br.ufma.mestrado.computacao.ps.cinema.dominio.Maquina;
import br.ufma.mestrado.computacao.ps.cinema.negocio.ManutencacaoNegocio;

@ManagedBean
@ViewScoped
public class AberturaCaixaBean {

	private Dinheiro dinheiro;

	private ManutencacaoNegocio manutencacaoNegocio;

	private Maquina maquina;

	public AberturaCaixaBean() {
		manutencacaoNegocio = new ManutencacaoNegocio();
		maquina = manutencacaoNegocio.recuperarMaquina(1);
		dinheiro = new Dinheiro();
	}

	public String abrirCaixa() {
		try {
			manutencacaoNegocio.abrirCaixa(maquina, dinheiro);

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Caixa atualizado com sucesso.", ""));

			return "inicio";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi possível atualizar o caixa. Causa: "
							+ e.getMessage(), e.getMessage()));

			return "";
		}
	}

	/* ################################################ Gets e Sets ################################################# */

	public Dinheiro getDinheiro() {
		return dinheiro;
	}

	public Maquina getMaquina() {
		return maquina;
	}

}
