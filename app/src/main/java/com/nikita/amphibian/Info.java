package com.nikita.amphibian;

public class Info {
        int id;
        String info;

    public Info (){

    }
    public Info(int _id, String _info) {
        this.id = _id;
        this.info = _info;
    }

    public int getID() {
        return this.id;
    }

    public void setID(int _id) {
        this.id = _id;
    }

    public String getInfo() {
        return this.info;
    }

    public void setInfo(String _info) {
        this.info = _info;
    }

}

