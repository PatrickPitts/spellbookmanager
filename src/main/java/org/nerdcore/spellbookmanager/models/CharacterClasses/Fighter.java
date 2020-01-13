package org.nerdcore.spellbookmanager.models.CharacterClasses;

import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("unused")
public class Fighter extends CharacterClass {
    Fighter(){
        this.setClassName("Fighter");
        this.addSubclassChoice("Champion");
        this.addSubclassChoice("Battle Master");
        this.addSubclassChoice("Eldritch Knight");
        this.setHitDieValue(10);

        classFeatures.put(1, new ArrayList<>(Arrays.asList("Fighting Style", "Second Wind") ) );
        classFeatures.put(2, new ArrayList<>(Arrays.asList("Action Surge") ) );
        classFeatures.put(4, new ArrayList<>(Arrays.asList("Ability Score Improvement") ) );
        classFeatures.put(5, new ArrayList<>(Arrays.asList("Extra Attack") ) );
        classFeatures.put(6, new ArrayList<>(Arrays.asList("Ability Score Improvement") ) );
        classFeatures.put(8, new ArrayList<>(Arrays.asList("Ability Score Improvement") ) );
        classFeatures.put(9, new ArrayList<>(Arrays.asList("Indomitable") ) );
        classFeatures.put(11, new ArrayList<>(Arrays.asList("Extra Attack+") ) );
        classFeatures.put(12, new ArrayList<>(Arrays.asList("Ability Score Improvement") ) );
        classFeatures.put(13, new ArrayList<>(Arrays.asList("Indomitable+") ) );
        classFeatures.put(14, new ArrayList<>(Arrays.asList("Ability Score Improvement") ) );
        classFeatures.put(16, new ArrayList<>(Arrays.asList("Ability Score Improvement") ) );
        classFeatures.put(17, new ArrayList<>(Arrays.asList("Action Surge+", "Indomitable++") ) );
        classFeatures.put(19, new ArrayList<>(Arrays.asList("Ability Score Improvement") ) );
        classFeatures.put(20, new ArrayList<>(Arrays.asList("Extra Attack++") ) );
    }

    public void setSubclass(String str){
        switch (str) {
            case "Champion":
                this.setSubclassName("Champion");
                classFeatures.put(3, new ArrayList<>(Arrays.asList("Improved Critical")));
                classFeatures.put(7, new ArrayList<>(Arrays.asList("Remarkable Athlete")));
                classFeatures.put(10, new ArrayList<>(Arrays.asList("Additional Fighting Style")));
                classFeatures.put(15, new ArrayList<>(Arrays.asList("Superior Critical")));
                classFeatures.put(18, new ArrayList<>(Arrays.asList("Survivor")));
                break;
            case "Battle Master":
                this.setSubclassName("Battle Master");
                classFeatures.put(3, new ArrayList<>(Arrays.asList("Combat Superiority", "Student of War")));
                classFeatures.put(7, new ArrayList<>(Arrays.asList("Add Superiority Die", "Add Maneuvers", "Know Your Enemy")));
                classFeatures.put(10, new ArrayList<>(Arrays.asList("Improved Combat Superiority", "Add Maneuvers")));
                classFeatures.put(15, new ArrayList<>(Arrays.asList("Add Superiority Die", "Add Maneuvers", "Relentless")));
                classFeatures.put(18, new ArrayList<>(Arrays.asList("Improved Combat Superiority+")));
                break;
            case "Eldritch Knight":
                this.setSubclassName("Eldritch Knight");
                classFeatures.put(3, new ArrayList<>(Arrays.asList("Spellcasting", "Weapon Bond")));
                classFeatures.put(7, new ArrayList<>(Arrays.asList("War Magic")));
                classFeatures.put(10, new ArrayList<>(Arrays.asList("Eldritch Strike")));
                classFeatures.put(15, new ArrayList<>(Arrays.asList("Arcane Charge")));
                classFeatures.put(18, new ArrayList<>(Arrays.asList("Improved War Magic")));
                break;
        }
    }



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
