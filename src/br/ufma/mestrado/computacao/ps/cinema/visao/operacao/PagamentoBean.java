package br.ufma.mestrado.computacao.ps.cinema.visao.operacao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.ufma.mestrado.computacao.ps.cinema.dominio.Assento;
import br.ufma.mestrado.computacao.ps.cinema.dominio.Dinheiro;
import br.ufma.mestrado.computacao.ps.cinema.dominio.Ingresso;
import br.ufma.mestrado.computacao.ps.cinema.dominio.Maquina;
import br.ufma.mestrado.computacao.ps.cinema.dominio.Pagamento;
import br.ufma.mestrado.computacao.ps.cinema.dominio.Sessao;
import br.ufma.mestrado.computacao.ps.cinema.dominio.TipoPagamento;
import br.ufma.mestrado.computacao.ps.cinema.negocio.CinemaNegocio;

@ManagedBean
@SessionScoped
public class PagamentoBean {

	private Assento assentoEscolhido;

	private CinemaNegocio cinemaNegocio;

	private Dinheiro dinheiro;

	private Ingresso ingresso;

	private static final Logger LOGGER = Logger.getLogger("PagamentoBeanLog");

	private Maquina maquina;

	private Pagamento pagamento;

	private Sessao sessaoEscolhida;

	private TipoPagamento tipoPagamentoEscolhido;

	private List<TipoPagamento> tiposPagamentos;

	public PagamentoBean() {
		cinemaNegocio = new CinemaNegocio();
		dinheiro = new Dinheiro();
		maquina = cinemaNegocio.recuperarMaquina(1);
		tiposPagamentos = cinemaNegocio.recuperarTiposPagamento();
	}

	public String pagar() {
		ingresso = new Ingresso(assentoEscolhido, sessaoEscolhida);
		pagamento = new Pagamento();

		if (tipoPagamentoEscolhido.getId() == TipoPagamento.DINHEIRO) {
			return "pagamentoEmDinheiro";
		} else {
			boolean cartaoCredito = tipoPagamentoEscolhido.getId() == TipoPagamento.CARTAO_CREDITO;
			try {
				cinemaNegocio.pagarIngressoComCartao(maquina, "", ingresso, cartaoCredito);

				return "ingresso";
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi possível realizar o pagamento. Causa: "
								+ e.getMessage(), e.getMessage()));

				return "";
			}
		}
	}

	public String pagarEmDinheiro() {
		LOGGER.log(Level.INFO, "Cédulas R$ 50,00: " + dinheiro.getQtdCedulas50());
		LOGGER.log(Level.INFO, "Cédulas R$ 10,00: " + dinheiro.getQtdCedulas10());
		LOGGER.log(Level.INFO, "Cédulas R$ 5,00: " + dinheiro.getQtdCedulas5());
		LOGGER.log(Level.INFO, "Moedas R$ 1,00: " + dinheiro.getQtdMoedas1Real());
		LOGGER.log(Level.INFO, "Moedas R$ 0,50: " + dinheiro.getQtdMoedas50Centavos());

		try {
			pagamento = cinemaNegocio.pagarIngressoComDinheiro(maquina, ingresso, dinheiro);

			dinheiro = new Dinheiro();

			return "ingresso";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi possível realizar o pagamento. Causa: "
							+ e.getMessage(), e.getMessage()));
			return "";
		}
	}

	/* ################################################ Gets e Sets ################################################# */

	public Assento getAssentoEscolhido() {
		return assentoEscolhido;
	}

	public void setAssentoEscolhido(Assento assentoEscolhido) {
		this.assentoEscolhido = assentoEscolhido;
	}

	public Dinheiro getDinheiro() {
		return dinheiro;
	}

	public void setDinheiro(Dinheiro dinheiro) {
		this.dinheiro = dinheiro;
	}

	public Ingresso getIngresso() {
		return ingresso;
	}

	public void setIngresso(Ingresso ingresso) {
		this.ingresso = ingresso;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Sessao getSessaoEscolhida() {
		return sessaoEscolhida;
	}

	public void setSessaoEscolhida(Sessao sessaoEscolhida) {
		this.sessaoEscolhida = sessaoEscolhida;
	}

	public TipoPagamento getTipoPagamentoEscolhido() {
		return tipoPagamentoEscolhido;
	}

	public void setTipoPagamentoEscolhido(TipoPagamento tipoPagamentoEscolhido) {
		this.tipoPagamentoEscolhido = tipoPagamentoEscolhido;
	}

	public List<TipoPagamento> getTiposPagamentos() {
		return tiposPagamentos;
	}

}
