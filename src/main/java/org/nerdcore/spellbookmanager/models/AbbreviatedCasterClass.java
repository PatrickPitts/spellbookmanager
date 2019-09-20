package org.nerdcore.spellbookmanager.models;

import java.util.Map;

public class AbbreviatedCasterClass {

    private String className;
    private int level;
    private Map<Integer, Integer[]> spellSlotsPerSpellLevel;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Map<Integer, Integer[]> getSpellSlotsPerSpellLevel() {
        return spellSlotsPerSpellLevel;
    }

    public void setSpellSlotsPerSpellLevel(Map<Integer, Integer[]> spellSlotsPerSpellLevel) {
        this.spellSlotsPerSpellLevel = spellSlotsPerSpellLevel;
    }
}
