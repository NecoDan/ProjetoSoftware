package br.ufma.mestrado.computacao.ps.cinema;

import org.hibernate.Session;

import br.ufma.mestrado.computacao.ps.cinema.util.HibernateUtil;

public class Main {

	public static void main(String[] args) {

		// Abre sessão com o Hibernate
		Session session = HibernateUtil.getInstance().getSession();
		
		// Transaction tx = session.beginTransaction();

		// TipoPagamento dinheiro = new TipoPagamento("DINHEIRO");
		// session.save(dinheiro);
		//
		// TipoPagamento cartaoCredito = new TipoPagamento("CARTÃO DE CRÉDITO");
		// session.save(cartaoCredito);
		//
		// TipoPagamento cartaoDebito = new TipoPagamento("CARTÃO DE DÉBITO");
		// session.save(cartaoDebito);
		//
		// System.out.println(dinheiro.getId());
		// System.out.println(cartaoCredito.getId());
		// System.out.println(cartaoDebito.getId());

		// Sala sala = new Sala("Sala 3", 200, 25);
		//
		// List<Assento> assentos = new ArrayList<Assento>();
		//
		// for (int i = 1; i <= sala.getQuantidadeFileiras(); i++) {
		// for (int j = 1; j <= sala.getQuantidadeAssentos() / sala.getQuantidadeFileiras(); j++) {
		// Assento assento = new Assento();
		// assento.setNumeroFileira(i);
		// assento.setPosicaoFileira(j);
		// assento.setSala(sala);
		//
		// assentos.add(assento);
		//
		// System.out.println(assento.getNumeroFileira() + " " + assento.getPosicaoFileira());
		// }
		// }
		//
		// sala.setAssentos(assentos);
		// session.save(sala);

		// Sala sala1 = new Sala();
		// sala1.setId(1);
		//
		// Calendar emCartazAte = Calendar.getInstance();
		// emCartazAte.set(2013, Calendar.NOVEMBER, 30);
		//
		// Filme filme = new Filme();
		// filme.setAno(2013);
		// filme.setClassificao(14);
		// filme.setDuracao(115);
		// filme.setEmCartazAte(emCartazAte);
		// filme.setNome("ROTA DE FUGA");
		// filme.setSinopse("Quando Ray Breslin (Sylvester Stallone), um especialista em segurança, é preso em um local à "
		// +
		// "prova de fugas, ele vai precisar usar todas as suas habilidades para escapar e descobrir quem o incriminou.");
		//
		// List<Sessao> sessoes = new ArrayList<Sessao>();
		//
		// // Sessao 11
		//
		// Calendar inicioSessao1 = Calendar.getInstance();
		// inicioSessao1.set(2013, Calendar.OCTOBER, 12, 16, 5);
		//
		// Calendar fimSessao1 = Calendar.getInstance();
		// fimSessao1.setTime(inicioSessao1.getTime());
		// fimSessao1.add(Calendar.MINUTE, filme.getDuracao());
		//
		// Sessao sessao1 = new Sessao();
		// sessao1.setFilme(filme);
		// sessao1.setFim(fimSessao1);
		// sessao1.setInicio(inicioSessao1);
		// sessao1.setPrecoIngresso(new BigDecimal(20.00));
		// sessao1.setSala(sala1);
		//
		// // Sessao 12
		//
		// Calendar inicioSessao2 = Calendar.getInstance();
		// inicioSessao2.set(2013, Calendar.OCTOBER, 12, 21, 5);
		//
		// Calendar fimSessao2 = Calendar.getInstance();
		// fimSessao2.setTime(inicioSessao2.getTime());
		// fimSessao2.add(Calendar.MINUTE, filme.getDuracao());
		//
		// Sessao sessao2 = new Sessao();
		// sessao2.setFilme(filme);
		// sessao2.setFim(fimSessao2);
		// sessao2.setInicio(inicioSessao2);
		// sessao2.setPrecoIngresso(new BigDecimal(26.00));
		// sessao2.setSala(sala1);
		//
		// sessoes.add(sessao1);
		// sessoes.add(sessao2);
		//
		// filme.setSessoes(sessoes);
		//
		// session.save(filme);

		// tx.commit();

		// Fecha sessão com o Hibernate
		session.close();
	}
}
