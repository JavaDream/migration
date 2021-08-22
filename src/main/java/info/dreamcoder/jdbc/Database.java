package info.dreamcoder.jdbc;



import info.dreamcoder.config.Config;
import info.dreamcoder.config.Factory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class Database {
    private static final Logger logger = LogManager.getLogger(Database.class);
    private Connection dbConnection;
    private Statement statement;

    public Database() throws SQLException {
        Config config = Factory.getConfig();

        this.dbConnection = DriverManager.getConnection(
                config.getDbUrl(),
                config.getDbUserName(),
                config.getPassword()
        );

        this.statement = dbConnection.createStatement();
    }

    public boolean isValid() {
        try {
            return this.dbConnection.isValid(1000);
        } catch (SQLException e) {
            logger.error(e.toString());
        }
        return false;
    }

    public void close() {
        if(dbConnection != null) {
            try {
                this.dbConnection.close();
            } catch (SQLException e) {
                logger.error(e.toString());
            }
        }
    }

    public boolean execute(String sql) {
        try {
            this.statement.execute(sql);
        } catch (SQLException e) {
            logger.error(e.toString());
            return false;
        }
        return true;
    }

    public ResultSet query(String sql) throws SQLException {
        return statement.executeQuery(sql);
    }
}
