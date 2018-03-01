package br.ufma.mestrado.computacao.ps.cinema.dao;

import java.util.List;

import org.hibernate.Session;

import br.ufma.mestrado.computacao.ps.cinema.dominio.TipoPagamento;

public class TipoPagamentoDAO extends GenericDAO<TipoPagamento> {

	public TipoPagamentoDAO(Session session) {
		super(session);
	}

	@SuppressWarnings("unchecked")
	public List<TipoPagamento> recuperar() {
		return session.createQuery("from TipoPagamento t").list();
	}
}
