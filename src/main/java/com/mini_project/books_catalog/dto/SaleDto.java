package com.mini_project.books_catalog.dto;

import com.mini_project.books_catalog.Entities.Store;

public class SaleDto {
    private int titleId;

    private Store store;

    private int qtySold;

    public SaleDto() {
    }

    public int getTitleId() {
        return titleId;
    }

    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public int getQtySold() {
        return qtySold;
    }

    public void setQtySold(int qtySold) {
        this.qtySold = qtySold;
    }

}
