package org.nerdcore.spellbookmanager;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.nerdcore.spellbookmanager.models.Spell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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



    @RequestMapping("/")
    public ModelAndView showDirectory(){
        //TODO: update from repository to JSON data store

        ModelAndView model = new ModelAndView("spelldirectory");
        model.addObject("spells", SpellJSONProcesser.getAllSpellsAsList());
        return model;
    }





    //TODO: Commit added spells to JSON database on save




    //after new spell data is posted, redirect to directory again.
    @PostMapping("/add-spell")
    @Transactional
    public ModelAndView addSpellThenDirectory(@ModelAttribute Spell newSpell){

        SpellJSONProcesser.writeNewSpellToJSON(newSpell);

        ModelAndView model = new ModelAndView("spelldirectory");

        model.addObject("spells", SpellJSONProcesser.getAllSpellsAsList());
        return model;
    }

    @PostMapping("/edit-spell")
    @Transactional
    public ModelAndView editSpellThenDirectory(@ModelAttribute Spell spellToEdit){

        System.out.println("Editing a Spell!" + spellToEdit);
        ModelAndView model = new ModelAndView("spelldirectory");

        return model;
    }


    @RequestMapping(value = "/spell", method = RequestMethod.GET)
    public ModelAndView displaySpell(@RequestParam("spellname") String spellname) {

        Spell spell = SpellJSONProcesser.getSingleSpellByName(spellname);

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
        Spell spell = SpellJSONProcesser.getSingleSpellByName(spellname);

        model.addObject("spell", spell);
        model.addObject("action","edit-spell");
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

        return model;
    }

}
