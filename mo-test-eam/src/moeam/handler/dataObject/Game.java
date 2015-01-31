package moeam.handler.dataObject;

/** Java bean for metadata about a game. */
public class Game
{
    private int m_gameId = -1;
    private String m_gameName;
    private String m_companyName;
    private String m_downloadLink;
    private String m_description;

    public Game(String p_gameName, String p_companyName, String p_downloadLink, String p_description)
    {
        m_gameName = p_gameName;
        m_companyName = p_companyName;
        m_downloadLink = p_downloadLink;
        m_description = p_description;
    }

    /**
     * This shouldn't be manually set as the database auto generates its ID.
     * @param p_gameId
     */
    public void setGameId(int p_gameId)
    {
        m_gameId = p_gameId;
    }

    public int getGameId()
    {
        return m_gameId;
    }

    public String getGameName()
    {
        return m_gameName;
    }

    public void setGameName(String p_gameName)
    {
        m_gameName = p_gameName;
    }

    public String getCompanyName()
    {
        return m_companyName;
    }

    public void setCompanyName(String p_companyName)
    {
        m_companyName = p_companyName;
    }

    public String getDownloadLink()
    {
        return m_downloadLink;
    }

    public void setDownloadLink(String p_downloadLink)
    {
        m_downloadLink = p_downloadLink;
    }

    public String getDescription()
    {
        return m_description;
    }

    public void setDescription(String p_description)
    {
        m_description = p_description;
    }
}
