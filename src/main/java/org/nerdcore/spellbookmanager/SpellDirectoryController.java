package org.nerdcore.spellbookmanager;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.nerdcore.spellbookmanager.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    @RequestMapping("/add-to-spellbook")
    public String addSpellToSpellbookThenSpellbookDisplay(@RequestParam("spellname") String spellname,
                                                          @RequestParam("spellbookID") int spellbookID,
                                                          HttpServletRequest request, ModelMap model) throws SQLException {
        SpellDatabaseManager.addSingleSpellToSpellBook(spellname, spellbookID);
        return "redirect:view-spellbook?spellbookID=" + spellbookID;
    }

    @RequestMapping("/spells-for-spellbook")
    public String addSpellToSpellbook(@RequestParam("spellbookID") int spellbookID, HttpServletRequest request, ModelMap model) throws SQLException {
        model.addAttribute("schoolList", schoolList);
        model.addAttribute("casterList", SpellDatabaseManager.getAllCastersAsList());

        model.addAttribute("spellbook", SpellDatabaseManager.getSpellbookBySpellbookID(spellbookID));
        model.addAttribute("spells", SpellDatabaseManager.getAllSpellsAsListAlphabetized());
        model.addAttribute("spellSearchParams", new SpellSearchParams());

        return "spelldirectory";
    }

    @PostMapping("/spells-for-spellbook")
    public String addSpellToSpellbookWithSearch(@ModelAttribute("spellSearchParams") SpellSearchParams spellSearchParams,
                                                @RequestParam("spellbookID") int spellbookID,
                                                HttpServletRequest request, ModelMap model) throws SQLException {
        model.addAttribute("schoolList", schoolList);
        model.addAttribute("casterList", SpellDatabaseManager.getAllCastersAsList());

        model.addAttribute("spellbook", SpellDatabaseManager.getSpellbookBySpellbookID(spellbookID));
        model.addAttribute("spells", SpellDatabaseManager.searchForSpells(spellSearchParams));
        model.addAttribute("spellSearchParams", spellSearchParams);

        return "spelldirectory";
    }

    @RequestMapping("/delete-spellbook")
    public String deleteSpellBookRedirectToSpellbookDirectory(@RequestParam("spellbookID") int spellbookID,
                                                              HttpServletRequest request,
                                                              ModelMap model) throws SQLException {

        SpellDatabaseManager.deleteSpellbookBySpellbookID(spellbookID);

        return "redirect:spellbook-directory";

    }


    @RequestMapping("/spellbook-directory")
    public String viewSpellbookManager(HttpServletRequest request, ModelMap model) throws SQLException {
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        model.addAttribute("spellbookList", SpellDatabaseManager.getSpellbookListByUsername(username));
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

        if(SpellDatabaseManager.addSpellbookToDatabase(spellBook, SecurityContextHolder.getContext().getAuthentication().getName())){

            return "redirect:spellbook-directory";
        }
        System.out.println("Its all going wrong!");
        model.addAttribute("spellbookNameAlreadyExists", true);
        model.addAttribute("casterList", SpellDatabaseManager.getAllCastersAsList());
        model.addAttribute("spellBook", new SpellBook());
        return "addspellbook";

    }


    @PostMapping("/search")
    public String directoryWithSearch(@ModelAttribute("spellSearchParams") SpellSearchParams spellSearchParams,
                                      HttpServletRequest request,
                                      ModelMap model) throws SQLException {

        model.addAttribute("casterList", SpellDatabaseManager.getAllCastersAsList());
        model.addAttribute("schoolList", schoolList);
        model.addAttribute("spells", SpellDatabaseManager.searchForSpells(spellSearchParams));
        model.addAttribute("spellSearchParams", spellSearchParams);
        if (request.getAttribute("spellbook") != null) {
            System.out.println("Not Null!!!");
        }
        return "spelldirectory";

    }

    @RequestMapping("/spells-to-caster")
    public String assignSpellsToCasters(HttpServletRequest request, ModelMap model) throws SQLException {

        model.addAttribute("spells", SpellDatabaseManager.getAllSpellsAsListAlphabetized());
        model.addAttribute("casterSpellList", new CasterSpellList());

        return "spelltocaster";
    }

    @PostMapping("/spells-to-caster")
    public String addSpellsToCasterList(@ModelAttribute("casterSpellList") CasterSpellList spellNames,
                                        HttpServletRequest request, ModelMap model) throws SQLException {

        SpellDatabaseManager.addSpellsToCasterTable(spellNames);

        return "redirect:spell-directory";
    }


    @RequestMapping("/spell-directory")
    public String showDirectory(HttpServletRequest request, ModelMap model) throws SQLException {
        //ModelAndView model = new ModelAndView("spelldirectory");
        model.addAttribute("casterList", SpellDatabaseManager.getAllCastersAsList());
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
            return "redirect:spell-directory";
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
    public String editSpellThenDirectory(@ModelAttribute("spell") Spell spellToEdit, HttpServletRequest request, ModelMap model) throws SQLException {
        if (Jsoup.isValid(spellToEdit.getName(), Whitelist.relaxed()) &&
                Jsoup.isValid(spellToEdit.getCastingTime(), Whitelist.relaxed()) &&
                Jsoup.isValid(spellToEdit.getDescription(), Whitelist.relaxed()) &&
                Jsoup.isValid(spellToEdit.getMaterialComponents(), Whitelist.relaxed()) &&
                Jsoup.isValid(spellToEdit.getRange(), Whitelist.relaxed()) &&
                Jsoup.isValid(spellToEdit.getSource(), Whitelist.relaxed()) &&
                Jsoup.isValid(spellToEdit.getDuration(), Whitelist.relaxed())) {

            SpellDatabaseManager.editSpell(spellToEdit);
            return "redirect:spell-directory";
        } else {
            //TODO: Update use of error message. Implement a single method to populate several error messages at once,
            //TODO: passing error boolean
            model.addAttribute("invalidInputError", "Input contains invalid text: HTML formatting and structure tags only!");
            model.addAttribute("spell", spellToEdit);
            return "redirect:" + request.getHeader("Referer");
        }
    }

    @RequestMapping(value = "/spell", params = {"spellname", "spellbookID"})
    public String displaySpellForSpellbook(@RequestParam("spellname") String spellname,
                                           @RequestParam("spellbookID") int spellbookID,
                                           HttpServletRequest request, ModelMap model) throws SQLException {
        Spell spell = SpellDatabaseManager.getSingleSpellBySpellName(spellname);
        SpellBook spellbook = SpellDatabaseManager.getSpellbookBySpellbookID(spellbookID);
        if (spell == null) {
            return "spellsearcherror";
        }
        model.addAttribute("spell", spell);
        model.addAttribute("spellbook", spellbook);
        return "displayspell";

    }

    @RequestMapping(value = "/spell", params = "spellname", method = RequestMethod.GET)
    public String displaySpell(@RequestParam("spellname") String spellname,
                               HttpServletRequest request, ModelMap model) throws SQLException {

        Spell spell = SpellDatabaseManager.getSingleSpellBySpellName(spellname);
        if (spell == null) {
            return "spellsearcherror";
        }

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
        spellToPass.setSource("Custom");
        spellToPass.setMaterialComponents("None");
        spellToPass.setDuration("Instantaneous");


        model.addAttribute("spell", spellToPass);
        model.addAttribute("schoolList", schoolList);
        model.addAttribute("action", "add-spell");
        model.addAttribute("title", "Add a Spell to the Spell Directory");

        return "addspell";
    }

    @RequestMapping("/delete-spell")
    public String deleteSpell(@RequestParam("spellname") String spellToDelete, HttpServletRequest request, ModelMap model) throws SQLException {
        SpellDatabaseManager.deleteSpellByName(spellToDelete);
        return "redirect:spell-directory";
    }

    @RequestMapping(value="/drop-spell-from-spellbook", params={"spellname", "spellbookID"})
    public String dropSpellFromSpellbook(@RequestParam("spellname")String spellname,
                                         @RequestParam("spellbookID")int spellbookID,
                                         HttpServletRequest request,
                                         ModelMap model) throws SQLException{

        SpellDatabaseManager.removeSpellFromSpellbook(spellname);
        model.addAttribute("spellbook", SpellDatabaseManager.getSpellbookBySpellbookID(spellbookID));
        return "redirect:view-spellbook?spellbookID=" + spellbookID;
    }

}
