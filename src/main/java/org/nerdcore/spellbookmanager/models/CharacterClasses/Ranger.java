package org.nerdcore.spellbookmanager.models.CharacterClasses;

import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("unused")
public class Ranger extends CharacterClass {
    Ranger(){
        this.setClassName("Ranger");
        this.addSubclassChoice("Hunter");
        this.addSubclassChoice("Beast Master");
        this.setHitDieValue(10);

        classFeatures.put(1, new ArrayList<>(Arrays.asList("Favored Enemy", "Natural Explorer") ) );
        classFeatures.put(2, new ArrayList<>(Arrays.asList("Fighting Style", "Spellcasting") ) );
        classFeatures.put(4, new ArrayList<>(Arrays.asList("Ability Score Improvement") ) );
        classFeatures.put(5, new ArrayList<>(Arrays.asList("Extra Attack") ) );
        classFeatures.put(6, new ArrayList<>(Arrays.asList("Favored Enemy+", "Natural Explorer+") ) );
        classFeatures.put(8, new ArrayList<>(Arrays.asList("Ability Score Improvement", "Land's Stride") ) );
        classFeatures.put(9, new ArrayList<>(Arrays.asList() ) );
        classFeatures.put(10, new ArrayList<>(Arrays.asList("Natural Explorer++", "Hide in Plain Sight") ) );
        classFeatures.put(12, new ArrayList<>(Arrays.asList("Ability Score Improvement") ) );
        classFeatures.put(13, new ArrayList<>(Arrays.asList() ) );
        classFeatures.put(14, new ArrayList<>(Arrays.asList("Favored Enemy++", "Vanish") ) );
        classFeatures.put(16, new ArrayList<>(Arrays.asList("Ability Score Improvement") ) );
        classFeatures.put(17, new ArrayList<>(Arrays.asList() ) );
        classFeatures.put(18, new ArrayList<>(Arrays.asList("Feral Senses") ) );
        classFeatures.put(19, new ArrayList<>(Arrays.asList("Ability Score Improvement") ) );
        classFeatures.put(20, new ArrayList<>(Arrays.asList("Foe Slayer") ) );
    }

    public void setSubclass(String str){
        switch(str){
            case "Hunter":
                this.setSubclassName("Hunter");
                classFeatures.put(3, new ArrayList<>(Arrays.asList("Primeval Awareness", "Hunter's Prey") ) );
                classFeatures.put(7, new ArrayList<>(Arrays.asList("Defensive Tactics") ) );
                classFeatures.put(11, new ArrayList<>(Arrays.asList("Multiattack") ) );
                classFeatures.put(15, new ArrayList<>(Arrays.asList("Superior Hunter's Defense") ) );
                break;
            case "Beast Master":
                this.setSubclassName("Beast Master");
                classFeatures.put(3, new ArrayList<>(Arrays.asList("Primeval Awareness", "Ranger's Companion") ) );
                classFeatures.put(7, new ArrayList<>(Arrays.asList("Exceptional Training") ) );
                classFeatures.put(11, new ArrayList<>(Arrays.asList("Bestial Fury") ) );
                classFeatures.put(15, new ArrayList<>(Arrays.asList("Share Spells") ) );
                break;
        }
    }
}
