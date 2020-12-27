package com.example.gujaratirecipe;

public class Model {
    String name;
    String sahitya;
    String kruti;


    public Model(String name, String sahitya, String kruti) {
        this.name = name;
        this.sahitya = sahitya;
        this.kruti = kruti;
    }

    public Model() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSahitya() {
        return sahitya;
    }

    public void setSahitya(String sahitya) {
        this.sahitya = sahitya;
    }

    public String getKruti() {
        return kruti;
    }

    public void setKruti(String kruti) {
        this.kruti = kruti;
    }

}
