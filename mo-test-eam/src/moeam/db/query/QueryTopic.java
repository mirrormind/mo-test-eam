package moeam.db.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import moeam.db.query.driver.DatabaseDriver;
import moeam.handler.dao.Topic;

public class QueryTopic
{
    /** Create a new DB connection */
    private Connection m_connection = new DatabaseDriver().openConnection();

    /**
     * Create a new topic in the database
     * @param p_topic
     * @return True if we have successfully entered the game into the DB, false if not.
     */
    public boolean createTopic(Topic p_topic)
    {
        String insertUserData = "INSERT INTO topics (F_gameId, topicName, dateCreated) VALUES (?, ?, ?)";

        int affectedRows = -1;
        try
        {
            PreparedStatement statement = m_connection.prepareStatement(insertUserData);
            statement.setInt(1, p_topic.getGameId());
            statement.setString(2, p_topic.getTopicName());
            statement.setDate(3, convertToSqlDate(p_topic.getDate()));
            affectedRows = statement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        // If 1 was returned (meaning 1 row was changed), return true.
        if (affectedRows == 1)
        {
            return true;
        }
        return false;
    }

    /**
     * Converts java.util.Date to java.sql.Date. This only supports date conversion and NOT time.
     * @param p_utilDate
     * @return SQL Date object.
     */
    private java.sql.Date convertToSqlDate(Date p_utilDate)
    {
        return new java.sql.Date(p_utilDate.getTime());
    }

    /**
     * Converts java.sql.Date to java.util.Date. This only supports date conversion and NOT time.
     * @param p_sqlDate
     * @return Java util Date object.
     */
    private Date convertToUtilDate(java.sql.Date p_sqlDate)
    {
        return new Date(p_sqlDate.getTime());
    }

    /**
     * Find a topic by its title.
     * @param p_topicName
     * @return True if the topic was found, false if not.
     */
    public boolean findTopic(String p_topicName)
    {
        Topic topic = getTopic(p_topicName);

        // Return true if the results aren't null
        if (topic != null)
        {
            return true;
        }
        return false;
    }

    /**
     * Get a topic by its title.
     * @param p_topicName
     * @return The Topic object if found, else null.
     */
    public Topic getTopic(String p_topicName)
    {
        String findTopic = "SELECT * FROM topics WHERE topicName=?";

        ResultSet resultSet = null;
        try
        {
            PreparedStatement statement = m_connection.prepareStatement(findTopic);
            statement.setString(1, p_topicName);
            resultSet = statement.executeQuery();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        // Transform the result set into a user object
        ArrayList<Topic> topics = generateTopicObject(resultSet);
        if (!topics.isEmpty())
        {
            return topics.get(0);
        }
        return null;
    }

    /**
     * Gets all topics in the database.
     * @return A collection of topics.
     * TODO this has the potential to return unlimited number of entries! Should be limited!
     */
    @Deprecated
    public ArrayList<Topic> getAllTopics()
    {
        String findAllGames = "SELECT * FROM topics";

        ResultSet resultSet = null;
        try
        {
            PreparedStatement statement = m_connection.prepareStatement(findAllGames);
            resultSet = statement.executeQuery();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        // Transform the result set into a list of topic objects
        ArrayList<Topic> topics = generateTopicObject(resultSet);
        if (!topics.isEmpty())
        {
            return topics;
        }
        return null;
    }

    /**
     * Turns a database result set into a collection of topic objects.
     * @param p_resultSet
     * @return Collection of topic objects.
     */
    private ArrayList<Topic> generateTopicObject(ResultSet p_resultSet)
    {
        try
        {
            ArrayList<Topic> topics = new ArrayList<Topic>();

            // Only create the topic if something was found in the result set.
            while (p_resultSet.next())
            {
                int topicId = p_resultSet.getInt("P_topicId");
                int gameId = p_resultSet.getInt("F_gameId");
                String topicName = p_resultSet.getString("topicName");
                Date date = convertToUtilDate(p_resultSet.getDate("dateCreated"));

                // Create a new topic and also set its ID
                Topic topic = new Topic(gameId, topicName, date);
                topic.setTopicId(topicId);
                topics.add(topic);
            }
            return topics;
        }
        catch (SQLException e)
        {
            System.out.println("Something went wrong when opening the result set");
            e.printStackTrace();
            return null;
        }
    }
}
