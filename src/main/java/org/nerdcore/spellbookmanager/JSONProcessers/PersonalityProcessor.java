package org.nerdcore.spellbookmanager.JSONProcessers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class PersonalityProcessor {

    private static JSONParser parser = new JSONParser();

    public static void test(){
        try {
            JSONObject obj = (JSONObject) parser.parse(new FileReader("src/main/resources/static/JSON/PersonalityTraits.json"));
            JSONObject charlatan = (JSONObject) obj.get("Charlatan");
            JSONObject features = (JSONObject) charlatan.get("Extra Features");
            //Throws java.lang.ClassCastException if trying to convert what should be a JSON String to a JSONArray.
            //Use try/catch to check if the "Extra Feature" should be randomly selected or just taken as is.
            JSONArray testArr = (JSONArray) features.get("False Identity");
        } catch (FileNotFoundException e){
            System.out.println("Couldn't find the file");
        } catch (ParseException e){
            System.out.println("Couldn't parse the JSON object");
        } catch (IOException e){
            System.out.println("I/O Exception");
        }
    }

    public static void main(String[] args){
        //test();
        read();
    }

    static void read(){
        Random random = new Random();

        try{
            JSONObject obj = (JSONObject) parser.parse(new FileReader("src/main/resources/static/JSON/PersonalityTraits.json"));
            //TODO: Optimize random select of backgrounds
            //Set<String> bgKeys = obj.keySet();

            ArrayList<String> backgrounds = new ArrayList<>(obj.keySet());

            //String background = backgrounds.get(random.nextInt(backgrounds.size()));
            ArrayList<String> background = new ArrayList<String>(obj.keySet());


            JSONObject charictaristics = (JSONObject) ((JSONObject) obj.get(background)).get("Characteristics");
            //            Iterator<String> prof = ((JSONArray) acolyte.get("Skill Proficiencies")).iterator();
//            while(prof.hasNext()){
//                System.out.println(prof.next());
//            }

            ArrayList<String> personalities = JSONProcessor.JSONArrayToArrayList((JSONArray) charictaristics.get("Personality Trait"));

            System.out.println(personalities.get(random.nextInt(personalities.size())));

//            for(String str : personalities){
//                System.out.println(str);
//            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
