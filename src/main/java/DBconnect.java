/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author marttpq
 */
import java.sql.*;
public class DBconnect {
    public Connection con = null;
    public Statement stm;
    public ResultSet rs;
    private String url = "jdbc:mysql://127.0.0.1:3306/product";
    private String username = "root";
    private String password = "admin12345";
    public ResultSet getConnect(String s){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url,username,password);
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
