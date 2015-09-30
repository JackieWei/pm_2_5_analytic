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
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CSVReader {
    private static String VALID = "valid";
    private static String DASH = "-";
    private static int NULL_VALUE = -999;
    
    public static void main(String[] args) {
        CSVReader reader = new CSVReader("C:\\ng\\pm_2_5_analytic\\data");
        reader.storeIntoDB();
    }

    public CSVReader(String folderName) {
        try {
            Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection conn = driver.connect("jdbc:mysql://localhost:3306/pm25?user=root&password=", null);
            Statement stmt = conn.createStatement();
            int rs = -1;
            StringBuilder sql = new StringBuilder();
            Files.walk(Paths.get(folderName)).forEach(filePath -> {
                if (Files.isRegularFile(filePath) && filePath.toUri().getRawPath().endsWith(".csv")) {
                    sql.append(readFile(filePath.toUri().getRawPath()));
                }
            });
            String sqlEx = sql.toString();
            rs = stmt.executeUpdate("insert into hourly values" + sqlEx.substring(0, sqlEx.length() - 2));
            stmt.close();
            conn.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
    }
    
    private String getFullNumberString(String rawNumberStr) {
        final String ZERO = "0";
        int number = Integer.parseInt(rawNumberStr);
        if (number < 10) {
            return ZERO + rawNumberStr;
        }
        return rawNumberStr;
    }
    
    private String readFile(String fileName) {
        String csvFile = fileName;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        File file = null;
        String city = null;
        String date = null;
        String year = null;
        String month = null;
        String day = null;
        String hour = null;
        int value = 0;
        String status = null;
        StringBuilder sql = new StringBuilder();

        try {
            file = new File(csvFile);
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] entry = line.split(cvsSplitBy);
                if (entry.length == 11 && !entry[0].equals("Site")) {
                    city = entry[0];
                    year = entry[3];
                    month = getFullNumberString(entry[4]);
                    day = getFullNumberString(entry[5]);
                    hour = getFullNumberString(entry[6]);
                    date = year + DASH + month + DASH + day + "  " + hour + ":00:00";
                    value = Integer.parseInt(entry[7]);
                    status = entry[10];
                    //4/8/2008  15:00:00 PM
                    if (status.equalsIgnoreCase(VALID) && value != NULL_VALUE) {
                        sql.append("(0,'" + city + "','PM2.5','" + date + "'," + value + "),\n");
                    }
                }
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

        System.out.println("File name:" + fileName + " --> Done!");
        
        return sql.toString();
    }

    public boolean storeIntoDB() {
        return true;
    }
}
