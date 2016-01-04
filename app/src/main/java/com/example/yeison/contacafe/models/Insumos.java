package com.example.yeison.contacafe.models;

/**
 * Created by YEISON on 13/10/2015.
 */
public class Insumos {
    private String idInsumo, nombreInsumo;
    private double costoInsumo;

    public Insumos() {
    }

    public Insumos(String nombreInsumo, double costoInsumo) {
        this.nombreInsumo = nombreInsumo;
        this.costoInsumo = costoInsumo;
    }

    public String getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(String idInsumo) {
        this.idInsumo = idInsumo;
    }

    public String getNombreInsumo() {
        return nombreInsumo;
    }

    public void setNombreInsumo(String nombreInsumo) {
        this.nombreInsumo = nombreInsumo;
    }

    public double getCostoInsumo() {
        return costoInsumo;
    }

    public void setCostoInsumo(double costoInsumo) {
        this.costoInsumo = costoInsumo;
    }
}
