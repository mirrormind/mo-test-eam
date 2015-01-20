package moeam.db.driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseDriver
{
    private final String DB_USERNAME = "root";
    private final String DB_PASSWORD = "01001";
    private final String DB_URL = "jdbc:mysql://localhost:3306/test";

    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    /**
     * Establishes a DB connection.
     * @return The Connection object if successful, else null
     */
    public Connection openConnection()
    {
        try
        {
            Connection connection;
            System.out.println("Opening a connection to the databse ..."); // TODO logger
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            return connection;
        }
        catch (ClassNotFoundException e)
        {
            // TODO change to logger
            System.out.println("Tried to access the class [" + JDBC_DRIVER + "] but failed with error:");
            e.printStackTrace();
            return null;
        }
        catch (SQLException e)
        {
            // TODO change to logger
            String message = "Tried to connect to database using these credentials: ";
            message += "DB URL [" + DB_URL + "], DB Username [" + DB_USERNAME + "], DB Password [" + DB_PASSWORD + "] ";
            message += "but failed with error:";
            System.out.println(message);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Terminate the DB connection.
     * @param p_connection The connection to close.
     */
    public void closeConnection(Connection p_connection)
    {
        try
        {
            System.out.println("Closing connection to database ..."); // TODO logger
            p_connection.close();
        }
        catch (SQLException e)
        {
            System.out.println("Tried to close the database connection but failed with error:");
            e.printStackTrace();
            // Try again?
        }
    }
}
