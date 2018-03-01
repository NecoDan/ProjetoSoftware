package br.ufma.mestrado.computacao.ps.cinema.negocio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Session;

import br.ufma.mestrado.computacao.ps.cinema.dao.FilmeDAO;
import br.ufma.mestrado.computacao.ps.cinema.dao.IngressoDAO;
import br.ufma.mestrado.computacao.ps.cinema.dao.MaquinaDAO;
import br.ufma.mestrado.computacao.ps.cinema.dao.PagamentoDAO;
import br.ufma.mestrado.computacao.ps.cinema.dao.SessaoDAO;
import br.ufma.mestrado.computacao.ps.cinema.dao.TipoPagamentoDAO;
import br.ufma.mestrado.computacao.ps.cinema.dominio.Assento;
import br.ufma.mestrado.computacao.ps.cinema.dominio.Dinheiro;
import br.ufma.mestrado.computacao.ps.cinema.dominio.Filme;
import br.ufma.mestrado.computacao.ps.cinema.dominio.Ingresso;
import br.ufma.mestrado.computacao.ps.cinema.dominio.Maquina;
import br.ufma.mestrado.computacao.ps.cinema.dominio.Pagamento;
import br.ufma.mestrado.computacao.ps.cinema.dominio.Sessao;
import br.ufma.mestrado.computacao.ps.cinema.dominio.TipoPagamento;
import br.ufma.mestrado.computacao.ps.cinema.exception.ValorInsuficientePagarIngressoExcpetion;
import br.ufma.mestrado.computacao.ps.cinema.util.HibernateUtil;

/**
 * Classe responsável por implementar as operações/regras de negócio que envolvem os filmes
 */
public final class CinemaNegocio {

	public void carregarAssentos(Filme filme) {
		Session session = HibernateUtil.getInstance().getSession();
		IngressoDAO ingressoDAO = new IngressoDAO(session);

		for (Sessao sessao : filme.getSessoes()) {
			List<Assento> assentos = new ArrayList<Assento>();

			for (Assento assento : sessao.getSala().getAssentos()) {
				assentos.add(new Assento(assento));
			}

			sessao.setAssentos(assentos);

			for (Assento assento : sessao.getAssentos()) {
				assento.setOcupado(ingressoDAO.existeIngresso(sessao, assento));
			}
		}

		session.close();
	}

	public void finalizarCompra(Maquina maquina, Ingresso ingresso, Pagamento pagamento) throws Exception {
		Session session = HibernateUtil.getInstance().getSession();
		MaquinaDAO maquinaDAO = new MaquinaDAO(session);
		IngressoDAO ingressoDAO = new IngressoDAO(session);
		PagamentoDAO pagamentoDAO = new PagamentoDAO(session);

		org.hibernate.Transaction tx = session.beginTransaction();

		try {
			maquina.imprimirIngresso();

			maquinaDAO.salvar(maquina);
			ingressoDAO.salvar(ingresso);
			pagamentoDAO.salvar(pagamento);

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	public Pagamento pagarIngressoComCartao(Maquina maquina, String cartao, Ingresso ingresso, boolean cartaoCredito)
			throws Exception {
		Pagamento pagamento = new Pagamento();
		pagamento.setData(Calendar.getInstance());
		pagamento.setIngresso(ingresso);
		pagamento.setTipo(cartaoCredito ? new TipoPagamento(TipoPagamento.CARTAO_CREDITO) : new TipoPagamento(
				TipoPagamento.CARTAO_DEBITO));
		pagamento.setValorPago(ingresso.getPreco());

		finalizarCompra(maquina, ingresso, pagamento);

		return pagamento;
	}

	public Pagamento pagarIngressoComDinheiro(Maquina maquina, Ingresso ingresso, Dinheiro valorPago) throws Exception {
		Pagamento pagamento = new Pagamento();
		pagamento.setData(Calendar.getInstance());
		pagamento.setIngresso(ingresso);
		pagamento.setTipo(new TipoPagamento(TipoPagamento.DINHEIRO));
		pagamento.setValorPago(ingresso.getPreco());

		// Verificando se o valor depositado na máquina é suficiente para pagar o ingresso

		if (valorPago.valorEmReais().compareTo(ingresso.getPreco()) < 0) {
			throw new ValorInsuficientePagarIngressoExcpetion();
		}

		Dinheiro dinheiroTrocado = maquina.colocarDinheiro(valorPago, ingresso.getPreco());
		pagamento.setTrocoEmDinheiro(dinheiroTrocado);

		finalizarCompra(maquina, ingresso, pagamento);

		return pagamento;
	}

	public Maquina recuperarMaquina(int id) {
		Session session = HibernateUtil.getInstance().getSession();
		MaquinaDAO maquinaDAO = new MaquinaDAO(session);
		Maquina maquina = maquinaDAO.recuperar(id);
		session.close();
		return maquina;
	}

	/**
	 * Método responsável por recuperar os filmes que estão sendo exibidos hoje
	 * 
	 * @return uma lista dos filmes em exibição hoje
	 */
	public List<Filme> recuperarFilmesEmExibicao() {
		Session session = HibernateUtil.getInstance().getSession();
		FilmeDAO filmeDAO = new FilmeDAO(session);
		SessaoDAO sessaoDAO = new SessaoDAO(session);

		Calendar hoje = Calendar.getInstance();
		hoje.set(Calendar.HOUR_OF_DAY, 0);

		List<Filme> filmesEmExibicao = filmeDAO.recuperarFilmesEmExibicao(hoje.getTime());

		for (Filme filme : filmesEmExibicao) {
			filme.setSessoes(sessaoDAO.recuperar(filme, hoje.getTime()));

			Logger.getLogger("CinemaLog").log(Level.INFO, "Filme: " + filme);
		}

		session.close();

		return filmesEmExibicao;
	}

	public List<TipoPagamento> recuperarTiposPagamento() {
		Session session = HibernateUtil.getInstance().getSession();
		TipoPagamentoDAO tipoPagamentoDAO = new TipoPagamentoDAO(session);
		List<TipoPagamento> tipos = tipoPagamentoDAO.recuperar();
		session.close();
		return tipos;
	}
}
