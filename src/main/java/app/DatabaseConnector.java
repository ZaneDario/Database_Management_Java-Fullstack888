package app;
import java.sql.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnector {

    public static Connection getConnection(String userName, String password,
                                           String serverName, String portNumber, String database){
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn;
            //Properties connectionProps = new Properties();
            //connectionProps.put("user", userName);
            //connectionProps.put("password", password);

            conn = DriverManager.getConnection("jdbc:mysql" + "://" + serverName +
                    ":" + portNumber + "/" + database, userName, password);

            System.out.println("Connected to database");
            return conn;
        }
        catch (SQLException e)
        {
            System.out.println("ERROR: "+ e.toString());
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("ERROR: "+ e.toString());
            return null;
        }
    }
}
