package br.ufma.mestrado.computacao.ps.cinema.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import br.ufma.mestrado.computacao.ps.cinema.dominio.Sala;

/**
 * Classe responsável por realizar o acesso aos dados das salas.
 */
public class SalaDAO extends GenericDAO<Sala> {

	public SalaDAO(Session session) {
		super(session);
	}

	/**
	 * Método responsável por recuperar todas as salas que estão cadastradas no cinema
	 * 
	 * @return a lista de todas as salas cadastradas no cinema
	 */
	@SuppressWarnings("unchecked")
	public List<Sala> recuperarTodasAsSalas() {
		Criteria criteria = session.createCriteria(Sala.class);
		return criteria.list();
	}
}
