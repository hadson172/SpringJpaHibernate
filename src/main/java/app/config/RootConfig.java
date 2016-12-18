package app.config;


import org.springframework.context.annotation.*;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@Import(JdbcConfig.class)
@ComponentScan(basePackages = "app", excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {EnableWebMvc.class, Controller.class})})
public class RootConfig {

    @Bean
    public PersistenceAnnotationBeanPostProcessor paPostProcessor() {
        return new PersistenceAnnotationBeanPostProcessor();
    }
}
