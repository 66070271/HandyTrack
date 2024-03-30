/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author marttpq
 */
import java.sql.*;
public class TrackInfo   extends Info{
        private ResultSet rs;
        private String Recieved;
        private String Sort;
        private String Transit;
        private String Delivery;
        private String Finish;
            public TrackInfo(String num){
                  DBconnect db = new DBconnect();
                  String sql = String.format("SELECT * FROM product.trackinfo WHERE TrackNum = '%s'",num);
                  this.rs = db.getConnect(sql);
                  try{
                      if(this.rs.next()){
                         this.Recieved = this.rs.getString("Recieved");
                         this.Sort = this.rs.getString("Sorting");
                         this.Transit = this.rs.getString("Transit");
                         this.Finish  = this.rs.getString("Finish");
                         this.Delivery = this.rs.getString("Delivery");
                      }
                      }catch(SQLException e){
                              e.printStackTrace();
                              }
                  }
            

                    @Override
        public String getNameS(){
           return"";
        }
        @Override
        public String getNameR(){
        return"";
        }
        @Override
        public String getDate(){
             return"";
        }
        @Override
        public int getCost(){
            return 0;
        }
        @Override
        public String getAddress(){
             return"";
        }
        @Override
        public Double getWeight(){
             return 0.0;
        }
        @Override
        public String getType(){
             return"";
        }
        @Override
        public int getContact(){
             return 0;
        }
        @Override
        public String getStatus(){
             return"";
        }
        @Override
       public String getRecieved(){
           return this.Recieved;
       }
        @Override
       public String getSort(){
           return this.Sort;
       }
        @Override
       public String getTransit(){
           return this.Transit;
       }
        @Override
       public String getFinish(){
           return this.Finish;
       }
        @Override
       public String getDelivery(){
           return this.Delivery;
       }
       }

    
