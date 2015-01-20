package moeam.db;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

public class QueryTest
{
    private final String USERNAME = "mirrormindUsername";
    private final String PASSWORD = "mirrormindPassword";
    private final String NON_EXISTING_USER = "nonExistantUser";

    private QueryUser m_queryUser = new QueryUser();

    /** Test if we can create a user */
    @Test
    public void createUser()
    {
        int affectedRows = -1;
        try
        {
            affectedRows = m_queryUser.createUser(USERNAME, PASSWORD);
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        assertThat("User was successfully created", affectedRows, is(1));
    }

    /** Test if we can find a user */
    @Test
    public void findUser()
    {
        boolean userIsFound = false;
        try
        {
            userIsFound = m_queryUser.findUser(USERNAME);
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        assertThat("Existing user was successfully found", userIsFound, is(true));
    }

    /** Test if user is not found */
    @Test
    public void findNoUser()
    {
        boolean userIsFound = false;
        try
        {
            userIsFound = m_queryUser.findUser(NON_EXISTING_USER);
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        assertThat("Non existant user was not found", userIsFound, is(false));
    }

    /** Test if user can be retrieved */
    @Test
    public void getUser()
    {
        ResultSet resultSet = null;

        String userName = "";
        String password = "";
        try
        {
            resultSet = m_queryUser.getUser(USERNAME);
            userName = resultSet.getString("userName");
            password = resultSet.getString("password");
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail();
        }

        assertThat("Username is correct", userName, is(USERNAME));
        assertThat("Password is correct", password, is(PASSWORD));
    }
}
