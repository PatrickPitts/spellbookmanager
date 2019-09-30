package org.nerdcore.spellbookmanager;

import org.nerdcore.spellbookmanager.models.SpellSearchParams;
import org.nerdcore.spellbookmanager.models.Spell;
import org.nerdcore.spellbookmanager.models.SpellBook;

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
    public static void addSpellListToDatabase(List<Spell> spellList) throws SQLException{
        //TODO: Implement single sql update, rather than iterating
        for(Spell spell: spellList) {
            addSingleSpellToSpellCollection(spell);
        }
    }

    public static void addSingleSpellToSpellCollection(Spell spell) throws SQLException{
        Connection conn = connect();
        String preparedString = "INSERT INTO spellCollection " +
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

        PreparedStatement ps = conn.prepareStatement(preparedString);

        ps.setString(1, spell.getName());
        ps.setString(2, spell.getDescription());
        ps.setInt(3, spell.getSpellLevel());
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

    }


    //Takes a Spell object, prepares an SQL Query String to add the Spell object data to the database,
    //the attempts to connect to the database, and execute the SQL Query.
/*
    public static void addSingleSpellToDatabase(Spell spell){
        //TODO: Phase this method out
        Connection conn = connect();
        String preparedString = "INSERT INTO spells " +
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
            ps.setInt(3, spell.getSpellLevel());
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
*/

    public static Spell getSingleSpellBySpellName(String spellName) throws SQLException{
        Connection conn = connect();
        String prepString = "SELECT * FROM spellCollection WHERE spellName IS ?;";

        PreparedStatement sql = conn.prepareStatement(prepString);
        sql.setString(1, spellName);
        ResultSet rs = sql.executeQuery();
        Spell spell = new Spell(rs);

        conn.close();
        return spell;


    }

    public static List<Spell> getAllSpellsAsListAlphabatized() throws SQLException{

        Connection conn = connect();
        Statement st = conn.createStatement();
        List<Spell> spellList = new ArrayList<>();
        String sql;
        //String sql = "SELECT *, rowid FROM spells";

        sql = "SELECT * from spellCollection ORDER BY spellName;";
        ResultSet rs = st.executeQuery(sql);

        while(rs.next()){
            spellList.add(new Spell(rs));
        }
        conn.close();

        return spellList;
    }

    public static List<Spell> searchForSpells(SpellSearchParams params) throws SQLException{

        //TODO implement casters

        Connection conn = connect();
        Statement st = conn.createStatement();
        List<Spell> spellsToReturn = new ArrayList<>();


        String sql = "SELECT * FROM spellCollection";
        if(params.isEmpty()){
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
        String sql = "DELETE from spellCollection WHERE spellName IS '" + spellName +"';";
        Connection conn = connect();
        Statement st = conn.createStatement();
        st.execute(sql);
        conn.close();
    }

    public static void editSpell(Spell spellToEdit) throws SQLException{

        Connection conn = connect();
        String sql = "UPDATE spellCollection SET " +
                "spellName = ?, " +
                "description = ?, " +
                "spellLevel = ?, " +
                "school = ?, " +
                "castingTime = ?, " +
                "range = ?, " +
                "verbalComponent = ?, " +
                "somaticComponent = ?, " +
                "ritualCasting = ?, " +
                "concentration = ?, " +
                "materialComponents = ?, " +
                "source = ? " +
                "WHERE spellID = ?;";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, spellToEdit.getName());
        ps.setString(2,spellToEdit.getDescription());
        ps.setInt(3, spellToEdit.getSpellLevel());
        ps.setString(4,spellToEdit.getSchool());
        ps.setString(5, spellToEdit.getCastingTime());
        ps.setString(6, spellToEdit.getRange());
        ps.setBoolean(7, spellToEdit.isVerbalComponent());
        ps.setBoolean(8,spellToEdit.isSomaticComponent());
        ps.setBoolean(9, spellToEdit.isRitualCasting() );
        ps.setBoolean(10, spellToEdit.isConcentration());
        ps.setString(11, spellToEdit.getMaterialComponents());
        ps.setString(12, spellToEdit.getSource());
        ps.setInt(13, spellToEdit.getId());

        ps.executeUpdate();
        conn.close();
    }

    public static void addSpellbookToDatabase(SpellBook spellbook) throws SQLException{

        //TODO: Name sanitization; this should fail if the name is already present in the spellBooks table

        Connection conn = connect();
        Statement st = conn.createStatement();

        String sql = "INSERT INTO spellBooks (spellbookName) VALUES(?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, spellbook.getSpellbookName());

        ps.execute();
        conn.close();
    }

    public static List<String> getAllCastersAsList() throws SQLException{
        List<String> casterList = new ArrayList<>();
        Connection conn = connect();
        Statement st = conn.createStatement();
        String sql = "SELECT * FROM casterClasses;";
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            casterList.add(rs.getString("casterClass"));
        }
        return casterList;
    }

    public static List<SpellBook> getAllSpellbooksAsList()throws SQLException{
        List<SpellBook> spellBooks = new ArrayList<>();

        Connection conn = connect();
        Statement st = conn.createStatement();
        String sql = "SELECT * FROM spellBooks;";

        ResultSet rs = st.executeQuery(sql);

        while(rs.next()){
            spellBooks.add(new SpellBook(rs));

        }

        return spellBooks;
    }

    public static SpellBook getSingleSpellbookBySpellbookName(String spellbookName) throws SQLException{

        //TODO:
        Connection conn = connect();
        //Statement st = conn.createStatement();

        String sql = "SELECT * FROm spellBooks WHERE spellbookName IS ?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, spellbookName);

        ResultSet rs = ps.executeQuery();
        SpellBook bookToReturn = new SpellBook(rs);
        conn.close();
        return bookToReturn;



    }

    public static List<String> getSpellbookNames()throws SQLException{
        //TODO: Finish spellbook search
        List<String> spellbookNameList = new ArrayList<>();

        Connection conn = connect();
        String sql = "SELECT spellbookName from spellBooks;";

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            spellbookNameList.add(rs.getString("spellbookName"));
        }

        conn.close();
        return spellbookNameList;

    }

    public static SpellBook getSpellbookBySpellbookID(int spellbookID) throws SQLException{
        Connection conn = connect();
        PreparedStatement ps = conn.prepareStatement(String.format(
                "SELECT * FROM spellBooks WHERE spellbookID IS ?"
        ));
        ps.setInt(1, spellbookID);

        SpellBook spellbook = new SpellBook(ps.executeQuery());

        spellbook.setListOfSpells(getAllSpellsInSpellbookBySpellbookID(spellbookID));

        return spellbook;

    }

    public static List<Spell> getAllSpellsInSpellbookBySpellbookID(int spellbookID)throws SQLException{



        List<Spell> spellbookSpellList = new ArrayList<>();
        Connection conn = connect();

        PreparedStatement ps = conn.prepareStatement(String.format(
                "SELECT * FROM spellCollection WHERE spellName IN " +
                        "(SELECT spellName FROM spellBookStorage WHERE spellbookID IS ?)" +
                        "ORDER BY spellLevel; "
        ));

        ps.setInt(1,spellbookID);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            Spell spell = new Spell(rs);
            spellbookSpellList.add(spell);
        }

        return spellbookSpellList;
    }

}
