package moeam.db;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import moeam.db.query.QueryUser;
import moeam.user.User;

import org.junit.BeforeClass;
import org.junit.Test;

public class QueryUserTest
{
    private static final String USERNAME = "mirrormindUsername";
    private static final String PASSWORD = "mirrormindPassword";
    private final String NON_EXISTING_USER = "nonExistantUser";

    private static QueryUser m_queryUser = new QueryUser();

    // Junit tests aren't guaranteed to run in this specific order
    // so we force create a user before tests are run. This is a temporary solution
    @BeforeClass
    public static void setup()
    {
        createUser();
    }

    /** Test if we can create a user */
    // @Test
    public static void createUser()
    {
        boolean affectedRows = m_queryUser.createUser(USERNAME, PASSWORD);
        assertThat("User was successfully created", affectedRows, is(true));
    }

    /** Test if we can find a user */
    @Test
    public void findUser()
    {
        boolean userIsFound = m_queryUser.findUser(USERNAME);
        assertThat("Existing user was successfully found", userIsFound, is(true));
    }

    /** Test if user is not found */
    @Test
    public void findNoUser()
    {
        boolean userIsFound = m_queryUser.findUser(NON_EXISTING_USER);
        assertThat("Non existant user was not found", userIsFound, is(false));
    }

    /** Test if user can be retrieved */
    @Test
    public void getUser()
    {
        String userName = "";
        String password = "";

        User user = m_queryUser.getUser(USERNAME);
        if (user != null)
        {
            userName = user.getUserName();
            password = user.getPassword();

            assertThat("Username is correct", userName, is(USERNAME));
            assertThat("Password is correct", password, is(PASSWORD));
        }
        else
        {
            fail("User was not found");
        }
    }
}
