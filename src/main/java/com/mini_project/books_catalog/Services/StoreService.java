package com.mini_project.books_catalog.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mini_project.books_catalog.Entities.Store;
import com.mini_project.books_catalog.Entities.Titles;
import com.mini_project.books_catalog.Exceptions.CustomException;
import com.mini_project.books_catalog.Repositores.SaleRepo;
import com.mini_project.books_catalog.Repositores.StoreRepo;
import com.mini_project.books_catalog.Repositores.TitleRepo;

@Service
public class StoreService {

    @Autowired
    StoreRepo storeRepo;

    @Autowired
    SaleRepo saleRepo;

    @Autowired
    TitleRepo titleRepo;

    public Store addStores(Store stores) throws CustomException{
        if(stores.getStoreLocation() == null || stores.getStoreCity() == null || stores.getStoreCountry() == null)
            throw new CustomException("Enter all store details");
        return storeRepo.save(stores);
    }

    public void deleteStore(int storeId) throws CustomException {
        if (!storeRepo.existsById(storeId)) {
            throw new CustomException(String.format("store id  %s  not found", storeId));
        }
        storeRepo.deleteById(storeId);
    }

    public List<Store> getTopNStoresByTitlesSold(int n) {
        List<Integer> storesIds = saleRepo.getTopNStoresByTitlesSold(n);

        List<Store> stores = storeRepo.findAllById(storesIds);

        return stores;
    }

    public Store updateStore(int storeId, Store store) throws CustomException {
        Store s1 = storeRepo.findById(storeId).orElseThrow(() -> new CustomException("StoreID not found"));
        if (store.getStoreLocation() != null)
            s1.setStoreLocation(store.getStoreLocation());
        if (store.getStoreCity() != null)
            s1.setStoreCity(store.getStoreCity());
        if (store.getStoreCountry() != null)
            s1.setStoreCountry(store.getStoreCountry());

        return storeRepo.save(s1);
    }

    public List<Titles> getTitlesByStoreId(int storeId)throws CustomException {
        List<Integer> titleIds = saleRepo.getTitlesByStoreId(storeId);
        if (titleIds.isEmpty()) {
            throw new CustomException("No titles sold at storeId: " + storeId);
        }

        List<Titles> titles = titleRepo.findAllById(titleIds);

        return titles;
    }
}
