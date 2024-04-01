package net.handytrack.HANDYTRACKMAIN;

public class User {
    private final String name;
    private final String surename;
    private final String email;
    private final String tel;


    public User() {
        this("", "", "", "");
    }

    public User(String name, String surename, String email, String tel) {
        this.name = name;
        this.surename = surename;
        this.email = email;
        this.tel = tel;
    }

    public String getName() {
        return this.name;
    }



    public String getSurename() {
        return this.surename;
    }



    public String getEmail() {
        return this.email;
    }



    public String getTel() {
        return this.tel;
    }


}
