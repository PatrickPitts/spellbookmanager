package org.nerdcore.spellbookmanager;

import org.nerdcore.spellbookmanager.models.SearchParams;
import org.nerdcore.spellbookmanager.models.Spell;

import java.util.ArrayList;
import java.util.List;



public class SpellFilter {

    public static List<Spell> getFilteredSpellList(SearchParams params){
        List<Spell> temp = new ArrayList<>();
        List<Spell> sorted = SpellJSONProcesser.getAllSpellsAsList();

        if(!params.getSpellName().equals("")){
            for(Spell spell : sorted){
                if(spell.getName().contains(params.getSpellName())){
                    temp.add(spell);
                }
            }
            sorted=new ArrayList<>(temp);
            temp.clear();
        }

        if(!params.getSchool().equals("")){
            for(Spell spell : sorted){
                if(spell.getSchool().equals(params.getSchool())){
                    temp.add(spell);
                }
            }
            sorted = new ArrayList<>(temp);
            temp.clear();
        }




        return sorted;
    }

    public static List<Spell> getSpellListByName(String name){
        List<Spell> filtered = new ArrayList<>();

        for(Spell spell : SpellJSONProcesser.getAllSpellsAsList()){
            if(spell.getName().contains(name)){
                filtered.add(spell);
            }
        }
        return filtered;
    }

}
