import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OpenDBConn {
    Connection conn;
    public Connection openConnection() throws ClassNotFoundException {
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
}
