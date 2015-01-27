package moeam.db.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import moeam.db.query.driver.DatabaseDriver;
import moeam.handler.dao.Game;

public class QueryGame
{
    /** Create a new DB connection */
    private Connection m_connection = new DatabaseDriver().openConnection();

    /**
     * Create a new game in the database
     * @param p_gameName
     * @param p_companyName
     * @param p_downloadLink
     * @param p_description
     * @return True if we have successfully entered the game into the DB, false if not.
     */
    public boolean createGame(String p_gameName, String p_companyName, String p_downloadLink, String p_description)
    {
        String insertUserData = "INSERT INTO games (gameName, companyName, downloadLink, description) VALUES (?, ?, ?, ?)";

        int affectedRows = -1;
        try
        {
            PreparedStatement statement = m_connection.prepareStatement(insertUserData);
            statement.setString(1, p_gameName);
            statement.setString(2, p_companyName);
            statement.setString(3, p_downloadLink);
            statement.setString(4, p_description);
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
     * Find a game by its title and company.
     * @param p_gameName
     * @param p_companyName
     * @return True if the game was found, false if not.
     */
    public boolean findGame(String p_gameName, String p_companyName)
    {
        Game game = getGame(p_gameName, p_companyName);

        // Return true if the results aren't null
        if (game != null)
        {
            return true;
        }
        return false;
    }

    /**
     * Get a game by its title and company.
     * @param p_gameName
     * @param p_companyName
     * @return The Game object if found, else null.
     */
    public Game getGame(String p_gameName, String p_companyName)
    {
        String findGame = "SELECT * FROM games WHERE gameName=? AND companyName=?";

        ResultSet resultSet = null;
        try
        {
            PreparedStatement statement = m_connection.prepareStatement(findGame);
            statement.setString(1, p_gameName);
            statement.setString(2, p_companyName);
            resultSet = statement.executeQuery();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        // Transform the result set into a user object
        ArrayList<Game> games = generateGameObject(resultSet);
        if (!games.isEmpty())
        {
            return games.get(0);
        }
        return null;
    }

    /**
     * Gets all games in the database.
     * @return A collection of games.
     * TODO this has the potential to return unlimited number of entries! Should be limited!
     */
    @Deprecated
    public ArrayList<Game> getAllGames()
    {
        String findAllGames = "SELECT * FROM games";

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

        // Transform the result set into a list of game objects
        ArrayList<Game> games = generateGameObject(resultSet);
        if (!games.isEmpty())
        {
            return games;
        }
        return null;
    }

    /**
     * Turns a database result set into a collection of game objects.
     * @param p_resultSet
     * @return Collection of game objects.
     */
    private ArrayList<Game> generateGameObject(ResultSet p_resultSet)
    {
        ArrayList<Game> games = new ArrayList<Game>();

        try
        {
            // Only create the user if something was found in the result set.
            while (p_resultSet.next())
            {
                String gameName = p_resultSet.getString("gameName");
                String companyName = p_resultSet.getString("companyName");
                String downloadLink = p_resultSet.getString("downloadLink");
                String description = p_resultSet.getString("description");
                games.add(new Game(gameName, companyName, downloadLink, description));
            }
            return games;
        }
        catch (SQLException e)
        {
            System.out.println("Something went wrong when opening the result set");
            e.printStackTrace();
            return null;
        }
    }
}
