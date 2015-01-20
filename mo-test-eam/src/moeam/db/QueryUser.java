package moeam.db;

import moeam.db.driver.DatabaseDriver;

public class QueryUser
{
    private DatabaseDriver m_connection = new DatabaseDriver();

    /**
     * Create a user.
     * @param p_userName
     * @param p_password TODO has this been hashed? Where do we do it?
     * @return True if we have successfully entered the user into the DB
     */
    public boolean createUser(String p_userName, String p_password)
    {
        String insertUserData = "INSERT INTO users (userName, password) VALUES ";
        insertUserData += "('" + p_userName + "', '" + p_password + "')";

        return m_connection.createRecord(insertUserData);
    }

    /**
     * Find if a specified user exists in the database or not.
     * We never return the actual user back to the application for security reasons.
     * @param p_loginName
     * @param p_password
     * @return true if the user was found
     */
    public boolean findUser(String p_userName)
    {
        String findUserData = "SELECT EXISTS (SELECT 1 FROM users ";
        findUserData += "WHERE p_userName = '" + p_userName + "')";

        return m_connection.findRecord(findUserData);
    }
}