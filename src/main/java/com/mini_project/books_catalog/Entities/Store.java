package com.mini_project.books_catalog.Entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int storeId;
    private String storeLocation;
    private String storeCity;
    private String storeCountry;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id.store")
    private List<Sale> titles = new ArrayList<>();

    public Store() {
    }

    public Store(String storeLocation, String storeCity, String storeCountry) {

        this.storeLocation = storeLocation;
        this.storeCity = storeCity;
        this.storeCountry = storeCountry;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getStoreLocation() {
        return storeLocation;
    }

    public void setStoreLocation(String storeLocation) {
        this.storeLocation = storeLocation;
    }

    public String getStoreCity() {
        return storeCity;
    }

    public void setStoreCity(String storeCity) {
        this.storeCity = storeCity;
    }

    public String getStoreCountry() {
        return storeCountry;
    }

    public void setStoreCountry(String storeCountry) {
        this.storeCountry = storeCountry;
    }

    public List<Sale> getTitles() {
        return titles;
    }

    public void setTitles(List<Sale> titles) {
        this.titles = titles;
    }

    @Override
    public String toString() {
        return "Stores [storeId=" + storeId + ", storeLocation=" + storeLocation + ", storeCity=" + storeCity
                + ", storeCountry=" + storeCountry + "]";
    }

}
