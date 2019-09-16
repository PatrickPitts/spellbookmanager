package org.nerdcore.spellbookmanager.databaseInit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class BuildDBTables {

    public static void createSpellTable(String dbFilename){

        Connection conn = null;
        String url="jdbc:sqlite:src/main/resources/static/"+dbFilename;
        try{
            conn = DriverManager.getConnection(url);

            String sql = "CREATE TABLE IF NOT EXISTS spells(\n"
                    + "spellID integer AUTO_INCREMENT,\n"
                    + "spellName VARCHAR(128) NOT NULL,\n"
                    + "description text,\n"
                    + "spellLevel integer,\n"
                    + "school varchar(16),\n"
                    + "castingTime varchar(32),\n"
                    + "range varchar(32),\n"
                    + "verbalComponent BOOLEAN,\n"
                    + "somaticComponent BOOLEAN,\n"
                    + "ritualCasting BOOLEAN,\n"
                    + "concentration BOOLEAN,\n"
                    + "materialComponents text,\n"
                    + "duration varchar(32),\n"
                    + "source varchar(32),"
                    + "PRIMARY KEY (spellID));";

            Statement st = conn.createStatement();
            st.execute(sql);
            System.out.println("spells Table Created");


        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){

        createSpellTable("spellbookDatabase.db");

    }


}
