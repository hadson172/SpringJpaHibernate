package app.config;


import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.*;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

@Configuration
@Import(JdbcConfig.class)
@ComponentScan(basePackages = "app", excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,value = {EnableWebMvc.class, Controller.class})})
public class RootConfig
{
    @Bean
    public PersistenceAnnotationBeanPostProcessor paPostProcessor()
    {
        return new PersistenceAnnotationBeanPostProcessor();
    }
}
