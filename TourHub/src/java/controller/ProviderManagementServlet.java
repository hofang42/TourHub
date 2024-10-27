package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import DataAccess.KhanhDB;
import DataAccess.ProvinceDB;
import DataAccess.TourDB;
import DataAccess.UserDB;
import DataAccess.WithdrawalsDB;
import DataAccess.hoang_UserDB;
import com.google.gson.Gson;
import jakarta.servlet.RequestDispatcher;
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
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import model.Province;
import model.Tour;
import model.Withdrawals;
import utils.RemoveDiacritics;

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
            case "show-add-tour":
                showAddTour(request, response);
                break;
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
            case "save-edit-tour":
                saveEditTour(request, response);
                break;
            case "withdraw":
                requesrWithdrawMoney(request, response);
                break;
            case "remove-image":
                removeImage(request, response);
                break;
            case "show-withdraw-page":
                showBalancePage(request, response);
                break;
            case "add-option": {
                addOption(request, response);
                break;
            }
            case "save-option": {
                saveOption(request, response);
                break;
            }
        }
    }

    private void showAddTour(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Province> provinces = new ProvinceDB().getAllProvince();
        for (Province province : provinces) {
            province.setProvince_name(new RemoveDiacritics().removeAccent(province.getProvince_name()));
        }
        request.setAttribute("provinces", provinces);
        request.getRequestDispatcher("add-tour.jsp").forward(request, response);
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
//        double price = Double.parseDouble(request.getParameter("price"));
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
//        BigDecimal price_db = BigDecimal.valueOf(price);
        // Save tour information to the database
        try {
            new TourDB().saveTourToDatabase(request, tourName, tourDescription, startDate, endDate, new RemoveDiacritics().removeAccent(location),
                    duration, slot, imageFilenames);
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

        System.out.println("TEST Image" + tourEdit.toString());
        System.out.println("TEST Image" + tourEdit.getTour_Img());
        List<String> images = tourEdit.getTour_Img(); // Assuming this is a single String with images separated by commas
        List<String> imageList = images != null ? images : new ArrayList<>(); // Directly assign images if it's not null
        List<Province> provinces = new ProvinceDB().getAllProvince();
        for (Province province : provinces) {
            province.setProvince_name(new RemoveDiacritics().removeAccent(province.getProvince_name()));
        }

        request.setAttribute("provinces", provinces);
        request.setAttribute("tourEditImages", imageList);
        request.setAttribute("location", new RemoveDiacritics().removeAccent(tourEdit.getLocation()));
        request.setAttribute("tourEdit", tourEdit);
//        request.setAttribute("tourEditImages", imageList);
        request.getRequestDispatcher("edit-tour-page.jsp").forward(request, response);
    }

    private void removeImage(HttpServletRequest request, HttpServletResponse response) {
        // Get parameters from the request
        String tourId = request.getParameter("tourId");
        String imageToRemove = request.getParameter("imageToRemove");

        if (tourId == null || imageToRemove == null) {
            // Bad request: missing required parameters
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        TourDB tourDB = new TourDB();
        hoang_UserDB hoangDB = new hoang_UserDB();

        try {
            // Fetch the tour by ID
            Tour tour = tourDB.getTourFromTourID(tourId);

            if (tour == null) {
                // Tour not found
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            // Get the current list of images
            List<String> images = tour.getTour_Img();

            // Check if the image exists in the list
            if (!images.contains(imageToRemove)) {
                // Image not found in the list
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            // Remove the image from the list
            images.remove(imageToRemove);

            // Update the tour's images in the database with the updated list
            hoangDB.updateTourImages(tourId, images);

            // Success, return 200 OK status
            response.setStatus(HttpServletResponse.SC_OK);

        } catch (Exception e) {
            // Log the exception and return 500 status
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void saveEditTour(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String tourId = request.getParameter("tourId");
        TourDB tourDB = new TourDB();
        Tour oldTour = tourDB.getTourFromTourID(tourId);

        // Extract parameters with potential null-checks
        String newTourName = request.getParameter("tour_Name");
        String newTourDescription = request.getParameter("tour_Description");
        String newStartDateStr = request.getParameter("start_Date");
        String newEndDateStr = request.getParameter("end_Date");
        String newLocation = new RemoveDiacritics().removeAccent(request.getParameter("location"));
        String dayParam = request.getParameter("day");
        String nightParam = request.getParameter("night");

        // Parse dates
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date newStartDate = null, newEndDate = null;
        try {
            newStartDate = dateFormat.parse(newStartDateStr);
            newEndDate = dateFormat.parse(newEndDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            request.setAttribute("message", "Invalid date format. Please use yyyy-MM-dd.");
            forwardToEditPage(request, response, tourId);
            return;
        }

        // Set default values if days or nights are missing
        String newDay = (dayParam != null && !dayParam.isEmpty()) ? dayParam : "0";
        String newNight = (nightParam != null && !nightParam.isEmpty()) ? nightParam : "0";
        String newDuration = newDay + "N" + newNight + "D";

        // Parse price and slot with error handling
        BigDecimal newPrice;
        int newSlot;
        try {
//            newPrice = new BigDecimal(request.getParameter("price"));
            newSlot = Integer.parseInt(request.getParameter("slot"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("message", "Invalid number format for price or slot.");
            forwardToEditPage(request, response, tourId);
            return;
        }

        // Handle multiple file uploads for images without adding a leading semicolon
        StringBuilder imageFilenames = new StringBuilder();
        try {
            boolean firstImage = true; // Flag to check if it's the first image
            for (Part part : request.getParts()) {
                if ("tour_Img".equals(part.getName()) && part.getSize() > 0) {
                    String fileName = extractFileName(part);
                    fileName = new File(fileName).getName(); // Get the file name
                    part.write(getFolderUpload(request).getAbsolutePath() + File.separator + fileName); // Save file

                    // Add semicolon only after the first filename has been appended
                    if (!firstImage) {
                        imageFilenames.append(";"); // Add separator for subsequent images
                    } else {
                        firstImage = false; // Turn off the flag after the first image
                    }
                    imageFilenames.append(fileName); // Append filename
                }
            }
        } catch (IOException | ServletException ex) {
            Logger.getLogger(ProviderManagementServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Ensure no leading semicolon in the final concatenated filenames
        String newImageFilenames = imageFilenames.toString();
        if (newImageFilenames.startsWith(";")) {
            newImageFilenames = newImageFilenames.substring(1);
        }

        // Compare old and new values, update only if values have changed
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
//        if (newPrice.compareTo(oldTour.getPrice()) != 0) {
//            oldTour.setPrice(newPrice);
//            isUpdated = true;
//        }
        if (newSlot != oldTour.getSlot()) {
            oldTour.setSlot(newSlot);
            isUpdated = true;
        }

        // Update images if new ones are uploaded
        if (!newImageFilenames.isEmpty()) {
            Set<String> uniqueImages = new HashSet<>(oldTour.getTour_Img() != null ? oldTour.getTour_Img() : new ArrayList<>());
            uniqueImages.addAll(Arrays.asList(newImageFilenames.split(";")));

            // Convert unique images back to a single string without leading semicolons
            String updatedImageString = String.join(";", uniqueImages);
            if (updatedImageString.startsWith(";")) {
                updatedImageString = updatedImageString.substring(1);
            }

            oldTour.setTour_Img(new ArrayList<>(Arrays.asList(updatedImageString.split(";"))));
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
        forwardToEditPage(request, response, tourId);
    }

// Helper method to forward to the edit page
    private void forwardToEditPage(HttpServletRequest request, HttpServletResponse response, String tourId) throws IOException {
        try {
            getServletContext().getRequestDispatcher("/provider-management?action=edit-tour&tourId=" + tourId).forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(ProviderManagementServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        List<Tour> tourEdit = tourDBs.getTourFromQuery(new RemoveDiacritics().removeAccent(query), companyId);
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

    private void requesrWithdrawMoney(HttpServletRequest request, HttpServletResponse response) {

        // Retrieve the selected amount from the radio buttons
        String amountParam = request.getParameter("amount");
        String customAmountParam = request.getParameter("customAmount");

        double withdrawMoneyDouble;

        // Check if a radio button is selected, otherwise use custom amount
        if (amountParam != null) {
            withdrawMoneyDouble = Double.parseDouble(amountParam);
        } else if (customAmountParam != null && !customAmountParam.isEmpty()) {
            withdrawMoneyDouble = Double.parseDouble(customAmountParam);
        } else {
            // Handle case where no amount is provided
            request.setAttribute("message", "Please select an amount to withdraw.");
            try {
                request.getRequestDispatcher("provider-management?action=show-withdraw-page").forward(request, response);
            } catch (ServletException ex) {
                Logger.getLogger(ProviderManagementServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ProviderManagementServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            return;
        }

        BigDecimal bdWithdrawMoney = BigDecimal.valueOf(withdrawMoneyDouble);
        int provider_Id = 0;
        try {
            provider_Id = new hoang_UserDB().getProviderIdFromUserId(new UserDB().getUserFromSession(request.getSession()).getUser_Id());
        } catch (SQLException ex) {
            Logger.getLogger(ProviderManagementServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        WithdrawalsDB withdrawalsDB = new WithdrawalsDB();
        String message;

        if (withdrawalsDB.saveWithdrawal(provider_Id, bdWithdrawMoney)) {
            message = "Request sent, please wait for a response.";
        } else {
            message = "Request failed, please try again.";
        }

        request.setAttribute("message", message);
        try {
            request.getRequestDispatcher("provider-management?action=show-withdraw-page").forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(ProviderManagementServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProviderManagementServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showBalancePage(HttpServletRequest request, HttpServletResponse response) {
        hoang_UserDB hoangDB = new hoang_UserDB();
        WithdrawalsDB withdrawalsDB = new WithdrawalsDB();
        int companyId = 0;
        try {
            companyId = new hoang_UserDB().getProviderIdFromUserId(new UserDB().getUserFromSession(request.getSession()).getUser_Id());
        } catch (SQLException ex) {
            Logger.getLogger(ProviderManagementServlet.class.getName()).log(Level.SEVERE, null, ex);
            // Optionally redirect or set error messages here
        }

        BigDecimal balance = hoangDB.getBalanceByCompanyId(companyId);
        if (balance == null) {
            balance = BigDecimal.ZERO; // Avoid null balance issue
        }

        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        String formattedBalance = currencyFormat.format(balance);

        List<Withdrawals> withdrawalses = null;
        try {
            withdrawalses = withdrawalsDB.getWithdrawalsByProviderId(companyId);
        } catch (SQLException ex) {
            Logger.getLogger(ProviderManagementServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("SIZE" + withdrawalses.size());
        request.setAttribute("withdrawalses", withdrawalses);
        request.setAttribute("balance", formattedBalance);
        System.out.println("TESTTTT" + withdrawalses + formattedBalance);
        try {
            request.getRequestDispatcher("payment.jsp").forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(ProviderManagementServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProviderManagementServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

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

    public void addOption(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String tourId = request.getParameter("tourId");
        int companyId = 0;
        try {
            companyId = new hoang_UserDB().getProviderIdFromUserId(new UserDB().getUserFromSession(request.getSession()).getUser_Id());
        } catch (SQLException ex) {
            Logger.getLogger(ProviderManagementServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        Tour tourEdit = new TourDB().getTourFromTourID(tourId, companyId);
        request.setAttribute("tour", tourEdit);

        getServletContext().getRequestDispatcher("/add-option.jsp").forward(request, response);
    }

    public void saveOption(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        KhanhDB khanhDB = new KhanhDB();

        // Retrieve parameters from request
        String tourId = request.getParameter("tourId");
        String optionName = request.getParameter("option_Name");
        String optionDescription = request.getParameter("option_Description");
        String[] daysOfWeek = request.getParameterValues("dayOfWeek");
        String startRepeatDate = request.getParameter("start_Repeat_Date");
        String endRepeatDate = request.getParameter("end_Repeat_Date");
        String slot = request.getParameter("option_Slot");
        String[] peopleTypes = request.getParameterValues("people_Type[]");
        String[] peopleDescriptions = request.getParameterValues("people_Description[]");
        String[] peopleMinQtys = request.getParameterValues("people_MinQty[]");
        String[] peopleMaxQtys = request.getParameterValues("people_MaxQty[]");
        String[] peoplePrices = request.getParameterValues("people_Price[]");

        // Validation: Check if all required fields are populated
        if (tourId == null || optionName == null || optionDescription == null
                || daysOfWeek == null || startRepeatDate == null || endRepeatDate == null
                || slot == null || peopleTypes == null || peopleDescriptions == null
                || peopleMinQtys == null || peopleMaxQtys == null || peoplePrices == null) {

            request.setAttribute("message", "All fields must be filled out.");
            request.getRequestDispatcher("provider-management?action=add-option&tourId=" + tourId).forward(request, response);
            return;
        }

        int optionSlot = Integer.parseInt(slot);
        BigDecimal minPrice = Arrays.stream(peoplePrices)
                .map(BigDecimal::new)
                .min(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);

        int optionId;
        try {
            // Insert the tour option and get the generated option ID
            optionId = khanhDB.importTourOption(tourId, optionName, minPrice, optionDescription);
            System.out.println("Generated Option ID: " + optionId);

            // Insert each person type as a separate record in TourOptionPeople
            for (int i = 0; i < peopleTypes.length; i++) {
                String peopleType = peopleTypes[i];
                String peopleDescription = peopleDescriptions[i];
                int minCount = Integer.parseInt(peopleMinQtys[i]);
                int maxCount = Integer.parseInt(peopleMaxQtys[i]);
                BigDecimal price = new BigDecimal(peoplePrices[i]);

                khanhDB.importTourOptionPeople(optionId, peopleType, minCount, maxCount, price, peopleDescription);
                System.out.println("Inserted People Type: " + peopleType + " for Option ID: " + optionId);
            }

            // Insert Tour Schedules for each selected day of the week
            for (String dayOfWeek : daysOfWeek) {
                khanhDB.importTourSchedule(optionId, startRepeatDate, endRepeatDate, dayOfWeek, optionSlot);
                System.out.println("Inserted schedule for " + dayOfWeek + " between " + startRepeatDate + " and " + endRepeatDate);
            }

            // Set success message after all imports are completed
            request.setAttribute("message", "Add Tour Option Successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("message", "Error adding tour option.");
        }

        request.getRequestDispatcher("provider-management?action=add-option&tourId=" + tourId).forward(request, response);
    }

}
