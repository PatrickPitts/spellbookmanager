package org.nerdcore.spellbookmanager.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpellBook {

    private String spellbookName;
    private String casterClass = "";
    private int spellbookID;
    private List<Spell> listOfSpells;
    private List<String> listOfSpellNames;


    public List<String> getListOfSpellNames() {
        return listOfSpellNames;
    }

    public void setListOfSpellNames(List<String> listOfSpellNames) {
        this.listOfSpellNames = listOfSpellNames;
    }

    public SpellBook(ResultSet rs) throws SQLException {
        this.spellbookName = rs.getString("spellBookName");
        this.casterClass = rs.getString("casterClass");
        this.spellbookID = rs.getInt("spellbookID");
    }

    public SpellBook() {
    }

    public int getSpellbookID() {
        return spellbookID;
    }

    public void setSpellbookID(int spellbookID) {
        this.spellbookID = spellbookID;
    }

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

    public List<Spell> getListOfSpells() {
        return listOfSpells;
    }

    public void setListOfSpells(List<Spell> listOfSpells) {

        this.listOfSpells = listOfSpells;
        this.listOfSpellNames = new ArrayList<>();

        for (Spell spell : this.listOfSpells) {
            this.listOfSpellNames.add(spell.getName());
        }
    }


}
