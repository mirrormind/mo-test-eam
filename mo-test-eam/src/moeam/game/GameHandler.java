package moeam.game;

import java.util.ArrayList;

import moeam.db.query.QueryGame;

/** Incomplete */
public class GameHandler
{
    private QueryGame m_queryGame = new QueryGame();

    /**
     * This isn't a UI feature yet, nor is it exactly supported until the module is created for retrieving a list of games.
     * 
     * Creates a new game in the database. If a game with the same name and company already exists, it doesn't create it.
     * @param p_gameName
     * @param p_companyName
     * @param p_downloadLink
     * @param p_description
     */
    public void createNewGame(String p_gameName, String p_companyName, String p_downloadLink, String p_description)
    {
        boolean gameAlreadyExists = m_queryGame.findGame(p_gameName, p_companyName);

        if (gameAlreadyExists)
        {
            System.out.println("Tried to create a game but the game already exists");
        }
        else
        {
            boolean gameWasCreated = m_queryGame.createGame(p_gameName, p_companyName, p_downloadLink, p_description);

            if (!gameWasCreated)
            {
                System.out.println("Something went wrong when creating the game");
            }
        }
    }

    /**
     * Gets all games.
     * @return A collection of games.
     */
    public ArrayList<Game> getAllGames()
    {
        ArrayList<Game> allGames = m_queryGame.getAllGames();
        return allGames;
    }

    /**
     * Gets a game by its title and company.
     * @param p_gameName
     * @param p_companyName
     * @return A game object.
     */
    public Game getGame(String p_gameName, String p_companyName)
    {
        return m_queryGame.getGame(p_gameName, p_companyName);
    }
}
