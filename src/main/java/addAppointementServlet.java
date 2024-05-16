

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Servlet implementation class addAppointementServlet
 */
public class addAppointementServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/hospitaldb";
	    private static final String JDBC_USER = "root";
	    private static final String JDBC_PASSWORD = "";

	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        // Récupérer les données du formulaire
	        String id = request.getParameter("id");
	        String name = request.getParameter("name");
	        String phoneNumber = request.getParameter("phone_number");
	        String date = request.getParameter("date");
	        String hour = request.getParameter("hour");
	        String idDoctor = request.getParameter("id_doctor");

	        // Connexion à la base de données et insertion des données
	        Connection conn = null;
	        PreparedStatement stmt = null;
	        try {
	        	// Charger le driver JDBC
	            Class.forName("com.mysql.jdbc.Driver");
	            // Établir une connexion à la base de données
	            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
	          if (id != null && !id.isEmpty()) {
	        	// Modification d'un médecin existant
	            String sql = "UPDATE appointement SET name=?, phone_number=?, date=?, hour=?, idDoctor=? WHERE id=?";
	            stmt = conn.prepareStatement(sql);
	            stmt.setString(1, id);
	            stmt.setString(2, name);
	            stmt.setString(3, phoneNumber);
	            stmt.setString(4, date);
	            stmt.setString(5, hour);
	            stmt.setString(6, idDoctor);
	          } else {
	              // Ajout d'un nouveau médecin
	              String sql = "INSERT INTO appointement (name, phone_number, date, hour, idDoctors) VALUES (?, ?, ?, ?, ?)";
	              stmt = conn.prepareStatement(sql);
	              stmt.setString(1, id);
		            stmt.setString(2, name);
		            stmt.setString(3, phoneNumber);
		            stmt.setString(4, date);
		            stmt.setString(5, hour);
		            stmt.setString(6, idDoctor);
	          }
	          stmt.executeUpdate();
	          response.sendRedirect("confirmation.jsp");
	      } catch (Exception e) {
	          e.printStackTrace();
	          response.sendRedirect("error.jsp");
		} finally {
	          try {
	              if (stmt != null) stmt.close();
	              if (conn != null) conn.close();
	          } catch (SQLException e) {
	              e.printStackTrace();
	          }
	      }
	  }
	}