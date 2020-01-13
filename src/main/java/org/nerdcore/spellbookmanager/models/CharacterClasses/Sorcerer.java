package org.nerdcore.spellbookmanager.models.CharacterClasses;

import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("unused")
public class Sorcerer extends CharacterClass {
    Sorcerer(){
        this.setClassName("Sorcerer");
        this.addSubclassChoice("Draconic Bloodline");
        this.addSubclassChoice("Wild Magic");
        this.setHitDieValue(6);

        classFeatures.put(2, new ArrayList<>(Arrays.asList("Font of Magic") ) );
        classFeatures.put(3, new ArrayList<>(Arrays.asList("Metamagic") ) );
        classFeatures.put(4, new ArrayList<>(Arrays.asList("Ability Score Improvement") ) );
        classFeatures.put(5, new ArrayList<>(Arrays.asList() ) );
        classFeatures.put(7, new ArrayList<>(Arrays.asList() ) );
        classFeatures.put(8, new ArrayList<>(Arrays.asList("Ability Score Improvement") ) );
        classFeatures.put(9, new ArrayList<>(Arrays.asList() ) );
        classFeatures.put(10, new ArrayList<>(Arrays.asList("Metamagic") ) );
        classFeatures.put(11, new ArrayList<>(Arrays.asList() ) );
        classFeatures.put(12, new ArrayList<>(Arrays.asList("Ability Score Improvement") ) );
        classFeatures.put(13, new ArrayList<>(Arrays.asList() ) );
        classFeatures.put(15, new ArrayList<>(Arrays.asList() ) );
        classFeatures.put(16, new ArrayList<>(Arrays.asList("Ability Score Improvement") ) );
        classFeatures.put(17, new ArrayList<>(Arrays.asList("Metamagic") ) );
        classFeatures.put(19, new ArrayList<>(Arrays.asList("Ability Score Improvement") ) );
        classFeatures.put(20, new ArrayList<>(Arrays.asList("Sorcerous Restoration") ) );
    }

    public void setSubclass(String str){
        switch(str){
            case "Draconic Bloodline":
                this.setSubclassName("Draconic Bloodline");
                classFeatures.put(1, new ArrayList<>(Arrays.asList("Spellcasting", "Dragon Ancestor", "Draconic Resilience") ) );
                classFeatures.put(6, new ArrayList<>(Arrays.asList("Elemental Affinity") ) );
                classFeatures.put(14, new ArrayList<>(Arrays.asList("Dragon Wings") ) );
                classFeatures.put(18, new ArrayList<>(Arrays.asList("Draconic Presence") ) );
                break;
            case "Wild Magic":
                this.setSubclassName("Wild Magic");
                classFeatures.put(1, new ArrayList<>(Arrays.asList("Spellcasting", "Wild Magic Surge", "Tides of Chaos") ) );
                classFeatures.put(6, new ArrayList<>(Arrays.asList("Bend Luck") ) );
                classFeatures.put(14, new ArrayList<>(Arrays.asList("Controlled Chaos") ) );
                classFeatures.put(18, new ArrayList<>(Arrays.asList("Spell Bombardment") ) );
                break;
        }
    }

    public int getSorceryPoints(){
        if(this.getClassLevel()>1){
            return this.getClassLevel();
        } return 0;
    }
}
