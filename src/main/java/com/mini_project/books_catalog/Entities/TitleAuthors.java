package com.mini_project.books_catalog.Entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class TitleAuthors {

    @EmbeddedId
    private TitleAuthorsId id = new TitleAuthorsId();

    private double royaltyPct;

    public TitleAuthors() {
    }

    public TitleAuthorsId getId() {
        return id;
    }

    public void setId(TitleAuthorsId id) {
        this.id = id;
    }

    public double getRoyaltyPct() {
        return royaltyPct;
    }

    public void setRoyaltyPct(double royaltyPct) {
        this.royaltyPct = royaltyPct;
    }

    public void setTitles(Titles titles) {
        getId().setTitles(titles);
    }

    public Titles getTitles() {
        return getId().getTitles();
    }

    public void setAuthors(Authors authors) {
        getId().setAuthors(authors);
    }

    public Authors getAuthors() {
        return getId().getAuthors();
    }
}
