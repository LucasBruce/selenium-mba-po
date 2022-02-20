package com.thiago.driver;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.thiago.core.OptionsManager;
 
 
public class TLDriverFactory {
 
    private static OptionsManager optionsManager = new OptionsManager();
    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
 
    public static synchronized void setDriver(String browser) {
        if (browser.equals("firefox")) {
            //For Local Usage
            tlDriver = ThreadLocal.withInitial(() -> new FirefoxDriver(optionsManager.getFirefoxOptions()));
 
        } else if (browser.equals("chrome")) {
            //For Local Usage
            tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
        }else if (browser.equals("grid")){
			try {
				tlDriver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), optionsManager.getRemoteDriverChrome()));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
        }
    }
 
    public static synchronized WebDriverWait getWait (WebDriver driver) {
        return new WebDriverWait(driver,20);
    }
 
    public static synchronized WebDriver getDriver() {
        return tlDriver.get();
    }
    public static synchronized void endDriver() {
		tlDriver.get().quit();
		tlDriver.remove();
	}
}
