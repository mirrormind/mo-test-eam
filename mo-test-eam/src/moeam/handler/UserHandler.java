package moeam.handler;

import moeam.db.query.QueryUser;
import moeam.handler.dataObject.User;

public class UserHandler
{
    private QueryUser m_queryUser = new QueryUser();

    /**
     * Register a new user with a username and password, only if the username doesn't exist in the DB yet.
     * @param p_userName
     * @param p_password
     */
    public void createNewUser(String p_userName, String p_password)
    {
        // Check if the username is unique
        boolean userAlreadyExists = m_queryUser.findUser(p_userName);
        if (userAlreadyExists)
        {
            // TODO redirect to a page saying this user already exists, please pick a different name
            System.out.println("Tried to create a user but user already exists");
        }
        else
        {
            // Create the user
            boolean userWasCreated = m_queryUser.createUser(p_userName, p_password);
            if (!userWasCreated)
            {
                // TODO say that something went wrong and try again?
                System.out.println("Something went wrong when creating the user");
            }
            else
            {
                // TODO say that user creation was successful and redirect them to the login page
                System.out.println("User creation was successful");
            }
        }
    }

    /**
     * Login to the application using a username and password.
     * @param p_userName
     * @param p_password
     */
    public void login(String p_userName, String p_password)
    {
        User user = m_queryUser.getUser(p_userName);
        if (p_userName.equals(user.getUserName()) && p_password.equals(user.getPassword()))
        {
            // TODO let the user log in
            System.out.println("Username and password are correct");
        }
        else
        {
            // TODO don't let the user in and prompt them
            System.out.println("Username or password is incorrect");
        }
    }

    /**
     * TODO this encryption is crappy.
     * @param p_password
     * @return The encrypted password
     */
    private String encryptPassword(String p_password)
    {
        String encryptedPassword = "";
        return encryptedPassword;
    }
}
