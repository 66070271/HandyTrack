//package net.handytrack.employee;
//import net.handytrack.database.DBquery;
//
//import java.sql.*;
//public class employee{
//    private String name;
//    private String tel;
//    private ResultSet rs;
//    public employee(String s){
//        try {
//
//            this.rs = DBquery.getInstance().getSelect(String.format("SELECT * FROM customer WHERE name = '%s'",s));
//            if(rs.next()){
//                this.tel = rs.getString("tel");
//                this.name = rs.getString("name");
//            }
//        }catch(SQLException e){
//            e.printStackTrace();
//        }
//    }
//    public String getTel(){
//        return this.tel;
//    }
//
//    public static void main(String[] args) {
//        new net.handytrack.customer.customer("sad");
//    }
