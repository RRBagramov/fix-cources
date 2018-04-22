package ru.ivmiit.product.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.ivmiit.product.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 22.04.2018
 *
 * @author Robert Bagramov.
 */
@Configuration
@ComponentScan(basePackages = "ru.ivmiit.product")
@PropertySource(value = "/db/application.properties")
public class AppConfig {
    private final Environment environment;

    @Autowired
    public AppConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public DataSource getDataSource() {
        String jdbcURL = environment.getProperty("db.url");
        String jdbcUsername = environment.getProperty("db.username");
        String jdbcPassword = environment.getProperty("db.password");
        String jdbcDriverClassName = environment.getProperty("db.DriverClassName");

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(jdbcURL);
        dataSource.setUsername(jdbcUsername);
        dataSource.setPassword(jdbcPassword);
        dataSource.setDriverClassName(jdbcDriverClassName);

        return dataSource;
    }

    @Bean
    public Connection getConnection(DataSource dataSource) {

        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Bean
    public Session getSession() {
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration.setProperty("hibernate.connection.url", environment.getProperty("hibernate.connection.url"));
        configuration.setProperty("hibernate.connection.username", environment.getProperty("hibernate.connection.username"));
        configuration.setProperty("hibernate.connection.password", environment.getProperty("hibernate.connection.password"));
        configuration.setProperty("hibernate.connection.driver_class", environment.getProperty("hibernate.connection.driver_class"));
        configuration.setProperty("hibernate.dialect", environment.getProperty("hibernate.dialect"));
        configuration.setProperty("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
        configuration.setProperty("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));

        configuration.addAnnotatedClass(User.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        return sessionFactory.openSession();
    }

}
