package moeam.db.queryTest;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import moeam.db.query.QueryGame;
import moeam.handler.dataObject.Game;

import org.junit.BeforeClass;
import org.junit.Test;

public class QueryGameTest extends AbstractQueryTest
{
    private static String GAME_NAME = "Recrudesce";
    private static String COMPANY_NAME = "MirrorMind";
    private static String DOWNLOAD_LINK = "http://example.com/";
    private static String DESCRIPTION = "This is an example description for the game Recrudesce.";

    private static String NON_EXISTING_GAME_NAME = "Non-existing game";
    private static String NON_EXISTING_COMPANY = "Non-existing company";

    private static QueryGame m_queryGame = new QueryGame();

    // Junit tests aren't guaranteed to run in this specific order
    // so we force create a game before tests are run. This is a temporary solution
    @BeforeClass
    public static void setup()
    {
        createGame();
    }

    /** Test if we can create a game */
    // @Test
    public static void createGame()
    {
        boolean gameWasCreated = m_queryGame.createGame(GAME_NAME, COMPANY_NAME, DOWNLOAD_LINK, DESCRIPTION);
        assertThat("Game was successfully created", gameWasCreated, is(true));
    }

    /** Test if we can find a game */
    @Test
    public void findGame()
    {
        boolean gameIsFound = m_queryGame.findGame(GAME_NAME, COMPANY_NAME);
        assertThat("Existing game was successfully found", gameIsFound, is(true));
    }

    /** Test if a non existing game is not found */
    @Test
    public void findNoGame()
    {
        boolean gameIsFound = m_queryGame.findGame(NON_EXISTING_GAME_NAME, NON_EXISTING_COMPANY);
        assertThat("Non existent game was not found", gameIsFound, is(false));
    }

    /** Test if game can be retrieved */
    @Test
    public void getGame()
    {
        Game game = m_queryGame.getGame(GAME_NAME, COMPANY_NAME);
        if (game != null)
        {
            int gameId = game.getGameId();
            String gameName = game.getGameName();
            String companyName = game.getCompanyName();
            String downloadLink = game.getDownloadLink();
            String description = game.getDescription();

            assertThat("Game ID is not a null value", gameId, greaterThan(0));
            assertThat("Game name is correct", gameName, is(GAME_NAME));
            assertThat("Company name is correct", companyName, is(COMPANY_NAME));
            assertThat("Download link is correct", downloadLink, is(DOWNLOAD_LINK));
            assertThat("Game description is correct", description, is(DESCRIPTION));
        }
        else
        {
            fail("Game was not found");
        }
    }
}
