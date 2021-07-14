package info.dreamcoder.jdbc;

import java.sql.*;
import java.util.Objects;

public class Database {
    Connection dbConnection;

    public Database(String url, String username, String password) throws SQLException {
        this.dbConnection = DriverManager.getConnection(url, username, password);
    }

    public boolean isValid() {
        try {
            return this.dbConnection.isValid(1000);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void close() throws SQLException {
        this.dbConnection.close();
    }

    public boolean execute(String sql) {
        Statement statement = null;
        try {
            statement = dbConnection.createStatement();
            statement.execute(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                Objects.requireNonNull(statement).close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public ResultSet query(String sql) {
        Statement statement = null;
        try {
            statement = dbConnection.createStatement();
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}