package br.ufma.mestrado.computacao.ps.cinema.visao.manutencao;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.ufma.mestrado.computacao.ps.cinema.dominio.Pagamento;
import br.ufma.mestrado.computacao.ps.cinema.negocio.ManutencacaoNegocio;

@ManagedBean
@ViewScoped
public class RelatorioVendasBean {

	private String dataFim;

	private String dataInicio;

	private ManutencacaoNegocio manutencacaoNegocio;

	private List<Pagamento> pagamentos;

	private BigDecimal total;

	public RelatorioVendasBean() {
		manutencacaoNegocio = new ManutencacaoNegocio();
	}

	private Calendar toCalendar(String data, String rotulo) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

		if (data.trim().isEmpty()) {
			return null;
		}

		try {
			Calendar dataHora = Calendar.getInstance();
			dataHora.setTime(df.parse(data));
			return dataHora;
		} catch (ParseException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, rotulo + "inválida.", rotulo + "inválida."));
			return null;
		}
	}

	public String visualizarRelatorioVendas() {
		Calendar inicio = null;
		Calendar fim = null;

		inicio = toCalendar(dataInicio, "Data de início");

		if (inicio != null) {
			inicio.set(Calendar.HOUR_OF_DAY, 0);
			inicio.set(Calendar.MINUTE, 0);
			inicio.set(Calendar.SECOND, 0);
		}

		fim = toCalendar(dataFim, "Data de fim");

		if (fim != null) {
			fim.set(Calendar.HOUR_OF_DAY, 23);
			fim.set(Calendar.MINUTE, 59);
			fim.set(Calendar.SECOND, 59);
		}

		if (inicio == null || fim == null) {
			return "";
		}

		pagamentos = manutencacaoNegocio.recuperarPagamentos(inicio, fim);

		total = BigDecimal.ZERO;

		for (Pagamento pagamento : pagamentos) {
			total = total.add(pagamento.getValorPago());
		}

		if (pagamentos.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Não foram realizadas vendas no período especificado.",
							"Não foram realizadas vendas no período especificado."));
		}

		return "";
	}

	/* ################################################ Gets e Sets ################################################# */

	public String getDataFim() {
		return dataFim;
	}

	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}

	public BigDecimal getTotal() {
		return total;
	}

}
