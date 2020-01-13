package org.nerdcore.spellbookmanager.models.CharacterClasses;

import java.util.ArrayList;
import java.util.Arrays;

public class Barbarian extends CharacterClass{


    Barbarian() {
        this.setClassName("Barbarian");
        this.addSubclassChoice("Path of the Berserker");
        this.addSubclassChoice("Path of the Totem Warrior");
        this.setHitDieValue(12);
        // Populates the classFeatures Map for Barbarian class features


        classFeatures.put(1, new ArrayList<>(Arrays.asList("Rage", "Unarmored Defense") ) );
        classFeatures.put(2,new ArrayList<>(Arrays.asList("Reckless Attack", "Danger Sense") ) );
        classFeatures.put(4 ,new ArrayList<>(Arrays.asList("Ability Score Improvement") ) );
        classFeatures.put(5, new ArrayList<>(Arrays.asList("Extra Attack", "Fast Movement") ) );
        classFeatures.put(7, new ArrayList<>(Arrays.asList("Feral Instinct") ) );
        classFeatures.put(8, new ArrayList<>(Arrays.asList("Ability Score Improvement") ) );
        classFeatures.put(9, new ArrayList<>(Arrays.asList("Brutal Critical") ) );
        classFeatures.put(11, new ArrayList<>(Arrays.asList("Relentless Rage") ) );
        classFeatures.put(12, new ArrayList<>(Arrays.asList("Ability Score Improvement") ) );
        classFeatures.put(13, new ArrayList<>(Arrays.asList("Brutal Critical+") ) );
        classFeatures.put(15, new ArrayList<>(Arrays.asList("Persistent Rage") ) );
        classFeatures.put(16, new ArrayList<>(Arrays.asList("Ability Score Improvement") ) );
        classFeatures.put(17, new ArrayList<>(Arrays.asList("Brutal Critical++") ) );
        classFeatures.put(18, new ArrayList<>(Arrays.asList("Indomitable Might") ) );
        classFeatures.put(19, new ArrayList<>(Arrays.asList("Ability Score Improvement") ) );
        classFeatures.put(20, new ArrayList<>(Arrays.asList("Primal Champion") ) );
    }

    public void setSubclass(String str){

        if(str.equals("Path of the Berserker")){
            this.setSubclassName("Path of the Berserker");

            classFeatures.put(3, new ArrayList<>(Arrays.asList("Frenzy") ) );
            classFeatures.put(6, new ArrayList<>(Arrays.asList("Mindless Rage") ) );
            classFeatures.put(10, new ArrayList<>(Arrays.asList("Intimidating Presence") ) );
            classFeatures.put(14, new ArrayList<>(Arrays.asList("Retaliation") ) );

        } else if(str.equals("Path of the Totem Warrior")){
            this.setSubclassName("Path of the Totem Warrior");

            classFeatures.put(3, new ArrayList<>(Arrays.asList("Spirit Seeker", "Totem Spirit") ) );
            classFeatures.put(6, new ArrayList<>(Arrays.asList("Aspect of the Beast") ) );
            classFeatures.put(10, new ArrayList<>(Arrays.asList("Spirit Walker") ) );
            classFeatures.put(14, new ArrayList<>(Arrays.asList("Totemic Attunement") ) );
        }
    }


    /**This method loops across the classFeatures ArrayList, up to the
     * set classLevel for this characterClass. The elements of the classFeatures
     * ArrayList are appended to featuresAList, which is then returned
     *
     * TL;DR, returns all level appropriate class features
    */
    public ArrayList<String> getClassFeatures(){
        ArrayList<String> featuresAlist = new ArrayList<>();
        for(int i = 1; i < this.getClassLevel(); i++){
            for(String f : classFeatures.get(i)){
                featuresAlist.add(f);
            }
        }
        return featuresAlist;
    }
}
