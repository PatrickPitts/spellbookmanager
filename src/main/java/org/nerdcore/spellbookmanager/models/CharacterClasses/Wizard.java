package org.nerdcore.spellbookmanager.models.CharacterClasses;

import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("unused")
public class Wizard extends CharacterClass {
    Wizard(){
        this.setClassName("Wizard");
        this.addSubclassChoice("Abjuration");
        this.addSubclassChoice("Conjuration");
        this.addSubclassChoice("Divination");
        this.addSubclassChoice("Enchantment");
        this.addSubclassChoice("Evocation");
        this.addSubclassChoice("Illusion");
        this.addSubclassChoice("Necromancy");
        this.addSubclassChoice("Transmutation");
        this.setHitDieValue(6);

        classFeatures.put(1, new ArrayList<>(Arrays.asList("Spellcasting", "Arcane Recovery") ) );
        classFeatures.put(3, new ArrayList<>(Arrays.asList() ) );
        classFeatures.put(4, new ArrayList<>(Arrays.asList("Ability Score Improvement") ) );
        classFeatures.put(5, new ArrayList<>(Arrays.asList() ) );
        classFeatures.put(7, new ArrayList<>(Arrays.asList() ) );
        classFeatures.put(8, new ArrayList<>(Arrays.asList("Ability Score Improvement") ) );
        classFeatures.put(9, new ArrayList<>(Arrays.asList() ) );
        classFeatures.put(11, new ArrayList<>(Arrays.asList() ) );
        classFeatures.put(12, new ArrayList<>(Arrays.asList("Ability Score Improvement") ) );
        classFeatures.put(13, new ArrayList<>(Arrays.asList() ) );
        classFeatures.put(15, new ArrayList<>(Arrays.asList() ) );
        classFeatures.put(16, new ArrayList<>(Arrays.asList("Ability Score Improvement") ) );
        classFeatures.put(17, new ArrayList<>(Arrays.asList() ) );
        classFeatures.put(18, new ArrayList<>(Arrays.asList("Spell Mastery") ) );
        classFeatures.put(19, new ArrayList<>(Arrays.asList("Ability Score Improvement") ) );
        classFeatures.put(20, new ArrayList<>(Arrays.asList("Signature Spell") ) );
    }

    public void setSubclass(String str){
        switch(str){
            case "Abjuration":
                this.setSubclassName("Abjuration");
                classFeatures.put(2, new ArrayList<>(Arrays.asList("Abjuration Savant", "Arcane Ward") ) );
                classFeatures.put(6, new ArrayList<>(Arrays.asList("Projected Ward") ) );
                classFeatures.put(10, new ArrayList<>(Arrays.asList("Improved Abjuration") ) );
                classFeatures.put(14, new ArrayList<>(Arrays.asList("Spell Resistance") ) );
                break;
            case "Conjuration":
                this.setSubclassName("Conjuration");
                classFeatures.put(2, new ArrayList<>(Arrays.asList("Conjuration Savant", "Minor Conjuration") ) );
                classFeatures.put(6, new ArrayList<>(Arrays.asList("Benign Transposition") ) );
                classFeatures.put(10, new ArrayList<>(Arrays.asList("Focused Conjuration") ) );
                classFeatures.put(14, new ArrayList<>(Arrays.asList("Durable Summons") ) );
                break;
            case "Divination":
                this.setSubclassName("Divination");
                classFeatures.put(2, new ArrayList<>(Arrays.asList("Divination Savant", "Portent") ) );
                classFeatures.put(6, new ArrayList<>(Arrays.asList("Expert Divination") ) );
                classFeatures.put(10, new ArrayList<>(Arrays.asList("The Third Eye") ) );
                classFeatures.put(14, new ArrayList<>(Arrays.asList("Greater Portent") ) );
                break;
            case "Enchantment":
                this.setSubclassName("Enchantment");
                classFeatures.put(2, new ArrayList<>(Arrays.asList("Enchantment Savant", "Hypnotic Gaze") ) );
                classFeatures.put(6, new ArrayList<>(Arrays.asList("Instinctive Charm") ) );
                classFeatures.put(10, new ArrayList<>(Arrays.asList("Split Enchantment") ) );
                classFeatures.put(14, new ArrayList<>(Arrays.asList("Alter Memories") ) );
                break;
            case "Evocation":
                this.setSubclassName("Evocation");
                classFeatures.put(2, new ArrayList<>(Arrays.asList("Evocation Savant", "Sculpt Spells") ) );
                classFeatures.put(6, new ArrayList<>(Arrays.asList("Potent Cantrip") ) );
                classFeatures.put(10, new ArrayList<>(Arrays.asList("Empowered Evocations") ) );
                classFeatures.put(14, new ArrayList<>(Arrays.asList("Overchannel") ) );
                break;
            case "Illusion":
                this.setSubclassName("Illusion");
                classFeatures.put(2, new ArrayList<>(Arrays.asList("Illusion Savant", "Improved Minor Illusion") ) );
                classFeatures.put(6, new ArrayList<>(Arrays.asList("Malleable Illusions") ) );
                classFeatures.put(10, new ArrayList<>(Arrays.asList("Illusory Self") ) );
                classFeatures.put(14, new ArrayList<>(Arrays.asList("Illusory Reality") ) );
                break;
            case "Necromancy":
                this.setSubclassName("Necromancy");
                classFeatures.put(2, new ArrayList<>(Arrays.asList("Necromancy Savant", "Grim Harvest") ) );
                classFeatures.put(6, new ArrayList<>(Arrays.asList("Undead Thralls") ) );
                classFeatures.put(10, new ArrayList<>(Arrays.asList("Inured to Undeath") ) );
                classFeatures.put(14, new ArrayList<>(Arrays.asList("Command Undead") ) );
                break;
            case "Transmutation":
                this.setSubclassName("Transmutation");
                classFeatures.put(2, new ArrayList<>(Arrays.asList("Transmutation Savant", "Minor Alchemy") ) );
                classFeatures.put(6, new ArrayList<>(Arrays.asList("Transmuter's Stone") ) );
                classFeatures.put(10, new ArrayList<>(Arrays.asList("Shapechanger") ) );
                classFeatures.put(14, new ArrayList<>(Arrays.asList("Master Transmuter") ) );
                break;
        }
    }
}
