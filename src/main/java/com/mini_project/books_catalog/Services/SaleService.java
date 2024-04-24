package com.mini_project.books_catalog.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mini_project.books_catalog.Entities.Sale;
import com.mini_project.books_catalog.Entities.SaleId;
import com.mini_project.books_catalog.Entities.Store;
import com.mini_project.books_catalog.Entities.Titles;
import com.mini_project.books_catalog.Exceptions.CustomException;
import com.mini_project.books_catalog.Repositores.SaleRepo;
import com.mini_project.books_catalog.Repositores.StoreRepo;
import com.mini_project.books_catalog.Repositores.TitleRepo;
import com.mini_project.books_catalog.dto.SaleDto;

import jakarta.transaction.Transactional;

@Service
public class SaleService {
    @Autowired
    SaleRepo salesRepo;
    @Autowired
    TitleRepo titleRepo;
    @Autowired
    StoreRepo storeRepo;

    public List<Sale> getAllSales() {
        return salesRepo.findAll();
    }

    public Sale getSaleById(SaleId id) {
        Optional<Sale> sale = salesRepo.findById(id);
        return sale.orElse(null);
    }

    public Sale addSale(int titleId, int storeId, int qtySold) throws Exception {
        Titles title = titleRepo.findById(titleId).orElseThrow(() -> new Exception("Title not found"));
        Store store = storeRepo.findById(storeId).orElseThrow(() -> new Exception("No store found"));
        Sale s = new Sale();
        s.setTitles(title);
        s.setStore(store);
        s.setQtySold(qtySold);

        return salesRepo.save(s);
    }

    @Transactional
    public void updateQtySold(int titleId, int storeId, int newQtySold) throws CustomException {
        // Check if Title with given ID exists
        if (!titleRepo.existsById(titleId)) {
            throw new CustomException(String.format("Title with id %s not found", titleId));
        }

        // Check if Store with given ID exists
        if (!storeRepo.existsById(storeId)) {
            throw new CustomException(String.format("Store with id %s not found", storeId));
        }

        // Fetch existing Title and Store entities
        Titles title = titleRepo.findById(titleId)
                .orElseThrow(() -> new CustomException("Title not found"));
        Store store = storeRepo.findById(storeId)
                .orElseThrow(() -> new CustomException("Store not found"));

        // Create SaleId with existing Title and Store
        SaleId saleId = new SaleId(store, title);

        // Find or create Sale entity
        Sale sale = salesRepo.findById(saleId).orElse(new Sale(saleId, 0));

        // Update qtySold
        sale.setQtySold(newQtySold);

        // Save the updated Sale
        salesRepo.save(sale);
    }

    public void deleteSale(int titleId, int storeId) throws CustomException {
        // Find the Sale entity corresponding to the given title and store IDs
        Titles title = titleRepo.findById(titleId)
                .orElseThrow(() -> new CustomException("Title not found"));
        Store store = storeRepo.findById(storeId)
                .orElseThrow(() -> new CustomException("Store not found"));

        // Create SaleId with existing Title and Store
        SaleId saleId = new SaleId(store, title);

        Sale sale = salesRepo.findById(saleId)
                .orElseThrow(() -> new CustomException("Sale not found"));

        // Delete the Sale entity
        salesRepo.delete(sale);
    }

    public List<SaleDto> getAllSalesForGivenTitle(int titleId) throws CustomException {
        List<Sale> sales = salesRepo.getAllSalesForGivenTitle(titleId);

        if (sales.size() == 0) {
            throw new CustomException(String.format("No sales for title id %s", titleId));
        }

        List<SaleDto> saleDtos = new ArrayList<>();

        for (Sale s : sales) {
            SaleDto saleDto = new SaleDto();
            saleDto.setTitleId(titleId);
            saleDto.setStore(s.getStore());
            saleDto.setQtySold(s.getQtySold());

            saleDtos.add(saleDto);
        }
        return saleDtos;
    }

}
