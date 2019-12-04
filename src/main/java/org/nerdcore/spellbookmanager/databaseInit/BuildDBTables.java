package org.nerdcore.spellbookmanager.databaseInit;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class BuildDBTables {

    public static void createSpellsToCasterTable() throws SQLException {
        Connection conn = connect();
        Statement st = conn.createStatement();
        String sql;
        sql = "CREATE TABLE spellCasterAssignment (casterClass VARCHAR(64), spellID int);";
        st.execute(sql);
        conn.close();
        //TODO: create spellID and casterClass table
    }

    public static void createSpellTable(String dbFilename) {

        Connection conn = null;
        String url = "jdbc:sqlite:src/main/resources/static/" + dbFilename;
        try {
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


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createSpellBookTableAndConnector(String dbFilename) {

        Connection conn = null;
        String url = "jdbc:sqlite:src/main/resources/static/" + dbFilename;
        try {
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


        } catch (Exception e) {
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

    public static void createSpellCollectionTable() throws SQLException {
        Connection conn = connect();
        Statement st = conn.createStatement();
        String sql;

        /*sql = sql = "CREATE TABLE IF NOT EXISTS spellCollection(\n"
                + "spellID integer PRIMARY KEY,\n"
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
                + "source varchar(32));";


        st.execute(sql);

        sql = "INSERT INTO spellCollection " +
                "(spellName, description, source) VALUES" +
                "('test spell', 'This is a testing spell', 'test');";

        st.execute(sql);*/

        sql = "INSERT INTO spellCollection " +
                "(spellName, description, source) VALUES" +
                "('2nd test spell', 'This is another testing spell', 'test');";

        st.execute(sql);

        sql = "SELECT * FROM spellCollection;";
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            System.out.print(rs.getInt("spellID"));
            System.out.println(rs.getString("spellName"));
        }
    }


    public static void createTestTable() throws SQLException {
        Connection conn = connect();
        Statement st = conn.createStatement();
        String sql;
               /* sql= "CREATE TABLE test IF NOT EXISTS (" +
                "testID INTEGER PRIMARY KEY," +
                "textValue VARCHAR(64));";
        st.execute(sql);*/
        sql = "INSERT INTO test(textValue) VALUES " +
                "('text_1'), ('text_2'), ('text_3'); ";

        st.execute(sql);

        sql = "SELECT * FROM test;";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getInt("testID") + "  " + rs.getString("textValue"));
        }
    }

    public static void createUserTable() throws SQLException {
        Connection conn = connect();
        String sql;
        Statement st = conn.createStatement();

//        sql = "CREATE TABLE users IF NOT EXISTS (username VARCHAR(64), password VARCHAR(64), role VARCHAR(64));";
//        st.execute(sql);

        sql="INSERT INTO users VALUES ('admin', 'adminadministrator', 'ADMIN'), " +
                "('test','password','USER');";

        st.execute(sql);
        conn.close();
    }

    public static void addUsernameToSpellbook() throws SQLException{
        Connection conn = connect();
        String sql;
        Statement st = conn.createStatement();

        sql= "ALTER TABLE spellBooks ADD username VARCHAR(64);";
        st.execute(sql);

        sql="UPDATE spellBooks SET username = 'spugneemo';";
        st.execute(sql);
        conn.close();

    }

    public static void main(String[] args) throws SQLException {

        //createSpellTable("spellbookDatabase.db");
        //createCasterTable();
        //createSpellBookTableAndConnector("spellbookDatabase.db");
        //createTestTable();
        //createSpellCollectionTable();
        //createSpellsToCasterTable();
        //createUserTable();
        //addUsernameToSpellbook();
    }

    private static Connection connect() {
        String url = "jdbc:sqlite:src/main/resources/static/spellbookDatabase.db";
        try {
            Connection conn = DriverManager.getConnection(url);
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
