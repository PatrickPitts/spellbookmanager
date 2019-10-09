package org.nerdcore.spellbookmanager;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.nerdcore.spellbookmanager.models.SpellBookSearchParams;
import org.nerdcore.spellbookmanager.models.SpellSearchParams;
import org.nerdcore.spellbookmanager.models.Spell;
import org.nerdcore.spellbookmanager.models.SpellBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.ArrayList;

@Controller
public class SpellDirectoryController {

    @Autowired
    private SpellRepository repository;

    private ObjectMapper objMapper = new ObjectMapper();

    ArrayList<String> schoolList = new ArrayList<String>() {
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

    //TODO: Implement ModelMap data transfer more completely between servlet methods
    @RequestMapping("/add-to-spellbook")
    public String addSpellToSpellbookThenSpellbookDisplay(@RequestParam("spellname") String spellname,
                                                          @RequestParam("spellbookID") int spellbookID,
                                                          HttpServletRequest request, ModelMap model) throws SQLException {
        SpellDatabaseManager.addSingleSpellToSpellBook(spellname, spellbookID);
        return "redirect:view-spellbook?spellbookID=" + spellbookID;
    }

    @RequestMapping("/search-spells-for-spellbook")
    public String addSpellToSpellbook(@RequestParam("spellbookID") int spellbookID,HttpServletRequest request, ModelMap model) throws SQLException {
        //ModelAndView model = new ModelAndView("spelldirectory");

        //model.addObject("spellbookID", spellbookID);
        model.addAttribute("schoolList", schoolList);

        model.addAttribute("spellbook", SpellDatabaseManager.getSpellbookBySpellbookID(spellbookID));
        model.addAttribute("spells", SpellDatabaseManager.getAllSpellsAsListAlphabetized());
        model.addAttribute("spellSearchParams", new SpellSearchParams());

        return "spelldirectory";
    }


    @RequestMapping("/spellbook-directory")
    public String viewSpellbookManager(HttpServletRequest request, ModelMap model) throws SQLException {

        //ModelAndView model = new ModelAndView("spellbookdirectory");

        model.addAttribute("spellbookList", SpellDatabaseManager.getAllSpellbooksAsList());
        model.addAttribute("spellBookSearchParams", new SpellBookSearchParams());
        return "spellbookdirectory";
    }

    @RequestMapping("/view-spellbook")
    public String displaySingleSpellbook(@RequestParam("spellbookID") int spellbookID,
                                               HttpServletRequest request,
                                               ModelMap model) throws SQLException {

        //TODO: Handle when too many parameters are passed through URL

        //ModelAndView model = new ModelAndView("displayspellbook");
        model.addAttribute("spellbook", SpellDatabaseManager.getSpellbookBySpellbookID(spellbookID));

        return "displayspellbook";
    }

    @RequestMapping("/add-spellbook")
    public String addNewSpellBook(HttpServletRequest request, ModelMap model) throws SQLException {
        //ModelAndView model = new ModelAndView("addspellbook");
        model.addAttribute("casterList", SpellDatabaseManager.getAllCastersAsList());
        model.addAttribute("spellBook", new SpellBook());
        return "addspellbook";
    }

    @PostMapping("/add-spellbook")
    public String addSpellbookAndDisplay(@ModelAttribute("spellbookToAdd") SpellBook spellBook,
                                         HttpServletRequest request,
                                         ModelMap model) throws SQLException {

        SpellDatabaseManager.addSpellbookToDatabase(spellBook);
        //ModelAndView model = new ModelAndView("spellbookdirectory");
        return "spellbookdirectory";
    }


    @PostMapping("/search")
    public String directoryWithSearch(@ModelAttribute("spellSearchParams") SpellSearchParams spellSearchParams,
                                            HttpServletRequest request,
                                            ModelMap model) throws SQLException {

        //ModelAndView model = new ModelAndView("spelldirectory");
        model.addAttribute("schoolList", schoolList);
        model.addAttribute("spells", SpellDatabaseManager.searchForSpells(spellSearchParams));
        model.addAttribute("spellSearchParams", spellSearchParams);
        return "spelldirectory";

    }


    @RequestMapping("/")
    public String showDirectory(HttpServletRequest request, ModelMap model) throws SQLException {
        //ModelAndView model = new ModelAndView("spelldirectory");

        model.addAttribute("schoolList", schoolList);
        model.addAttribute("spells", SpellDatabaseManager.getAllSpellsAsListAlphabetized());
        model.addAttribute("spellSearchParams", new SpellSearchParams());
        return "spelldirectory";
    }

    //after new spell data is posted, redirect to directory again.
    @PostMapping("/add-spell")
    @Transactional
    public String addSpellThenDirectory(@ModelAttribute Spell newSpell, HttpServletRequest request, ModelMap model) throws SQLException {

        if (Jsoup.isValid(newSpell.getName(), Whitelist.relaxed()) &&
                Jsoup.isValid(newSpell.getCastingTime(), Whitelist.relaxed()) &&
                Jsoup.isValid(newSpell.getDescription(), Whitelist.relaxed()) &&
                Jsoup.isValid(newSpell.getMaterialComponents(), Whitelist.relaxed()) &&
                Jsoup.isValid(newSpell.getRange(), Whitelist.relaxed()) &&
                Jsoup.isValid(newSpell.getSource(), Whitelist.relaxed()) &&
                Jsoup.isValid(newSpell.getDuration(), Whitelist.relaxed())) {

            SpellDatabaseManager.addSingleSpellToSpellCollection(newSpell);
            return "redirect:";
        } else {
            //TODO: Update use of error message. Implement a single method to populate several error messages at once,
            //TODO: passing error boolean
            model.addAttribute("invalidInputError", "Input contains invalid text: HTML formatting and structure tags only!");
            model.addAttribute("spell", newSpell);
            return "redirect:" + request.getHeader("Referer");
        }


    }

    @PostMapping("/edit-spell")
    @Transactional
    public String editSpellThenDirectory(@ModelAttribute("spell") Spell spellToEdit,HttpServletRequest request, ModelMap model) throws SQLException {
        if (Jsoup.isValid(spellToEdit.getName(), Whitelist.relaxed()) &&
                Jsoup.isValid(spellToEdit.getCastingTime(), Whitelist.relaxed()) &&
                Jsoup.isValid(spellToEdit.getDescription(), Whitelist.relaxed()) &&
                Jsoup.isValid(spellToEdit.getMaterialComponents(), Whitelist.relaxed()) &&
                Jsoup.isValid(spellToEdit.getRange(), Whitelist.relaxed()) &&
                Jsoup.isValid(spellToEdit.getSource(), Whitelist.relaxed()) &&
                Jsoup.isValid(spellToEdit.getDuration(), Whitelist.relaxed())) {

            SpellDatabaseManager.editSpell(spellToEdit);
            return "redirect:";
        } else {
            //TODO: Update use of error message. Implement a single method to populate several error messages at once,
            //TODO: passing error boolean
            model.addAttribute("invalidInputError", "Input contains invalid text: HTML formatting and structure tags only!");
            model.addAttribute("spell", spellToEdit);
            return "redirect:" + request.getHeader("Referer");
        }
    }


    @RequestMapping(value = "/spell", method = RequestMethod.GET)
    public String displaySpell(@RequestParam("spellname") String spellname,HttpServletRequest request, ModelMap model) throws SQLException {

        Spell spell = SpellDatabaseManager.getSingleSpellBySpellName(spellname);
        if (spell == null) {
            return "spellsearcherror";
        }
        //ModelAndView model = new ModelAndView("displayspell");
        model.addAttribute("spell", spell);
        return "displayspell";

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
    public String addSpellPage(HttpServletRequest request, ModelMap model) {
        //ModelAndView model = new ModelAndView("addspell");

        Spell spellToPass = new Spell();
        spellToPass.setSource("Player's Handbook");
        spellToPass.setMaterialComponents("None");
        spellToPass.setDuration("Instantaneous");


        model.addAttribute("spell", spellToPass);
        model.addAttribute("schoolList", schoolList);
        model.addAttribute("action", "add-spell");
        model.addAttribute("title", "Add a Spell to the Spell Directory");

        return "addspell";
    }

    @RequestMapping("/delete-spell")
    public String deleteSpell(@RequestParam("spellname") String spellToDelete,HttpServletRequest request, ModelMap model) throws SQLException {
        SpellDatabaseManager.deleteSpellByName(spellToDelete);
        return "redirect:";
    }

}
