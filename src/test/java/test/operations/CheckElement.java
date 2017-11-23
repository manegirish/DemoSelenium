package test.operations;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

class CheckElement {
	
	protected static void isPresent(WebDriver driver,Properties properties,String objectName,
			String path, String book_name, int row_number, int coloumn) throws IOException {
		// if element present != 0 
		if (driver.findElements(By.xpath(properties.getProperty(objectName))).size() != 0) {
			WriteExcel.write(path, book_name, "PASS", row_number, coloumn);
		}else {
			WriteExcel.write(path, book_name, "FAILED", row_number, coloumn);
		}
	}
	
}
