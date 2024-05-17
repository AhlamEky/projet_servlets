

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
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/hospitaldata";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les données du formulaire
        String Num_RoomS = request.getParameter("Num_Room");
        String Type = request.getParameter("Type");
        String Statut = request.getParameter("Statut");

        int Num_Room = 0;
        if (Num_RoomS != null && !Num_RoomS.trim().isEmpty()) {
            try {
            	Num_Room = Integer.parseInt(Num_RoomS);
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
          if (Num_Room>0) {
        	// Modification d'un médecin existant
            sql = "UPDATE rooms SET Num_Room=?, Type=?,Statut=? WHERE Num_Room=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Num_Room);
            stmt.setString(2, Type);
            stmt.setString(3, Statut);
          } else {
              // Ajout d'un nouveau médecin
              sql = "INSERT INTO rooms (Num_Room, Type, Statut) VALUES (?, ?, ?)";
              stmt = conn.prepareStatement(sql);
              stmt.setInt(1, Num_Room);
              stmt.setString(2, Type);
              stmt.setString(3, Statut);
          }
          stmt.executeUpdate();
          response.sendRedirect("rooms.jsp");
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
