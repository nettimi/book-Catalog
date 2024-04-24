package com.mini_project.books_catalog.Entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Publishers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pubId;
    @Column(nullable = false)
    private String pubName;
    @Column(nullable = false, unique = true)
    private String pubEmail;
    private String pubCity;
    private String pubCountry;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "publishers")
    private List<Titles> titles;

    public Publishers() {
    }

    public Publishers(String pubName, String pubEmail, String pubCity, String pubCountry) {

        this.pubName = pubName;
        this.pubEmail = pubEmail;
        this.pubCity = pubCity;
        this.pubCountry = pubCountry;
    }

    public int getPubId() {
        return pubId;
    }

    public void setPubId(int pubId) {
        this.pubId = pubId;
    }

    public String getPubName() {
        return pubName;
    }

    public void setPubName(String pubName) {
        this.pubName = pubName;
    }

    public String getPubEmail() {
        return pubEmail;
    }

    public void setPubEmail(String pubEmail) {
        this.pubEmail = pubEmail;
    }

    public String getPubCity() {
        return pubCity;
    }

    public void setPubCity(String pubCity) {
        this.pubCity = pubCity;
    }

    public String getPubCountry() {
        return pubCountry;
    }

    public void setPubCountry(String pubCountry) {
        this.pubCountry = pubCountry;
    }

    public List<Titles> getTitles() {
        return titles;
    }

    public void setTitles(List<Titles> titles) {
        this.titles = titles;
    }

}
