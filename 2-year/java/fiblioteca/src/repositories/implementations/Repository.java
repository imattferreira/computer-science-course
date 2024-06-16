package repositories.implementations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Repository {
    private static Repository _instance;
    private final String url = "jdbc:mysql://localhost:3306/fiblioteca?serverTimezone=UTC";
    private final String user = "docker";
    private final String password = "docker";
    private Connection connection;

    public Repository() {
      try {
        this.connection = DriverManager.getConnection(url, user, password);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    public static Repository getInstance() {
      if (_instance == null) {
        _instance = new Repository();
      }

      return _instance;
    }

    public Connection getConnection() {
        return this.connection;
    }
}
