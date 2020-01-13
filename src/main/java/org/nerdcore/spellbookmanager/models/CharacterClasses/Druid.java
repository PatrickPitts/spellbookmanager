package org.nerdcore.spellbookmanager.models.CharacterClasses;

import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("unused")
public class Druid extends CharacterClass {
    Druid(){
        this.setClassName("Druid");
        this.addSubclassChoice("Circle of the Land");
        this.addSubclassChoice("Circle of the Moon");
        this.setHitDieValue(8);

        classFeatures.put(1, new ArrayList<>(Arrays.asList("Druidic", "Spellcasting") ) );
        classFeatures.put(2, new ArrayList<>(Arrays.asList("Wild Shape", "Druid Circle") ) );
        classFeatures.put(3, new ArrayList<>(Arrays.asList() ) );
        classFeatures.put(4, new ArrayList<>(Arrays.asList("Wild Shape Improvement", "Ability Score Improvement") ) );
        classFeatures.put(5, new ArrayList<>(Arrays.asList() ) );
        classFeatures.put(7, new ArrayList<>(Arrays.asList() ) );
        classFeatures.put(8, new ArrayList<>(Arrays.asList("Wild Shape Improvement", "Ability Score Improvement") ) );
        classFeatures.put(9, new ArrayList<>(Arrays.asList() ) );
        classFeatures.put(11, new ArrayList<>(Arrays.asList() ) );
        classFeatures.put(12, new ArrayList<>(Arrays.asList("Ability Score Improvement") ) );
        classFeatures.put(13, new ArrayList<>(Arrays.asList() ) );
        classFeatures.put(15, new ArrayList<>(Arrays.asList() ) );
        classFeatures.put(16, new ArrayList<>(Arrays.asList("Ability Score Improvement") ) );
        classFeatures.put(17, new ArrayList<>(Arrays.asList() ) );
        classFeatures.put(18, new ArrayList<>(Arrays.asList("Timeless Body", "Beast Spells") ) );
        classFeatures.put(19, new ArrayList<>(Arrays.asList("Ability Score Improvement") ) );
        classFeatures.put(20, new ArrayList<>(Arrays.asList("Archdruid") ) );
    }


    public void setSubclass(String str){

        if(str.equals("Circle of the Land")){
            this.setSubclassName("Circle of the Land");
            classFeatures.put(2, new ArrayList<>(Arrays.asList("Bonus Cantrip", "Natural Recovery") ) );
            classFeatures.put(3, new ArrayList<>(Arrays.asList("Circle Spells") ) );
            classFeatures.put(5, new ArrayList<>(Arrays.asList("Circle Spells") ) );
            classFeatures.put(6, new ArrayList<>(Arrays.asList("Land's Stride") ) );
            classFeatures.put(7, new ArrayList<>(Arrays.asList("Circle Spells") ) );
            classFeatures.put(9, new ArrayList<>(Arrays.asList("Circle Spells") ) );
            classFeatures.put(10, new ArrayList<>(Arrays.asList("Nature's Ward") ) );
            classFeatures.put(14, new ArrayList<>(Arrays.asList("Nature's Sanctuary") ) );

        }else if (str.equals("Circle of the Moon")){
            this.setSubclassName("Circle of the Moon");
            classFeatures.put(2, new ArrayList<>(Arrays.asList("Combat Wild Shape", "Circle Forms") ) );
            classFeatures.put(6, new ArrayList<>(Arrays.asList("Improved Circle Forms", "Primal Strike") ) );
            classFeatures.put(10, new ArrayList<>(Arrays.asList("Elemental Wild Shape") ) );
            classFeatures.put(14, new ArrayList<>(Arrays.asList("Thousand Forms") ) );
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
