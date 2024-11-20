package amazon_web_scarping;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ScrapeAmazon")
public class ScrapeAmazon extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the URL entered by the user
        String searchUrl = request.getParameter("searchUrl");

        if (searchUrl == null || !searchUrl.startsWith("https://www.amazon.")) {
            response.getWriter().println("Invalid URL. Please enter a valid Amazon search URL.");
            return;
        }

        // Path for the CSV file
        String csvFile = getServletContext().getRealPath("/") + "amazon_products.csv";
        System.out.println("Saving data to CSV file at: " + csvFile);

        try (FileWriter writer = new FileWriter(csvFile)) {
            // Write CSV header
            writer.append("Product Name, Price, Rating\n");

            // Connect to Amazon with a user-agent header
            Connection connection = Jsoup.connect(searchUrl)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.5735.110 Safari/537.36")
                    .timeout(10000) // Set timeout
                    .followRedirects(true);

            Document doc = connection.get();

            // Select product containers (update selectors if needed)
            Elements products = doc.select(".s-main-slot .s-result-item");

            // Check if products were found
            if (products.isEmpty()) {
                System.out.println("No products found. Please verify the selectors or URL.");
                response.getWriter().println("No products found. Please try again.");
                return;
            }

            for (Element product : products) {
                // Extract product name
                String name = product.select("h2 a span").text();

                // Extract price (whole and fraction parts)
                String priceWhole = product.select(".a-price .a-price-whole").text();
                String priceFraction = product.select(".a-price .a-price-fraction").text();
                String price = !priceWhole.isEmpty() ? priceWhole + "." + priceFraction : "N/A";

                // Extract ratings
                String rating = product.select(".a-icon-alt").text();

                // Log the data
                System.out.println("Product Name: " + name);
                System.out.println("Price: " + price);
                System.out.println("Rating: " + rating);

                // Write data to CSV
                writer.append(name.replace(",", " ")).append(", ") // Handle commas in the product name
                      .append(price).append(", ")
                      .append(rating.isEmpty() ? "N/A" : rating).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Error occurred during scraping or file writing: " + e.getMessage());
            response.getWriter().println("An error occurred: " + e.getMessage());
            return;
        }

        // Respond to the user
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Amazon Scraping Completed</h1>");
        out.println("<a href=\"amazon_products.csv\">Download CSV File</a>");
        out.println("</body></html>");
    }
}
