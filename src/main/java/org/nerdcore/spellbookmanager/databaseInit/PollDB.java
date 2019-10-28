package org.nerdcore.spellbookmanager.databaseInit;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.nerdcore.spellbookmanager.SpellDatabaseManager;
import org.nerdcore.spellbookmanager.models.Spell;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("SqlDialectInspection")
public class PollDB {

    public static void main(String[] args) throws SQLException{
        //genericSQLCheck();

        getTablesAndColumns();


        //getCasters();
        //checkSpellTableColumns();
        //addTestSpell();
        //checkTestSpell();
        //printAllRowsOfSpellbookStorage();
        //addTestSpellsToSpellBooks();
        //testSpellList();
        //SpellDatabaseManager.getSingleSpellFromSpellName("Aid");
        //BuildDBTables.createSpellBookTableAndConnector("spellbookDatabase.db");
        //SpellDatabaseManager.getAllSpellsInSpellbookBySpellbookID(2);
        //migrateFromSpellsToSpellCollection();
        //testSpellCollection();
        /*for(Spell spell: SpellDatabaseManager.getAllSpellsInSpellbookBySpellbookID(1)){
            System.out.println(spell);
        }*/

    }

    public static void genericSQLCheck() throws SQLException{
        Connection conn = connect();
        Statement st = conn.createStatement();
        String sql;

        sql= "SELECT DISTINCT casterClass FROM spellCasterAssignment;";
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            System.out.println(rs.getString(1));
        }


        conn.close();
    }


    public static void testSpellCollection() throws SQLException{
        Connection conn = connect();
        assert conn != null;
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql;
        sql = "SELECT * FROM spellCollection ORDER BY spellName;";
        rs = st.executeQuery(sql);
        while(rs.next()){
            System.out.print(rs.getInt("spellID"));
            System.out.println(rs.getString("spellName"));
        }
        System.out.println("_____________________");

        sql = "SELECT * FROM spellCollection ORDER BY school;";
        rs = st.executeQuery(sql);
        while(rs.next()){
            System.out.print(rs.getInt("spellID"));
            System.out.println(rs.getString("spellName"));
        }
    }


    public static void getCasters() throws SQLException{
        Connection conn = connect();
        Statement st = conn.createStatement();
        String sql = "SELECT * FROM casterClasses;";
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            System.out.println(rs.getString("casterClass"));
        }
    }


    public static void getTablesAndColumns() throws SQLException{
        Connection conn = connect();
        Statement st = conn.createStatement();
        ResultSet rs =  conn.getMetaData().getTables(null,null,null,null );
        while(rs.next()){
            String tableName = rs.getString("TABLE_NAME");

            ResultSet tableResults = conn.createStatement().executeQuery("Select * FROM "+tableName+";");

            ResultSetMetaData rsmd = tableResults.getMetaData();
            System.out.print("Table: ");

            System.out.println(tableName);
            for(int i = 1; i <= rsmd.getColumnCount(); i++){
                System.out.println("- " + rsmd.getColumnName(i));
            }
            System.out.println("-------------");
        }

        conn.close();
    }


    public static void JSONStorageToDatabase(){
        //SpellDatabaseManager.addSpellListToDatabase(SpellJSONProcesser.getAllSpellsAsListAlphabetized());
    }


    public static void dropSpellTable(){
        Connection conn = connect();

        try (Statement st = conn.createStatement()) {
            String sql = "DROP TABLE spells;";
            st.execute(sql);
            System.out.println("Dropped spells table");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    public static void addTestSpellsToSpellBooks() throws SQLException{
        Connection conn = connect();
        assert conn != null;
        Statement st = conn.createStatement();
        String sql = "INSERT INTO spellBookStorage(spellbookID, spellName) VALUES " +
                "(1, 'Acid Splash'),(1,'Alter Self'), (2,'Delayed Blast Fireball'), (2,'Darkness');";
        st.execute(sql);
        conn.close();

    }

    public static void printAllRowsOfSpellbookStorage() throws SQLException{
        Connection conn = connect();
        assert conn != null;
        Statement st = conn.createStatement();
        String sql = "SELECT * FROM spellBookStorage";

        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            System.out.print(rs.getInt("spellbookID"));
            System.out.println(rs.getString("spellName"));
        }

    }

    private static Connection connect(){
        String url = "jdbc:sqlite:src/main/resources/static/spellbookDatabase.db";
        try{
            return DriverManager.getConnection(url);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
