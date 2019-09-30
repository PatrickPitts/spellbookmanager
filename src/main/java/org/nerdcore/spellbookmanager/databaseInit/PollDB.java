package org.nerdcore.spellbookmanager.databaseInit;

import org.nerdcore.spellbookmanager.SpellDatabaseManager;
import org.nerdcore.spellbookmanager.models.Spell;

import java.sql.*;

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

        String sql;
        sql = "SELECT * from spellCollection;";

        assert conn != null;
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            Spell spell = new Spell(rs);
            System.out.println(spell.getId());
            System.out.println(spell.getName());
        }

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

    public static void migrateFromSpellsToSpellCollection()throws SQLException{

        Connection conn = connect();
        Statement st = conn.createStatement();
        String sql;

        sql = "INSERT INTO spellCollection SELECT * FROM spells;";

        st.execute(sql);



        /*List<Spell> spellTableList = SpellDatabaseManager.getAllSpellsAsListAlphabatized();
        System.out.println(spellTableList.get(1).getName());
        for(Spell spell : spellTableList){
            SpellDatabaseManager.addSingleSpellToSpellCollection(spell);
        }*/

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

    public static void testSpellList() throws SQLException{
        for(Spell spell : SpellDatabaseManager.getAllSpellsAsListAlphabatized()){
            System.out.println(spell.getName());
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

    public static void testSingleSpell() throws SQLException{
        Spell testSpell = SpellDatabaseManager.getSingleSpellBySpellName("Aid");
        System.out.println(testSpell);
    }

    public static void JSONStorageToDatabase(){
        //SpellDatabaseManager.addSpellListToDatabase(SpellJSONProcesser.getAllSpellsAsListAlphabatized());
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
            assert conn != null;
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
