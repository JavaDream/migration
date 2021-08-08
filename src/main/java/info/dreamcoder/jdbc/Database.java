package info.dreamcoder.jdbc;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class Database {
    private static final Logger logger = LogManager.getLogger(Database.class);
    private Connection dbConnection;
    private Statement statement;

    public Database(String url, String username, String password) throws SQLException {
        this.dbConnection = DriverManager.getConnection(url, username, password);
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
