package br.ufma.mestrado.computacao.ps.cinema.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.ufma.mestrado.computacao.ps.cinema.dominio.Filme;
import br.ufma.mestrado.computacao.ps.cinema.dominio.Sessao;

/**
 * Classe responsável por realizar o acesso aos dados das sessões de cinema.
 */
public final class SessaoDAO extends GenericDAO<Sessao> {

	public SessaoDAO(Session session) {
		super(session);
	}

	/**
	 * Método responsável por obter as sessões de um filme em uma sala em um dia
	 * 
	 * @param filme
	 *            o filme que desejmos obter as sessoes
	 * @param sala
	 *            a sala onde o filme será exibido
	 * @param data
	 *            o dia que o filme será exibido
	 * @return as sessões de um filme em uma sala em um dia
	 */
	@SuppressWarnings("unchecked")
	public List<Sessao> recuperar(Filme filme, Date data) {
		Query query = session
				.createQuery("from Sessao s where s.filme = :filme and s.fim >= :data order by s.sala.nome asc, s.inicio asc");
		query.setParameter("filme", filme);
		query.setParameter("data", data);

		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Sessao> recuperar(Filme filme) {
		Query query = session
				.createQuery("from Sessao s where s.filme = :filme order by s.sala.nome asc, s.inicio asc");
		query.setParameter("filme", filme);
		return query.list();
	}
}
