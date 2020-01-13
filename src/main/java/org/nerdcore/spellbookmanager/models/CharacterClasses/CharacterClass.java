package org.nerdcore.spellbookmanager.models.CharacterClasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class CharacterClass {
    //stores all subclass options for the class, populated in classes than inherit from this
    private ArrayList<String> subclassChoices = new ArrayList<>();

    //stores all class features, depending on the chosen subclass
    Map<Integer, ArrayList<String>> classFeatures = new HashMap<>();


    private String className = "";
    private String subClassName;
    private int classLevel;
    private int hitDieValue;

    CharacterClass(){
        classLevel = 1;
        subClassName = "None";
    }

    void setSubclassName(String str){this.subClassName = str;}
    void setClassName(String str){this.className = str;}
    void setClassLevel(int n){
        this.classLevel = n;
    }
    void setHitDieValue(int n) {this.hitDieValue = n;}

    String getSubClassName(){
        return this.subClassName;
    }
    String getClassName(){return this.className;}
    int getClassLevel(){
        return this.classLevel;
    }
    int getHitDieValue(){return this.hitDieValue; }

    void addSubclassChoice(String str){
        this.subclassChoices.add(str);
    }

    public ArrayList<String> getSubclassChoices(){return this.subclassChoices;}

    String getClassString(){
        return this.getClassName()+"~"+this.getSubClassName()+"~"+this.getClassLevel();
    }

    ArrayList<String> getAllClassFeatures(){
        ArrayList<String> allClassFeatures = new ArrayList<>();
        for(int i = 1; i <= this.classLevel; i++){
            allClassFeatures.addAll(this.classFeatures.get(i));
        }
        return allClassFeatures;
    }
    /*
    * MAY CAUSE PROBLEMS WITH INHERITED CHARACTER CLASS OBJECTS, AS THIS WAS ORIGINALLY IMPLEMENTED IN
    * EACH CLASS INDIVIDUALLY.
    * */
    public ArrayList<String> getClassFeatures(){
        ArrayList<String> featuresAlist = new ArrayList<>();
        for(int i = 1; i < this.getClassLevel(); i++){
            featuresAlist.addAll(classFeatures.get(i));
        }
        return featuresAlist;
    }
}
