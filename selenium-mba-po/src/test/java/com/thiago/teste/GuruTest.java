package com.thiago.teste;

import com.thiago.driver.TLDriverFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.thiago.core.InvokedMethodListener;
import com.thiago.page.PageGuruHome;
 
@Listeners(InvokedMethodListener.class)
public class GuruTest{

	public WebDriver driver;
	private static String texto;

	@Before
	public void before() {
		driver = TLDriverFactory.getDriver();
	}

	@After
	public void after() {
		driver = null;
		TLDriverFactory.endDriver();
	}

    @Test
    public void loginSucesso() throws Exception {
		new PageGuruHome()
    	.openPage(PageGuruHome.class, "http://demo.guru99.com/test/login.html")
    	.acessarLoginGuru("thiagoovcf@gmail.com", "123")
    	.validarAcessoOK();
		texto = new PageGuruHome().getElement(By.className("error-copy")).findElement(By.tagName("h3")).getText();
		Assert.assertEquals("Successfully Logged in...", texto);
    	
    }
}