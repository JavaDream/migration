package info.dreamcoder.jdbc;

import info.dreamcoder.finterface.IDBExecute;
import info.dreamcoder.finterface.IDBQuery;

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

    public void execute(String sql) {
        this.execute(sql, (result) -> {});
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
                Objects.requireNonNull(statement).close();
            } catch (SQLException e) {
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
            e.printStackTrace();
        } finally {
            try {
                Objects.requireNonNull(resultSet).close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                Objects.requireNonNull(statement).close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
