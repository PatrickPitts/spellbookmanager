package org.nerdcore.spellbookmanager.models;

public class SearchParams {

    private String spellName;
    private String school;
    private int spellLevel;
    private String caster;
    private boolean ritualCasting;
    private boolean concentration;

    public SearchParams(){}


    public boolean isRitualCasting() {
        return ritualCasting;
    }

    public void setRitualCasting(boolean ritualCasting) {
        this.ritualCasting = ritualCasting;
    }

    public boolean isConcentration() {
        return concentration;
    }

    public void setConcentration(boolean concentration) {
        this.concentration = concentration;
    }

    public String getSpellName() {
        return spellName;
    }

    public void setSpellName(String spellName) {
        this.spellName = spellName;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public int getSpellLevel() {
        return spellLevel;
    }

    public void setSpellLevel(int spellLevel) {
        this.spellLevel = spellLevel;
    }

    public String getCaster() {
        return caster;
    }

    public void setCaster(String caster) {
        this.caster = caster;
    }

    @Override
    public String toString(){
        return String.format(
                "Spell Name: " + this.spellName + " Spell Level: " + this.spellLevel
                + " Caster: " + this.caster + " School: " + this.school
                + " Concentration: " + this.concentration + " Ritual: " + this.ritualCasting
        );
    }

}
