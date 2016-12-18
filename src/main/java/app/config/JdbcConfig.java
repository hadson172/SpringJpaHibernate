package app.config;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;


@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
public class JdbcConfig {

    @Resource
    public Environment env;


    @Bean
    @Profile("prod")
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(env.getProperty("dataSource.driverClassName"));
        ds.setUrl(env.getProperty("dataSource.Url"));
        ds.setUsername(env.getProperty("dataSource.username"));
        ds.setPassword(env.getProperty("dataSource.password"));
        return ds;

    }

    @Bean
    @Profile("test")
    public DataSource embeddedDataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .build();
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource ds, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
        emfb.setDataSource(ds);
        emfb.setJpaVendorAdapter(jpaVendorAdapter);
        emfb.setPackagesToScan("app.model");
        return emfb;
    }


    @Bean
    @Profile("test")
    public JpaVendorAdapter jpaVendorAdapterEmbedded() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.H2);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);
        adapter.setDatabasePlatform(env.getProperty("dataSource.testDialect"));
        return adapter;
    }


    @Bean
    @Profile("prod")
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.POSTGRESQL);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);
        adapter.setDatabasePlatform(env.getProperty("dataSource.dialect"));

        return adapter;
    }


    @Bean
    public BeanPostProcessor beanPostProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }


    @Bean
    public JpaTransactionManager jpaTransactionManager(EntityManagerFactory em) {
        return new JpaTransactionManager(em);
    }


}
