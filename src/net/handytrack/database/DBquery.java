package net.handytrack.database;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBquery extends DBconnect{
    private DBquery(){
        super();
    }
    private DBquery(String url){
        super(url);
    }
    private static DBquery instance;
    public static DBquery getInstance(){
        if(instance == null){
            instance = new DBquery();
        }
        return instance;
    }
    public ResultSet getSelect(String s){
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
}
