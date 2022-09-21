package ru.pavelluytov.testworkalpha.initialiser;

import lombok.experimental.UtilityClass;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

/**
 * Код закомментирован из-за ошибки flay-way
 * всплыла несовместимость миграций Н2 и postgres
 */
@UtilityClass
public class Postgres {

//    @Container
//    public static PostgreSQLContainer<?> postgresqlContainer = new PostgreSQLContainer<>("postgres:12.3")
//            .withDatabaseName("mydb")
//            .withUsername("postgres")
//            .withPassword("postgres")
//            .withInitScript("db.sql");
//
//    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
//        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
//            TestPropertyValues.of(
//                    "spring.datasource.url=" + postgresqlContainer.getJdbcUrl(),
//                    "spring.datasource.username=" + postgresqlContainer.getUsername(),
//                    "spring.datasource.password=" + postgresqlContainer.getPassword()
//            ).applyTo(configurableApplicationContext.getEnvironment());
//        }
//    }
}
