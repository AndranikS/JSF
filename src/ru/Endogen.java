package ru;

/**
 * Created by Акопов on 02.02.2016.
 */
public class Endogen {
    String date;
    double ugas;
    double pi;
    double pippi;

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public double getUgas() {
        return ugas;
    }
    public void setUgas(double ugas) {
        this.ugas = ugas;
    }

    public double getPi() {
        return pi;
    }
    public void setPi(double pi) {
        this.pi = pi;
    }

    public double getPippi() {
        return pippi;
    }
    public void setPippi(double pippi) {
        this.pi = pippi;
    }


    public Endogen(String date, double ugas, double pi, double pippi) {
        this.date = date;
        this.ugas = ugas;
        this.pi = pi;
        this.pippi = pippi;

    }

}
