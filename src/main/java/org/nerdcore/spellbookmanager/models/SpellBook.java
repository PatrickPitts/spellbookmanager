package org.nerdcore.spellbookmanager.models;

import java.util.List;

public class SpellBook {

    private String spellbookName;
    private String casterClass = "";
    private List<String> listOfSpells;

    public String getSpellbookName() {
        return spellbookName;
    }

    public void setSpellbookName(String spellbookName) {
        this.spellbookName = spellbookName;
    }

    public String getCasterClass() {
        return casterClass;
    }

    public void setCasterClass(String casterClass) {
        this.casterClass = casterClass;
    }

    public List<String> getListOfSpells() {
        return listOfSpells;
    }

    public void setListOfSpells(List<String> listOfSpells) {
        this.listOfSpells = listOfSpells;
    }



}
