package environment;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.DirectoryResourceAccessor;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@ContextConfiguration(classes = IntegrationEnvironment.EnvironmentConfiguration.class)
public abstract class IntegrationEnvironment {


    @Configuration
    static class EnvironmentConfiguration{
        @Bean
        public DataSource dataSource() {
            return DataSourceBuilder.create()
                    .url(POSTGRES_CONTAINER.getJdbcUrl())
                    .username(POSTGRES_CONTAINER.getUsername())
                    .password(POSTGRES_CONTAINER.getPassword())
                    .build();
        }

        @Bean
        public JdbcTemplate jdbcTemplate(DataSource dataSource){
            return new JdbcTemplate(dataSource);
        }

    }

    static final String IMAGE_VERSION = "postgres:15";

    static final PostgreSQLContainer POSTGRES_CONTAINER;

    static {
        POSTGRES_CONTAINER = new PostgreSQLContainer(IMAGE_VERSION)
                .withDatabaseName("scrapper")
                .withUsername("nyaninyayo")
                .withPassword("2281337");


        POSTGRES_CONTAINER.start();
        executeMigrations();
    }

    private static void executeMigrations() {
        try (Connection conn = DriverManager.getConnection(IntegrationEnvironment.POSTGRES_CONTAINER.getJdbcUrl(), IntegrationEnvironment.POSTGRES_CONTAINER.getUsername(),
                IntegrationEnvironment.POSTGRES_CONTAINER.getPassword())) {
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(conn));
            Path changeLogFile = new File("").toPath().toAbsolutePath().getParent().resolve("migrations");
            Liquibase liquibase = new Liquibase("master.xml",new DirectoryResourceAccessor(changeLogFile), database);
            liquibase.update(new Contexts(), new LabelExpression());
        } catch (SQLException | LiquibaseException e) {
            throw new RuntimeException("Failed to execute migrations", e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Changelog file not found",e);
        }
    }

    public static void stopContainer() {
        POSTGRES_CONTAINER.stop();
    }

}
