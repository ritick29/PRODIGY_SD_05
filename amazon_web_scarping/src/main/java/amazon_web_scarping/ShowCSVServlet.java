package amazon_web_scarping;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/showCSV")
public class ShowCSVServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String csvFile = getServletContext().getRealPath("/") + "AmazonProducts.csv";

        if (Files.exists(Paths.get(csvFile))) {
            response.setContentType("text/csv");
            response.setHeader("Content-Disposition", "inline; filename=AmazonProducts.csv");

            Files.copy(Paths.get(csvFile), response.getOutputStream());
            response.getOutputStream().flush();
        } else {
            response.setContentType("text/html");
            response.getWriter().println("<h3>No CSV file found!</h3>");
        }
    }
}
