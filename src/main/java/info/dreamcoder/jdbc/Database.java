package info.dreamcoder.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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

    public void execute(String sql) throws SQLException {
        Statement statement = dbConnection.createStatement();
        statement.execute(sql);
    }


}
