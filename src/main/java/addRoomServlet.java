

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
 * Servlet implementation class addRoomServlet
 */
public class addRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/hospitaldb";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les données du formulaire
        String num_rooms = request.getParameter("num_rooms");
        String type = request.getParameter("type");
        String statut = request.getParameter("statut");

        // Connexion à la base de données et insertion des données
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
        	// Charger le driver JDBC
            Class.forName("com.mysql.jdbc.Driver");
            // Établir une connexion à la base de données
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
          if (num_rooms != null && !num_rooms.isEmpty()) {
        	// Modification d'un médecin existant
            String sql = "UPDATE rooms SET num_room=?, type=?, statut=? WHERE num_room=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, num_rooms);
            stmt.setString(2, type);
            stmt.setString(3, statut);
          } else {
              // Ajout d'un nouveau médecin
              String sql = "INSERT INTO rooms (num_room, type, statut) VALUES (?, ?, ?)";
              stmt = conn.prepareStatement(sql);
              stmt.setString(1, num_rooms);
              stmt.setString(2, type);
              stmt.setString(3, statut);
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
