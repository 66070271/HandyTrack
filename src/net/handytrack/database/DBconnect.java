/**
 *
 * @author marttpq
 */
package net.handytrack.database;
import java.sql.*;
public class DBconnect {
    public Connection con = null;
    public Statement stm;
    public ResultSet rs;
    private static String url;
    private static DBconnect instance;
    public DBconnect(){
        this.url = "jdbc:sqlite:resources/DB.db";
    }
    public DBconnect(String url){
        this.url = url;
    }
    public static DBconnect getInstance(){
        if(instance == null){
            instance = new DBconnect();
        }
        return instance;
    }

    public ResultSet getConnect(String s){
        try{
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(url);
            stm = con.createStatement();
            rs = stm.executeQuery(s);
        }catch(SQLException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        return rs;
        
    }
    public void getUpdate(String s){
        try{
            stm = con.createStatement();
            con = DriverManager.getConnection(url);
            stm.executeUpdate(s);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void disconnect(){
        try{
            stm.close();
            con.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    
}
