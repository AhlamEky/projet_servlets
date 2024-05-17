

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
 * Servlet implementation class addPatientServlet
 */
public class addPatientServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/hospitaldata";
	    private static final String JDBC_USER = "root";
	    private static final String JDBC_PASSWORD = "";

	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        // Récupérer les données du formulaire
	        String idS = request.getParameter("id");
	        String Name = request.getParameter("Name");
	        String Phone_number = request.getParameter("Phone_number");
	        String Date_birth = request.getParameter("Date_birth");
	        String Adress = request.getParameter("Adress");
	        String Sex = request.getParameter("Sex");
	        String Disease = request.getParameter("Disease");
	        String Id_DoctorS = request.getParameter("Id_Doctor");
	        String Number_Rooms = request.getParameter("Number_Rooms");

	        
	     // Vérifier et convertir les chaînes en int
	        int id = 0;
	        int Id_Doctor = 0;

	        if (idS != null && !idS.trim().isEmpty()) {
	            try {
	                id = Integer.parseInt(idS);
	            } catch (NumberFormatException e) {
	                e.printStackTrace();
	                response.sendRedirect("error.jsp");
	                return;
	            }
	        }

	        if (Id_DoctorS != null && !Id_DoctorS.trim().isEmpty()) {
	            try {
	                Id_Doctor = Integer.parseInt(Id_DoctorS);
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
	            if (id >0) {
	        	// Modification d'un médecin existant
	             sql = "UPDATE patientss SET Name=?, Phone_number=?, Date_birth=?, Adress=?, Sex=?, Disease=?, Id_Doctor=?, Number_Rooms=? WHERE id=?";
	            stmt = conn.prepareStatement(sql);
	            stmt.setString(1, Name);
	            stmt.setString(2, Phone_number);
	            stmt.setString(3, Date_birth);
	            stmt.setString(4, Adress);
	            stmt.setString(5, Sex);
	            stmt.setString(6, Disease);
	            stmt.setInt(7, Id_Doctor);
	            stmt.setString(8, Number_Rooms);
	          } else {
	              // Ajout d'un nouveau médecin
	               sql = "INSERT INTO patientss (Name, Phone_number, Date_birth, Adress, Sex, Disease, Id_Doctor, Number_Rooms) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	              stmt = conn.prepareStatement(sql);
	                stmt.setString(1, Name);
		            stmt.setString(2, Phone_number);
		            stmt.setString(3, Date_birth);
		            stmt.setString(4, Adress);
		            stmt.setString(5, Sex);
		            stmt.setString(6, Disease);
		            stmt.setInt(7, Id_Doctor);
		            stmt.setString(8, Number_Rooms);
	          }
	          stmt.executeUpdate();
	          response.sendRedirect("patient.jsp");
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