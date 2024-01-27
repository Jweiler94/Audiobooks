package JW.projects.Audiobooks.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DataSourceConfig {
    public DataSourceConfig() {
    }

    @Bean
    public DataSource datasource(@Value("${spring.datasource.password}") String password, @Value("${spring.datasource.username}") String username) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/Audiobooks");
        dataSource.setPassword(password);
        dataSource.setUsername(username);
        return dataSource;
    }
}
