package org.nerdcore.spellbookmanager;

import org.nerdcore.spellbookmanager.models.SearchParams;
import org.nerdcore.spellbookmanager.models.Spell;
import org.nerdcore.spellbookmanager.models.SpellBook;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SpellDatabaseManager {

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
    /*
    Takes in a List<Spell> called spellList
    For each Spell in spellList, creates a PreparedStatement object with the SQL
    query populated by that spell, then attempts to update the 'spells' table in the
    spellbookDatabase.db file.
    Used and intended to help convert the outdated JSON persistence structure to the SQL friendly
    Database structure.
         */
    public static void addSpellListToDatabase(List<Spell> spellList){

        for(Spell spell: spellList) {
            addSingleSpellToDatabase(spell);
        }
    }

    //Takes a Spell object, prepares an SQL Query String to add the Spell object data to the database,
    //the attempts to connect to the database, and execute the SQL Query.
    public static void addSingleSpellToDatabase(Spell spell){
        Connection conn = connect();
        String preparedString = "INSERT INTO spells" +
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
        try (PreparedStatement ps = conn.prepareStatement(preparedString)) {
            ps.setString(1, spell.getName());
            ps.setString(2, spell.getDescription());
            ps.setInt(3, spell.getLevel());
            ps.setString(4, spell.getSchool());
            ps.setString(5, spell.getCastingTime());
            ps.setString(6, spell.getRange());
            ps.setBoolean(7, spell.isVerbalComponent());
            ps.setBoolean(8, spell.isSomaticComponent());
            ps.setBoolean(9, spell.isRitualCasting());
            ps.setBoolean(10, spell.isConcentration());
            ps.setString(11, spell.getMaterialComponents());
            ps.setString(12, spell.getDuration());
            ps.setString(13, spell.getSource());

            ps.executeUpdate();
            conn.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Spell getSingleSpellBySpellName(String spellName){
        Connection conn = connect();
        String prepString = "SELECT * FROM spells WHERE spellName IS ?;";
        try {
            Statement st = conn.createStatement();
            PreparedStatement sql = conn.prepareStatement(prepString);
            sql.setString(1, spellName);
            ResultSet rs = sql.executeQuery();
            Spell spell = new Spell(rs);

            conn.close();
            return spell;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new Spell();

    }

    public static List<Spell> getAllSpellsAsList(){

        Connection conn = connect();
        String sql = "SELECT * FROM spells";
        List<Spell> spellList = new ArrayList<>();
        try (Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                spellList.add(new Spell(rs));
            }
            conn.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        return spellList;
    }

    public static List<Spell> searchForSpells(SearchParams params) throws SQLException{

        //TODO implement casters

        Connection conn = connect();
        Statement st = conn.createStatement();
        List<Spell> spellsToReturn = new ArrayList<>();


        String sql = "SELECT * FROM spells";
        if(params.isEmpty()){
            System.out.println("Empty Params");
            sql += ";";
        } else {
            sql+=" WHERE ";
            if(!params.getSpellName().equals("")){
                sql += "spellName LIKE '%" + params.getSpellName() + "%' AND ";
            }
            if(!params.getSchool().equals("")){
                sql += "school IS '" + params.getSchool() + "' AND ";
            }
            if(!params.getSpellLevel().equals("N")){
                sql += "spellLevel IS " + params.getSpellLevel() + " AND ";
            }
            if(params.isConcentration()){
                sql += "concentration IS TRUE AND ";
            }
            if(params.isRitualCasting()){
                sql += "ritualCasting IS TRUE AND ";
            }
            sql = sql.substring(0, sql.length()-5);
            sql += ";";
        }
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            spellsToReturn.add(new Spell(rs));
        }
        conn.close();
        return spellsToReturn;
    }

    public static void deleteSpellByName(String spellName) throws SQLException{
        String sql = "DELETE from spells WHERE spellName IS '" + spellName +"';";
        Connection conn = connect();
        Statement st = conn.createStatement();
        st.execute(sql);
        conn.close();
    }

    public static void editSpell(Spell spellToEdit) throws SQLException{
        deleteSpellByName(spellToEdit.getName());
        addSingleSpellToDatabase(spellToEdit);

    }

    public static void addSpellbook(SpellBook spellbook) throws SQLException{
        Connection conn = connect();
        Statement st = conn.createStatement();

        String sql = "INSERT INTO spellBooks (spellbookName) VALUES(?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, spellbook.getSpellbookName());

        ps.execute();
        conn.close();
    }
}
