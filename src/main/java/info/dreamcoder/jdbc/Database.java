package info.dreamcoder.jdbc;


import info.dreamcoder.finterface.IDBExecute;
import info.dreamcoder.finterface.IDBQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.Objects;

public class Database {
    private static final Logger logger = LogManager.getLogger(Database.class);
    private Connection dbConnection;

    public Database(String url, String username, String password) throws SQLException {
        this.dbConnection = DriverManager.getConnection(url, username, password);
    }

    public boolean isValid() {
        try {
            return this.dbConnection.isValid(1000);
        } catch (SQLException e) {
            logger.error(e.toString());
        }
        return false;
    }

    public void close() throws SQLException {
        this.dbConnection.close();
    }

    public void execute(String sql) {
        this.execute(sql, result -> {});
    }

    public void execute(String sql, IDBExecute action) {
        Statement statement = null;
        try {
            statement = dbConnection.createStatement();
            statement.execute(sql);
            action.run(true);
        } catch (SQLException e) {
            action.run(false);
        } finally {
            try {
                if(statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error(e);
            }
        }
    }

    public void query(String sql, IDBQuery query) {
        ResultSet resultSet = null;
        Statement statement = null;
        try {
            statement = dbConnection.createStatement();
            resultSet = statement.executeQuery(sql);
            query.run(resultSet);
        } catch (SQLException e) {
            logger.error(e);
        } finally {
            try {
                if(resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.error(e);
            }

            try {
                if(statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error(e);
            }
        }
    }
}
