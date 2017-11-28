package test.operations;

import java.io.IOException;
import java.util.Properties;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

class CheckElement {

	protected static void isPresent(WebDriver driver, Properties properties, String objectName, String path,
			String book_name, int row_number, int coloumn) throws IOException {
		// if element present != 0
		if (driver.findElements(By.xpath(properties.getProperty(objectName))).size() != 0) {
			WriteExcel.write(path, book_name, "PASS", row_number, coloumn);
		} else {
			WriteExcel.write(path, book_name, "FAILED", row_number, coloumn);
		}
	}

	public void verifyURLStatus(String hyperlink) {
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(hyperlink);
		try {
			HttpResponse response = client.execute(request);
			if (response.getStatusLine().getStatusCode() == 200) {

			} else {

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
