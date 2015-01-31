package moeam.handler;

import java.time.LocalDate;
import java.util.ArrayList;

import moeam.db.query.QueryTopic;
import moeam.handler.dataObject.Topic;

// TODO incomplete, not sure how you'll get the game ID and where we'll do a look up for it
public class TopicHandler
{
    private QueryTopic m_queryTopic = new QueryTopic();

    public void createNewTopic(int p_gameId, String p_topicName, LocalDate p_date)
    {
        boolean topicSuccessfullyCreated = m_queryTopic.createTopic(p_gameId, p_topicName, p_date);
        if (!topicSuccessfullyCreated)
        {
            System.out.println("Oops, something went wrong when creating your topic!");
        }
        else
        {
            System.out.println("Topic was successfully created!");
        }
    }

    public Topic getTopic(String p_topicName)
    {
        return m_queryTopic.getTopic(p_topicName);
    }

    public ArrayList<Topic> getAllTopics(int p_gameId)
    {
        ArrayList<Topic> allTopics = m_queryTopic.getAllTopics(p_gameId);
        return allTopics;
    }
}
