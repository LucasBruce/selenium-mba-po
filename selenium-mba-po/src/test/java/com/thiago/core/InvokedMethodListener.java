package com.thiago.core;

import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import com.thiago.driver.TLDriverFactory;
 

public class InvokedMethodListener implements IInvokedMethodListener {
	
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            System.out.println("Metodo de teste invocado no Before. " + Thread.currentThread().getId());
            String browserName = method.getTestMethod().getXmlTest().getLocalParameters().get("browser");
            if(null ==browserName) {
            	TLDriverFactory.setDriver("chrome");
            }else {
            	TLDriverFactory.setDriver(browserName);
            }
        }
    }
 
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            System.out.println("Metodo de teste invocado no After. " + Thread.currentThread().getId());
            WebDriver driver = TLDriverFactory.getDriver();
            if (driver != null) {
                driver.quit();
            }
        }
    }
}

