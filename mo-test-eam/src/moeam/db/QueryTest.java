package moeam.db;

import org.junit.Test;

public class QueryTest
{
    @Test
    public void createUser()
    {
        QueryUser queryUser = new QueryUser();
        boolean creationSuccess = queryUser.createUser("mirrormind",
                "mirrorPassword");

        assert (creationSuccess);
    }
}
