

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
 * Servlet implementation class addDoctorServlet
 */
@WebServlet("/addDoctorServlet")
public class addDoctorServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/hospitaldata";
	    private static final String JDBC_USER = "root";
	    private static final String JDBC_PASSWORD = "";

	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        // Récupérer les données du formulaire
	        String ID_DoctorS = request.getParameter("IS_Doctor");
	        String Name = request.getParameter("Name");
	        String Operating_Room = request.getParameter("Operating_Room");
	        String Phone_number = request.getParameter("Phone_number");
	        String Date_Birth = request.getParameter("Date_Birth");
	        String Sex = request.getParameter("Sex");
	        String Speciality = request.getParameter("Speciality");

	        int ID_Doctor = 0;
	        if (ID_DoctorS != null && !ID_DoctorS.trim().isEmpty()) {
	            try {
	            	ID_Doctor = Integer.parseInt(ID_DoctorS);
	            } catch (NumberFormatException e) {
	                e.printStackTrace();
	                response.sendRedirect("error.jsp");
	                return;
	            }
	        }
	        // Connexion à la base de données et insertion ou mise à jour des données
	        Connection conn = null;
	        PreparedStatement stmt = null;
	        try {
	            // Charger le driver JDBC
	            Class.forName("com.mysql.jdbc.Driver");
	            // Établir une connexion à la base de données
	            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
	            String sql;
	            if (ID_Doctor>0) {
	                // Modification d'un médecin existant
	                sql = "UPDATE doctors SET Name=?, Operating_Room=?, Phone_number=?, Date_Birth=?, Sex=?, Speciality=? WHERE Id_Doctor=?";
	                stmt = conn.prepareStatement(sql);
	                stmt.setInt(1, ID_Doctor);
	                stmt.setString(2, Name);
	                stmt.setString(3, Operating_Room);
	                stmt.setString(4, Phone_number);
	                stmt.setString(5, Date_Birth);
	                stmt.setString(6, Sex);
	                stmt.setString(7, Speciality);
	               // Utilisation de l'ID du médecin dans la clause WHERE
	            } else {
	                // Ajout d'un nouveau médecin
	                sql = "INSERT INTO doctors ( Name, Operating_Room, Phone_number, Date_Birth, Sex, Speciality) VALUES (?, ?, ?, ?, ?, ?)";
	                stmt = conn.prepareStatement(sql);
	                stmt.setString(1, Name);
	                stmt.setString(2, Operating_Room);
	                stmt.setString(3, Phone_number);
	                stmt.setString(4, Date_Birth);
	                stmt.setString(5, Sex);
	                stmt.setString(6, Speciality);
	            }
	            stmt.executeUpdate();
	            response.sendRedirect("doctors.jsp");
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