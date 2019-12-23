package org.nerdcore.spellbookmanager;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.nerdcore.spellbookmanager.models.*;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.ArrayList;

@RestController
public class SpellDirectoryController {

    private ArrayList<String> schoolList = new ArrayList<String>() {
        {
            add("Abjuration");
            add("Conjuration");
            add("Divination");
            add("Enchantment");
            add("Evocation");
            add("Illusion");
            add("Necromancy");
            add("Transmutation");
        }
    };

    @RequestMapping("/login")
    public ModelAndView loginToSpellbookManager(@ModelAttribute("basicUser") BasicUser basicUser,
                                                HttpServletResponse response,
                                                HttpServletRequest request
    ) throws SQLException {
        HttpSession session = request.getSession();
        if (LoginDatabaseManager.checkUser(basicUser)) {
            session.setAttribute("username", basicUser.getUsername());
        } else {
            session.setAttribute("invalidUsername", true);
        }
        return new ModelAndView("redirect:");
    }

    @RequestMapping("/new-user")
    public ModelAndView newUser(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("newuser");
        model.addObject("basicUser", new BasicUser());
        return model;
    }

    @PostMapping("/new-user")
    public ModelAndView addNewUser(@ModelAttribute("basicUser") BasicUser user,
                                   HttpServletRequest request, HttpServletResponse response) throws SQLException {

        if (LoginDatabaseManager.addUser(user)) {
            request.getSession().setAttribute("username", user.getUsername());
            return new ModelAndView("redirect:");
        }
        ModelAndView model = new ModelAndView("newuser");
        model.addObject("repeatUsername", true);
        return model;


    }


    @RequestMapping("/logout")
    public ModelAndView logoutFromSpellbookManager(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("username");
        return new ModelAndView("redirect:");
    }

    @RequestMapping("/add-to-spellbook")
    public ModelAndView addSpellToSpellbookThenSpellbookDisplay(@RequestParam("spellname") String spellname,
                                                                @RequestParam("spellbookID") int spellbookID,
                                                                HttpServletRequest request, ModelMap model) throws SQLException {
        SpellDatabaseManager.addSingleSpellToSpellBook(spellname, spellbookID);
        return new ModelAndView("redirect:view-spellbook?spellbookID=" + spellbookID);
    }

    @RequestMapping("/spells-for-spellbook")
    public ModelAndView addSpellToSpellbook(@RequestParam("spellbookID") int spellbookID, HttpServletRequest request) throws SQLException {
        ModelAndView model = new ModelAndView("spelldirectory");
        model.addObject("schoolList", schoolList);
        model.addObject("casterList", SpellDatabaseManager.getAllCastersAsList());
        model.addObject("basicUser", new BasicUser());
        model.addObject("spellbook", SpellDatabaseManager.getSpellbookBySpellbookID(spellbookID));
        model.addObject("spells", SpellDatabaseManager.getAllSpellsAsListAlphabetized());
        model.addObject("spellSearchParams", new SpellSearchParams());

        return model;
    }

    @PostMapping("/spells-for-spellbook")
    public ModelAndView addSpellToSpellbookWithSearch(@ModelAttribute("spellSearchParams") SpellSearchParams spellSearchParams,
                                                      @RequestParam("spellbookID") int spellbookID,
                                                      HttpServletRequest request) throws SQLException {
        ModelAndView model = new ModelAndView("spelldirectory");
        model.addObject("schoolList", schoolList);
        model.addObject("casterList", SpellDatabaseManager.getAllCastersAsList());

        model.addObject("spellbook", SpellDatabaseManager.getSpellbookBySpellbookID(spellbookID));
        model.addObject("spells", SpellDatabaseManager.searchForSpells(spellSearchParams));
        model.addObject("spellSearchParams", spellSearchParams);

        return model;
    }

    @RequestMapping("/delete-spellbook")
    public ModelAndView deleteSpellBookRedirectToSpellbookDirectory(@RequestParam("spellbookID") int spellbookID,
                                                                    HttpServletRequest request) throws SQLException {

        SpellDatabaseManager.deleteSpellbookBySpellbookID(spellbookID);

        return new ModelAndView("redirect:spellbook-directory");

    }


    @RequestMapping("/spellbook-directory")
    public ModelAndView viewSpellbookManager(HttpServletRequest request) throws SQLException {
        ModelAndView model = new ModelAndView("spellbookdirectory");
        model.addObject("spellbookList", SpellDatabaseManager.getSpellbookListByUsername((String) request.getSession().getAttribute("username")));
        model.addObject("spellBookSearchParams", new SpellBookSearchParams());
        return model;
    }

    @RequestMapping("/view-spellbook")
    public ModelAndView displaySingleSpellbook(@RequestParam("spellbookID") int spellbookID,
                                               HttpServletRequest request) throws SQLException {

        //TODO: Handle when too many parameters are passed through URL
        ModelAndView model = new ModelAndView("displayspellbook");
        model.addObject("spellbook", SpellDatabaseManager.getSpellbookBySpellbookID(spellbookID));

        return model;
    }

    @RequestMapping("/add-spellbook")
    public ModelAndView addNewSpellBook(HttpServletRequest request) throws SQLException {
        ModelAndView model = new ModelAndView("addspellbook");
        model.addObject("casterList", SpellDatabaseManager.getAllCastersAsList());
        model.addObject("spellBook", new SpellBook());
        return model;
    }

    @PostMapping("/add-spellbook")
    public ModelAndView addSpellbookAndDisplay(@ModelAttribute("spellbookToAdd") SpellBook spellBook,
                                               HttpServletRequest request) throws SQLException {

        if (SpellDatabaseManager.addSpellbookToDatabase(spellBook, (String) request.getSession().getAttribute("username"))) {

            return new ModelAndView("redirect:spellbook-directory");
        }
        ModelAndView model = new ModelAndView("addspellbook");

        model.addObject("spellbookNameAlreadyExists", true);
        model.addObject("casterList", SpellDatabaseManager.getAllCastersAsList());
        model.addObject("spellBook", new SpellBook());
        return model;

    }


    @PostMapping("/search")
    public ModelAndView directoryWithSearch(@ModelAttribute("spellSearchParams") SpellSearchParams spellSearchParams,
                                            HttpServletRequest request) throws SQLException {
        ModelAndView model = new ModelAndView("spelldirectory");

        model.addObject("basicUser", new BasicUser());
        model.addObject("casterList", SpellDatabaseManager.getAllCastersAsList());
        model.addObject("schoolList", schoolList);
        model.addObject("spells", SpellDatabaseManager.searchForSpells(spellSearchParams));
        model.addObject("spellSearchParams", spellSearchParams);
        if (request.getAttribute("spellbook") != null) {
            System.out.println("Not Null!!!");
        }
        return model;

    }

    @RequestMapping("/spells-to-caster")
    public ModelAndView assignSpellsToCasters(HttpServletRequest request) throws SQLException {

        ModelAndView model = new ModelAndView("spelltocaster");

        model.addObject("spells", SpellDatabaseManager.getAllSpellsAsListAlphabetized());
        model.addObject("casterSpellList", new CasterSpellList());

        return model;
    }

    @PostMapping("/spells-to-caster")
    public ModelAndView addSpellsToCasterList(@ModelAttribute("casterSpellList") CasterSpellList spellNames,
                                              HttpServletRequest request) throws SQLException {

        //this line actively inputs the casterSpellList object data into the database.
        //SpellDatabaseManager.addSpellsToCasterTable(spellNames);

        return new ModelAndView("redirect:/");
    }


    @RequestMapping("/")
    public ModelAndView showDirectory(HttpServletRequest request) throws SQLException {


        ModelAndView model = new ModelAndView("spelldirectory");
        model.addObject("basicUser", new BasicUser());
        model.addObject("casterList", SpellDatabaseManager.getAllCastersAsList());
        model.addObject("schoolList", schoolList);
        model.addObject("spells", SpellDatabaseManager.getAllSpellsAsListAlphabetized());
        model.addObject("spellSearchParams", new SpellSearchParams());
        return model;
    }

    //after new spell data is posted, redirect to directory again.
    @PostMapping("/add-spell")
    @Transactional
    public ModelAndView addSpellThenDirectory(@ModelAttribute("spell") Spell newSpell, HttpServletRequest request) throws SQLException {

        if (Jsoup.isValid(newSpell.getName(), Whitelist.relaxed()) &&
                Jsoup.isValid(newSpell.getCastingTime(), Whitelist.relaxed()) &&
                Jsoup.isValid(newSpell.getDescription(), Whitelist.relaxed()) &&
                Jsoup.isValid(newSpell.getMaterialComponents(), Whitelist.relaxed()) &&
                Jsoup.isValid(newSpell.getRange(), Whitelist.relaxed()) &&
                Jsoup.isValid(newSpell.getSource(), Whitelist.relaxed()) &&
                Jsoup.isValid(newSpell.getDuration(), Whitelist.relaxed())) {

            //This line adds a new spell to the database
            //SpellDatabaseManager.addSingleSpellToSpellCollection(newSpell);
            return new ModelAndView("redirect:/");
        } else {
            //TODO: Update use of error message. Implement a single method to populate several error messages at once,
            ModelAndView model = new ModelAndView("redirect:" + request.getHeader("Referer"));
            model.addObject("invalidInputError", "Input contains invalid text: HTML formatting and structure tags only!");
            model.addObject("spell", newSpell);
            return model;
        }


    }

    @PostMapping("/edit-spell")
    @Transactional
    public ModelAndView editSpellThenDirectory(@ModelAttribute("spell") Spell spellToEdit, HttpServletRequest request) throws SQLException {
        if (Jsoup.isValid(spellToEdit.getName(), Whitelist.relaxed()) &&
                Jsoup.isValid(spellToEdit.getCastingTime(), Whitelist.relaxed()) &&
                Jsoup.isValid(spellToEdit.getDescription(), Whitelist.relaxed()) &&
                Jsoup.isValid(spellToEdit.getMaterialComponents(), Whitelist.relaxed()) &&
                Jsoup.isValid(spellToEdit.getRange(), Whitelist.relaxed()) &&
                Jsoup.isValid(spellToEdit.getSource(), Whitelist.relaxed()) &&
                Jsoup.isValid(spellToEdit.getDuration(), Whitelist.relaxed())) {
            //This line updates the database with the updated spell.
            //SpellDatabaseManager.editSpell(spellToEdit);
            return new ModelAndView("redirect:/");
        } else {
            //TODO: Update use of error message in application.properties. Implement a single method to populate several error messages at once,
            //TODO: passing error boolean
            ModelAndView model = new ModelAndView("redirect:" + request.getHeader("Referer"));

            model.addObject("invalidInputError", "Input contains invalid text: HTML formatting and structure tags only!");
            model.addObject("spell", spellToEdit);
            return model;
        }
    }

    @RequestMapping(value = "/spell", params = {"spellname", "spellbookID"})
    public ModelAndView displaySpellForSpellbook(@RequestParam("spellname") String spellname,
                                                 @RequestParam("spellbookID") int spellbookID,
                                                 HttpServletRequest request) throws SQLException {
        Spell spell = SpellDatabaseManager.getSingleSpellBySpellName(spellname);
        SpellBook spellbook = SpellDatabaseManager.getSpellbookBySpellbookID(spellbookID);
        if (spell == null) {
            return new ModelAndView("spellsearcherror");
        }

        ModelAndView model = new ModelAndView("displayspell");
        model.addObject("spell", spell);
        model.addObject("spellbook", spellbook);
        return model;

    }

    @RequestMapping(value = "/spell", params = "spellname", method = RequestMethod.GET)
    public ModelAndView displaySpell(@RequestParam("spellname") String spellname,
                                     HttpServletRequest request) throws SQLException {

        Spell spell = SpellDatabaseManager.getSingleSpellBySpellName(spellname);
        if (spell == null) {
            return new ModelAndView("spellsearcherror");
        }

        ModelAndView model = new ModelAndView("displayspell");
        model.addObject("spell", spell);

        String casterString = "";
        for (String str : SpellDatabaseManager.getAllSpellCasters(spell.getId())) casterString += str + ", ";

        casterString = casterString.substring(0, casterString.length() - 2);

        model.addObject("casterList", casterString);
        return model;

    }


    @RequestMapping("/edit-spell")
    public ModelAndView editSpell(@RequestParam("spellname") String spellname) throws SQLException {
        ModelAndView model = new ModelAndView("addspell");
        Spell spell = SpellDatabaseManager.getSingleSpellBySpellName(spellname);
        model.addObject("schoolList", schoolList);
        model.addObject("spell", spell);
        model.addObject("action", "edit-spell");
        model.addObject("title", "Edit Spell: " + spellname);
        return model;
    }


    @RequestMapping("/add-spell")
    public ModelAndView addSpellPage(HttpServletRequest request) {

        Spell spellToPass = new Spell();
        spellToPass.setSource("Custom");
        spellToPass.setMaterialComponents("None");
        spellToPass.setDuration("Instantaneous");

        ModelAndView model = new ModelAndView("addspell");

        model.addObject("spell", spellToPass);
        model.addObject("schoolList", schoolList);
        model.addObject("action", "add-spell");
        model.addObject("title", "Add a Spell to the Spell Directory");

        return model;
    }

    @RequestMapping("/delete-spell")
    public ModelAndView deleteSpell(@RequestParam("spellname") String spellToDelete, HttpServletRequest request) throws SQLException {

        //Deletes a spell from the database
        //SpellDatabaseManager.deleteSpellByName(spellToDelete);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/drop-spell-from-spellbook", params = {"spellname", "spellbookID"})
    public ModelAndView dropSpellFromSpellbook(@RequestParam("spellname") String spellname,
                                               @RequestParam("spellbookID") int spellbookID,
                                               HttpServletRequest request) throws SQLException {

        SpellDatabaseManager.removeSpellFromSpellbook(spellname, spellbookID);
        ModelAndView model = new ModelAndView("redirect:view-spellbook?spellbookID=" + spellbookID);
        model.addObject("spellbook", SpellDatabaseManager.getSpellbookBySpellbookID(spellbookID));
        return model;
    }

}
