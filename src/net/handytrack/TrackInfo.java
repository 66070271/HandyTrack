/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * @author marttpq
 */
package net.handytrack;

import net.handytrack.database.DBquery;
import net.handytrack.infoInterface.Status;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TrackInfo implements Status {
    private ResultSet rs;
    private String Recieved;
    private String Sort;
    private String Transit;
    private String Delivery;
    private String Finish;

    public TrackInfo(String num) {
        String sql = String.format("SELECT * FROM trackinfo WHERE TrackNum = '%s'", num);
        this.rs = DBquery.getInstance().getSelect(sql);
        try {
            if (this.rs.next()) {
                this.Recieved = this.rs.getString("Recieved");
                this.Sort = this.rs.getString("Sorting");
                this.Transit = this.rs.getString("Transit");
                this.Finish = this.rs.getString("Finish");
                this.Delivery = this.rs.getString("Delivery");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getRecieved() {
        return this.Recieved;
    }

    @Override
    public String getSort() {
        return this.Sort;
    }

    @Override
    public String getTransit() {
        return this.Transit;
    }

    @Override
    public String getFinish() {
        return this.Finish;
    }

    @Override
    public String getDelivery() {
        return this.Delivery;
    }
}

    
