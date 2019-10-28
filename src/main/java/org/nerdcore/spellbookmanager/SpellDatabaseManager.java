package org.nerdcore.spellbookmanager;

import org.nerdcore.spellbookmanager.models.CasterSpellList;
import org.nerdcore.spellbookmanager.models.SpellSearchParams;
import org.nerdcore.spellbookmanager.models.Spell;
import org.nerdcore.spellbookmanager.models.SpellBook;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SpellDatabaseManager {

    /**
     *  Attempts to connect to the spellbookDatabase.db file using java.sql.DriverManager
     *  this connection to the spellbookDatabase.db is used for all spell focused operations for the spellbookmanager project
     *
     * @return Connection object, connecting to the spellbookDatabase.db file
     */
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

    /**
     * Inserts all relevant data for a Spell object into the spellbookDatabase.db file
     *
     * Creates a connection to the spellbookDatabase.db file. Prepares a String that is a valid SQL Insert query,
     * in the format necessary for an Sqlite PreparedStatement Object. The PreparedStatement is populated by the spell
     * data, the SQL query is executed, and the connection to the spellbookDatabase.db file is closed.
     * @param spell , the Spell object containing all relevant data for a spell to be stored in the database
     * @throws SQLException, if the connection to the database cannot be executed.
     */
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

    /**
     * Inserts a spellName into the spellBookStorage table
     *
     * Creates a connection to the spellbookDatabase.db file. Creates a String appropriate for an Sqlite PreparedStatement
     * object to insert data into the spellbookDatabase.db Databased, then creates the PreparedStatement object directly.
     * Populates the PreparedStatement with the appropriate field data, executes the PreparedStatement query,
     * then closes the spellbookDatabase.db connection.
     *
     * @param spellName
     * @param spellbookID
     * @throws SQLException
     */
    public static void addSingleSpellToSpellBook(String spellName, int spellbookID) throws SQLException{

        Connection conn = connect();
        String sql;

        sql="INSERT INTO spellBookStorage (spellbookID, spellName) VALUES" +
                "(?, ?);";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, spellbookID);
        ps.setString(2, spellName);

        ps.executeUpdate();

        conn.close();
    }

    /**
     * Returns a Spell object populated with data appropriate for the spell with the @param spellName
     *
     * Creates a connection to the spellbookDatabase.db file. Creates a String appropriate for an Sqlite PreparedStatement
     * object to insert data into the spellbookDatabase.db Databased, then creates the PreparedStatement object directly.
     * Populates the PreparedStatement with the appropriate field data, executes the PreparedStatement query,
     * then closes the spellbookDatabase.db connection.
     *
     * @param spellName
     * @return Spell object populated with data appropriate for the spell with the @param spellName
     * @throws SQLException
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

    /**
     * Returns a list of all rows found in the spellbookDatabase.db spellCollection table, ordered by ascending spellName
     *
     * Creates a connection to the spellbookDatabase.db file. Creates an String that is the appropriate SQL query to
     * get all data from the spellCollection table. Gets the ResultSet Object from executing the query, then iterates
     * through the result set and populates a List of Spell Objects based on the ResultSet. Closes the connection, then
     * returns the populated List of Spell Objects.
     * @return List of Spell Objects, ordered by ascending spellName
     * @throws SQLException
     */
    public static List<Spell> getAllSpellsAsListAlphabetized() throws SQLException{

        Connection conn = connect();
        Statement st = conn.createStatement();
        List<Spell> spellList = new ArrayList<>();
        String sql;

        sql = "SELECT * from spellCollection ORDER BY spellLevel, spellName;";
        ResultSet rs = st.executeQuery(sql);

        while(rs.next()){
            spellList.add(new Spell(rs));
        }
        conn.close();

        return spellList;
    }

    /**
     * Returns a List of Spell Objects based on the SpellSearchParams field data. This contains all the user input search
     * parameters from the spelldirectory.jsp View. Fields with data are incorporated into a String that builds a valid
     * SQL Query, while empty fields are ignored. The SQL Query is executed, creating a ResultSet object, which is iterated
     * over to create the List of Spell objects. The connection to spellbookDatabase.db is closed, and the List
     * of Spell objects is returned.
     *
     * TODO: As of this todo, does not properly implement the restriction of the type of character class that can cast this spell.
     *
     * @param spellSearchParams
     * @return List of Spell objects matching the specified parameters in spellSearchParams
     * @throws SQLException
     */
    public static List<Spell> searchForSpells(SpellSearchParams spellSearchParams) throws SQLException{

        Connection conn = connect();
        Statement st = conn.createStatement();
        List<Spell> spellsToReturn = new ArrayList<>();


        String sql = "SELECT * FROM spellCollection";

        String casterSql = "(SELECT * FROM spellCollection WHERE (SELECT ))";

        if(spellSearchParams.isEmpty()){
            sql += ";";
        } else {
            sql+=" WHERE ";
            if(!spellSearchParams.getCaster().equals("")){
                sql += "spellID IN (SELECT spellID FROM spellCasterAssignment WHERE casterClass IS '"+ spellSearchParams.getCaster()+ "' ) AND ";
            }
            if(!spellSearchParams.getSpellName().equals("")){
                sql += "spellName LIKE '%" + spellSearchParams.getSpellName() + "%' AND ";
            }
            if(!spellSearchParams.getSchool().equals("")){
                sql += "school IS '" + spellSearchParams.getSchool() + "' AND ";
            }
            if(!spellSearchParams.getSpellLevel().equals("N")){
                sql += "spellLevel IS " + spellSearchParams.getSpellLevel() + " AND ";
            }
            if(spellSearchParams.isConcentration()){
                sql += "concentration IS TRUE AND ";
            }
            if(spellSearchParams.isRitualCasting()){
                sql += "ritualCasting IS TRUE AND ";
            }
            //Truncates extra ' AND ' characters automatically added to each possible parameter.
            sql = sql.substring(0, sql.length()-5);
            //TODO: Needs 'ORDER BY spellName' incorporated, works as is.
            sql += " ORDER BY spellLevel, spellName;";
        }
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            spellsToReturn.add(new Spell(rs));
        }
        conn.close();
        return spellsToReturn;
    }

    /**
     * Removes all data associated with the spellName field
     *
     * @param spellName
     * @throws SQLException
     */
    public static void deleteSpellByName(String spellName) throws SQLException{
        Connection conn = connect();
        String sql;

        sql="DELETE FROM spellCollection WHERE spellName IS ?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, spellName);
        ps.executeUpdate();

        sql="DELETE FROM spellBookStorage where spellName is ?";
        ps = conn.prepareStatement(sql);
        ps.setString(1, spellName);
        ps.executeUpdate();

        conn.close();
    }

    public static void removeSpellFromSpellbook(String spellName) throws SQLException{
        Connection conn = connect();
        String sql;

        sql="DELETE FROM spellBookStorage where spellName is ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, spellName);
        ps.executeUpdate();
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
        conn.close();
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
        conn.close();
        return spellBooks;
    }

    public static SpellBook getSingleSpellbookBySpellbookName(String spellbookName) throws SQLException{

        Connection conn = connect();


        String sql = "SELECT * FROm spellBooks WHERE spellbookName IS ?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, spellbookName);

        ResultSet rs = ps.executeQuery();
        SpellBook bookToReturn = new SpellBook(rs);
        conn.close();
        return bookToReturn;
    }

    public static void deleteSpellbookBySpellbookID(int spellbookID) throws SQLException{
        Connection conn = connect();
        String sql = "DELETE FROM spellBooks where spellbookID IS ?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, spellbookID);
        ps.executeUpdate();

        sql = "DELETE FROM spellBookStorage where spellbookID IS ?;";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, spellbookID);
        ps.executeUpdate();

        conn.close();
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
        conn.close();
        return spellbook;

    }

    public static List<Spell> getAllSpellsInSpellbookBySpellbookID(int spellbookID)throws SQLException{

        List<Spell> spellbookSpellList = new ArrayList<>();
        List<String> spellbookSpellNameList = new ArrayList<>();
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
            spellbookSpellNameList.add(spell.getName());
            spellbookSpellList.add(spell);
        }
        conn.close();
        return spellbookSpellList;
    }

    public static void addSpellsToCasterTable(CasterSpellList casterSpellList) throws SQLException{

        Connection conn = connect();
        String sql;
        ResultSet rs;
        PreparedStatement ps;
        String casterName = casterSpellList.getCasterClassName();
        List<String> spellNames = casterSpellList.getListOfSpellNames();

        for(String spellName: spellNames){
            sql = "SELECT spellID FROM spellCollection where spellName IS ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, spellName);
            rs = ps.executeQuery();

            sql = "INSERT INTO spellCasterAssignment VALUES (?, ?);";
            ps = conn.prepareStatement(sql);
            ps.setString(1, casterName);
            ps.setInt(2,rs.getInt("spellID"));
            ps.executeUpdate();
        }

        conn.close();
    }

}
