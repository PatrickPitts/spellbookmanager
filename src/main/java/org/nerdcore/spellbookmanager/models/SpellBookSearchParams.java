package org.nerdcore.spellbookmanager.models;

public class SpellBookSearchParams {

    private String spellbookName;
    private String casterClass;
    private int spellbookRowID;

    public SpellBookSearchParams(){}


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

    public int getSpellbookRowID() {
        return spellbookRowID;
    }

    public void setSpellbookRowID(int spellbookRowID) {
        this.spellbookRowID = spellbookRowID;
    }
}
