package br.ufma.mestrado.computacao.ps.cinema.dao;

import org.hibernate.Session;

/**
 * Classe responsável por fornecer as operações básicas de acesso a dados (CRUD) que as entidades do sistema irão
 * precisar. Esta classe serve como classe-mãe para os outros DAOs.
 * 
 * @param <T>
 *            a entidade que irá ser manipulada pelo DAO-filho
 */
public class GenericDAO<T> {

	protected Session session;

	public GenericDAO(Session session) {
		this.session = session;
	}

	/**
	 * Método responsável por excluir um objeto persistente da base de dados
	 * 
	 * @param obj
	 *            o objeto que desejamos excluir da base de dados
	 */
	public void excluir(T obj) {
		session.delete(obj);
	}

	/**
	 * Método responsável por salvar (inserir ou atualizar) um objeto na base de dados
	 * 
	 * @param obj
	 *            o objeto que desejamos salvar (inserir ou atualizar) da base de dados
	 */
	public void salvar(T obj) {
		session.saveOrUpdate(obj);
	}
}
