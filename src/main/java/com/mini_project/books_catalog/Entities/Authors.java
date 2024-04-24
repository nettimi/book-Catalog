package com.mini_project.books_catalog.Entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Authors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int auId;
    private String auName;
    @Column(nullable = false, unique = true)
    private String auEmail;
    @Column(nullable = false, unique = true)
    private long auMobile;
    private String auCity;
    private String auCountry;

    @JsonIgnore
    @OneToMany(mappedBy = "id.authors")
    private List<TitleAuthors> titles = new ArrayList<>();

    public Authors() {
    }

    public Authors(String auName, String auEmail, long auMobile, String auCity, String auCountry) {
        this.auName = auName;
        this.auEmail = auEmail;
        this.auMobile = auMobile;
        this.auCity = auCity;
        this.auCountry = auCountry;
    }

    public int getAuId() {
        return auId;
    }

    public void setAuId(int auId) {
        this.auId = auId;
    }

    public String getAuName() {
        return auName;
    }

    public void setAuName(String auName) {
        this.auName = auName;
    }

    public String getAuEmail() {
        return auEmail;
    }

    public void setAuEmail(String auEmail) {
        this.auEmail = auEmail;
    }

    public long getAuMobile() {
        return auMobile;
    }

    public void setAuMobile(long auMobile) {
        this.auMobile = auMobile;
    }

    public String getAuCity() {
        return auCity;
    }

    public void setAuCity(String auCity) {
        this.auCity = auCity;
    }

    public String getAuCountry() {
        return auCountry;
    }

    public void setAuCountry(String auCountry) {
        this.auCountry = auCountry;
    }

    public List<TitleAuthors> getTitles() {
        return titles;
    }

    public void setTitles(List<TitleAuthors> titles) {
        this.titles = titles;
    }

    @Override
    public String toString() {
        return "Authors [auId=" + auId + ", auName=" + auName + ", auEmail=" + auEmail + ", auMobile=" + auMobile
                + ", auCity=" + auCity + ", auCountry=" + auCountry + "]";
    }

}
