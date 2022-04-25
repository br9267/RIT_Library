package com.example.rit_library;

import java.sql.*;
import java.util.ArrayList;

public class RIT_LibraryDatabase {
    private String database;
    private String server;
    private String port;
    private String database_name;
    private static String url;
    private static String user;
    private static String password;
    private static Connection connection = null;
    public static RIT_LibraryDatabase RITdatabase = null;

    public RIT_LibraryDatabase(String server,String database,String port, String database_name,String user, String password){
        this.server = server;
        this.database = database;
        this.port = port;
        this.database_name = database_name;
        this.user = user;
        this.password = password;
        this.url = server+"://"+database+":"+port+"/"+database_name;
    }

    public RIT_LibraryDatabase() {

    }

    public static boolean connect(){
        try {
            connection = DriverManager.getConnection(url, user, password);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static RIT_LibraryDatabase getInstance(){
        if(RITdatabase == null){
            RITdatabase = new RIT_LibraryDatabase("jdbc:" + "mysql", "localhost", "3306", "RIT_Library", "root","ritcroatia");
        }
        return RITdatabase;
    }
    public static boolean close(){
        try {
            if(connection != null){
                connection.close();
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public ArrayList<ArrayList<String>> getData(String sqlStatement) {
        ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
        ArrayList<String> list = new ArrayList<String>();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sqlStatement);
            ResultSetMetaData  rsmd = rs.getMetaData();
            while (rs.next()) {
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    list.add(rs.getString(i));
                }
                results.add(list);

            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        return results;
    }
    public ArrayList<ArrayList<String>> getData(String sqlStatement, boolean value){
        ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();

        try {
            if (value == true) {
                DatabaseMetaData databaseMetaData = connection.getMetaData();
                ArrayList<String> list = new ArrayList<String>();
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sqlStatement);
                ResultSetMetaData  rsmd = rs.getMetaData();
                for (int i = 1;i<=rsmd.getColumnCount();i++){
                    list.add(rsmd.getColumnLabel(i));
                }
                array.add(list);
            }
            array.addAll(this.getData(sqlStatement));
            return array;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return array;
    }
    public PreparedStatement prepare(String sqlStatement, ArrayList<String> list) throws SQLException{
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(sqlStatement);
            for (int i = 0; i<list.size();i++){
                stmt.setString(i+1, list.get(i));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return stmt;
    }
    public ArrayList<ArrayList<String>> getData(String sqlStatement, ArrayList<String> list, boolean value) {
        ArrayList<String> columnNames = new ArrayList<>();
        ArrayList<String> table = new ArrayList<>();
        ArrayList<ArrayList<String>> array = new ArrayList<>();
        try {
            this.connect();
            ResultSet rs = prepare(sqlStatement, list).executeQuery();
            if (rs == null){
                return null;
            }

                ResultSetMetaData rsmd = rs.getMetaData();
            if(value) {
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    columnNames.add(rsmd.getColumnName(i));
                }
                array.add(columnNames);
            }
            while (rs.next()) {
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    table.add(rs.getString(i));
                }
            }
            array.add(table);

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return array;
    }

    public boolean setData(String sqlStatement,ArrayList<String> list) {
        try {
            PreparedStatement stmt = prepare(sqlStatement, list);
            int execute = stmt.executeUpdate();
            if (execute > 0){
                return true;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;
        }
        return false;
    }

    public ArrayList<ArrayList<String>> getBookData(){
        String sqlStatement = "SELECT book.book_id,book.book_title, CONCAT(author.author_first_name,' ',author.author_last_name) ,book.book_isbn, genre.genre_name, book.format_id, book.book_available\n" +
                "FROM book\n" +
                "INNER JOIN genre ON book.book_genre_id = genre.genre_id\n" +
                "INNER JOIN author_book ON book.book_id = author_book.book_id\n" +
                "INNER JOIN author ON author_book.author_id = author.author_id;";
        ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
        ArrayList<String> list = new ArrayList<String>();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sqlStatement);
            ResultSetMetaData  rsmd = rs.getMetaData();
            while (rs.next()) {
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    list.add(rs.getString(i));
                }
                results.add(list);
                list = new ArrayList<String>();
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        return results;
    }
    public ArrayList<ArrayList<String>> getAllData(String sqlStatement){
        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
        ArrayList<String> table = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.prepareStatement(sqlStatement);
            rs = stmt.executeQuery();
            ResultSetMetaData  rsmd = rs.getMetaData();
            while (rs.next()) {
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    table.add(rs.getString(i));
                }
                data.add(table);
                table = new ArrayList<String>();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return data;
    }
}
