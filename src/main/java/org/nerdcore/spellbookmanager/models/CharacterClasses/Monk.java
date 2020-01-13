package org.nerdcore.spellbookmanager.models.CharacterClasses;

import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("unused")
public class Monk extends CharacterClass {
    Monk(){
        this.setClassName("Monk");
        this.addSubclassChoice("Way of the Open Hand");
        this.addSubclassChoice("Way of Shadow");
        this.addSubclassChoice("Way of the Four Elements");
        this.setHitDieValue(8);


        classFeatures.put(1, new ArrayList<>(Arrays.asList("Unarmored Defense", "Martial Arts") ) );
        classFeatures.put(2, new ArrayList<>(Arrays.asList("Ki", "Unarmored Movement") ) );
        classFeatures.put(4, new ArrayList<>(Arrays.asList("Ability Score Improvement", "Slow Fall") ) );
        classFeatures.put(5, new ArrayList<>(Arrays.asList("Extra Attack", "Stunning Strike") ) );
        classFeatures.put(7, new ArrayList<>(Arrays.asList("Evasion", "Stillness of Mind") ) );
        classFeatures.put(8, new ArrayList<>(Arrays.asList("Ability Score Improvement") ) );
        classFeatures.put(9, new ArrayList<>(Arrays.asList("Unarmored Movement+") ) );
        classFeatures.put(10, new ArrayList<>(Arrays.asList("Purity of Body") ) );
        classFeatures.put(11, new ArrayList<>(Arrays.asList() ) );
        classFeatures.put(12, new ArrayList<>(Arrays.asList("Ability Score Improvement") ) );
        classFeatures.put(13, new ArrayList<>(Arrays.asList("Tongue of the Sun and Moon") ) );
        classFeatures.put(14, new ArrayList<>(Arrays.asList("Diamond Soul") ) );
        classFeatures.put(15, new ArrayList<>(Arrays.asList("Timeless Body") ) );
        classFeatures.put(16, new ArrayList<>(Arrays.asList("Ability Score Improvement") ) );
        classFeatures.put(18, new ArrayList<>(Arrays.asList("Empty Body") ) );
        classFeatures.put(19, new ArrayList<>(Arrays.asList("Ability Score Improvement") ) );
        classFeatures.put(20, new ArrayList<>(Arrays.asList("Perfect Self") ) );
    }

    public void setSubclass(String str){
        switch (str) {
            case "Way of the Open Hand":
                this.setSubclassName("Way of the Open Hand");
                classFeatures.put(3, new ArrayList<>(Arrays.asList("Deflect Missiles", "Open Hand Technique") ) );
                classFeatures.put(6, new ArrayList<>(Arrays.asList("Ki-Empowered Strike", "Wholeness of Body") ) );
                classFeatures.put(11, new ArrayList<>(Arrays.asList("Tranquility")));
                classFeatures.put(17, new ArrayList<>(Arrays.asList("Quivering Palm")));
                break;
            case "Way of Shadow":
                this.setSubclassName("Way of Shadow");
                classFeatures.put(3, new ArrayList<>(Arrays.asList("Deflect Missiles", "Shadow Arts") ) );
                classFeatures.put(6, new ArrayList<>(Arrays.asList("Ki-Empowered Strike", "Shadow Step") ) );
                classFeatures.put(11, new ArrayList<>(Arrays.asList("Cloak of Shadows")));
                classFeatures.put(17, new ArrayList<>(Arrays.asList("Opportunist")));
                break;
            case "Way of the Four Elements":
                this.setSubclassName("Way of the Four Elements");
                classFeatures.put(3, new ArrayList<>(Arrays.asList("Deflect Missiles", "Disciple of the Elements") ) );
                classFeatures.put(6, new ArrayList<>(Arrays.asList("Ki-Empowered Strike", "Disciple of the Elements") ) );
                classFeatures.put(11, new ArrayList<>(Arrays.asList("Disciple of the Elements")));
                classFeatures.put(17, new ArrayList<>(Arrays.asList("Disciple of the Elements")));
                break;
        }
    }

    public int getKiPoints(){
        if(this.getClassLevel()>1){
            return this.getClassLevel();
        } return 0;
    }
}
