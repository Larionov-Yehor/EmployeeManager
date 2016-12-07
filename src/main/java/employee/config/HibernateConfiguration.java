package employee.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.net.URISyntaxException;
import java.util.Properties;
/**
 * Created by employee on 12/7/16.
 */
@Configuration
@EnableTransactionManagement
@PropertySource(value = "classpath:hibernate.properties")
public class HibernateConfiguration {


    @Value("${db.url}")
    String dataBaseUrl;

    @Value("${db.username}")
    String dataBaseUser;

    @Value("${db.password}")
    String dataBasePass;

    @Value("${db.driver}")
    String dbDriver;


    @Autowired
    private Environment environment;


    @Bean
    public DataSource dataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUsername(dataBaseUser);
        dataSource.setPassword(dataBasePass);
        dataSource.setUrl(dataBaseUrl);
        dataSource.setDriverClassName(dbDriver);


        return dataSource;
    }
    @Bean
    public LocalSessionFactoryBean sessionFactory() {

        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("employee.model");
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        sessionFactory.setHibernateProperties(properties);

        return sessionFactory;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {

        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }


}