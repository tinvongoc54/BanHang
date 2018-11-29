package com.example.philong.banhang.Objects;

import java.io.Serializable;
import java.util.ArrayList;

public class Product implements Serializable {
    private String product_name;
    private String product_description;
    private String category_name;
    private String product_price;

    public Product() {
        this.product_name = "";
        this.product_description = "";
        this.category_name = "";
        this.product_price = "";
    }

    public Product(String product_name, String product_description, String category_name, String product_price) {
        this.product_name = product_name;
        this.product_description = product_description;
        this.category_name = category_name;
        this.product_price = product_price;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }
}
