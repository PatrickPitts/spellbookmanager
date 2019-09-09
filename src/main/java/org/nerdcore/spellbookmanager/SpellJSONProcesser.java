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
import java.util.List;

public class SpellJSONProcesser {

    private static JSONParser parser = new JSONParser();

    public static List<Spell> getAllSpellsAsList(){
        List<Spell> spellList = new ArrayList<>();
        //JSONParser parser = new JSONParser();
        try{
            //File file = new ClassPathResource();
            Object obj = parser.parse(new FileReader("src/main/resources/static/spellstore.json"));
            JSONArray spellArray = (JSONArray) obj;

            for(int i = 0; i < spellArray.size();i++){
                JSONObject spellObj = (JSONObject) spellArray.get(i);
                spellList.add(new Spell(spellObj));
            }

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
        try{
            FileWriter file = new FileWriter("src/main/resources/static/spellstore.json");
            file.write(jArray.toJSONString());
            file.flush();
            file.close();
        } catch (Exception e){
            e.printStackTrace();
        }


    }

    public static Spell getSingleSpellByName(String spellName){
        try{
            Object obj = parser.parse(new FileReader("src/main/resources/static/spellstore.json"));
            JSONArray spellArray = (JSONArray) obj;
            for(int i =0; i < spellArray.size(); i++){
                Spell spell = new Spell((JSONObject) spellArray.get(i));
                if(spell.getName().equals(spellName)){
                    return spell;
                }
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
