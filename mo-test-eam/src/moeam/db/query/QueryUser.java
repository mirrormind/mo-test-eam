package moeam.db.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import moeam.db.driver.DatabaseDriver;
import moeam.user.User;

public class QueryUser
{
    /** Create a new DB connection */
    private Connection m_connection = new DatabaseDriver().openConnection();

    /**
     * Create a user.
     * @param p_userName
     * @param p_password
     * @return True if we have successfully entered the user into the DB, false if not.
     */
    public boolean createUser(String p_userName, String p_password)
    {
        String insertUserData = "INSERT INTO users (userName, password) VALUES (?, ?)";

        int affectedRows = -1;
        try
        {
            PreparedStatement statement = m_connection.prepareStatement(insertUserData);
            statement.setString(1, p_userName);
            statement.setString(2, p_password);
            affectedRows = statement.executeUpdate();
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // If 1 was returned (meaning 1 row was changed), return true.
        if (affectedRows == 11)
        {
            return true;
        }
        return false;
    }

    /**
     * Find a user by their username.
     * @param p_userName
     * @return True if a user was found, false if not.
     */
    public boolean findUser(String p_userName)
    {
        User user = getUser(p_userName);

        // Return true if the results aren't null
        if (user != null)
        {
            return true;
        }
        return false;
    }

    /**
     * Get a user by their username.
     * @param p_userName
     * @return The result set object if a user was found, null if no user was found.
     */
    public User getUser(String p_userName)
    {
        String findUser = "SELECT userName, password FROM users WHERE userName=?";

        ResultSet resultSet = null;
        try
        {
            PreparedStatement statement = m_connection.prepareStatement(findUser);
            statement.setString(1, p_userName);
            resultSet = statement.executeQuery();
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Transform the result set into a user object
        ArrayList<User> users = generateUserObject(resultSet);
        return users.get(0);
    }

    /**
     * Turns a database result set into a user object.
     * @param p_resultSet
     * @return The user object.
     */
    private ArrayList<User> generateUserObject(ResultSet p_resultSet)
    {
        ArrayList<User> users = new ArrayList<User>();

        try
        {
            // Only create the user if something was found in the result set.
            while (p_resultSet.next())
            {
                String userName = p_resultSet.getString("userName");
                String password = p_resultSet.getString("password");
                users.add(new User(userName, password));
            }
            return users;
        }
        catch (SQLException e)
        {
            System.out.println("Something went wrong when opening the result set");
            e.printStackTrace();
            return null;
        }
    }
}