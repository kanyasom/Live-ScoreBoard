package com.webdriver.selenium.basic;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

//TESTING FOR MY LANDING PAGE
public class Basic {

	WebDriver driver;
	public void invokeBrowser() {
		
		try {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\somka\\Downloads\\selenium-server-standalone-4.0.0-alpha-2\\chromedriver_win32 (1)\\chromedriver.exe");
			
			driver = new ChromeDriver();
			driver.manage().deleteAllCookies(); //manage browsers property
			driver.manage().window().maximize();// maximize browser window
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);//page synchronisation
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			
			//fetch url
			driver.get("http://localhost:8082/TournamentLiveScore/index.html");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*public void hoverHome() {
		
		WebElement e = driver.findElement(By.xpath());
		Actions act = new Actions(driver);
	}*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Basic myObj = new Basic();
		myObj.invokeBrowser();
		
		
	}


}
