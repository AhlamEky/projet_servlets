

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
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/hospitaldb";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les données du formulaire
        String idDoctor = request.getParameter("id_doctor");
        String name = request.getParameter("name");
        String operatingRoom = request.getParameter("operating_room");
        String phoneNumber = request.getParameter("phone_number");
        String dateBirth = request.getParameter("date_birth");
        String sex = request.getParameter("sex");
        String speciality = request.getParameter("speciality");

        // Connexion à la base de données et insertion des données
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
        	// Charger le driver JDBC
            Class.forName("com.mysql.jdbc.Driver");
            // Établir une connexion à la base de données
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
          if (idDoctor != null && !idDoctor.isEmpty()) {
        	// Modification d'un médecin existant
            String sql = "UPDATE doctors SET name=?, operating_room=?, phone_number=?, date_birth=?, sex=?, speciality=? WHERE id_doctor=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, idDoctor);
            stmt.setString(2, name);
            stmt.setString(3, operatingRoom);
            stmt.setString(4, phoneNumber);
            stmt.setString(5, dateBirth);
            stmt.setString(6, sex);
            stmt.setString(7, speciality);
          } else {
              // Ajout d'un nouveau médecin
              String sql = "INSERT INTO doctors (name, operating_room, phone_number, date_birth, sex, speciality) VALUES (?, ?, ?, ?, ?, ?)";
              stmt = conn.prepareStatement(sql);
              stmt.setString(1, idDoctor);
              stmt.setString(2, name);
              stmt.setString(3, operatingRoom);
              stmt.setString(4, phoneNumber);
              stmt.setString(5, dateBirth);
              stmt.setString(6, sex);
              stmt.setString(7, speciality);
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