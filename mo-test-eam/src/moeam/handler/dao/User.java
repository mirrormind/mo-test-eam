package moeam.handler.dao;

/** Java bean for metadata about a user */
public class User
{
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
