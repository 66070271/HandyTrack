package net.handytrack.database;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBquery extends DBconnect {
    private static DBquery instance;

    public DBquery() {
        super();
    }

    public DBquery(String url) {
        super(url);
    }

    public static DBquery getInstance() {
        if (instance == null) {
            instance = new DBquery();
        }
        return instance;
    }

    public ResultSet getSelect(String s) {
        try {

            con = DriverManager.getConnection(url);
            stm = con.createStatement();
            rs = stm.executeQuery(s);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;

    }

    @Override
    public void getUpdate(String s) {

    }

}
