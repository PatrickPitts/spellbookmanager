package org.nerdcore.spellbookmanager;

import org.nerdcore.spellbookmanager.models.BasicUser;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoginDatabaseManager {

    public static Connection connect() {
        String url = "jdbc:sqlite:src/main/resources/static/spellbookDatabase.db";
        try {
            Connection conn = DriverManager.getConnection(url);
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    public static List<UserDetails> getUsersAsList() throws SQLException {
//
//        List<UserDetails> userDetails = new ArrayList<>();
//        Connection conn = connect();
//        String sql;
//
//        sql = "SELECT * FROM users";
//        ResultSet rs = conn.createStatement().executeQuery(sql);
//
//        while (rs.next()) {
//            userDetails.add(User.withDefaultPasswordEncoder()
//                    .username(rs.getString(1))
//                    .password(rs.getString(2))
//                    .roles(rs.getString(3))
//                    .build());
//        }
//
//        conn.close();
//
//        return userDetails;
//    }
    public static boolean checkUser(BasicUser user) throws SQLException {

        Connection conn = connect();
        String sql;
        PreparedStatement ps;

        sql = "SELECT * FROM users WHERE username is ? AND password IS ?;";
        ps = conn.prepareStatement(sql);
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword());
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            conn.close();
            return true;
        }
        conn.close();
        return false;
    }

    public static boolean addUser(BasicUser user) throws SQLException {

        Connection conn = connect();
        String sql;
        PreparedStatement ps;

        sql = "SELECT username FROM users WHERE username IS ?;";
        ps = conn.prepareStatement(sql);
        ps.setString(1, user.getUsername());
        ResultSet rs = ps.executeQuery();

        if (!rs.next()) {
            sql = "INSERT INTO users (username, password, role)" +
                    "VALUES (?,?,?)";

            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, "USER");

            ps.executeUpdate();

            conn.close();
            return true;
        }


        conn.close();
        return false;

    }

}
