package org.nerdcore.spellbookmanager.config;

//import org.nerdcore.spellbookmanager.LoginDatabaseManager;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//import java.util.ArrayList;
//import java.util.List;


//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception{
//        http.authorizeRequests().antMatchers("/new-user")
//                .permitAll().anyRequest().authenticated()
//                .and()
//                .formLogin().loginPage("/login")
//                .permitAll()
//                .and()
//                .logout().permitAll();
//    }
//
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth.userDetailsService(inMemoryUserDetailsManager());
//        for(UserDetails u : LoginDatabaseManager.getUsersAsList()){
//            auth.inMemoryAuthentication().withUser(u.getUsername()).password(u.getPassword()).roles(u.getAuthorities().toString());
//        }
//    }
//
//
//    //@Bean
//    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
//
//        List<UserDetails> users = new ArrayList<>();
//
//        try{
//            users = LoginDatabaseManager.getUsersAsList();
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//
//
//        return new InMemoryUserDetailsManager(users);
//    }
//
//}
