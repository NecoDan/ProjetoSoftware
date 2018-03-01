package br.ufma.mestrado.computacao.ps.cinema.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

import br.ufma.mestrado.computacao.ps.cinema.dominio.Filme;

/**
 * Classe responsável por realizar o acesso aos dados dos filmes.
 */
public class FilmeDAO extends GenericDAO<Filme> {

	public FilmeDAO(Session session) {
		super(session);
	}

	/**
	 * Método responsável por recuperar os filmes que estão em cartaz numa determinada data
	 * 
	 * @param data
	 *            a data que queremos obter os filmes que estão em cartaz
	 * @return a lista dos filmes que estão em cartaz naquela data
	 */
	@SuppressWarnings("unchecked")
	public List<Filme> recuperarFilmesEmExibicao(Date data) {
		Query query = session.createQuery("from Filme f where f.emCartazAte >= :data");
		query.setParameter("data", data);

		return query.list();
	}

	/**
	 * Método responsável por recuperar todos os filmes que estão cadastrados no cinema
	 * 
	 * @return a lista de todos os filmes cadastrados no cinema
	 */
	@SuppressWarnings("unchecked")
	public List<Filme> recuperarTodosOsFilmes() {
		Criteria criteria = session.createCriteria(Filme.class);
		return criteria.list();
	}
}
