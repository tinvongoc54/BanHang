package com.example.philong.banhang.Objects;

public class Category {
    private String catalog_name;
    private String catalogs_des;

    public Category() {
        catalog_name = "";
        catalogs_des = "";
    }

    public Category(String catalog_name, String catalogs_des) {
        this.catalog_name = catalog_name;
        this.catalogs_des = catalogs_des;
    }

    public String getCatalog_name() {
        return catalog_name;
    }

    public void setCatalog_name(String catalog_name) {
        this.catalog_name = catalog_name;
    }

    public String getCatalogs_des() {
        return catalogs_des;
    }

    public void setCatalogs_des(String catalogs_des) {
        this.catalogs_des = catalogs_des;
    }
}
