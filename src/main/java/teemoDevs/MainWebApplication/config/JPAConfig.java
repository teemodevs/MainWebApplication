package teemoDevs.MainWebApplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;

@Configuration
public class JPAConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("21478965");
        dataSource.setUrl("jdbc:mysql://localhost:3306/mainwebapplication?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false");

        return dataSource;
    }
}
