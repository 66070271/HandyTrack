package net.handytrack.database;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DBmanipulation extends DBconnect{
    private DBmanipulation(){
        super();
    }
    private DBmanipulation(String url){
        super(url);
    }
    private static DBmanipulation instance;
    public static DBmanipulation getInstance(){
        if(instance == null){
            instance = new DBmanipulation();
        }
        return instance;
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

}
