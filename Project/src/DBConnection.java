import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

    public Connection openConnection() throws ClassNotFoundException {
        Connection conn = null;
        try {
            String myDriver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/ProcessData?useSSL=false";
            Class.forName(myDriver);
            conn = DriverManager.getConnection(url, "root", "dumb_password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    public Connection RecreateTable() throws ClassNotFoundException, SQLException {
        Connection conn;
        Statement drop;
        Statement recreate;
        conn = openConnection();
        drop = conn.createStatement();
        drop.executeUpdate("DROP TABLE IF EXISTS Pdata");
        recreate = conn.createStatement();
        recreate.executeUpdate("CREATE TABLE Pdata(pid int, cpu float, ram float, PRIMARY KEY (pid))");
        return conn;
    }
}
