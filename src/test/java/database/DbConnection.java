package database;

import configUtility.ConfigReader;
import configUtility.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static DbConnection instance;
    private Connection connection;

    public DbConnection() throws SQLException {
        createConnection();
    }

    private void createConnection() throws SQLException {
        Configuration config = ConfigReader.readConfig("src/test/resources/generalConfiguration.xml");
        connection = DriverManager.getConnection(getPreparedUrl(config), config.getDatabaseConfig().getUsername(), config.getDatabaseConfig().getPassword());
    }

    public static synchronized DbConnection getInstance() throws SQLException {
        if(instance == null) {
            instance = new DbConnection();
        }

        return instance;
    }

    private static String getPreparedUrl(Configuration configuration){
        return "jdbc:mysql://localhost:" + configuration.getDatabaseConfig().getPort() +
                "/" + configuration.getDatabaseConfig().getDatabase() +
                "?allowPublicKeyRetrieval=true&useSSL=false";
    }

    public Connection getConnection(){
        return connection;
    }

}
