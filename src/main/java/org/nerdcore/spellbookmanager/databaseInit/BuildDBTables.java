package org.nerdcore.spellbookmanager.databaseInit;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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

    public static void createSpellBookTableAndConnector(String dbFilename){

        Connection conn = null;
        String url="jdbc:sqlite:src/main/resources/static/"+dbFilename;
        try{
            conn = DriverManager.getConnection(url);

            String sql;
            Statement st = conn.createStatement();

            sql = "DROP TABLE spellBooks";
            st.execute(sql);

            sql = "DROP TABLE spellBookStorage";
            st.execute(sql);

            sql = "CREATE TABLE IF NOT EXISTS spellBooks(\n" +
                    "spellbookID INTEGER PRIMARY KEY,\n" +
                    "spellbookName VARCHAR(128),\n" +
                    "casterClass VARCHAR(64)" +
                    ");";

            st.execute(sql);

            sql = "CREATE TABLE IF NOT EXISTS spellBookStorage(\n" +
                    "spellbookID INTEGER,\n" +
                    "spellName VARCHAR(128),\n" +
                    "PRIMARY KEY (spellbookID, spellName),\n" +
                    "FOREIGN KEY(spellbookID) REFERENCES spellBooks(spellbookID));";

            st = conn.createStatement();
            st.execute(sql);



        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void createCasterTable() throws SQLException {
        Connection conn = connect();
        Statement st = conn.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS casterClasses (casterClass VARCHAR(64));";

        st.execute(sql);

        sql = "INSERT INTO casterClasses (casterClass) VALUES" +
                "('Bard'), ('Cleric'), ('Druid'), ('Paladin'), ('Ranger')," +
                "('Sorcerer'), ('Warlock'),('Wizard')";

        st.execute(sql);
        conn.close();
    }


    public static void main(String[] args)throws SQLException{

        //createSpellTable("spellbookDatabase.db");
        //createCasterTable();
        createSpellBookTableAndConnector("spellbookDatabase.db");
    }

    private static Connection connect(){
        String url = "jdbc:sqlite:src/main/resources/static/spellbookDatabase.db";
        try{
            Connection conn = DriverManager.getConnection(url);
            return conn;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
