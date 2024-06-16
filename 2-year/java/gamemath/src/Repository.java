import java.sql.*;
import java.util.ArrayList;

public class Repository {
    private final String url = "jdbc:sqlserver://localhost:1433;databaseName=game;encrypt=false;";
    private final String username = "gameUser";
    private final String password = "123";

    private Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(this.url, this.username, this.password);

            return connection;
        } catch (SQLException | ClassNotFoundException err) {
            err.printStackTrace();
        }

        return null;
    }

    public void createRecord(String username, int points) {
        try {
            PreparedStatement statement = this
                    .getConnection()
                    .prepareStatement("INSERT INTO records (username, points) VALUES (?,?);");

            statement.setString(1, username);
            statement.setInt(2, points);

            statement.execute();
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }

    public ArrayList<Record> getBetterRecords() {
        ArrayList<Record> result = new ArrayList<Record>();

        try {
            ResultSet lines = this.getConnection()
                    .prepareStatement("SELECT TOP 5 records.username, records.points FROM records ORDER BY records.points DESC;")
                    .executeQuery();

            while (lines.next()) {
                String username = lines.getString("username");
                int points = lines.getInt("points");

                Record record = new Record(username, points);

                result.add(record);
            }
        } catch (SQLException err) {
            err.printStackTrace();
        }

        return result;
    }
}
