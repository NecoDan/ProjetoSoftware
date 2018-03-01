package br.ufma.mestrado.computacao.ps.cinema.negocio;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InclusaoFilmePage {
	
	private WebDriver driver;

	public InclusaoFilmePage(WebDriver driver) {
		this.driver = driver;
	}

	public InclusaoFilmePage nome(String nomeFilme) {
		driver.findElement(By.id("formCinema:Nome")).sendKeys(nomeFilme);
		return this;
	}

	public InclusaoFilmePage classificacao(String classificacao) {
		driver.findElement(By.id("formCinema:classificacao")).sendKeys(classificacao);
		return this;
	}
	
	public InclusaoFilmePage ano(String ano) {
		driver.findElement(By.id("formCinema:Ano")).sendKeys(ano);
		return this;
	}
	
	public InclusaoFilmePage duracao(String duracao) {
		driver.findElement(By.id("formCinema:duracao")).sendKeys(duracao);
		return this;
	}
	
	public InclusaoFilmePage emCartazAteh(String emCartaz) {
		driver.findElement(By.id("formCinema:emCartaz")).sendKeys(emCartaz);
		return this;
	}
	
	public InclusaoFilmePage sinopse(String sinopse) {
		driver.findElement(By.id("formCinema:Sinopse")).sendKeys(sinopse);
		return this;
	}

	public ListaFilmesPage salvarFilme() {
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		return new ListaFilmesPage(driver);
	}
    
}
