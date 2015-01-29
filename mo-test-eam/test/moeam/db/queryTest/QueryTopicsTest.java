package moeam.db.queryTest;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.Date;

import moeam.db.query.QueryTopic;
import moeam.handler.dao.Topic;

import org.junit.BeforeClass;
import org.junit.Test;

public class QueryTopicsTest extends AbstractQueryTest
{
    /** The topic */
    private static Topic TOPIC = new Topic(1, "Topic 1", new Date());

    /** A topic name that doesn't exist */
    private static String NON_EXISTING_TOPIC_NAME = "Non-existing topic";

    /** The query to test */
    private static QueryTopic m_queryGame = new QueryTopic();

    // Junit tests aren't guaranteed to run in this specific order
    // so we force create a topic before tests are run. This is a temporary solution
    @BeforeClass
    public static void setup()
    {
        createTopic();
    }

    /** Test if we can create a topic */
    // @Test
    public static void createTopic()
    {
        boolean topicWasCreated = m_queryGame.createTopic(TOPIC);
        assertThat("Topic was successfully created", topicWasCreated, is(true));
    }

    /** Test if we can find a topic */
    @Test
    public void findTopic()
    {
        boolean topicIsFound = m_queryGame.findTopic(TOPIC.getTopicName());
        assertThat("Existing topic was successfully found", topicIsFound, is(true));
    }

    /** Test if a non existing topic is not found */
    @Test
    public void findNoTopic()
    {
        boolean topicIsFound = m_queryGame.findTopic(NON_EXISTING_TOPIC_NAME);
        assertThat("Non existant topic was not found", topicIsFound, is(false));
    }

    /** Test if a topic can be retrieved */
    @Test
    public void getGame()
    {
        Topic topic = m_queryGame.getTopic(TOPIC.getTopicName());
        if (topic != null)
        {
            int gameId = topic.getGameId();
            int topicId = topic.getTopicId();
            String topicName = topic.getTopicName();
            Date date = topic.getDate();

            assertThat("Topic ID is not a null value", topicId, greaterThan(0));
            assertThat("Game ID is assigned", gameId, is(1));
            assertThat("Topic name is correct", topicName, is(TOPIC.getTopicName()));
            assertThat("Date is correct", date, is(TOPIC.getDate()));
        }
        else
        {
            fail("Game was not found");
        }
    }
}
