package org.nerdcore.spellbookmanager.JSONProcessers;

import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.Iterator;

public class JSONProcessor {
    public static ArrayList<String> JSONArrayToArrayList(JSONArray arr){

        ArrayList<String> ret = new ArrayList<>();
        Iterator it = arr.iterator();

        while(it.hasNext()){
            ret.add((String) it.next());
        }

        return ret;
    }
}
