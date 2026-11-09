package com.studentfee.management;

import java.time.LocalDate;

public class Payment {

    private double amount;
    private String date;

    public Payment(double amount) {
        this.amount = amount;
        this.date = LocalDate.now().toString();
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }
}