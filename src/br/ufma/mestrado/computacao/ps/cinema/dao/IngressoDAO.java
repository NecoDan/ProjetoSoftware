package br.ufma.mestrado.computacao.ps.cinema.dao;

import org.hibernate.Query;
import org.hibernate.Session;

import br.ufma.mestrado.computacao.ps.cinema.dominio.Assento;
import br.ufma.mestrado.computacao.ps.cinema.dominio.Ingresso;
import br.ufma.mestrado.computacao.ps.cinema.dominio.Sessao;

public class IngressoDAO extends GenericDAO<Ingresso> {

	public IngressoDAO(Session session) {
		super(session);
	}

	public boolean existeIngresso(Sessao sessao, Assento assento) {
		Query query = session.createQuery("from Ingresso i where i.sessao = :sessao and i.assento = :assento ");
		query.setParameter("sessao", sessao);
		query.setParameter("assento", assento);

		return ((Ingresso) query.uniqueResult()) != null;
	}
}
