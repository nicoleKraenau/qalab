package com.everis.base.models;

import java.util.List;

import com.fasterxml.jackson.annotation.*;

@JsonPropertyOrder({"page", "per_page", "total", "total_pages", "data", "support"})
public class PetCollection {

    private String page;
    private String per_page;
    private String total;
    private String total_pages;
    private List<Pet> pets;
    private Support support;

    @Override
    public String toString() {
        return "PetCollection{" +
                "page='" + page + '\'' +
                ", per_page='" + per_page + '\'' +
                ", total='" + total + '\'' +
                ", total_pages='" + total_pages + '\'' +
                ", pets=" + pets +
                ", support=" + support +
                '}';
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPer_page() {
        return per_page;
    }

    public void setPer_page(String per_page) {
        this.per_page = per_page;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(String total_pages) {
        this.total_pages = total_pages;
    }

    public List<Pet> getData() {
        return pets;
    }

    public void setData(List<Pet> data) {
        this.pets = data;
    }

    public Support getSupport() {
        return support;
    }

    public void setSupport(Support support) {
        this.support = support;
    }
}
