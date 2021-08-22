package info.dreamcoder.config;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static com.github.stefanbirkner.systemlambda.SystemLambda.*;


class EnvConfigTest {
    private static Config config;

    @BeforeAll
    public static void createConfig() throws Exception {
        config = withEnvironmentVariable("MYSQL_URL", "MYSQL_URL_test")
                .and("MYSQL_USERNAME", "MYSQL_USERNAME_test")
                .and("MYSQL_PASSWORD", "MYSQL_PASSWORD_test")
                .execute(Factory::getConfig);
    }

    @Test
    void getDbUrl() throws Exception {
        assertThat(config.getDbUrl()).isEqualTo("MYSQL_URL_test");
    }

    @Test
    void getDbUserName() throws Exception {
        assertThat(config.getDbUserName()).isEqualTo("MYSQL_USERNAME_test");
    }

    @Test
    void getPassword() throws Exception {
        assertThat(config.getPassword()).isEqualTo("MYSQL_PASSWORD_test");
    }
}