/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * @author marttpq
 */
package net.handytrack.tracker;

import net.handytrack.database.DBquery;
import net.handytrack.infoInterface.Parcel;
import net.handytrack.infoInterface.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ParcelInfo implements Person, Parcel {
    private String TrackNum;
    private String Date;
    private int Cost;
    private String NameS;
    private String NameR;
    private String Address;
    private Double Weight;
    private String Type;
    private int Contact;
    private String Status;
    private ResultSet rs;

    public ParcelInfo(String num) {
        String sql = String.format("SELECT * FROM product WHERE TrackNum = '%s'", num);
        this.rs = DBquery.getInstance().getSelect(sql);
        try {
            if (this.rs.next()) {
                this.Type = this.rs.getString("Type");
                this.NameS = this.rs.getString("NameS");
                this.NameR = this.rs.getString("NameR");
                this.Date = this.rs.getString("Date");
                this.Cost = Integer.parseInt(this.rs.getString("Cost"));
                this.Address = this.rs.getString("Address");
                this.Weight = Double.valueOf(this.rs.getString("Weight"));
                this.Contact = this.rs.getInt("contactNum");
                this.Status = this.rs.getString("Status");
            }
        } catch (SQLException e) {
            Status = "Sorry, your package couldn't be found.";
        }
    }

    @Override
    public String getNameS() {
        return this.NameS;
    }

    @Override
    public String getNameR() {
        return this.NameR;
    }

    @Override
    public String getDate() {
        return this.Date;
    }

    @Override
    public int getCost() {
        return this.Cost;
    }

    @Override
    public String getAddress() {
        return this.Address;
    }

    @Override
    public Double getWeight() {
        return this.Weight;
    }

    @Override
    public String getType() {
        return this.Type;
    }

    @Override
    public int getContact() {
        return this.Contact;
    }

    @Override
    public String getStatus() {
        return this.Status;
    }


}