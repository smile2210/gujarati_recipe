package com.gujaratirecipe.Model;

import java.io.Serializable;

public class Model implements Serializable {
    String name;
    String sahitya;
    String kruti;
    int type_id;
    int row_id;


    public Model(String name, String sahitya, String kruti, int type_id, int row_id) {
        this.name = name;
        this.sahitya = sahitya;
        this.kruti = kruti;
        this.type_id = type_id;
        this.row_id = row_id;
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

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public int getRow_id() {
        return row_id;
    }

    public void setRow_id(int row_id) {
        this.row_id = row_id;
    }
}
