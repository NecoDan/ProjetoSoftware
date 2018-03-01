package br.ufma.mestrado.computacao.ps.cinema.negocio;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

	private WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public LoginPage visita(String url) {
		driver.get(url);
		return this;
	}

	public ListaFilmesPage autentica(String usuario, String senha) {
		driver.findElement(By.id("usuario")).sendKeys(usuario);
		driver.findElement(By.id("senha")).sendKeys(senha);
		
		driver.findElement(By.cssSelector("btn btn-success")).click();
		//driver.findElement(By.id("acessoManutencao")).click();

		return new ListaFilmesPage(driver);
	}
}
