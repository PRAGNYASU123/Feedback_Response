package feedback_app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Response")
public class response extends HttpServlet {

    private static final String INSERT_QUERY = "INSERT INTO RESPONSE (NAME,EMAIL,RESPONSE) VALUES (?,?,?)";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String feedback = req.getParameter("message");

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            writer.println("Error loading database driver: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/feedback_response", "root", "Enter your password");
             PreparedStatement ps = connection.prepareStatement(INSERT_QUERY)) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, feedback);

            int count = ps.executeUpdate();
            if (count > 0) {
                writer.println("<h2>Your Form Details That You Submitted</h2>");
                writer.println("NAME: " + name);
                writer.println("E-mail: " + email);
                writer.println("RESPONSE: " + feedback);
                writer.println("<br>Record stored in database successfully.");
            } else {
                writer.println("<h2>Your Form Details That You Submitted</h2>");
                writer.println("NAME: " + name);
                writer.println("E-mail: " + email);
                writer.println("RESPONSE: " + feedback);
                writer.println("<br>Record not stored in database.");
            }

        } catch (SQLException e) {
            writer.println("Database error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            writer.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("index.jsp");
    }
}
