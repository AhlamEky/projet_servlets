

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
	    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/hospitaldb";
	    private static final String JDBC_USER = "root";
	    private static final String JDBC_PASSWORD = "";

	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        // Récupérer les données du formulaire
	        String id = request.getParameter("id");
	        String name = request.getParameter("name");
	        String phoneNumber = request.getParameter("phone_number");
	        String dateBirth = request.getParameter("date_birth");
	        String adress = request.getParameter("adress");
	        String sex = request.getParameter("sex");
	        String disease = request.getParameter("disease");
	        String idDoctor = request.getParameter("id_doctor");
	        String Number_Rooms = request.getParameter("Number_Rooms");

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
	            String sql = "UPDATE patientss SET name=?, phone_number=?, date_birth=?, adress=?, sex=?, disease=?, id_doctor=?, Number_Rooms=? WHERE id=?";
	            stmt = conn.prepareStatement(sql);
	            stmt.setString(1, id);
	            stmt.setString(2, name);
	            stmt.setString(3, phoneNumber);
	            stmt.setString(4, dateBirth);
	            stmt.setString(5, adress);
	            stmt.setString(6, sex);
	            stmt.setString(7, disease);
	            stmt.setString(8, idDoctor);
	            stmt.setString(9, Number_Rooms);
	          } else {
	              // Ajout d'un nouveau médecin
	              String sql = "INSERT INTO patientss (name, phone_number, date_birth, adress, sex, disease, id_doctor, Number_Rooms) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	              stmt = conn.prepareStatement(sql);
	              stmt.setString(1, id);
		          stmt.setString(2, name);
		          stmt.setString(3, phoneNumber);
		          stmt.setString(4, dateBirth);
		          stmt.setString(5, adress);
		          stmt.setString(6, sex);
		          stmt.setString(7, disease);
	              stmt.setString(8, idDoctor);
		          stmt.setString(9, Number_Rooms);
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