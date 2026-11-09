package com.studentfee.management;

public class Student {

    private int id;
    private String name;
    private String department;
    private double totalFee;
    private double totalPaid;

    public Student(int id, String name, String department, double totalFee) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.totalFee = totalFee;
        this.totalPaid = 0;
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

    public double getTotalPaid() {
        return totalPaid;
    }

    public double getBalance() {
        return totalFee - totalPaid;
    }

    public String getStatus() {
        if (totalPaid == 0)
            return "Unpaid";
        else if (getBalance() == 0)
            return "Fully Paid";
        else
            return "Partially Paid";
    }

    public void addPayment(double amount) {
        totalPaid += amount;
    }
}