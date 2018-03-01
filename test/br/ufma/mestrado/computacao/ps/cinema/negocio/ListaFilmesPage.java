package br.ufma.mestrado.computacao.ps.cinema.negocio;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ListaFilmesPage {

	private WebDriver driver;

	public ListaFilmesPage(WebDriver driver) {
		this.driver = driver;
	}

	public InclusaoFilmePage novoFilme() {
		driver.findElement(By.linkText("Novo Filme")).click();
		return new InclusaoFilmePage(driver);
	}

	public boolean filmeIncluidoSucesso() {
		WebElement divSucesso = driver.findElement(By.className("alert-success"));
		return divSucesso.getText().contains("Filme salvo com sucesso.");
	}

	public boolean isValida() {
		return temBarraNavegacao() && temListagemFilmes();
	}

	private boolean temBarraNavegacao() {
		return driver.findElement(By.className("navbar")) != null;
	}

	private boolean temListagemFilmes() {
		return driver.findElement(By.tagName("h3")).getText().contains("Filmes Cadastrados");
	}
	
}
