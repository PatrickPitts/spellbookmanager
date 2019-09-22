package org.nerdcore.spellbookmanager;

import org.nerdcore.spellbookmanager.models.Spell;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpellbookManagerApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpellbookManagerApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpellbookManagerApplication.class, args);
    }
}
