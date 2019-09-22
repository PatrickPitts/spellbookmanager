package org.nerdcore.spellbookmanager;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.nerdcore.spellbookmanager.models.SpellBookSearchParams;
import org.nerdcore.spellbookmanager.models.SpellSearchParams;
import org.nerdcore.spellbookmanager.models.Spell;
import org.nerdcore.spellbookmanager.models.SpellBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.ArrayList;

@Controller
public class SpellDirectoryController {

    @Autowired
    private SpellRepository repository;

    private ObjectMapper objMapper = new ObjectMapper();

    ArrayList<String> schoolList = new ArrayList<String>(){
        {
            add("Abjuration");
            add("Conjuration");
            add("Divination");
            add("Enchantment");
            add("Evocation");
            add("Illusion");
            add("Necromancy");
            add("Transmutation");
        }};

    //Without URL mapping, this displays a list of all saved spellbooks
    @RequestMapping("/manage-spellbooks")
    public ModelAndView viewSpellbookManager() throws SQLException{
        ModelAndView model = new ModelAndView("spellbooks");

        model.addObject("spellbookList", SpellDatabaseManager.getAllSpellbooksAsList());
        model.addObject("spellBookSearchParams", new SpellBookSearchParams());
        return model;
    }

    //With the appropriate URL mapping, this method redirects the view to display content
    //associated with the named spellbook
    @RequestMapping("/view-spellbook")
    public ModelAndView displaySingleSpellbook(@RequestParam("spellbookName")String spellbookName){

        System.out.println(spellbookName);
        ModelAndView model = new ModelAndView("displayspellbook");
        //model.addObject()


        return model;
    }

    @RequestMapping("/add-spellbook")
    public ModelAndView addNewSpellBook() throws SQLException{
        ModelAndView model = new ModelAndView("addspellbook");
        model.addObject("casterList", SpellDatabaseManager.getAllCastersAsList());
        model.addObject("spellBook", new SpellBook());
        return model;
    }

    @PostMapping("/add-spellbook")
    public ModelAndView addSpellbookAndDisplay(@ModelAttribute("spellbookToAdd")SpellBook spellBook) throws SQLException{

        SpellDatabaseManager.addSpellbookToDatabase(spellBook);
        ModelAndView model = new ModelAndView("spellbooks");


        return model;
    }


    @PostMapping("/search")
    public ModelAndView directoryWithSearch(@ModelAttribute("spellSearchParams") SpellSearchParams spellSearchParams) throws SQLException {

        ModelAndView model = new ModelAndView("spelldirectory");
        model.addObject("schoolList", schoolList);
        model.addObject("spells", SpellDatabaseManager.searchForSpells(spellSearchParams));
        model.addObject("spellSearchParams", spellSearchParams);
        return model;

    }


    @RequestMapping("/")
    public ModelAndView showDirectory(){
        ModelAndView model = new ModelAndView("spelldirectory");

        model.addObject("schoolList", schoolList);
        model.addObject("spells", SpellDatabaseManager.getAllSpellsAsList());
        model.addObject("spellSearchParams", new SpellSearchParams());
        return model;
    }

    //after new spell data is posted, redirect to directory again.
    @PostMapping("/add-spell")
    @Transactional
    public String addSpellThenDirectory(@ModelAttribute Spell newSpell){
        SpellDatabaseManager.addSingleSpellToDatabase(newSpell);
        //SpellJSONProcesser.writeNewSpellToJSON(newSpell);
        return "redirect:";
    }

    @PostMapping("/edit-spell")
    @Transactional
    public String editSpellThenDirectory(@ModelAttribute Spell spellToEdit) throws SQLException{
        SpellDatabaseManager.editSpell(spellToEdit);
        return "redirect:";
    }


    @RequestMapping(value = "/spell", method = RequestMethod.GET)
    public ModelAndView displaySpell(@RequestParam("spellname") String spellname) {

        Spell spell = SpellDatabaseManager.getSingleSpellBySpellName(spellname);
        //Spell spell = SpellJSONProcesser.getSingleSpellByName(spellname);

        if(spell==null){
            return new ModelAndView("spellsearcherror");
        }
        ModelAndView model = new ModelAndView("displayspell");
        model.addObject("spell", spell);
        return model;

    }

    @RequestMapping("/edit-spell")
    public ModelAndView editSpell(@RequestParam("spellname")String spellname){
        ModelAndView model = new ModelAndView("addspell");
        Spell spell = SpellDatabaseManager.getSingleSpellBySpellName(spellname);
        //Spell spell = SpellJSONProcesser.getSingleSpellByName(spellname);

        model.addObject("schoolList", schoolList);
        model.addObject("spell", spell);
        model.addObject("action","edit-spell");
        model.addObject("title", "Edit Spell: "+ spellname);
        return model;
    }


    @RequestMapping("/add-spell")
    public ModelAndView addSpellPage() {
        ModelAndView model = new ModelAndView("addspell");

        Spell spellToPass = new Spell();
        spellToPass.setSource("Player's Handbook");
        spellToPass.setMaterialComponents("None");
        spellToPass.setDuration("Instantaneous");


        model.addObject("spell", spellToPass);
        model.addObject("schoolList", schoolList);
        model.addObject("action","add-spell");
        model.addObject("title", "Add a Spell to the Spell Directory");

        return model;
    }

    @RequestMapping("/delete-spell")
    public String deleteSpell(@RequestParam("spellname")String spellToDelete) throws SQLException{
        //System.out.println(spellToDelete);
        SpellDatabaseManager.deleteSpellByName(spellToDelete);
        return "redirect:";
    }

}
