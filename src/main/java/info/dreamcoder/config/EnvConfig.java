package info.dreamcoder.config;

public class EnvConfig implements Config {
    private String dbUrl = System.getenv("MYSQL_URL");
    private String dbUsername = System.getenv("MYSQL_USERNAME");
    private String dbPassword = System.getenv("MYSQL_PASSWORD");

    @Override
    public String getDbUrl() {
        return dbUrl;
    }

    @Override
    public String getDbUserName() {
        return dbUsername;
    }

    @Override
    public String getPassword() {
        return dbPassword;
    }
}
