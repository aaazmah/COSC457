package com.example.project_cosc.WAGE;

public class WageModel {
    int hourlyrate, hoursworked, weeklytotal, weeklypay, ytd, id;

    public WageModel(int hourlyrate, int hoursworked, int weeklytotal, int weeklypay, int ytd, int id) {
        this.hourlyrate = hourlyrate;
        this.hoursworked = hoursworked;
        this.weeklytotal = weeklytotal;
        this.weeklypay = weeklypay;
        this.ytd = ytd;
        this.id = id;
    }

    public int getHourlyrate() {
        return hourlyrate;
    }

    public void setHourlyrate(int hourlyrate) {
        this.hourlyrate = hourlyrate;
    }

    public int getHoursworked() {
        return hoursworked;
    }

    public void setHoursworked(int hoursworked) {
        this.hoursworked = hoursworked;
    }

    public int getWeeklytotal() {
        return weeklytotal;
    }

    public void setWeeklytotal(int weeklytotal) {
        this.weeklytotal = weeklytotal;
    }

    public int getWeeklypay() {
        return weeklypay;
    }

    public void setWeeklypay(int weeklypay) {
        this.weeklypay = weeklypay;
    }

    public int getYtd() {
        return ytd;
    }

    public void setYtd(int ytd) {
        this.ytd = ytd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
