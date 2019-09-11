package org.nerdcore.spellbookmanager;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.nerdcore.spellbookmanager.models.Spell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SpellJSONProcesser {

    private static JSONParser parser = new JSONParser();

    public static void writeJSON(JSONArray jArray){
        try{
            FileWriter file = new FileWriter("src/main/resources/static/spellstore.json");
            file.write(jArray.toJSONString());
            file.flush();
            file.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static JSONArray sortBySpellName(JSONArray spellArray){
        Collections.sort(spellArray, new Comparator<JSONObject>() {

            @Override
            public int compare(JSONObject jObjA, JSONObject jObjB){
                int compare = 0;
                compare = ((String)jObjA.get("name")).compareTo((String) jObjB.get("name"));
                return compare;
            }
        });
        return spellArray;

    }

    public static List<JSONObject> getAllSpellsAsJSONObjects(){
        List<JSONObject> spellJSONList = new ArrayList<>();
        try{
            FileReader fileReader = new FileReader("src/main/resources/static/spellstore.json");
            Object obj = parser.parse(fileReader);
            JSONArray spellArray = (JSONArray) obj;

            return spellArray;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static List<Spell> getAllSpellsAsList(){
        List<Spell> spellList = new ArrayList<>();

        try{
            FileReader fileReader = new FileReader("src/main/resources/static/spellstore.json");
            Object obj = parser.parse(fileReader);
            JSONArray spellArray = (JSONArray) obj;

            for(int i = 0; i < spellArray.size();i++){
                JSONObject spellObj = (JSONObject) spellArray.get(i);
                spellList.add(new Spell(spellObj));
            }

            fileReader.close();

        } catch (Exception e){
            e.printStackTrace();
        }
        return spellList;
    }

    public static void writeNewSpellToJSON(Spell spell){
        List<Spell> allSpellList = getAllSpellsAsList();
        allSpellList.add(spell);
        JSONArray jArray = new JSONArray();
        for(Spell sp : allSpellList){
            JSONObject jsonSpell = sp.getJSONObject();
            jsonSpell.put("_class", "org.nerdcore.spellbookmanager.models.Spell");
            jArray.add(jsonSpell);
        }
        writeJSON(jArray);


    }

    public static Spell getSingleSpellByName(String spellName){
        try{
            FileReader fileReader = new FileReader("src/main/resources/static/spellstore.json");
            Object obj = parser.parse(fileReader);
            JSONArray spellArray = (JSONArray) obj;
            for(int i =0; i < spellArray.size(); i++){
                Spell spell = new Spell((JSONObject) spellArray.get(i));
                if(spell.getName().equals(spellName)){
                    return spell;
                }
            }

            fileReader.close();

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void deleteSpell(String spellName){

        List<JSONObject> spellJSONObjList = getAllSpellsAsJSONObjects();
        JSONArray spellListToWrite = new JSONArray();
        for(int i = 0; i < spellJSONObjList.size(); i++){
            if(!spellName.equals(spellJSONObjList.get(i).get("name"))){
                spellListToWrite.add(spellJSONObjList.get(i));
            }
        }
        writeJSON(spellListToWrite);
    }

    public static void editSpell(Spell editedSpell){
        deleteSpell(editedSpell.getName());
        writeNewSpellToJSON(editedSpell);
    }

    public static List<Spell> sortJSONObjectListOnSpellName(List<JSONObject> listToSort){

        List<Spell> sortedSpellList = new ArrayList<>();
        List<String> spellNamesToSort = new ArrayList<>();
        for(JSONObject obj : listToSort){
            spellNamesToSort.add((String) obj.get("name"));
        }

        Collections.sort(spellNamesToSort);
        for(String spellName : spellNamesToSort){
            sortedSpellList.add(getSingleSpellByName(spellName));
        }
        return sortedSpellList;
    }

}
