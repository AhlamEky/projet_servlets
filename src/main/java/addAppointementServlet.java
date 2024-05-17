

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
	    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/hospitaldata";
	    private static final String JDBC_USER = "root";
	    private static final String JDBC_PASSWORD = "";

	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        // Récupérer les données du formulaire
	        String IDS = request.getParameter("ID");
	        String Name = request.getParameter("Name");
	        String Phone_numberA = request.getParameter("Phone_numberA");
	        String Date = request.getParameter("Date");
	        String Hour = request.getParameter("Hour");
	        String ID_DoctorS = request.getParameter("ID_Doctor");
	        
	     // Convertir les chaînes en int
	        int ID = 0;
	        int ID_Doctor = 0;

	        if (IDS != null && !IDS.trim().isEmpty()) {
	            try {
	            	ID = Integer.parseInt(IDS);
	            } catch (NumberFormatException e) {
	                e.printStackTrace();
	                response.sendRedirect("error.jsp");
	                return;
	            }
	        }

	        if (ID_DoctorS != null && !ID_DoctorS.trim().isEmpty()) {
	            try {
	            	ID_Doctor = Integer.parseInt(ID_DoctorS);
	            } catch (NumberFormatException e) {
	                e.printStackTrace();
	                response.sendRedirect("error.jsp");
	                return;
	            }
	        }

	        // Connexion à la base de données et insertion des données
	        Connection conn = null;
	        PreparedStatement stmt = null;
	        try {
	        	// Charger le driver JDBC
	            Class.forName("com.mysql.jdbc.Driver");
	            // Établir une connexion à la base de données
	            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
	            String sql;
	          if (ID > 0) {
	        	// Modification d'un médecin existant
	            sql = "UPDATE appointment SET Name=?, Phone_numberA=?, Date=?, Hour=?, ID_Doctor=? WHERE ID=?";
	            stmt = conn.prepareStatement(sql);
	            stmt.setString(1, Name);
	            stmt.setString(2, Phone_numberA);
	            stmt.setString(3, Date);
	            stmt.setString(4, Hour);
	            stmt.setInt(5, ID_Doctor);
	          } else {
	              // Ajout d'un nouveau médecin
	              sql = "INSERT INTO appointment (Name, Phone_numberA, Date, Hour, ID_Doctor) VALUES (?, ?, ?, ?, ?)";
	              stmt = conn.prepareStatement(sql);
		            stmt.setString(1, Name);
		            stmt.setString(2, Phone_numberA);
		            stmt.setString(3, Date);
		            stmt.setString(4, Hour);
		            stmt.setInt(5, ID_Doctor);
	          }
	          stmt.executeUpdate();
	          response.sendRedirect("appointement.jsp");
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