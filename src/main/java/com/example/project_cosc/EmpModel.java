package com.example.project_cosc;

public class EmpModel {
    String firstname, lastname, address, BDATE;
    int SSN, wage;



    public EmpModel(String firstname, String lastname, String address, String BDATE, int SSN,int wage) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.SSN = SSN;
        this.BDATE = BDATE;
        this.wage = wage;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSSN() {
        return SSN;
    }

    public void setSSN(int SSN) {
        this.SSN = SSN;
    }

    public String getBDATE() {
        return BDATE;
    }

    public void setBDATE(String BDATE) {
        this.BDATE = BDATE;
    }

    public int getWage() {
        return wage;
    }

    public void setWage(int wage) {
        this.wage = wage;
    }
}
