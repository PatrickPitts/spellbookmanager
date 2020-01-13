package org.nerdcore.spellbookmanager.models.CharacterClasses;

import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("unused")
public class Paladin extends CharacterClass{
    Paladin(){
        this.setClassName("Paladin");
        this.addSubclassChoice("Oath of Devotion");
        this.addSubclassChoice("Oath of the Ancients");
        this.addSubclassChoice("Oath of Vengeance");
        this.setHitDieValue(10);


        classFeatures.put(1, new ArrayList<>(Arrays.asList("Divine Sense", "Lay on Hands")) );
        classFeatures.put(2, new ArrayList<>(Arrays.asList("Fighting Style", "Spellcasting", "Divine Smite")) );
        classFeatures.put(4, new ArrayList<>(Arrays.asList("Ability Score Improvement")) );
        classFeatures.put(5, new ArrayList<>(Arrays.asList("Extra Attack")) );
        classFeatures.put(6, new ArrayList<>(Arrays.asList("Aura of Protection")) );
        classFeatures.put(8, new ArrayList<>(Arrays.asList("Ability Score Improvement")) );
        classFeatures.put(9, new ArrayList<>(Arrays.asList()) );
        classFeatures.put(10, new ArrayList<>(Arrays.asList("Aura of Courage")) );
        classFeatures.put(11, new ArrayList<>(Arrays.asList("Improved Divine Smite")) );
        classFeatures.put(12, new ArrayList<>(Arrays.asList("Ability Score Improvement")) );
        classFeatures.put(13, new ArrayList<>(Arrays.asList()) );
        classFeatures.put(14, new ArrayList<>(Arrays.asList("Cleansing Touch")) );
        classFeatures.put(16, new ArrayList<>(Arrays.asList("Ability Score Improvement")) );
        classFeatures.put(17, new ArrayList<>(Arrays.asList()) );
        classFeatures.put(18, new ArrayList<>(Arrays.asList("Aura Improvements")) );
        classFeatures.put(19, new ArrayList<>(Arrays.asList("Ability Score Improvement")) );
    }

    public void setSubclass(String str){
        switch(str){
            case "Oath of Devotion":
                this.setSubclassName("Oath of Devotion");
                classFeatures.put(3, new ArrayList<>(Arrays.asList("Channel Divinity Effect: Devotion", "Devotion Spells")) );
                classFeatures.put(7, new ArrayList<>(Arrays.asList("Aura of Devotion")) );
                classFeatures.put(15, new ArrayList<>(Arrays.asList("Purity of Spirit")) );
                classFeatures.put(18, new ArrayList<>(Arrays.asList("Aura of Devotion+")) );
                classFeatures.put(20, new ArrayList<>(Arrays.asList("Holy Nimbus")) );
                break;
            case "Oath of Vengeance":
                this.setSubclassName("Oath of Vengeance");
                classFeatures.put(3, new ArrayList<>(Arrays.asList("Channel Divinity Effect: Vengeance", "Vengeance Spells")) );
                classFeatures.put(7, new ArrayList<>(Arrays.asList("Relentless Avenger")) );
                classFeatures.put(15, new ArrayList<>(Arrays.asList("Soul of Vengeance")) );
                classFeatures.put(18, new ArrayList<>(Arrays.asList()) );
                classFeatures.put(20, new ArrayList<>(Arrays.asList("Avenging Angel")) );
                break;
            case "Oath of the Ancients":
                this.setSubclassName("Oath of the Ancients");
                classFeatures.put(3, new ArrayList<>(Arrays.asList("Channel Divinity Effect: Ancients", "Ancient Spells")) );
                classFeatures.put(7, new ArrayList<>(Arrays.asList("Aura of Warding")) );
                classFeatures.put(15, new ArrayList<>(Arrays.asList("Undying Sentinel")) );
                classFeatures.put(18, new ArrayList<>(Arrays.asList("Aura of Warding+")) );
                classFeatures.put(20, new ArrayList<>(Arrays.asList("Elder Champion")) );
                break;
        }
    }

    public ArrayList<String> getClassFeatures(){
        ArrayList<String> featuresAlist = new ArrayList<>();
        for(int i = 1; i < this.getClassLevel(); i++){
            featuresAlist.addAll(classFeatures.get(i));
        }
        return featuresAlist;
    }
}

