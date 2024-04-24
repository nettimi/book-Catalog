package com.mini_project.books_catalog.Entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.DecimalMin;

@Entity
public class Titles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int titleId;
    @Column(nullable = false, unique = true)
    private String titleName;
    @DecimalMin(value = "0.01")
    private double price;
    private double ytdSales;
    @Column(nullable = false)
    private LocalDate releaseYear;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "pub_id")
    private Publishers publishers;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id.titles")
    private List<TitleAuthors> authors = new ArrayList<>();
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id.titles")
    private List<Sale> store = new ArrayList<>();

    public Titles() {
    }

    public Titles(String titleName, double price, double ytdSales, LocalDate releaseYear) {
        this.titleName = titleName;
        this.price = price;
        this.ytdSales = ytdSales;
        this.releaseYear = releaseYear;
    }

    public int getTitleId() {
        return titleId;
    }

    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getYtdSales() {
        return ytdSales;
    }

    public void setYtdSales(double ytdSales) {
        this.ytdSales = ytdSales;
    }

    public LocalDate getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(LocalDate releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Publishers getPublishers() {
        return publishers;
    }

    public void setPublishers(Publishers publishers) {
        this.publishers = publishers;
    }

    public List<TitleAuthors> getAuthors() {
        return authors;
    }

    public void setAuthors(List<TitleAuthors> authors) {
        this.authors = authors;
    }

    public List<Sale> getStore() {
        return store;
    }

    public void setStore(List<Sale> store) {
        this.store = store;
    }

    @Override
    public String toString() {
        return "Titles [titleId=" + titleId + ", titleName=" + titleName + ", price=" + price + ", ytdSales=" + ytdSales
                + ", releaseYear=" + releaseYear + ", publishers=" + publishers + ", authors=" + authors + ", store="
                + store + "]";
    }

}
