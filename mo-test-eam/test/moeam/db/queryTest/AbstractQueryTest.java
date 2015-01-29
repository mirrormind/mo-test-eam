package moeam.db.queryTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import moeam.db.query.driver.DatabaseDriver;

import org.junit.AfterClass;
import org.junit.BeforeClass;

public abstract class AbstractQueryTest
{
    private static final String USERS_TABLE = "users";
    private static final String GAMES_TABLE = "games";
    private static final String TOPICS_TABLE = "topics";
    private static final String POSTS_TABLE = "posts";

    /** Create a new DB connection */
    private static Connection m_connection = new DatabaseDriver().openConnection();
    private static PreparedStatement m_statement;

    /** Create all the tables and populate them with default data */
    @BeforeClass
    public static void createTables()
    {
        // Drop tables before recreating them
        deleteTables();

        // Create tables
        createUsersTable();
        createGamesTable();
        createTopicsTable();
        createPostsTable();

        // Create table data
        createUser();
        createGame();
        createTopic();
        createPost();
    }

    private static void createUsersTable()
    {
        try
        {
            // Create table
            String createUsersTable = "CREATE TABLE " + USERS_TABLE + " (";
            createUsersTable += "P_userId int PRIMARY KEY NOT NULL AUTO_INCREMENT UNIQUE,";
            createUsersTable += "userName VARCHAR(30),";
            createUsersTable += "password VARCHAR(30))";

            m_statement = m_connection.prepareStatement(createUsersTable);
            m_statement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private static void createGamesTable()
    {
        try
        {
            // Create table
            String createGamesTable = "CREATE TABLE " + GAMES_TABLE + " (";
            createGamesTable += "P_gameId int PRIMARY KEY NOT NULL AUTO_INCREMENT UNIQUE,";
            createGamesTable += "gameName VARCHAR(15),";
            createGamesTable += "companyName VARCHAR(15),";
            createGamesTable += "downloadLink VARCHAR(50),";
            createGamesTable += "description VARCHAR(300))";

            m_statement = m_connection.prepareStatement(createGamesTable);
            m_statement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private static void createTopicsTable()
    {
        try
        {
            // Create table
            String createTopicsTable = "CREATE TABLE " + TOPICS_TABLE + " (";
            createTopicsTable += "P_topicId int PRIMARY KEY NOT NULL AUTO_INCREMENT UNIQUE,";
            createTopicsTable += "F_gameId int, FOREIGN KEY (F_gameId) REFERENCES games(P_gameId),";
            createTopicsTable += "topicName VARCHAR(20),";
            createTopicsTable += "dateCreated DATE)";

            m_statement = m_connection.prepareStatement(createTopicsTable);
            m_statement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private static void createPostsTable()
    {
        try
        {
            // Create table
            String createPostsTable = "CREATE TABLE " + POSTS_TABLE + " (";
            createPostsTable += "P_postId int PRIMARY KEY NOT NULL AUTO_INCREMENT UNIQUE,";
            createPostsTable += "F_topicId int, FOREIGN KEY (F_topicId) REFERENCES topics(P_topicId),";
            createPostsTable += "F_userId int, FOREIGN KEY (F_userId) REFERENCES users(P_userId),";
            createPostsTable += "contents VARCHAR(500),";
            createPostsTable += "dateCreated DATE)";

            m_statement = m_connection.prepareStatement(createPostsTable);
            m_statement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private static void createUser()
    {
        final String userName = "User1";
        final String password = "User1Password";

        try
        {
            String insertUserData = "INSERT INTO users (userName, password) VALUES (?, ?)";
            m_statement = m_connection.prepareStatement(insertUserData);
            m_statement.setString(1, userName);
            m_statement.setString(2, password);
            m_statement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private static void createGame()
    {
        final String gameName = "Game1";
        final String companyName = "Company1";
        final String downloadLink = "http://Game1.Company1.com/";
        final String description = "Description1";

        try
        {
            String insertGameData = "INSERT INTO games (gameName, companyName, downloadLink, description) VALUES (?, ?, ?, ?)";
            m_statement = m_connection.prepareStatement(insertGameData);
            m_statement.setString(1, gameName);
            m_statement.setString(2, companyName);
            m_statement.setString(3, downloadLink);
            m_statement.setString(4, description);
            m_statement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private static void createTopic()
    {
        final int gameId = 1;
        final String topicName = "Topic1";
        final Date date = new Date();
        final java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        try
        {
            String insertGameData = "INSERT INTO topics (F_gameId, topicName, dateCreated) VALUES (?, ?, ?)";
            m_statement = m_connection.prepareStatement(insertGameData);
            m_statement.setInt(1, gameId);
            m_statement.setString(2, topicName);
            m_statement.setDate(3, sqlDate);
            m_statement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private static void createPost()
    {
        final int topicId = 1;
        final int userId = 1;
        final String contents = "Post1Content";
        final Date date = new Date();
        final java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        try
        {
            String insertPostData = "INSERT INTO posts (F_topicId, F_userId, contents, dateCreated) VALUES (?, ?, ?, ?)";
            m_statement = m_connection.prepareStatement(insertPostData);
            m_statement.setInt(1, topicId);
            m_statement.setInt(2, userId);
            m_statement.setString(3, contents);
            m_statement.setDate(4, sqlDate);
            m_statement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void deleteTables()
    {
        try
        {
            String dropPostsTable = "DROP TABLE IF EXISTS " + POSTS_TABLE;
            m_statement = m_connection.prepareStatement(dropPostsTable);
            m_statement.executeUpdate();

            String dropTopicsTable = "DROP TABLE IF EXISTS " + TOPICS_TABLE;
            m_statement = m_connection.prepareStatement(dropTopicsTable);
            m_statement.executeUpdate();

            String dropGamesTable = "DROP TABLE IF EXISTS " + GAMES_TABLE;
            m_statement = m_connection.prepareStatement(dropGamesTable);
            m_statement.executeUpdate();

            String dropUsersTable = "DROP TABLE IF EXISTS " + USERS_TABLE;
            m_statement = m_connection.prepareStatement(dropUsersTable);
            m_statement.executeUpdate();
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
