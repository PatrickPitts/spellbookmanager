package org.nerdcore.spellbookmanager.databaseInit;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;

public class CreateDB {

    public static void createNewDatabase(String dbFilename){

        String url = "jdbc:sqlite:src/main/resources/static/" + dbFilename;
        try(Connection conn = DriverManager.getConnection(url)){
            DatabaseMetaData meta = conn.getMetaData();
            System.out.println("The driver name is " + meta.getDriverName());
            System.out.println(String.format("A database called %s has been created!",dbFilename));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        createNewDatabase("spellbookDatabase.db");
    }


}
