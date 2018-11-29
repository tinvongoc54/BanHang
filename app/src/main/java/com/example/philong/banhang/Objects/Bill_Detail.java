package com.example.philong.banhang.Objects;

public class Bill_Detail {
    private String product_price;
    private String product_quantity;

    public Bill_Detail() {
    }

    public Bill_Detail(String product_price, String product_quantity) {
        this.product_price = product_price;
        this.product_quantity = product_quantity;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getProduct_quantity() {
        return product_quantity;
    }

    public void setProduct_quantity(String product_quantity) {
        this.product_quantity = product_quantity;
    }
}
