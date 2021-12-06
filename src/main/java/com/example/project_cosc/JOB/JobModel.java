package com.example.project_cosc.JOB;

public class JobModel {

    String jobtype, address, name, amount, formofpayment;
    int id;

    public JobModel(String jobtype, String address, String name, String amount, String formofpayment, int id) {
        this.jobtype = jobtype;
        this.address = address;
        this.name = name;
        this.amount = amount;
        this.formofpayment = formofpayment;
        this.id = id;
    }

    public String getJobtype() {
        return jobtype;
    }

    public void setJobtype(String jobtype) {
        this.jobtype = jobtype;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getFormofpayment() {
        return formofpayment;
    }

    public void setFormofpayment(String formofpayment) {
        this.formofpayment = formofpayment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
