package com.example.yeison.contacafe.models;

import java.util.Date;

/**
 * Created by YEISON on 08/10/2015.
 */
public class Semana {
    String nombreSemana, idSemana;
    Date fechaSemana;

    public Semana(String nombreSemana) {
        this.nombreSemana = nombreSemana;
    }

    public Semana() {
    }

    public String getNombreSemana() {
        return nombreSemana;
    }

    public void setNombreSemana(String nombreSemana) {
        this.nombreSemana = nombreSemana;
    }

    public String getIdSemana() {
        return idSemana;
    }

    public void setIdSemana(String idSemana) {
        this.idSemana = idSemana;
    }

    public Date getFechaSemana() {
        return fechaSemana;
    }

    public void setFechaSemana(Date fechaSemana) {
        this.fechaSemana = fechaSemana;
    }
}
