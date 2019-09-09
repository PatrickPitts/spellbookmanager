package org.nerdcore.spellbookmanager.config;

import javassist.ClassPath;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;

@Configuration
@EnableJpaRepositories("org.nerdcore.spellbookmanager")
public class JPAConfig {

    @Bean
    public Jackson2RepositoryPopulatorFactoryBean getRepositoryPopulator(){
        Jackson2RepositoryPopulatorFactoryBean factory = new Jackson2RepositoryPopulatorFactoryBean();
        factory.setResources(new Resource[]{new ClassPathResource("static/spellstore.json")});
        return factory;
    }

}
