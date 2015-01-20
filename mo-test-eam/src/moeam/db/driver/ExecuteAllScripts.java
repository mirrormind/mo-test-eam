package moeam.db.driver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.Test;

@Deprecated
public class ExecuteAllScripts
{

    private final String DB_URL = "jdbc:mysql://localhost:3306/test";
    private final String DB_USERNAME = "root";
    private final String DB_PASSWORD = "01001";

    /**
     * Run this test to create all tables for the database
     * 
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @Test
    public void executeAllScripts() throws ClassNotFoundException, SQLException {
        // Get driver to connect to the DB
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME,
                DB_PASSWORD);

        // Get all script names and execute each script
        ArrayList<String> scripts = getScriptNames();
        for (String script : scripts)
        {

            // Try read a script and execute it
            try
            {
                ScriptRunner scriptRunner = new ScriptRunner(conn);
                BufferedReader reader = new BufferedReader(new FileReader(
                        script));
                scriptRunner.runScript(reader);
            }
            catch (FileNotFoundException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    // TODO directory.listFiles() returns null so this class doesn't actually
    // work yet
    /** Get all script names in this package */
    private ArrayList<String> getScriptNames() {
        Class<ExecuteAllScripts> thisClass = ExecuteAllScripts.class;
        Package thisPackage = thisClass.getPackage();
        String packageName = thisPackage.getName();

        // Locate all files in this package directory
        File directory = new File(packageName);
        File[] files = directory.listFiles();

        // Retrieve class names
        ArrayList<String> classNames = new ArrayList<String>();
        for (File file : files)
        {
            if (file.getName().endsWith(".sql"))
                classNames.add(file.getName());
        }
        return classNames;
    }
}
