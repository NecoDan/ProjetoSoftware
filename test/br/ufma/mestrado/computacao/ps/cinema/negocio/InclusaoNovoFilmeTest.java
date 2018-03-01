package br.ufma.mestrado.computacao.ps.cinema.negocio;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class InclusaoNovoFilmeTest {

	private WebDriver driver;

	@Before
	public void before() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void inclusaoFilmeComSucesso() {
		LoginPage loginPage = new LoginPage(driver);
		ListaFilmesPage homePage = loginPage.
				visita("http://localhost:8080/ProjetoSoftware/").
				autentica("danielsantos","123456789");

		InclusaoFilmePage inclusaoFilmePage = homePage.novoFilme();

		ListaFilmesPage listaFilmesPage = inclusaoFilmePage
				.nome("The Girl on the Train - A Garota no Trem")
				.classificacao("14 Anos")
				.ano("2016")
				.emCartazAteh("01/03/2018")
				.duracao("84")
				.sinopse("A divorcee becomes entangled in a missing persons investigation that promises to send shockwaves throughout her life (Um divorciado torna-se enredado em uma investigação de pessoas desaparecidas que promete enviar ondas de choque ao longo de sua vida).")
				.salvarFilme();

		assertTrue(listaFilmesPage.filmeIncluidoSucesso());
	}

	@After
	public void after() {
		driver.quit();
	}

}
