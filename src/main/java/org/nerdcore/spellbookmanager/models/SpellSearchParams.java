package org.nerdcore.spellbookmanager.models;

public class SpellSearchParams {

    private String spellName;
    private String school;
    private String spellLevel;
    private String caster;
    private boolean ritualCasting;
    private boolean concentration;

    public SpellSearchParams(){}


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

    public String getSpellLevel() {
        return spellLevel;
    }

    public void setSpellLevel(String spellLevel) {
        this.spellLevel = spellLevel;
    }

    public String getCaster() {
        return caster;
    }

    public void setCaster(String caster) {
        this.caster = caster;
    }

    public boolean isEmpty(){
        if(!this.spellName.equals("")){
            return false;
        }
        if(!this.spellLevel.equals("")){
            return false;
        }
        if(!this.school.equals("")){
            return false;
        }
        if(!this.caster.equals("")){
            return false;
        }
        if(this.concentration || this.ritualCasting){
            return false;
        }
        return true;
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
