package moeam.user;

import moeam.db.query.QueryUser;

public class UserHandler
{
    private QueryUser m_queryUser = new QueryUser();
    
    public void createNewUser(String p_userName, String p_password)
    {
        // Check if the username is unique before creating a user
        boolean userWasFound = m_queryUser.findUser(p_userName);
        if (userWasFound)
        {
            // TODO redirect to a page saying this user already exists, please pick a different name
            System.out.println("Tried to create a user but user already exists");
        }
        else
        {
            // Create the user only if we've confirmed that the username is unique
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
    
    public void login(String p_userName, String p_password)
    {
    }
}
