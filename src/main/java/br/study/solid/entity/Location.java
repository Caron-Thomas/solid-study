package br.study.solid.entity;

import org.springframework.stereotype.Service;


public class Location {
    //Apenas para exemplificar, todas as localizações serão fixas.

    private String local;

    public Location() {
        this.local = "Curitiba/PR";
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}
