package org.nerdcore.spellbookmanager.models;

import java.util.ArrayList;
import java.util.List;

public class CasterSpellList {
    private List<String> listOfSpellNames = new ArrayList<>();
    private String casterClassName;


    public String getCasterClassName() {
        return casterClassName;
    }

    public void setCasterClassName(String casterClassName) {
        this.casterClassName = casterClassName;
    }

    public List<String> getListOfSpellNames() {
        return listOfSpellNames;
    }

    public void setListOfSpellNames(List<String> listOfSpellNames) {
        this.listOfSpellNames = listOfSpellNames;
    }

    public void addStringToCasterSpellList(String spellName){
        this.listOfSpellNames.add(spellName);
    }
}
