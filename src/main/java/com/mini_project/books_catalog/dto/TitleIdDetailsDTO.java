package com.mini_project.books_catalog.dto;

import java.util.List;

public class TitleIdDetailsDTO {

    private String titleName;
    private double price;
    private double ytdSales;
    private List<String> authorNames;
    private String publisherName;

    public TitleIdDetailsDTO(String titleName, double price, double ytdSales, List<String> authorNames,
            String publisherName) {
        this.titleName = titleName;
        this.price = price;
        this.ytdSales = ytdSales;
        this.authorNames = authorNames;
        this.publisherName = publisherName;
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

    public List<String> getAuthorNames() {
        return authorNames;
    }

    public void setAuthorNames(List<String> authorNames) {
        this.authorNames = authorNames;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

}