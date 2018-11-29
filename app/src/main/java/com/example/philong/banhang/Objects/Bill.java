package com.example.philong.banhang.Objects;

public class Bill {
    private String amount;
    private String bill_number;
    private String customer;
    private String date_create;
    private String discount;
    private String staff_create;
    private String status;
    private String table;
    private String total;

    public Bill() {
    }

    public Bill(String amount, String bill_number, String customer, String date_create, String discount, String staff_create, String status, String table, String total) {
        this.amount = amount;
        this.bill_number = bill_number;
        this.customer = customer;
        this.date_create = date_create;
        this.discount = discount;
        this.staff_create = staff_create;
        this.status = status;
        this.table = table;
        this.total = total;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBill_number() {
        return bill_number;
    }

    public void setBill_number(String bill_number) {
        this.bill_number = bill_number;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getDate_create() {
        return date_create;
    }

    public void setDate_create(String date_create) {
        this.date_create = date_create;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getStaff_create() {
        return staff_create;
    }

    public void setStaff_create(String staff_create) {
        this.staff_create = staff_create;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
