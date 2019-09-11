package org.nerdcore.spellbookmanager;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.nerdcore.spellbookmanager.models.SearchParams;
import org.nerdcore.spellbookmanager.models.Spell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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


    @PostMapping("/search")
    public String directoryWithSearch(@ModelAttribute("searchParams")SearchParams searchParams){
        System.out.print(searchParams);
        return "redirect:";

    }


    @RequestMapping("/")
    public ModelAndView showDirectory(){
        ModelAndView model = new ModelAndView("spelldirectory");

        model.addObject("schoolList", schoolList);
        model.addObject("spells", SpellJSONProcesser.getAllSpellsAsList());
        model.addObject("searchParams", new SearchParams());
        return model;
    }

    //after new spell data is posted, redirect to directory again.
    @PostMapping("/add-spell")
    @Transactional
    public String addSpellThenDirectory(@ModelAttribute Spell newSpell){

        SpellJSONProcesser.writeNewSpellToJSON(newSpell);
        return "redirect:";
    }

    @PostMapping("/edit-spell")
    @Transactional
    public String editSpellThenDirectory(@ModelAttribute Spell spellToEdit){
        SpellJSONProcesser.editSpell(spellToEdit);
        return "redirect:";
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
    public String deleteSpell(@RequestParam("spellname")String spellToDelete){
        System.out.println(spellToDelete);
        SpellJSONProcesser.deleteSpell(spellToDelete);
        return "redirect:";
    }

}
