package com.mini_project.books_catalog.Entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;

@Embeddable
public class SaleId implements Serializable {

    @ManyToOne
    @JsonIgnore
    private Store store;

    @ManyToOne
    @JsonIgnore
    private Titles titles;

    public SaleId() {
    }

    public SaleId(Store store, Titles titles) {
        this.store = store;
        this.titles = titles;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Titles getTitles() {
        return titles;
    }

    public void setTitles(Titles titles) {
        this.titles = titles;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((store == null) ? 0 : store.hashCode());
        result = prime * result + ((titles == null) ? 0 : titles.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SaleId other = (SaleId) obj;
        if (store == null) {
            if (other.store != null)
                return false;
        } else if (!store.equals(other.store))
            return false;
        if (titles == null) {
            if (other.titles != null)
                return false;
        } else if (!titles.equals(other.titles))
            return false;
        return true;
    }

}