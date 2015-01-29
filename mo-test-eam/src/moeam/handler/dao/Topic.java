package moeam.handler.dao;

import java.util.Calendar;
import java.util.Date;

public class Topic
{
    private int m_topicId = -1;
    private int m_gameId;
    private String m_topicName;
    private Date m_date;

    public Topic(int p_gameId, String p_topicName, Date p_date)
    {
        m_gameId = p_gameId;
        m_topicName = p_topicName;

        // Ensure date only stores the date and not the time
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        m_date = calendar.getTime();
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

    public void setDate(Date p_date)
    {
        m_date = p_date;
    }

    public Date getDate()
    {
        return m_date;
    }
}
