package com.mini_project.books_catalog.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mini_project.books_catalog.Entities.Store;
import com.mini_project.books_catalog.Entities.Titles;
import com.mini_project.books_catalog.Exceptions.CustomException;
import com.mini_project.books_catalog.Services.StoreService;

@RestController
@RequestMapping("/api/store")
public class StoreController {
    @Autowired
    StoreService storeService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<String> addStores(@RequestBody Store stores) throws CustomException{
        storeService.addStores(stores);
        String msg = "Store Inserted successfully";
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    @PutMapping("stor/{id}")
    public ResponseEntity<String> updateStore(@PathVariable int id, @RequestBody Store store) throws CustomException {
        String msg = "details updated succesfully";
        storeService.updateStore(id, store);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStore(@PathVariable("id") int storeId) throws CustomException {
        storeService.deleteStore(storeId);

        String msg = "Store details Deleted Succesfully";
        return new ResponseEntity<>(msg, HttpStatus.OK);

    }

    @GetMapping("/{n}")
    public List<Store> getTopNStoresByTitlesSold(@PathVariable int n) {
        return storeService.getTopNStoresByTitlesSold(n);
    }

    @GetMapping("/titles/{storeId}")
    public List<Titles> getTitlesByStoreId(@PathVariable int storeId) throws CustomException{
        return storeService.getTitlesByStoreId(storeId);
    }

}
