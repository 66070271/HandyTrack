package net.handytrack.database;

public class DBmanipulation extends DBconnect{
    public DBmanipulation(){
        super();
    }
    public DBmanipulation(String url){
        super(url);
    }
    private static DBmanipulation instance;
    public static DBmanipulation getInstance(){
        if(instance == null){
            instance = new DBmanipulation();
        }
        return instance;
    }

}
