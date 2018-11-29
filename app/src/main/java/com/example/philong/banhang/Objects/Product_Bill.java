package com.example.philong.banhang.Objects;

public class Product_Bill extends Product {
    private int Size;

    public Product_Bill(String name, String description, String category, String price, int size) {
        super(name, description, category, price);
        Size = size;
    }

    public int getSize() {
        return Size;
    }

    public void setSize(int size) {
        Size = size;
    }
}
