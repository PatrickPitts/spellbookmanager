package org.nerdcore.spellbookmanager.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SpellBook {

    private String spellbookName;
    private String casterClass = "";
    private List<String> listOfSpells;

    public SpellBook(ResultSet rs) throws SQLException {
        this.spellbookName = rs.getString("spellBookName");
        this.casterClass = rs.getString("casterClass");
        //TODO: implement spell list
    }

    public SpellBook(){}
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
