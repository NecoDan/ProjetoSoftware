package br.ufma.mestrado.computacao.ps.cinema.dao;

import org.hibernate.Session;

import br.ufma.mestrado.computacao.ps.cinema.dominio.Maquina;

public class MaquinaDAO extends GenericDAO<Maquina> {

	public MaquinaDAO(Session session) {
		super(session);
	}

	public Maquina recuperar(int id) {
		return (Maquina) session.get(Maquina.class, id);
	}
}
