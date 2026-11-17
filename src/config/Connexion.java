package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
    public static final String URL = "jdbc:mysql://localhost:3306/banque_db";
    public static final String USER = "root";
    public static final String PASSWORD = "";

    public static Connection ouvrir() throws SQLException{
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }

}
