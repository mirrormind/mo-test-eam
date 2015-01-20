package moeam.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import moeam.db.driver.DatabaseDriver;

public class QueryUser
{
    /** Create a new DB connection */
    private Connection m_connection = new DatabaseDriver().openConnection();

    /**
     * Create a user.
     * @param p_userName
     * @param p_password
     * @return True if we have successfully entered the user into the DB
     * @throws SQLException
     */
    public int createUser(String p_userName, String p_password) throws SQLException
    {
        String insertUserData = "INSERT INTO users (userName, password) VALUES (?, ?)";

        PreparedStatement statement = m_connection.prepareStatement(insertUserData);
        statement.setString(1, p_userName);
        statement.setString(2, p_password);
        int affectedRows = statement.executeUpdate();

        return affectedRows;
    }

    /**
     * Find a user by their username.
     * @param p_userName
     * @return
     * @throws SQLException
     */
    public boolean findUser(String p_userName) throws SQLException
    {
        ResultSet resultSet = getUser(p_userName);

        // Return true if the results aren't null
        if (resultSet != null)
        {
            return true;
        }
        return false;
    }

    /**
     * Get a user by their username.
     * @param p_userName
     * @return
     * @throws SQLException
     */
    public ResultSet getUser(String p_userName) throws SQLException
    {
        String findUser = "SELECT userName, password FROM users WHERE userName=?";

        PreparedStatement statement = m_connection.prepareStatement(findUser);
        statement.setString(1, p_userName);
        ResultSet resultSet = statement.executeQuery();

        // Return null instead if there were no results in the set (ResultSets are always non-null)
        if (!resultSet.next())
        {
            return null;
        }
        return resultSet;
    }
}