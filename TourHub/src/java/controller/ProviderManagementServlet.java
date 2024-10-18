package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import DataAccess.ProvinceDB;
import DataAccess.TourDB;
import DataAccess.UserDB;
import DataAccess.hoang_UserDB;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Tour;

/**
 *
 * @author hoang
 */
@WebServlet(urlPatterns = {"/provider-management"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class ProviderManagementServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        doPost(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        // Sử dụng switch-case để xử lý các loại yêu cầu khác nhau
        switch (action) {
            case "add-tour":
                addNewTour(request, response);
                break;
            case "edit-tour":
                editTour(request, response);
                break;
            case "set-tour-status":
                setStatusATour(request, response);
                break;
            case "search":
                searchTour(request, response);
                break;
            case "save-edit-tour": {
                try {
                    saveEditTour(request, response);
                } catch (ParseException ex) {
                    Logger.getLogger(ProviderManagementServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            case "sort":
                sort(request, response);
        }
    }

    public void addNewTour(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
                part.write(getFolderUpload(request).getAbsolutePath() + File.separator + fileName); // Save file
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

    private void editTour(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tourId = request.getParameter("tourId");
        int companyId = 0;
        try {
            companyId = new hoang_UserDB().getProviderIdFromUserId(new UserDB().getUserFromSession(request.getSession()).getUser_Id());
        } catch (SQLException ex) {
            Logger.getLogger(ProviderManagementServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        Tour tourEdit = new TourDB().getTourFromTourID(tourId, companyId);
        request.setAttribute("tourEdit", tourEdit);
        request.getRequestDispatcher("edit-tour-page.jsp").forward(request, response);
    }

    private void saveEditTour(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        String tourId = request.getParameter("tourId");
        TourDB tourDB = new TourDB();
        Tour oldTour = tourDB.getTourFromTourID(tourId);

        String newTourName = request.getParameter("tour_Name");
        String newTourDescription = request.getParameter("tour_Description");
        String newStartDateStr = request.getParameter("start_Date");
        String newEndDateStr = request.getParameter("end_Date");
        String newLocation = request.getParameter("location");
        String dayParam = request.getParameter("day");
        String nightParam = request.getParameter("night");

        // Parse date strings to java.util.Date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date newStartDate = null;
        Date newEndDate = null;
        try {
            newStartDate = dateFormat.parse(newStartDateStr);
            newEndDate = dateFormat.parse(newEndDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            request.setAttribute("message", "Invalid date format. Please use yyyy-MM-dd.");
            getServletContext().getRequestDispatcher("my-tour").forward(request, response);
            return;
        }

        // Default day and night values to avoid null issues
        String newDay = (dayParam != null && !dayParam.isEmpty()) ? dayParam : "0";
        String newNight = (nightParam != null && !nightParam.isEmpty()) ? nightParam : "0";
        String newDuration = newDay + "D" + newNight + "N";

        // Parse price to BigDecimal
        BigDecimal newPrice = new BigDecimal(request.getParameter("price"));
        int newSlot = Integer.parseInt(request.getParameter("slot"));

        // Handle multiple file uploads for images and store them in a list
        List<String> newImageFilenames = new ArrayList<>();
        for (Part part : request.getParts()) {
            if (part.getName().equals("tour_Img") && part.getSize() > 0) {
                String fileName = extractFileName(part);
                fileName = new File(fileName).getName(); // Get the file name
                part.write(getFolderUpload(request).getAbsolutePath() + File.separator + fileName); // Save file
                newImageFilenames.add(fileName); // Add filename to the list
            }
        }

        // Compare and update only if values have changed
        boolean isUpdated = false;

        if (!newTourName.equals(oldTour.getTour_Name())) {
            oldTour.setTour_Name(newTourName);
            isUpdated = true;
        }
        if (!newTourDescription.equals(oldTour.getTour_Description())) {
            oldTour.setTour_Description(newTourDescription);
            isUpdated = true;
        }
        if (newStartDate != null && !newStartDate.equals(oldTour.getStart_Date())) {
            oldTour.setStart_Date(newStartDate);
            isUpdated = true;
        }
        if (newEndDate != null && !newEndDate.equals(oldTour.getEnd_Date())) {
            oldTour.setEnd_Date(newEndDate);
            isUpdated = true;
        }
        if (!newLocation.equals(oldTour.getLocation())) {
            oldTour.setLocation(newLocation);
            isUpdated = true;
        }
        if (!newDuration.equals(oldTour.getTotal_Time())) {
            oldTour.setTotal_Time(newDuration);
            isUpdated = true;
        }
        if (newPrice.compareTo(oldTour.getPrice()) != 0) {  // Using BigDecimal comparison
            oldTour.setPrice(newPrice);
            isUpdated = true;
        }
        if (newSlot != oldTour.getSlot()) {
            oldTour.setSlot(newSlot);
            isUpdated = true;
        }
        if (!newImageFilenames.isEmpty() && !newImageFilenames.equals(oldTour.getTour_Img())) {
            oldTour.setTour_Img(newImageFilenames);
            isUpdated = true;
        }

        // Save changes if any updates were made
        if (isUpdated) {
            try {
                new hoang_UserDB().updateTour(oldTour);
                request.setAttribute("message", "Tour updated successfully!");
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("message", "Error updating tour: " + e.getMessage());
            }
        } else {
            request.setAttribute("message", "No changes made to the tour.");
        }

        getServletContext().getRequestDispatcher("/provider-management?action=edit-tour&tourId=" + tourId).forward(request, response);
    }

    public void searchTour(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("tour-edit");

        TourDB tourDB = new TourDB();
        hoang_UserDB tourDBs = new hoang_UserDB();
        int companyId;

        try {
            // Fetch the provider Id from user session
            companyId = new hoang_UserDB().getProviderIdFromUserId(new UserDB().getUserFromSession(request.getSession()).getUser_Id());
            System.out.println("GET SUCCESS: Company ID = " + companyId);
        } catch (SQLException ex) {
            Logger.getLogger(SearchTourByIdServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("errorMessage", "Database error occurred while fetching the company ID.");
            request.getRequestDispatcher("edit-tour.jsp").forward(request, response);
            return;
        }

        // If no tourId is provided, fetch all tours
        if (query == null || query.trim().isEmpty()) {
            List<Tour> allTours = tourDB.getToursByProviderID(companyId);
            if (allTours.isEmpty()) {
                request.setAttribute("errorMessage", "No tours available.");
            } else {
                request.setAttribute("providerTours", allTours);
            }
            request.getRequestDispatcher("edit-tour.jsp").forward(request, response);
            return;
        }
        System.out.println("TESTTTTTT ----- " + query);
        // Retrieve the tour details by tourId
        List<Tour> tourEdit = tourDBs.getTourFromQuery(query, companyId);
        System.out.println("TESTTTTTT ----- " + tourEdit.size());
        // Check if the tour was found
        if (tourEdit == null) {
            request.setAttribute("errorMessage", "No tour found with the given ID.");
            request.getRequestDispatcher("edit-tour.jsp").forward(request, response);
            return;
        }

        // Set the tourEdit object in request scope and forward to the edit page
        request.setAttribute("tourEdit", tourEdit);
        List<Tour> tourEditSession = tourEdit;
        request.getSession().setAttribute("tourEditSession", tourEditSession);
        request.getRequestDispatcher("mytour.jsp").forward(request, response);
    }

    private void setStatusATour(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tourId = request.getParameter("tourId");
        String status = request.getParameter("status");
        TourDB tourDB = new TourDB();
        String errorMessage = "";

        switch (status) {
            case "Active":
                errorMessage = tourDB.setTourStatusToActive(tourId) ? "Active Successfully" : "Active Fail";
                break;
            case "Hidden":
                errorMessage = tourDB.setTourStatusToHidden(tourId) ? "Hidden Successfully" : "Hidden Fail";
                break;
            default:
                errorMessage = "Invalid Status";
                break;
        }

        // Set the error message in request attributes
        request.setAttribute("errorMessage", errorMessage);

        // Use include instead of forward
        request.getRequestDispatcher("my-tour").forward(request, response);
    }

    private void sort(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sortOrder = request.getParameter("sortOrder");
        hoang_UserDB tourDB = new hoang_UserDB();
        int companyId = 0;
        try {
            companyId = new hoang_UserDB().getProviderIdFromUserId(new UserDB().getUserFromSession(request.getSession()).getUser_Id());
        } catch (SQLException ex) {
            Logger.getLogger(ProviderManagementServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Fetch the sorted list of tours
        List<Tour> sortedTours = tourDB.SortProviderTour(sortOrder, companyId);

        // Set the sorted tours in the request scope
        request.setAttribute("tourEdit", sortedTours);

        // Forward to the JSP page to display the sorted tours
        request.getRequestDispatcher("my-tour").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

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

    // Updated getFolderUpload method
    private File getFolderUpload(HttpServletRequest request) throws IOException {
        // Get the ServletContext to use in the getFolderUpload method
        String uploadPath = request.getServletContext().getRealPath("/assests/images/tour-images");
        String noBuild = removeBuildPath(uploadPath);
        System.out.println("Upload Path: " + noBuild); // Debugging line

        File folderUpload = new File(noBuild);
        // Create the folder if it doesn't exist
        if (!folderUpload.exists()) {
            if (folderUpload.mkdirs()) {
                System.out.println("Created directory: " + folderUpload.getAbsolutePath());
            } else {
                System.out.println("Failed to create directory: " + folderUpload.getAbsolutePath());
            }
        } else {
            System.out.println("Directory already exists: " + folderUpload.getAbsolutePath());
        }

        return folderUpload;
    }

    private String removeBuildPath(String originalPath) {
        String buildPath = "build\\";
        if (originalPath.contains(buildPath)) {
            return originalPath.replace(buildPath, "");
        }
        return originalPath;
    }

}
