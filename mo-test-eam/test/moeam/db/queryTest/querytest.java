package moeam.db.queryTest;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import moeam.db.query.QueryUser;

import org.junit.Test;

public class querytest extends AbstractQueryTest
{

    private final String USERNAME = "mirrormindUsername";
    private final String PASSWORD = "mirrormindPassword";

    private QueryUser m_queryUser = new QueryUser();

    /** Test if we can create a user */
    @Test
    public void createUser()
    {
        boolean userWasCreated = m_queryUser.createUser(USERNAME, PASSWORD);
        assertThat("User was successfully created", userWasCreated, is(true));
    }
}
