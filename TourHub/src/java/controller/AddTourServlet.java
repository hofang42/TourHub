package controller;

import DataAccess.TourDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/addtour")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class AddTourServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tourName = request.getParameter("tour_Name");
        String tourDescription = request.getParameter("tour_Description");
        String startDate = request.getParameter("start_Date");
        String endDate = request.getParameter("end_Date");
        String location = request.getParameter("location");
        String dayParam = request.getParameter("day");
        String nightParam = request.getParameter("night");

        // Default day and night values to avoid null issues
        String day = (dayParam != null && !dayParam.isEmpty()) ? dayParam : "0";
        String night = (nightParam != null && !nightParam.isEmpty()) ? nightParam : "0";

        String duration = day + "D" + night + "N";
        double price = Double.parseDouble(request.getParameter("price"));
        int slot = Integer.parseInt(request.getParameter("slot"));

        // Handle multiple file uploads
        StringBuilder fileNames = new StringBuilder(); // To store the filenames separated by ";"
        for (Part part : request.getParts()) {
            if (part.getName().equals("tour_Img") && part.getSize() > 0) {
                String fileName = extractFileName(part);
                fileName = new File(fileName).getName(); // Get the file name
                part.write(getFolderUpload().getAbsolutePath() + File.separator + fileName); // Save file
                if (fileNames.length() > 0) {
                    fileNames.append(";"); // Separate filenames with a semicolon
                }
                fileNames.append(fileName); // Append the filename to the list
            }
        }

        String imageFilenames = fileNames.toString(); // Convert StringBuilder to String

        // Save tour information to the database
        try {
            new TourDB().saveTourToDatabase(request, tourName, tourDescription, startDate, endDate, location,
                    duration, price, slot, imageFilenames);
            request.setAttribute("message", "Tour added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("message", "Error adding tour: " + e.getMessage());
        }

        getServletContext().getRequestDispatcher("/add-tour.jsp").forward(request, response);
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

    public File getFolderUpload() {
        File folderUpload = new File(System.getProperty("user.home") + "/Uploads");
        if (!folderUpload.exists()) {
            folderUpload.mkdirs();
        }
        return folderUpload;
    }

}
