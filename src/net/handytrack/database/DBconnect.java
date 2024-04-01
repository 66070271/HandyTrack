/**
 * @author marttpq
 */
package net.handytrack.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DBconnect {
    protected static String url;
    protected Connection con = null;
    protected Statement stm;
    protected ResultSet rs;

    public DBconnect() {
        this.url = "jdbc:sqlite:resources/DB.db";
    }

    public DBconnect(String url) {
        this.url = url;
    }
    public abstract ResultSet getSelect(String s);
    public abstract DBquery getInstanceOQ();
    public abstract DBmanipulation getInstanceOM();
    public abstract void getUpdate(String s);

    public void disconnect() {
        try {
            stm.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
