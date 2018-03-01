package br.ufma.mestrado.computacao.ps.cinema.negocio;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;

import br.ufma.mestrado.computacao.ps.cinema.dao.FilmeDAO;
import br.ufma.mestrado.computacao.ps.cinema.dao.MaquinaDAO;
import br.ufma.mestrado.computacao.ps.cinema.dao.PagamentoDAO;
import br.ufma.mestrado.computacao.ps.cinema.dao.SalaDAO;
import br.ufma.mestrado.computacao.ps.cinema.dao.SessaoDAO;
import br.ufma.mestrado.computacao.ps.cinema.dominio.Dinheiro;
import br.ufma.mestrado.computacao.ps.cinema.dominio.Filme;
import br.ufma.mestrado.computacao.ps.cinema.dominio.Maquina;
import br.ufma.mestrado.computacao.ps.cinema.dominio.Pagamento;
import br.ufma.mestrado.computacao.ps.cinema.dominio.Sala;
import br.ufma.mestrado.computacao.ps.cinema.dominio.Sessao;
import br.ufma.mestrado.computacao.ps.cinema.exception.SalvarFilmeException;
import br.ufma.mestrado.computacao.ps.cinema.util.HibernateUtil;

public class ManutencacaoNegocio {

	public void abrirCaixa(Maquina maquina, Dinheiro dinheiro) throws Exception {
		Session session = HibernateUtil.getInstance().getSession();
		MaquinaDAO maquinaDAO = new MaquinaDAO(session);

		org.hibernate.Transaction tx = session.beginTransaction();

		try {
			maquina.colocarDinheiro(dinheiro);

			maquinaDAO.salvar(maquina);

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	public void recarregarMaquina(Maquina maquina, int qtdImpressoes) throws Exception {
		Session session = HibernateUtil.getInstance().getSession();
		MaquinaDAO maquinaDAO = new MaquinaDAO(session);

		org.hibernate.Transaction tx = session.beginTransaction();

		try {
			maquina.recarregar(qtdImpressoes);

			maquinaDAO.salvar(maquina);

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	public Maquina recuperarMaquina(int id) {
		Session session = HibernateUtil.getInstance().getSession();
		MaquinaDAO maquinaDAO = new MaquinaDAO(session);
		Maquina maquina = maquinaDAO.recuperar(id);
		session.close();
		return maquina;
	}

	public List<Pagamento> recuperarPagamentos(Calendar dataInicio, Calendar dataFim) {
		Session session = HibernateUtil.getInstance().getSession();
		PagamentoDAO pagamentoDAO = new PagamentoDAO(session);
		List<Pagamento> pagamentos = pagamentoDAO.recuperar(dataInicio, dataFim);
		session.close();
		return pagamentos;
	}

	public List<Filme> recuperarTodosOsFilmesCadastrados() {
		Session session = HibernateUtil.getInstance().getSession();
		FilmeDAO filmeDAO = new FilmeDAO(session);
		List<Filme> filmes = filmeDAO.recuperarTodosOsFilmes();
		session.close();
		return filmes;
	}

	public List<Sala> recuperarTodasAsSalas() {
		Session session = HibernateUtil.getInstance().getSession();
		SalaDAO salaDAO = new SalaDAO(session);
		List<Sala> salas = salaDAO.recuperarTodasAsSalas();
		session.close();
		return salas;
	}

	public List<Sessao> recuperarSessoes(Filme filme) {
		Session session = HibernateUtil.getInstance().getSession();
		SessaoDAO sessaoDAO = new SessaoDAO(session);
		List<Sessao> sessoes = sessaoDAO.recuperar(filme);
		session.close();
		return sessoes;
	}

	public void salvar(Filme filme) throws Exception {
		if (!filme.isEmCartaz()) {
			throw new SalvarFilmeException();
		}

		Session session = HibernateUtil.getInstance().getSession();
		FilmeDAO filmeDAO = new FilmeDAO(session);

		org.hibernate.Transaction tx = session.beginTransaction();

		try {
			filmeDAO.salvar(filme);

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	public void salvar(Sessao sessao) throws Exception {
		if (!sessao.naoIniciou()) {
			throw new Exception("Somente sessões que ainda vão começar podem ser salvas");
		}

		Session session = HibernateUtil.getInstance().getSession();
		SessaoDAO sessaoDAO = new SessaoDAO(session);

		org.hibernate.Transaction tx = session.beginTransaction();

		try {
			sessaoDAO.salvar(sessao);

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

}
