package pm25.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CSVReader {
	public static void main(String[] args) {
		CSVReader reader = new CSVReader("C:\\ng\\pm_2_5_analytic\\data");
		reader.storeIntoDB();
	}

	public CSVReader(String folderName) {
		try {
			Files.walk(Paths.get(folderName)).forEach(filePath -> {
				if (Files.isRegularFile(filePath)) {
					System.out.println(filePath);
					filePath.endsWith(".csv");
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void readFile(String fileName) {
		String csvFile = fileName;
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] country = line.split(cvsSplitBy);

				System.out.println("Country [code= " + country[4] + " , name="
						+ country[5] + "]");

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("Done");
	}

	public boolean storeIntoDB() {
		return true;
	}
}
