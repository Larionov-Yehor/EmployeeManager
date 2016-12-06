package employee.config;

import employee.properties.AppProperties;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
 * Created by employee on 12/6/16.
 */
@Configuration
@EnableTransactionManagement
@ComponentScan({"employee.config"})
@PropertySource(value = "classpath:hibernate.properties")

public class HibernateConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public Properties getHibernateProperties() throws URISyntaxException {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", getAppProperties().getDialect());
        properties.put("{hibernate.show_sql}", getAppProperties().getShowSql());
        properties.put("${hibernate.hbm2ddl.auto}", getAppProperties().getHibernateHbm2Dll());
        return properties;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() throws URISyntaxException {
        final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[]{"todolist.model"});

        sessionFactory.setHibernateProperties(getHibernateProperties());
        return sessionFactory;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }

    @Bean
    public DataSource dataSource() throws URISyntaxException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource(getAppProperties().getUrl(),
                getAppProperties().getUserName(),
                getAppProperties().getUserPassword());
        dataSource.setDriverClassName(getAppProperties().getDriver());
        return dataSource;
    }

    @Bean
    public AppProperties getAppProperties() throws URISyntaxException {
        return new AppProperties(System.getenv("DATABASE_URL"));

    }
}