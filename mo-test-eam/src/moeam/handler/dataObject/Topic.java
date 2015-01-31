package moeam.handler.dataObject;

import java.time.LocalDate;

public class Topic
{
    private int m_topicId = -1;
    private int m_gameId;
    private String m_topicName;
    private LocalDate m_date;

    public Topic(int p_gameId, String p_topicName, LocalDate p_date)
    {
        m_gameId = p_gameId;
        m_topicName = p_topicName;
        m_date = p_date;
    }

    /**
     * This method shouldn't be used unless retrieving data from the database.
     * Ideally the topic ID is never manually modified outside of converting the DB object into a DAO.
     */
    public void setTopicId(int p_topicId)
    {
        m_topicId = p_topicId;
    }

    public int getTopicId()
    {
        return m_topicId;
    }

    public void setGameId(int p_gameId)
    {
        m_gameId = p_gameId;
    }

    public int getGameId()
    {
        return m_gameId;
    }

    public void setTopicName(String p_topicName)
    {
        m_topicName = p_topicName;
    }

    public String getTopicName()
    {
        return m_topicName;
    }

    public void setDate(LocalDate p_date)
    {
        m_date = p_date;
    }

    public LocalDate getDate()
    {
        return m_date;
    }
}
