/**
 *
 * @author marttpq
 */
package net.handytrack.database;
import java.sql.*;
public abstract class DBconnect {
    public Connection con = null;
    public Statement stm;
    public ResultSet rs;
    protected static String url;
    public DBconnect(){
        this.url = "jdbc:sqlite:resources/DB.db";
    }
    public DBconnect(String url){
        this.url = url;
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
