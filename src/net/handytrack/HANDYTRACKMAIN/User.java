package net.handytrack.HANDYTRACKMAIN;

public class User {
    private String name;
    private String surename;
    private String email;
    private String tel;

    public User() {
        this("", "", "", "");
    }

    public User(String name, String surename, String email, String tel) {
        this.setName(name);
        this.setEmail(email);
        this.setSurename(surename);
        this.setTel(tel);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurename() {
        return this.surename;
    }

    public void setSurename(String surename) {
        this.surename = surename;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;

    }

    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
