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


//    @Bean
//    CommandLineRunner initDatabase(SpellRepository repository){
//        return args -> {
//            repository.save(new Spell("Fireball", "Blows up everything","Evocation", 3));
//            repository.save(new Spell("Teleport", "Get the hell out of there", "Conjuration", 7));
//            repository.save(new Spell("Magic Missile", "Bad damage, but guaranteed to hit.", "Evocation", 0));
//            repository.save(new Spell("Goodberry", "Delicious berries","Transmutation",1));
//            repository.save(new Spell("Fire Bolt", "Wizards auto-attack", "Evocation", 0));
//        };
//    }



}
