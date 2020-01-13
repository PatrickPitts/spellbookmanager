package org.nerdcore.spellbookmanager.models.CharacterClasses;

import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("unused")
public class Rogue extends CharacterClass {
    Rogue(){

        this.setClassName("Rogue");
        this.addSubclassChoice("Thief");
        this.addSubclassChoice("Assassin");
        this.addSubclassChoice("Arcane Trickster");
        this.setHitDieValue(8);

        classFeatures.put(1, new ArrayList<>(Arrays.asList("Expertise", "Sneak Attack", "Thieve's Cant") ) );
        classFeatures.put(2, new ArrayList<>(Arrays.asList("Cunning Action") ) );
        classFeatures.put(4, new ArrayList<>(Arrays.asList("Ability Score Improvement") ) );
        classFeatures.put(5, new ArrayList<>(Arrays.asList("Uncanny Dodge") ) );
        classFeatures.put(6, new ArrayList<>(Arrays.asList("Expertise") ) );
        classFeatures.put(7, new ArrayList<>(Arrays.asList("Evasion") ) );
        classFeatures.put(8, new ArrayList<>(Arrays.asList("Ability Score Improvement") ) );
        classFeatures.put(10, new ArrayList<>(Arrays.asList("Ability Score Improvement") ) );
        classFeatures.put(11, new ArrayList<>(Arrays.asList("Reliable Talent") ) );
        classFeatures.put(12, new ArrayList<>(Arrays.asList("Ability Score Improvement") ) );
        classFeatures.put(14, new ArrayList<>(Arrays.asList("Blindsense") ) );
        classFeatures.put(15, new ArrayList<>(Arrays.asList("Slippery Mind") ) );
        classFeatures.put(16, new ArrayList<>(Arrays.asList("Ability Score Improvement") ) );
        classFeatures.put(18, new ArrayList<>(Arrays.asList("Elusive") ) );
        classFeatures.put(19, new ArrayList<>(Arrays.asList("Ability Score Improvement") ) );
        classFeatures.put(20, new ArrayList<>(Arrays.asList("Stroke of Luck") ) );
    }

    public void setSubclass(String str){
        switch(str){
            case "Thief":
                this.setSubclassName("Thief");
                classFeatures.put(3, new ArrayList<>(Arrays.asList("Fast Hands", "Second-Story Work") ) );
                classFeatures.put(9, new ArrayList<>(Arrays.asList("Supreme Sneak") ) );
                classFeatures.put(13, new ArrayList<>(Arrays.asList("Use Magic Device") ) );
                classFeatures.put(17, new ArrayList<>(Arrays.asList("Thief's Reflexes") ) );
                break;
            case "Assassin":
                this.setSubclassName("Assassin");
                classFeatures.put(3, new ArrayList<>(Arrays.asList("Bonus Proficiencies", "Assassinate ") ) );
                classFeatures.put(9, new ArrayList<>(Arrays.asList("Infiltration Expertise") ) );
                classFeatures.put(13, new ArrayList<>(Arrays.asList("Impostor") ) );
                classFeatures.put(17, new ArrayList<>(Arrays.asList("Death Strike") ) );
                break;
            case "Arcane Trickster":
                this.setSubclassName("Arcane Trickster");
                classFeatures.put(3, new ArrayList<>(Arrays.asList("Spellcasting", "Mage Hand Legerdemain") ) );
                classFeatures.put(9, new ArrayList<>(Arrays.asList("Magical Ambush") ) );
                classFeatures.put(13, new ArrayList<>(Arrays.asList("Versatile Trickster") ) );
                classFeatures.put(17, new ArrayList<>(Arrays.asList("Spell Thief") ) );
                break;
        }
    }
}
