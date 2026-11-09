package com.studentfee.management;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private int id;
    private String name;
    private String department;
    private double totalFee;

    private List<Payment> payments = new ArrayList<>();

    public Student(int id, String name, String department, double totalFee) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.totalFee = totalFee;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getTotalFee() {
        return totalFee;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public double getTotalPaid() {
        double total = 0;

        for (Payment p : payments) {
            total += p.getAmount();
        }

        return total;
    }

    public double getBalance() {
        return totalFee - getTotalPaid();
    }

    public String getStatus() {
        if (getTotalPaid() == 0)
            return "Unpaid";
        else if (getBalance() <= 0.001)
            return "Fully Paid";
        else
            return "Partially Paid";
    }

    public void addPayment(double amount) {
        payments.add(new Payment(amount));
    }
}