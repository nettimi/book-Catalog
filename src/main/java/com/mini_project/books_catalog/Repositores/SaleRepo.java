package com.mini_project.books_catalog.Repositores;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mini_project.books_catalog.Entities.Sale;
import com.mini_project.books_catalog.Entities.SaleId;

public interface SaleRepo extends JpaRepository<Sale, SaleId> {

    @Query(value = "select top (?1) store_store_id from sale group by store_store_id ORDER by SUM(qty_sold) DESC", nativeQuery = true)
    List<Integer> getTopNStoresByTitlesSold(int n);

    @Query(value = "select titles_title_id from sale where store_store_id = ?1", nativeQuery = true)
    List<Integer> getTitlesByStoreId(int storeId);

    @Query(value = "select * from sale where titles_title_id = ?1", nativeQuery = true)
    List<Sale> getAllSalesForGivenTitle(int titleId);

}
