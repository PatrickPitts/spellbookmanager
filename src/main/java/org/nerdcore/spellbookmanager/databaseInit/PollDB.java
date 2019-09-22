package org.nerdcore.spellbookmanager.databaseInit;

import org.nerdcore.spellbookmanager.SpellDatabaseManager;
import org.nerdcore.spellbookmanager.SpellJSONProcesser;
import org.nerdcore.spellbookmanager.models.Spell;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class PollDB {

    public static void main(String[] args) throws SQLException{
        //getCasters();
        //checkSpellTableColumns();
        //addTestSpell();
        //checkTestSpell();
        //getTablesAndColumns();
        //genericSQLCheck();
        //testSpellList();
        //SpellDatabaseManager.getSingleSpellFromSpellName("Aid");
        //BuildDBTables.createSpellBookTableAndConnector("spellbookDatabase.db");


/*
        dropSpellTable();
        BuildDBTables.createSpellTable("spellbookDatabase.db");
        JSONStorageToDatabase();
*/
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

    public static void testSpellList(){
        for(Spell spell : SpellDatabaseManager.getAllSpellsAsList()){
            System.out.println(spell.getName());
        }
    }

    public static void getTablesAndColumns() throws SQLException{
        Connection conn = connect();
        Statement st = conn.createStatement();
        ResultSet rs =  conn.getMetaData().getTables(null,null,null,null );
        while(rs.next()){
            String tableName = rs.getString("TABLE_NAME");

//            PreparedStatement ps = conn.prepareStatement("SELECT * from ");
//            ps.setString(1, tableName);
//            ResultSet tableResults = ps.executeQuery();
            ResultSet tableResults = conn.createStatement().executeQuery("Select * FROM "+tableName+";");

            ResultSetMetaData rsmd = tableResults.getMetaData();
            System.out.print("Table: ");

            System.out.println(tableName);
            //System.out.println("Columns: ");
            for(int i = 1; i <= rsmd.getColumnCount(); i++){
                System.out.println("- " + rsmd.getColumnName(i));
            }
            System.out.println("-------------");
        }

        conn.close();
    }

    public static void testSingleSpell(){
        Spell testSpell = SpellDatabaseManager.getSingleSpellBySpellName("Aid");
        System.out.println(testSpell);
    }

    public static void JSONStorageToDatabase(){
        SpellDatabaseManager.addSpellListToDatabase(SpellJSONProcesser.getAllSpellsAsList());
    }

    public static void checkTestSpell(){
        Connection conn = connect();

        try (Statement st = conn.createStatement()) {
            String sql = "SELECT * FROM spells";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                System.out.print(rs.getString("spellName"));
                System.out.print("  ");
                System.out.println(rs.getInt("spellID"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public static void genericSQLCheck(){
        Connection conn = connect();
        //String sql = "PRAGMA table_info(spells)";
        String sql = "SELECT rowid, spellName from spells where spellName is 'Divine Favor';";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                System.out.println(rs.getInt(1));
                System.out.println(rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addTestSpell(){
        Connection conn = connect();
        String sql = "INSERT INTO spells" +
                "(spellName, " +
                "description, " +
                "spellLevel, " +
                "school, " +
                "castingTime," +
                "range, " +
                "verbalComponent, " +
                "somaticComponent, " +
                "ritualCasting," +
                "concentration, " +
                "materialComponents, " +
                "duration, " +
                "source)" +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,"Test Spell Name 55");
            preparedStatement.setString(2, "Test Spell Description");
            preparedStatement.setInt(3,1);
            preparedStatement.setString(4,"Abjuration");
            preparedStatement.setString(5, "1 action");
            preparedStatement.setString(6,"Self");
            preparedStatement.setBoolean(7, true);
            preparedStatement.setBoolean(8, true);
            preparedStatement.setBoolean(9,true);
            preparedStatement.setBoolean(10, true);
            preparedStatement.setString(11, "None");
            preparedStatement.setString(12, "1 hour");
            preparedStatement.setString(13, "Player's Handbook");

            System.out.println("Prepared Statement Done");
            preparedStatement.executeUpdate();

            System.out.println("Update Executed");

        } catch (SQLException e) {
            e.printStackTrace();
        }

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
