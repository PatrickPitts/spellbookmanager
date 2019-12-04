package org.nerdcore.spellbookmanager;

import org.nerdcore.spellbookmanager.models.BasicUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@Controller
public class LoginController {

    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;

    public LoginController(InMemoryUserDetailsManager inMemoryUserDetailsManager){
        this.inMemoryUserDetailsManager = inMemoryUserDetailsManager;
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/new-user")
    public String newUser(Model model) {
        model.addAttribute("basicUser", new BasicUser());
        return "newuser";
    }

    @PostMapping("/new-user")
    public String updateUsers(@ModelAttribute("basicUser") BasicUser user, Model model, HttpServletRequest request, HttpServletResponse response) throws SQLException {
        if (LoginDatabaseManager.addUser(user)) {

            UserDetails userDetails = User.withDefaultPasswordEncoder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .roles("USER").build();
            inMemoryUserDetailsManager.createUser(userDetails);
            return "login";
        }
        model.addAttribute("repeatUsername", true);
        return "newuser";

    }

    @RequestMapping("/logout")
    public String logoutUser(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);

        }
        return "redirect:/";
    }

}
