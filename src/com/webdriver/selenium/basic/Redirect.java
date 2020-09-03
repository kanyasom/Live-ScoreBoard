package com.webdriver.selenium.basic;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

//TESTING FOR MY LANDING PAGE
public class Redirect {

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
			driver.get("http://localhost:8082/TournamentLiveScore/redirect.html");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Redirect myObj = new Redirect();
		myObj.invokeBrowser();
	}


}
