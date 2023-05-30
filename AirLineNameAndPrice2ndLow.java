package Maven.DamcoSolution;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AirLineNameAndPrice2ndLow {
	
WebDriver driver;
	
	@BeforeTest
	public void config() {
		WebDriverManager.chromedriver().setup();
		 driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://www.makemytrip.com/flights/");

}
	
	
	@Test
	public void airlineNameAndPrice2ndLowest() {
		
		//Enter the from and distination city
		
		driver.findElement(By.id("fromCity")).click();
		  
			driver.findElement(By.xpath("//div[text()='DEL']")).click();
			driver.findElement(By.id("toCity")).click();
			driver.findElement(By.xpath("//div[text()='BOM']")).click();
		    
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("window.scrollBy(0,400)");
			
		   //Enter date in the sorted from calender
			driver.findElement(By.xpath("//div[@aria-label='Tue May 30 2023']")).click();
			
			//Search the flights list in the sorted form
			
			driver.findElement(By.cssSelector(".widgetSearchBtn ")).click();
			driver.findElement(By.xpath("//button[text()='OKAY, GOT IT!']")).click();
			
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("window.scrollBy(0,500)");
		
			
			
			//Store the 2nd lowest price in string and print them
			    String price = driver.findElement(By.xpath("//div[@id='flight_list_item_1_RKEY:51878459-2c6b-4e84-8f08-51fc3ce527de:33_0']//div[@class='textRight flexOne']//div[1]")).getText();
			     String airLine = driver.findElement(By.xpath("//div[@id='flight_list_item_5_RKEY:df722860-467a-4f44-86a9-fc1071a29908:13_0']//div[contains(@class,'listingCard')]//div[contains(@class,'makeFlex simpleow')]//div[contains(@class,'makeFlex align-items-center gap-x-10 airline-info-wrapper')]//div//p[contains(@class,'boldFont blackText airlineName')][normalize-space()='IndiGo']")).getText();
			     System.out.println("Second lowest price:"+ price);
					System.out.println("Name of the Airline with second lowest price:"+ airLine);
	}
	
	
	@AfterMethod
	public void tearDow() {
		driver.quit();
	}
}