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
    public Connection con = null;
    public Statement stm;
    public ResultSet rs;

    public DBconnect() {
        this.url = "jdbc:sqlite:resources/DB.db";
    }

    public DBconnect(String url) {
        this.url = url;
    }


    public void disconnect() {
        try {
            stm.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
