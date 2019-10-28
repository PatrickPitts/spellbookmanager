package org.nerdcore.spellbookmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

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
