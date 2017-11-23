package test.main;

import java.io.IOException;
import org.testng.annotations.Test;
import test.parser.ReadExcel;

public class TestMain {
	
	public static final String LINKED_IN_FILE_PATH = "test_case.xlsx";
	public static final String LINKED_IN_WORK_BOOK = "Sheet1";
	
	@Test(priority = 0, enabled = true)
	public void LinkedInLogin() throws IOException, InterruptedException {
		ReadExcel.rowParser(LINKED_IN_FILE_PATH, LINKED_IN_WORK_BOOK);
	}
	
}
