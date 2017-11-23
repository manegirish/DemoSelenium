package test.operations;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Keywords {

	private static WebDriver driver;
	private static Properties properties;
		
	public void openBrowser() throws IOException {
		System.setProperty("webdriver.chrome.driver","chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		properties = new Properties();
		FileInputStream input = new FileInputStream("object.properties");
		properties.load(input);
	}

	public void navigateUrl(String testData)throws InterruptedException {
		driver.get(testData);
	}

	public void click(String objectName,String path, String book_name, int row_number, int coloumn) throws InterruptedException{
		Thread.sleep(5000);
		try {
			CheckElement.isPresent(driver, properties, objectName, path, book_name, row_number, coloumn);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			driver.findElement(By.xpath(properties.getProperty(objectName))).click();
		}	
	}

	public void scrollDown(String testData) throws InterruptedException {
		Thread.sleep(5000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript(testData, "");
		Thread.sleep(1000);
	}

	public void inputData(String testData, String objectName,String path, String book_name, int row_number, int coloumn) 
					throws InterruptedException {
		Thread.sleep(5000);
		try {
			CheckElement.isPresent(driver, properties, objectName, path, book_name, row_number, coloumn);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			driver.findElement(By.xpath(properties.getProperty(objectName))).clear();
			driver.findElement(By.xpath(properties.getProperty(objectName))).sendKeys(testData);
		}	
	}

}
