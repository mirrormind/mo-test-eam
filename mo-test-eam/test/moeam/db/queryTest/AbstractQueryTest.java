package moeam.db.queryTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import moeam.db.query.driver.DatabaseDriver;

import org.junit.AfterClass;
import org.junit.BeforeClass;

public abstract class AbstractQueryTest
{
    private static final String USERS_TABLE = "AbstractUsers";
    private static final String GAMES_TABLE = "AbstractGames";
    private static final String TOPICS_TABLE = "AbstractTopics";
    private static final String POSTS_TABLE = "AbstractPosts";

    /** Create a new DB connection */
    private static Connection m_connection = new DatabaseDriver().openConnection();
    private static PreparedStatement m_statement;

    /** Create all the tables and populate them with default data */
    @BeforeClass
    public static void createTables()
    {
        createUsers();
        createGames();
        createTopics();
    }

    private static void createUsers()
    {
        try
        {
            // Create table
            String createUserTable = "CREATE TABLE " + USERS_TABLE + " (";
            createUserTable += "P_userId int PRIMARY KEY NOT NULL AUTO_INCREMENT UNIQUE,";
            createUserTable += "userName VARCHAR(30),";
            createUserTable += "password VARCHAR(30))";

            m_statement = m_connection.prepareStatement(createUserTable);
            m_statement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private static void createGames()
    {
        try
        {
            // Create table
            String createGameTable = "CREATE TABLE " + GAMES_TABLE + " (";
            createGameTable += "P_gameId int PRIMARY KEY NOT NULL AUTO_INCREMENT UNIQUE,";
            createGameTable += "gameName VARCHAR(15),";
            createGameTable += "companyName VARCHAR(15),";
            createGameTable += "downloadLink VARCHAR(50),";
            createGameTable += "description VARCHAR(300))";

            m_statement = m_connection.prepareStatement(createGameTable);
            m_statement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private static void createTopics()
    {
        try
        {
            // Create table
            String createGameTable = "CREATE TABLE " + TOPICS_TABLE + " (";
            createGameTable += "P_topicId int PRIMARY KEY NOT NULL AUTO_INCREMENT UNIQUE,";
            createGameTable += "F_gameId int, FOREIGN KEY (F_gameId) REFERENCES games(P_gameId),";
            createGameTable += "topicName VARCHAR(20),";
            createGameTable += "dateCreated DATE)";

            m_statement = m_connection.prepareStatement(createGameTable);
            m_statement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void deleteTables() throws SQLException
    {
        String dropUsersTable = "DROP TABLE IF EXISTS " + USERS_TABLE;
        m_statement = m_connection.prepareStatement(dropUsersTable);
        m_statement.executeUpdate();

        String dropGamesTable = "DROP TABLE IF EXISTS " + GAMES_TABLE;
        m_statement = m_connection.prepareStatement(dropGamesTable);
        m_statement.executeUpdate();

        String dropTopicsTable = "DROP TABLE IF EXISTS " + TOPICS_TABLE;
        m_statement = m_connection.prepareStatement(dropTopicsTable);
        m_statement.executeUpdate();

        String dropPostsTable = "DROP TABLE IF EXISTS " + POSTS_TABLE;
        m_statement = m_connection.prepareStatement(dropPostsTable);
        m_statement.executeUpdate();
    }
}
