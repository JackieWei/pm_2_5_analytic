package pm25.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
                if (Files.isRegularFile(filePath) && filePath.toUri().getRawPath().endsWith(".csv")) {
                    readFile(filePath.toUri().getRawPath());
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
        File file = null;

        try {
            file = new File(csvFile);
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] country = line.split(cvsSplitBy);
                System.out.println(line);
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
