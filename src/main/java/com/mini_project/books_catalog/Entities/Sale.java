package com.mini_project.books_catalog.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class Sale {
    @JsonIgnore
    @EmbeddedId
    private SaleId id = new SaleId();

    private int qtySold;

    public Sale() {
    }

    public Sale(SaleId id, int qtySold) {
        this.id = id;
        this.qtySold = qtySold;
    }

    public SaleId getId() {
        return id;
    }

    public void setId(SaleId id) {
        this.id = id;
    }

    public int getQtySold() {
        return qtySold;
    }

    public void setQtySold(int qtySold) {
        this.qtySold = qtySold;
    }

    public void setTitles(Titles titles) {
        getId().setTitles(titles);
    }

    public Titles getTitles() {
        return getId().getTitles();
    }

    public void setStore(Store store) {
        getId().setStore(store);
    }

    public Store getStore() {
        return getId().getStore();
    }
}