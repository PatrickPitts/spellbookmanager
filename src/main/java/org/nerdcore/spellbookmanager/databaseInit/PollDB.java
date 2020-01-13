package org.nerdcore.spellbookmanager.databaseInit;

import org.nerdcore.spellbookmanager.repository.SpellRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;

@SuppressWarnings("SqlDialectInspection")
public class PollDB {

    @Autowired
    static SpellRepository spellRepository;


    public static void main(String[] args) throws SQLException {

        //getTablesAndColumns();
        genericSQLCheck();


        //clearUsers();
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

    public static void genericSQLCheck() throws SQLException {
        Connection conn = connect();
        Statement st = conn.createStatement();
        String sql;

        Stopwatch s1 = new Stopwatch();

        sql = "SELECT * FROM spellCollection WHERE spellName IS ?";
        PreparedStatement ps1 = conn.prepareStatement(sql);

        ps1.setString(1, "Fireball");
        ps1.execute();
        System.out.println(s1.getElapsedTime());


        Stopwatch s2 = new Stopwatch();
        sql = "SELECT * FROM spellCollection WHERE spellName LIKE ?";
        PreparedStatement ps2 = conn.prepareStatement(sql);

        ps2.setString(1, "Fireball");
        ps2.execute();
        System.out.println(s2.getElapsedTime());
        conn.close();
    }

    public static void clearUsers() throws SQLException {
        Connection conn = connect();
        Statement st = conn.createStatement();
        String sql;

        sql = "DELETE FROM users WHERE username NOT IN ('admin', 'spugneemo');";
        st.execute(sql);
        conn.close();
    }


    public static void testSpellCollection() throws SQLException {
        Connection conn = connect();
        assert conn != null;
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql;
        sql = "SELECT * FROM spellCollection ORDER BY spellName;";
        rs = st.executeQuery(sql);
        while (rs.next()) {
            System.out.print(rs.getInt("spellID"));
            System.out.println(rs.getString("spellName"));
        }
        System.out.println("_____________________");

        sql = "SELECT * FROM spellCollection ORDER BY school;";
        rs = st.executeQuery(sql);
        while (rs.next()) {
            System.out.print(rs.getInt("spellID"));
            System.out.println(rs.getString("spellName"));
        }
    }


    public static void getCasters() throws SQLException {
        Connection conn = connect();
        Statement st = conn.createStatement();
        String sql = "SELECT * FROM casterClasses;";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getString("casterClass"));
        }
    }


    public static void getTablesAndColumns() throws SQLException {
        Connection conn = connect();
        Statement st = conn.createStatement();
        ResultSet rs = conn.getMetaData().getTables(null, null, null, null);
        while (rs.next()) {
            String tableName = rs.getString("TABLE_NAME");

            ResultSet tableResults = conn.createStatement().executeQuery("Select * FROM " + tableName + ";");

            ResultSetMetaData rsmd = tableResults.getMetaData();
            System.out.print("Table: ");

            System.out.println(tableName);
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                System.out.println("- " + rsmd.getColumnName(i));
            }
            System.out.println("-------------");
        }

        conn.close();
    }

    public static void dropSpellTable() {
        Connection conn = connect();

        try (Statement st = conn.createStatement()) {
            String sql = "DROP TABLE spells;";
            st.execute(sql);
            System.out.println("Dropped spells table");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void addTestSpellsToSpellBooks() throws SQLException {
        Connection conn = connect();
        assert conn != null;
        Statement st = conn.createStatement();
        String sql = "INSERT INTO spellBookStorage(spellbookID, spellName) VALUES " +
                "(1, 'Acid Splash'),(1,'Alter Self'), (2,'Delayed Blast Fireball'), (2,'Darkness');";
        st.execute(sql);
        conn.close();

    }

    public static void printAllRowsOfSpellbookStorage() throws SQLException {
        Connection conn = connect();
        assert conn != null;
        Statement st = conn.createStatement();
        String sql = "SELECT * FROM spellBookStorage";

        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            System.out.print(rs.getInt("spellbookID"));
            System.out.println(rs.getString("spellName"));
        }

    }

    private static Connection connect() {
        String url = "jdbc:sqlite:src/main/resources/static/spellbookDatabase.db";
        try {
            return DriverManager.getConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
