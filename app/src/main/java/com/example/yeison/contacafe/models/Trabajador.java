package com.example.yeison.contacafe.models;

import java.util.Date;

/**
 * Created by YEISON on 03/10/2015.
 */
public class Trabajador {
    String idtra, nombre, idsemana;
    Date createAt;
    int L=0, Ma=0, Mi=0, J=0, V=0, S=0, D=0;

    public Trabajador() {
    }

    public Trabajador(String nombre, int l, int ma, int mi, int j, int v, int s, int d, String idsemana) {
        this.nombre = nombre;
        L = l;
        Ma = ma;
        Mi = mi;
        J = j;
        V = v;
        S = s;
        D = d;
        this.idsemana=idsemana;
    }

    public String getIdsemana() {
        return idsemana;
    }

    public void setIdsemana(String idsemana) {
        this.idsemana = idsemana;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getIdtra() {
        return idtra;
    }

    public void setIdtra(String idtra) {
        this.idtra = idtra;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getL() {
        return L;
    }

    public void setL(int l) {
        L = l;
    }

    public int getMa() {
        return Ma;
    }

    public void setMa(int ma) {
        Ma = ma;
    }

    public int getMi() {
        return Mi;
    }

    public void setMi(int mi) {
        Mi = mi;
    }

    public int getJ() {
        return J;
    }

    public void setJ(int j) {
        J = j;
    }

    public int getV() {
        return V;
    }

    public void setV(int v) {
        V = v;
    }

    public int getS() {
        return S;
    }

    public void setS(int s) {
        S = s;
    }

    public int getD() {
        return D;
    }

    public void setD(int d) {
        D = d;
    }
}
