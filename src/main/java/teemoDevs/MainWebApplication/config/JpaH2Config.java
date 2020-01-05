package teemoDevs.MainWebApplication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * H2 DB를 사용하는 JpaRepository에 대한 설정
 * MultipleDataSource를 사용하려면 JavaConfig을 사용해야 함
 *
 * TODO : 개발, 상용환경(Heroku)에서 사용하도록 변경
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {"teemoDevs.MainWebApplication.util"})
public class JpaH2Config {
    @Autowired
    private Environment env;

    @Primary
    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource.db2")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder, @Qualifier("dataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = builder
                .dataSource(dataSource)
                .packages("teemoDevs.MainWebApplication.web.community.model")
                .persistenceUnit("customer")
                .build();
        em.setJpaProperties(additionalJpaProperties());
        return em;
    }

    @Primary
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    private Properties additionalJpaProperties() {
        Properties properties = new Properties();

        // DDL 옵션 설정
        properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));

        // H2 Dialect 설정
        properties.setProperty("hibernate.dialect", env.getProperty("spring.datasource.db2.hibernate.dialect"));

        // SQL 출력
        properties.setProperty("hibernate.show_sql", env.getProperty("spring.jpa.show-sql"));

        // SQL 포매팅
        properties.setProperty("hibernate.format_sql", env.getProperty("spring.jpa.properties.hibernate.format_sql"));

        // SQL 코멘트 추가
        properties.setProperty("hibernate.use_sql_comments", env.getProperty("spring.jpa.properties.hibernate.use_sql_comments"));
        return properties;
    }
}