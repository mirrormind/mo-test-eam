package moeam.db.driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseDriver
{
    private final String DB_USERNAME = "root";
    private final String DB_PASSWORD = "01001";
    private final String DB_URL = "jdbc:mysql://localhost:3306/test";

    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    /** DB Connection */
    private Connection m_conn;

    private Statement m_statement;

    /**
     * Establishes a DB connection.
     * @return true if the connection to the DB is successful
     */
    private boolean openConnection()
    {
        boolean hasOpenedConnection = false;
        try
        {
            System.out.println("Opening a connection to the databse ..."); // TODO logger
            Class.forName(JDBC_DRIVER);
            m_conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            hasOpenedConnection = true;
        }
        catch (ClassNotFoundException e)
        {
            // TODO change to logger
            System.out.println("Tried to access the class [" + JDBC_DRIVER + "] but failed with error:");
            e.printStackTrace();

            // Finally close the stream just in case
            closeConnection();
        }
        catch (SQLException e)
        {
            // TODO change to logger
            String message = "Tried to connect to database using these credentials: ";
            message += "DB URL [" + DB_URL + "], DB Username [" + DB_USERNAME + "], DB Password [" + DB_PASSWORD + "] ";
            message += "but failed with error:";
            System.out.println(message);
            e.printStackTrace();

            // Finally close the stream just in case
            closeConnection();
        }
        return hasOpenedConnection;
    }

    /**
     * Terminate the DB connection.
     * @return true if the connection has been successfully closed
     */
    private boolean closeConnection()
    {
        boolean hasClosedConnection = false;
        try
        {
            System.out.println("Closing connection to database ..."); // TODO logger
            m_conn.close();
            hasClosedConnection = true;
        }
        catch (SQLException e)
        {
            System.out.println("Tried to close the database connection but failed with error:");
            e.printStackTrace();
            // Try again?
        }
        return hasClosedConnection;
    }

    /**
     * Send an SQL statement which doesn't expect a return value from the DB.
     * @param p_query The SQL query.
     * @return True if the query has been successfully performed.
     */
    public boolean createRecord(String p_query)
    {
        boolean querySuccess = false;
        if (openConnection())
        {
            try
            {
                System.out.println("Attempting to execute database statement ..."); // TODO logger
                m_statement = m_conn.createStatement();
                // TODO logic for escaping strings in case of code injection
                int rowsAffected = m_statement.executeUpdate(p_query);
                System.out.println(rowsAffected + " were affected."); // TODO logger
                querySuccess = true;
            }
            catch (SQLException e)
            {
                System.out.println("Tried to execute a database statement but failed with error:");
                e.printStackTrace();
            }
            closeConnection();
        }
        return querySuccess;
    }

    /**
     * Send an SQL statement which tries to find if a record exists in the DB.
     * @param p_query The SQL query.
     * @return True if a record has been found with the query.
     */
    public boolean findRecord(String p_query)
    {
        boolean recordIsFound = false;
        if (openConnection())
        {
            try
            {
                m_statement = m_conn.createStatement();
                m_statement.executeQuery(p_query);
                ResultSet resultSet = m_statement.getResultSet();
                if (resultSet != null)
                {
                    recordIsFound = true;
                }
            }
            catch (SQLException e)
            {
                System.out.println("Tried to retrieve a database record failed with error:");
                e.printStackTrace();
            }
            closeConnection();
        }
        return recordIsFound;
    }
}
