package br.ufma.mestrado.computacao.ps.cinema.dao;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.ufma.mestrado.computacao.ps.cinema.dominio.Pagamento;

public class PagamentoDAO extends GenericDAO<Pagamento> {

	public PagamentoDAO(Session session) {
		super(session);
	}

	@SuppressWarnings("unchecked")
	public List<Pagamento> recuperar(Calendar dataInicio, Calendar dataFim) {
		Criteria criteria = session.createCriteria(Pagamento.class);

		if (dataInicio != null && dataFim != null) {
			criteria.add(Restrictions.between("data", dataInicio, dataFim));
		}

		return criteria.list();
	}
}
