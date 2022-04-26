package datasource;

import models.Person;

import java.sql.*;
import java.util.ArrayList;

public class MariaDB {
    private String conStr = "jdbc:mysql://localhost/persondb";
    private String user = "personuser";
    private String password = "Passw0rd";
    private Connection con;

    public MariaDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void connect() {
        try {
            con = DriverManager.getConnection(conStr, user, password);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        if (con != null) {
            System.out.println("Connected...");
        }
        else {
            System.out.println("Not Connected...");
        }
    }

    private void close() {
        try {
            con.close();
            System.out.println("DisConnection");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Person> getAllPersons() {
        connect();
        ArrayList<Person> pList = new ArrayList<>();

        String sql = "select * from person";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            //if (rs.next()) {
                while (rs.next()) {
                    Person p = new Person();
                    p.setPersId(rs.getInt("id"));
                    p.setFullName(rs.getString("fullName"));
                    p.setEmail(rs.getString("email"));
                    p.setNote(rs.getString("note"));
                    pList.add(p);
                }
            //}
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        close();
        return pList;
    }

    public Person getPerson(int persId) {
        connect();
        Person p = new Person();

        String sql = "select * from person where id = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, persId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                p.setPersId(rs.getInt("id"));
                p.setFullName(rs.getString("fullName"));
                p.setEmail(rs.getString("email"));
                p.setNote(rs.getString("note"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        close();
        return p;
    }

    public int updatePersList(int persId, Person pers) {
        connect();
        int result = 0;

        String sql = "update person set fullName = ?, email = ?, note = ? where id = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, pers.getFullName());
            pstmt.setString(2, pers.getEmail());
            pstmt.setString(3, pers.getNote());
            pstmt.setInt(4, persId);
            result = pstmt.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        close();
        return result;
    }

    public int deleteFromPersList(int persId) {
        connect();
        int result = 0;

        String sql = "delete from person where id = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, persId);
            result = pstmt.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        close();
        return result;
    }

    public int insertInPersList(Person pers) {
        connect();
        int result = 0;

        String sql = "insert into person (fullName, email, note) values(?,?,?)";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, pers.getFullName());
            pstmt.setString(2, pers.getEmail());
            pstmt.setString(3, pers.getNote());
            result = pstmt.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        close();
        return result;
    }
}
