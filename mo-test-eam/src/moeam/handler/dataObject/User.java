package moeam.handler.dataObject;

/** Java bean for metadata about a user */
public class User
{
    private int m_userId = -1;
    private String m_userName;
    private String m_password;

    /**
     * Create a user.
     * @param p_userName
     * @param p_password
     */
    public User(String p_userName, String p_password)
    {
        m_userName = p_userName;
        m_password = p_password;
    }

    /**
     * This shouldn't be manually set as the database auto generates an ID for the user.
     * @param p_userId
     */
    public void setUserId(int p_userId)
    {
        m_userId = p_userId;
    }

    public int getUserId()
    {
        return m_userId;
    }

    public String getUserName()
    {
        return m_userName;
    }

    public void setUserName(String p_userName)
    {
        this.m_userName = p_userName;
    }

    public String getPassword()
    {
        return m_password;
    }

    public void setPassword(String p_password)
    {
        this.m_password = p_password;
    }
}
