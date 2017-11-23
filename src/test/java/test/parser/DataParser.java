package test.parser;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;

import test.operations.Keywords;

class DataParser {

	private static Keywords keyword;

	protected static void parser(ArrayList<Object> data, String file_path, String work_book, int row_number)
			throws IOException, InterruptedException, AWTException {
		
		keyword = new Keywords();

		System.out.println(data);

		for (int i = 0; i < data.size(); i++) {
			System.out.println(data.get(i));

			if (data.get(i).equals("openBrowser")) {
				keyword.openBrowser();
			}
			
			if (data.get(i).equals("navigateUrl")) {
				String testData = (String) data.get(i + 1);
				keyword.navigateUrl(testData);
			}
			
			if (data.get(i).equals("inputData")) {
				String testData = (String) data.get(i + 1);
				String objectName = (String) data.get(i + 2);
				keyword.inputData(testData, objectName,file_path, work_book,  row_number, data.size()-1);
			}

			if (data.get(i).equals("click")) {
				String objectName = (String) data.get(i + 2);
				keyword.click(objectName,file_path, work_book,  row_number, data.size()-1);
			}

			if (data.get(i).equals("scrollDown")) {
				String testData = (String) data.get(i + 1);
				keyword.scrollDown(testData);
			}

		}
	}
}
