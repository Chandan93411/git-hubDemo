package Maven.DamcoSolution;

import java.io.File;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Mail {
	
	WebDriver driver;
	
	@BeforeTest
	public void config() {
		WebDriverManager.chromedriver().setup();
		 driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	   driver.get("https://www.aol.com/?ncid=crosssellusaolc00000003");
		
	}
	//Note Please use a valid emailid and password before running the script
	@Test
	public void emailBody() throws InterruptedException {
		driver.findElement(By.cssSelector(".profile-button")).click();
		   driver.findElement(By.id("login-username")).sendKeys("EmailId");
		   driver.findElement(By.id("login-signin")).click();
		   driver.findElement(By.id("login-passwd")).sendKeys("Password");
		   driver.findElement(By.id("login-signin")).click();
		  
		  //Navigate to compose mail
		  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Mail']"))).click();
		  String Window= driver.getWindowHandle();
		     Set<String> NewWindow = driver.getWindowHandles();
		    java.util.Iterator<String> It =  NewWindow.iterator();
		    String parentWind = It.next();
		    String ChildWindow = It.next();
		    driver.switchTo().window(ChildWindow);
		  WebDriverWait newwait = new WebDriverWait(driver, Duration.ofSeconds(50));
		  newwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-test-id='compose-button']"))).click();
		  
		  
		  
		  
		  //Enter receipent mail and subject
		   driver.findElement(By.xpath("//input[@aria-owns='react-typehead-list-to']")).sendKeys("chandan_90@aol.com");
		   driver.findElement(By.xpath("//input[@data-test-id='compose-subject']")).sendKeys("Damco");
		   //Save the body details in String Array
		   String[] body= {"Line one" ,"Line two", "Line three"};
		   int n = body.length;
		  
		   //click on the bullet icon and enter the body
		  driver.findElement(By.xpath("//button[@title='Bulleted List']")).click();
		  driver.findElement(By.xpath("//li[@name='bullet']")).click();
		  // Enter the body message by iterating through the saved body as an array
		   for(int i=0; i<n; i++) {
			   WebElement box = driver.findElement(By.xpath("//div[@role='textbox']"));
			 box.sendKeys(body[0]+"\n");
			 box.sendKeys(body[1]+"\n");
			 box.sendKeys(body[2]);
			 break;
		   }
		   //Attach the jpg file in the mail body
		   driver.findElement(By.xpath("//input[@type='file']")).sendKeys("C:\\Users\\CHANDAN\\Downloads\\img-331210904-0001.jpg");
		   
		   //Send the message 
		   driver.findElement(By.xpath("//button[@data-test-id='compose-send-button']")).click();
		  //Naviaget to inbox
		   driver.findElement(By.xpath("//div[@data-test-folder-container='Inbox']")).click();	
		 
		   //Open the first message from the message list
		   driver.findElement(By.xpath("//span[@data-test-id='senders_list']")).click();
		   
		   //Verify the subject in the message
		   String subject= driver.findElement(By.xpath("//span[@data-test-id='message-group-subject']")).getText();
		   Assert.assertEquals("Damco", subject);
		   //Verify the first line in the body
		   String  firstLine =driver.findElement(By.xpath("//li[normalize-space()='Line one']")).getText();
		   Assert.assertEquals("Line one", firstLine);
		   
		   //Verify the second line in the body
		   String secondLine = driver.findElement(By.xpath("//li[normalize-space()='Line two']")).getText();
		   Assert.assertEquals("Line two", secondLine);
		   //Verify the third line in the body
		   String thirdLine =  driver.findElement(By.xpath("//li[normalize-space()='Line three']")).getText();
		    Assert.assertEquals("Line three", thirdLine);
		//Print that all your cases is passed
		    System.out.println("File attachement and mail body is verified");
		    
		   // WebDriverWait image = new WebDriverWait(driver, Duration.ofSeconds(50));
		   // image.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@title='Download']"))).click();
		    
		  WebElement element=  driver.findElement(By.xpath("//span[contains(text(),'img-331210904-0001')]"));
		    
		    Actions act = new Actions(driver);
		    act.click(element).build().perform();
		    
		    
		    
		    String filePath = "C:\\Users\\CHANDAN\\Downloads\\img-331210904-0001 (1).jpg";
		    
		    File file = new File(filePath);
		    int fileSize = (int) file.length();
		    
		   System.out.println("file sie is:" + fileSize );

	}
	@AfterMethod
	public void tearDow() {
		driver.quit();
	}

}
