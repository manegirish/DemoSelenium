package test.operations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FindBrokenLinksExample {

	private WebDriver driver;

	@BeforeClass
	public void setUp() {

		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://digiperfect.com/");
	}

	@Test
	public void validateInvalidLinks() {
		try {
			List<WebElement> anchorTagsList = driver.findElements(By.tagName("a"));
			int row_number = 0;
			for (WebElement webElement : anchorTagsList) {
				ArrayList<String> dataList = new ArrayList<String>();
				if (webElement != null) {
					String url = webElement.getAttribute("href");
					int statusCode = 0;
					dataList.add(""+(row_number+1));
					dataList.add(url);
					dataList.add(webElement.getTagName());
					if (url != null && !url.contains("javascript")) {
						statusCode = verifyURLStatus(url);
						dataList.add(""+statusCode);
					} else {					
						dataList.add(""+statusCode);
					}
					dataList.add(webElement.getAttribute("outerHTML"));
					if (statusCode==200) {
						dataList.add("PASS");
					}else {
						dataList.add("FAILED");
					}
				}
				enterExcel(dataList, row_number+1, "digi.xlsx", "Sheet1");
				row_number++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	@AfterClass
	public void tearDown() {
		if (driver != null)
			driver.quit();
	}
	
	private void enterExcel(ArrayList<String> dataList, int row_number,String path,String  book_name) throws IOException {
		System.out.println("dataList: "+dataList);
		for (int i = 0; i < dataList.size(); i++) {
			WriteExcel.write(path, book_name, dataList.get(i), row_number, i);
		}
	}

	public int verifyURLStatus(String URL) {
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(URL);
		try {
			HttpResponse response = client.execute(request);
			return response.getStatusLine().getStatusCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}