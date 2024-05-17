<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
        body{
            margin :0;
            padding :0;
        }
        .sidebar {
            background-color: #5192A9; /* Couleur de fond bleue */
            position: fixed; /* Fixe la barre latérale à la page */
            float: left; /* Aligne la barre sur le côté droit */
            height: 100vh; /* Hauteur de la page */
            width: 20%; /* Largeur de la barre, ici 1/4 de la page */
            overflow-y: auto; /* Ajoute une barre de défilement si le contenu est trop long */
            padding: 20px;
            box-sizing: border-box; /* Inclut le padding dans la largeur et la hauteur */
            color: white; /* Couleur du texte */
        }
        .content{
            height: 100vh; /* Hauteur de la page */
            width: 80%;
            float: right;
            margin : 0;
        }
        .banner {
            background-color: #98d1f0; /* Change to desired background color */
            color: white;
            text-align: center;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 0px;
             margin: 0px;
        }

 h2 {
  margin: 10px; /* Remove default margin for headings */
  text-align: center;
}

.button{
        width: 12em;
        padding: 9px 50px; /* Ajouter un espace autour du texte */
        margin: 7px 30px 0 30px ; /* Ajouter un espacement entre les boutons */
        font-size: 16px;
        border: none;
        background-color: #b1b0b2; /* Couleur de fond */
        color: black; /* Couleur du texte */
        cursor: pointer;
        border-radius: 5px;
        font-weight: bold;
    }
    .search-bar{
        display: flex;
        justify-content: center; /* Centrer horizontalement */
        align-items: center;
        margin: 7px;
    }
    .search-bar input{
        width: 35em;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 1px;
    }
    .search-bar button{
        width: 10em;
        padding: 9px 30px; /* Ajouter un espace autour du texte */
        margin: 10px ; /* Ajouter un espacement entre les boutons */
        font-size: 16px;
        border: none;
        background-color: #98bbf4; /* Couleur de fond */
        color: black; /* Couleur du texte */
        cursor: pointer;
        border-radius: 5px;
        font-weight: bold;
    }
    .buttons{
        display: flex;
        justify-content: center; /* Centrer horizontalement */
        align-items: center;
    }
    .sidebar ul {
    list-style-type: none;
    padding: 0;
}

.sidebar ul li {
    padding: 36px;
    font-size: 29px;
    font-weight: bold;
}
    .sidebar ul li a {
        text-decoration: none; /* Supprime le soulignement par défaut */
        color: inherit; 
        display: block;
    }
    .logout{
    }
    .logout a {
    position: fixed;
    bottom: 20px; /* Ajustez cette valeur selon vos besoins */
    font-size: 25px;
    font-weight: bold;
    text-decoration: none;
    margin-left: 70px;
    }
    .add-doctor{ 
       /*  height: 100%; Hauteur de la page */
         width: 27%;
         float: left;
         margin: 30px;
         padding-top: 30px;
         padding-bottom: 30px;
    }
    .tableinfo{ 
        /* height: 100%;Hauteur de la page */
        width: 67%;
        float: right;
    }
    .add-doctor label {
            font-weight: bold;
            display: block;
            margin-top: 8px;
        }
        .add-doctor input,
        .add-doctor select{
            width: 90%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }
        .contai {
    margin: 20px auto;
    padding: 0 20px 0 0;
    max-width: 800px;
}

.contai table {
    width: 100%;
    border-collapse: collapse;
}

.contai th, td {
    padding: 8px;
    border-bottom: 2px solid #ddd;
}

.contai th {
    background-color: #f2f2f2;
    text-align: left;
}    
    </style>
   
<title>Insert title here</title>
</head>
<body>

<div class="sidebar">
        <!-- Contenu du menu -->
        <ul>
            <li><a href="doctors.jsp">Doctors</a></li>
            <li><a href="rooms.jsp">Rooms</a></li>
            <li><a href="appointment.jsp">Appointments</a></li>
            <li><a href="patient.jsp">Patients</a></li>
        </ul>
        <div class="logout">
        <a hraf="login.jsp">Log Out</a>
        </div>
</div>

<dic class="content">
    <div class="banner">
      <h1>Hospital Management System</h1>
    </div>
      <h2>Manage Doctors</h2>
    
    <main>
        <div class="add-doctor">
            <form action="addDoctorServlet" method="post">
                <label for="ID_Doctor">Id_Doctors :</label><br>
                <input type="INT(11)" id="ID_Doctor" name="ID_Doctor" value="<%= request.getParameter("ID_Doctor") %>">
                <label for="Name">Name :</label><br>
                <input type="text" id="Name" name="Name" value="<%= request.getParameter("Name") %>" required><br>
                <label for="Operating-Room">Operating Room :</label><br>
                <input type="text" id="Operating-Room" name="Operating_Room" value="<%= request.getParameter("Operating_Room") %>"><br>
                <label for="Phone-number">phone Number :</label><br>
                <input type="text" id="Phone-number" name="Phone_number" value="<%= request.getParameter("Phone_number") %>"><br>
                <label for="Date-Birth">Date Birth :</label><br>
                <input type="date" id="Date-Birth" name="Date_Birth" value="<%= request.getParameter("Date_Birth") %>"><br>
                <label for="sex">Sex :</label><br>
                <select id="Sex" name="Sex">
                    <option value="M" <%= "M".equals(request.getParameter("Sex")) ? "selected" : "" %>>Homme</option>
                    <option value="F" <%= "F".equals(request.getParameter("Sex")) ? "selected" : "" %>>Femme</option>
                </select><br>
                <label for="Speciality">Speciality :</label><br>
                <input type="text" id="Speciality" name="Speciality" value="<%= request.getParameter("Speciality") %>"><br>
            
           <!-- <section class="search-bar">
                <form action="#">
                    <input type="text" id="search-input" name="search" placeholder="Entrez votre recherche">
                    <button type="submit">Rechercher</button>
                </form>
            </section> -->
            <section class="buttons">
            <!-- Bouton Ajouter -->
        <button type="submit" class="button">Ajouter</button>
        <!-- Bouton Éditer -->
        <button type="submit" class="button">Editer</button>
        <!-- Bouton Supprimer 
        <button class="button">Supprimer</button>-->
        </form>
        </div>
          </section>
          
          <div class="tableinfo">
            <div class="contai">
            <table>
                <thead>
                    <tr>
                        <th>Id_Doctors</th>
                        <th>Name</th>
                        <th>Operating Room</th>
                        <th>phone Number</th>
                        <th>Date Birth</th>
                        <th>Sex</th>
                        <th>Speciality</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
            try {
                // Charger le driver JDBC
                Class.forName("com.mysql.jdbc.Driver");
                // Établir une connexion à la base de données
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitaldata", "root", "");
                // Préparer la requête SQL
                String sql = "SELECT * FROM doctors";
                Statement stmt = conn.createStatement();
                // Exécuter la requête
                ResultSet rs = stmt.executeQuery(sql);
                // Parcourir les résultats et afficher les données dans le tableau
                while(rs.next()) {
            %>
            <tr>
                <td><%= rs.getString("ID_Doctor") %></td>
                <td><%= rs.getString("Name") %></td>
                <td><%= rs.getString("Operating_Room") %></td>
                <td><%= rs.getString("Phone_number") %></td>
                <td><%= rs.getString("Date_Birth") %></td>
                <td><%= rs.getString("Sex") %></td>
                <td><%= rs.getString("Speciality") %></td>
                <td>
                    <form action="deleteDoctorServlet" method="post">
                        <input type="hidden" name="ID_Doctor" value="<%= rs.getString("ID_Doctor") %>">
                        <button type="submit">Supprimer</button>
                    </form>
                </td>
            </tr>
            <% 
                }
                // Fermer les ressources JDBC
                rs.close();
                stmt.close();
                conn.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
            %>
                </tbody>
            </table>
        </div>
        </div>
        
        
    </main>
</dic>
    <script src="script.js"></script>
</body>
</html>