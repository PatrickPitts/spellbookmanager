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

    @RequestMapping("/")
    public ModelAndView showDirectory(){
        //TODO: update from repository to JSON data store

        ModelAndView model = new ModelAndView("spelldirectory");
        model.addObject("spells", SpellJSONProcesser.getAllSpellsAsList());
        return model;
    }





    //TODO: Commit added spells to JSON database on save




    //after new spell data is posted, redirect to directory again.
    @PostMapping("/")
    @Transactional
    public ModelAndView addSpellThenDirectory(@ModelAttribute Spell newSpell){

        SpellJSONProcesser.writeNewSpellToJSON(newSpell);

        //Saves the newly created spell to the repository AND JSON file
        //Saves to repository first, to ensure data is stored in memory
        //then takes the updated in-memory spell data array, and writes the whole list to the persistent JSON file
//        repository.saveAndFlush(newSpell);
//        SpellJSONProcesser.writeSpellListToJSON(repository.findAll());



        ModelAndView model = new ModelAndView("spelldirectory");

        model.addObject("spells", SpellJSONProcesser.getAllSpellsAsList());
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


//
//
//
//        if(spellList.size()==1){
//            ModelAndView model = new ModelAndView("displayspell");
//
//            model.addObject("spell", spellList.get(0));
//            return model;
//        } else if (spellList.size()==0){
//            ModelAndView model = new ModelAndView("spellsearcherror");
//            return model;
//        } else {
//            ModelAndView model = new ModelAndView("displayspell");
//            return model;
//        }
    }

    @RequestMapping("/add-spell")
    public ModelAndView addSpellPage() {
        ModelAndView model = new ModelAndView("addspell");

        List<String> schoolList = new ArrayList<>();
        schoolList.add("Abjuration");
        schoolList.add("Conjuration");
        schoolList.add("Divination");
        schoolList.add("Enchantment");
        schoolList.add("Evocation");
        schoolList.add("Illusion");
        schoolList.add("Necromancy");
        schoolList.add("Transmutation");

        Spell spellToPass = new Spell();
        spellToPass.setSource("Player's Handbook");
        spellToPass.setMaterialComponents("None");
        spellToPass.setDuration("Instantaneous");


        model.addObject("spell", spellToPass);
        model.addObject("schoolList", schoolList);

        return model;
    }

}
