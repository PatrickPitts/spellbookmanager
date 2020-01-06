package org.nerdcore.spellbookmanager;

import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

class DatabaseConnect {


    private static String url = "jdbc:sqlite:src/main/resources/static/spellbookDatabase.db";

    static Connection getConnection() throws SQLException{

        return DriverManager.getConnection(url);

    }

}
