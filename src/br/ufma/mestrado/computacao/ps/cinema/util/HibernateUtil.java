package br.ufma.mestrado.computacao.ps.cinema.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

public final class HibernateUtil {

	private SessionFactory sf;

	private static HibernateUtil instance;

	private HibernateUtil() {
		// Cria objeto que receberá as configurações
		Configuration cfg = new AnnotationConfiguration();

		// Informe o arquivo XML que contém a configurações
		cfg.configure("br/ufma/mestrado/computacao/ps/cinema/resources/hibernate.cfg.xml");

		// Cria uma fábrica de sessões (deve existir apenas uma instância na aplicação)
		sf = cfg.buildSessionFactory();
	}

	public static HibernateUtil getInstance() {
		if (instance == null) {
			instance = new HibernateUtil();
		}

		return instance;
	}

	public Session getSession() {
		return sf.openSession();
	}

}
