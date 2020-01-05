package teemoDevs.MainWebApplication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
 * MySQL DB를 사용하는 JpaRepository에 대한 설정
 * MultipleDataSource를 사용하려면 JavaConfig을 사용해야 함
 *
 * TODO : 개발환경에서만 사용하도록 변경 : Heroku에 MySQL이 없음음 */
@Configuration                  // 설정 클래스로 지정
@EnableTransactionManagement    // 트랜잭션 사용 설정
@EnableJpaRepositories(         // JpaRepository를 사용하도록 설정
        // 사용할 EntityManagerFactory Bean
        entityManagerFactoryRef = "userEntityManagerFactory",

        // 사용할 TransactionManager Bean
        transactionManagerRef = "userTransactionManager",

        // 이 설정을 사용할 JpaRepository를 포함하는 패키지명
        basePackages = {"teemoDevs.MainWebApplication.web.community.repository"})
public class JpaMySQLConfig {
    // yml을 읽어옴
    @Autowired
    private Environment env;

    @Bean(name = "userDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mainwebapplicationdb")
    public DataSource userDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "userEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean userEntityManagerFactory(
            EntityManagerFactoryBuilder builder, @Qualifier("userDataSource") DataSource userDataSource) {
        LocalContainerEntityManagerFactoryBean em = builder
                .dataSource(userDataSource)
                .packages("teemoDevs.MainWebApplication.web.community.model")
                .persistenceUnit("user")
                .build();
        em.setJpaProperties(additionalJpaProperties());
        return em;
    }

    @Bean(name = "userTransactionManager")
    public PlatformTransactionManager userTransactionManager(
            @Qualifier("userEntityManagerFactory") EntityManagerFactory userEntityManagerFactory) {
        return new JpaTransactionManager(userEntityManagerFactory);
    }

    private Properties additionalJpaProperties() {
        Properties properties = new Properties();

        // DDL 옵션 설정
        properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));

        // MySQL 8 Dialect 설정
        properties.setProperty("hibernate.dialect", env.getProperty("spring.datasource.mainwebapplicationdb.hibernate.dialect"));

        // SQL 출력
        properties.setProperty("hibernate.show_sql", env.getProperty("spring.jpa.show-sql"));

        // SQL 포매팅
        properties.setProperty("hibernate.format_sql", env.getProperty("spring.jpa.properties.hibernate.format_sql"));

        // SQL 코멘트 추가
        properties.setProperty("hibernate.use_sql_comments", env.getProperty("spring.jpa.properties.hibernate.use_sql_comments"));
        return properties;
    }

}