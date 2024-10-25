package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import model.Tour;

public class CSVReader {

    public List<Tour> readTourFromFile(String filePath) {
        List<Tour> tours = new ArrayList<>();
        String line;
        String csvSplitBy = ",";

        // Use InputStreamReader with UTF-8 encoding to read the file
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"))) {
            // Skip the header line
            br.readLine();

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            // Read each data line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(csvSplitBy);

                if (values.length < 7) {
                    System.out.println("Skipping line due to insufficient data: " + line);
                    continue;
                }

                // Parse the data into appropriate types
                String tourName = values[0];
                String tourDescription = values[1];
                Date startDate = new Date(dateFormat.parse(values[2]).getTime());
                Date endDate = new Date(dateFormat.parse(values[3]).getTime());
                String location = values[4];
                BigDecimal price = new BigDecimal(values[5]);
                int slot = Integer.parseInt(values[6]);
                LocalDate currentDate = LocalDate.now();
                Date createAt = Date.valueOf(currentDate);

                // Calculate the duration in days
                long durationInMillis = endDate.getTime() - startDate.getTime();
                long days = TimeUnit.MILLISECONDS.toDays(durationInMillis) + 1;
                long nights = days - 1;
                String duration = days + "N" + nights + "D";

                // Add new Tour object to the list
                tours.add(new Tour(tourName, tourDescription, startDate, endDate, location, duration, price, slot, "Pending", createAt));
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return tours;
    }

// Method to get the latest file in a directory
    public File getLatestFileFromDir(String dirPath) {
        File dir = new File(dirPath);

        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("Directory does not exist or is not a directory.");
            return null;
        }

        // Get all files in the directory
        File[] files = dir.listFiles(File::isFile);

        if (files == null || files.length == 0) {
            System.out.println("No files found in the directory.");
            return null; // No files found
        }

        // Sort files by last modified date, in descending order (latest first)
        Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());

        // Return the latest file (first file after sorting)
        return files[0];
    }

    public static void main(String[] args) {
        CSVReader csvReader = new CSVReader();
        String dirPath = "E:/FPTU/Major 5/SWP/project/TourHub/TourHub/web/assets/tour-imported/";

        // Get the latest file from the specified directory
        File latestFile = csvReader.getLatestFileFromDir(dirPath);

        if (latestFile != null) {
            System.out.println("Reading tours from file: " + latestFile.getName());

            // Read the tours from the latest file
            List<Tour> tours = csvReader.readTourFromFile(latestFile.toString());
            for (Tour tour : tours) {
                System.out.println(tour.toString());
            }
        } else {
            System.out.println("No suitable file found to read tours from.");
        }
    }
}
