package org.nerdcore.spellbookmanager.models.CharacterClasses;

import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("unused")
public class Warlock extends CharacterClass {
    Warlock(){
        this.setClassName("Warlock");
        this.addSubclassChoice("The Archfey");
        this.addSubclassChoice("The Great Old One");
        this.addSubclassChoice("The Fiend");
        this.setHitDieValue(8);


        classFeatures.put(2, new ArrayList<>(Arrays.asList("Eldritch Invocations") ) );
        classFeatures.put(3, new ArrayList<>(Arrays.asList("Pact Boon") ) );
        classFeatures.put(4, new ArrayList<>(Arrays.asList("Ability Score Improvement") ) );
        classFeatures.put(5, new ArrayList<>(Arrays.asList() ) );
        classFeatures.put(7, new ArrayList<>(Arrays.asList() ) );
        classFeatures.put(8, new ArrayList<>(Arrays.asList("Ability Score Improvement") ) );
        classFeatures.put(9, new ArrayList<>(Arrays.asList() ) );
        classFeatures.put(11, new ArrayList<>(Arrays.asList("Mystic Arcanum (6th)") ) );
        classFeatures.put(12, new ArrayList<>(Arrays.asList("Ability Score Improvement") ) );
        classFeatures.put(13, new ArrayList<>(Arrays.asList("Mystic Arcanum (7th)") ) );
        classFeatures.put(15, new ArrayList<>(Arrays.asList("Mystic Arcanum (8th)") ) );
        classFeatures.put(16, new ArrayList<>(Arrays.asList("Ability Score Improvement") ) );
        classFeatures.put(17, new ArrayList<>(Arrays.asList("Mystic Arcanum (9th)") ) );
        classFeatures.put(18, new ArrayList<>(Arrays.asList() ) );
        classFeatures.put(19, new ArrayList<>(Arrays.asList("Ability Score Improvement") ) );
        classFeatures.put(20, new ArrayList<>(Arrays.asList("Eldritch Master") ) );
    }

    public void setSubclass(String str){
        switch(str){
            case "The Archfey":
                this.setSubclassName("The Archfey");
                classFeatures.put(1, new ArrayList<>(Arrays.asList("Pact Magic", "Expanded Spell List: Archfey", "Fey Presence") ) );
                classFeatures.put(6, new ArrayList<>(Arrays.asList("Misty Escape") ) );
                classFeatures.put(10, new ArrayList<>(Arrays.asList("Beguiling Defenses") ) );
                classFeatures.put(14, new ArrayList<>(Arrays.asList("Derk Delirium") ) );
                break;
            case "The Great Old One":
                this.setSubclassName("The Great Old One");
                classFeatures.put(1, new ArrayList<>(Arrays.asList("Pact Magic", "Expanded Spell List: Old One", "Awakened Mind") ) );
                classFeatures.put(6, new ArrayList<>(Arrays.asList("Entropic Ward") ) );
                classFeatures.put(10, new ArrayList<>(Arrays.asList("Thought Shield") ) );
                classFeatures.put(14, new ArrayList<>(Arrays.asList("Create Thrall") ) );
                break;
            case "The Fiend":
                this.setSubclassName("The Fiend");
                classFeatures.put(1, new ArrayList<>(Arrays.asList("Pact Magic", "Expanded Spell List: Fiend", "Dark One's Blessing") ) );
                classFeatures.put(6, new ArrayList<>(Arrays.asList("Dark One's Own Luck") ) );
                classFeatures.put(10, new ArrayList<>(Arrays.asList("Fiendish Resilience") ) );
                classFeatures.put(14, new ArrayList<>(Arrays.asList("Hurl Through Hell") ) );
                break;
        }
    }
}
