package by.artem_zakharov.user.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@ComponentScan("by.artem_zakharov.user")
@PropertySource("classpath:database/database.properties")
public class ApplicationConfig {

    @Value("${db.driver}")
    private String driver;
    @Value("${db.url}")
    private String url;
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;

    @Bean
    @Primary
    public DataSource dataSource(){
        HikariConfig hikariConfig = hikariConfig();
        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
        return dataSource;
    }

    private HikariConfig hikariConfig(){
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(driver);
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);

        return hikariConfig;
    }

    @Bean(name = "testDatabase")
    public DataSource initTestSchema(){
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();

        EmbeddedDatabase testDatabase = builder
                                            .setType(EmbeddedDatabaseType.H2)
                                            .addScript("classpath:database/schema.sql")
                                            .addScript("classpath:database/test-data.sql")
                                            .build();

        return testDatabase;
    }
}
