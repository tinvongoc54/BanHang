package com.example.philong.banhang.Objects;

public class Product_Bill extends Product {
    private int Size;

    public Product_Bill(int id, String name, int price, int size) {
        super(id, name, price);
        Size = size;
    }

    public int getSize() {
        return Size;
    }

    public void setSize(int size) {
        Size = size;
    }
}
