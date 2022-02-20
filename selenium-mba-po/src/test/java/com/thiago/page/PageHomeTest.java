package com.thiago.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.thiago.core.CorePage;
import com.thiago.driver.TLDriverFactory;

public class PageHomeTest extends CorePage<PageHomeTest>{
	
	public PageHomeTest() {
		this.driver = TLDriverFactory.getDriver();
		PageFactory.initElements(this.driver, this);
	}
	
	@FindBy(name = "q")
	private WebElement inputTextSearch;
	
	@FindBy(name = "btnK")
	private WebElement bntSearch;
	
	@FindBy(name = "btnK")
	private WebElement bntSearch2;
	
	
	public void pesquisarNoGoogle() {
		click(bntSearch);
		driver.findElement(By.id("btnK")).click();
	}

}
