package ru;

/**
 * Created by Акопов on 02.02.2016.
 */
public class Exogen {

    String date;
    double dollar;
    double gas;
    double oil;

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public double getDollar() {
        return dollar;
    }
    public void setDollar(double dollar) {
        this.dollar = dollar;
    }

    public double getGas() {
        return gas;
    }
    public void setGas(double gas) {
        this.gas = gas;
    }

    public double getOil() {
        return oil;
    }
    public void setOil(double oil) {
        this.oil = oil;
    }

    public Exogen(String date, double dollar, double gas, double oil) {
        this.date = date;
        this.dollar = dollar;
        this.gas = gas;
        this.oil = oil;

    }


}