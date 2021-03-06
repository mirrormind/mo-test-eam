package moeam.db.queryTest;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.util.ArrayList;

import moeam.db.query.QueryTopic;
import moeam.handler.dataObject.Topic;

import org.junit.BeforeClass;
import org.junit.Test;

public class QueryTopicsTest extends AbstractQueryTest
{
    /** The topic */
    private static int GAME_ID = 1;
    private static String TOPIC_NAME = "QueryTopicsTest";
    private static LocalDate DATE_NOW = LocalDate.now();

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
        boolean topicWasCreated = m_queryGame.createTopic(GAME_ID, TOPIC_NAME, DATE_NOW);
        assertThat("Topic was successfully created", topicWasCreated, is(true));
    }

    /** Test if we can find a topic */
    @Test
    public void findTopic()
    {
        boolean topicIsFound = m_queryGame.findTopic(TOPIC_NAME);
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
    public void getTopicByName()
    {
        Topic topic = m_queryGame.getTopic(TOPIC_NAME);
        if (topic != null)
        {
            int gameId = topic.getGameId();
            int topicId = topic.getTopicId();
            String topicName = topic.getTopicName();
            LocalDate date = topic.getDate();

            assertThat("Topic ID is not a null value", topicId, greaterThan(0));
            assertThat("Game ID is assigned", gameId, is(1));
            assertThat("Topic name is correct", topicName, is(TOPIC_NAME));
            assertThat("Date is correct", date, is(DATE_NOW));
        }
        else
        {
            fail("Game was not found");
        }
    }

    /** Test if we can get all topics of a certain game */
    @Test
    public void getAllTopicsInGame()
    {
        int gameId = 1;
        ArrayList<Topic> topics = m_queryGame.getAllTopics(gameId);
        assertThat("Topics were retrieved", topics.isEmpty(), is(false));

        boolean topicFound = false;
        for (Topic topic : topics)
        {
            if (topic.getTopicName().equals(TOPIC_NAME))
            {
                topicFound = true;
            }
        }
        assertThat("Topic created from this test was found", topicFound, is(true));
    }

    /** Test that we find no topics in a certain game */
    @Test
    public void getNoTopicsInGame()
    {
        int gameId = 5;
        ArrayList<Topic> topics = m_queryGame.getAllTopics(gameId);
        assertThat("No topics found in this game", topics.isEmpty(), is(true));
    }
}
