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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mini_project.books_catalog.Entities.Sale;
import com.mini_project.books_catalog.Exceptions.CustomException;
import com.mini_project.books_catalog.Services.SaleService;
import com.mini_project.books_catalog.dto.SaleDto;

@RestController
@RequestMapping("/api/sales")
public class SaleController {
    @Autowired
    SaleService saleService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{titleId}/{storeId}/{qtySold}")
    public ResponseEntity<String> addSale(@PathVariable int titleId, @PathVariable int storeId,
            @PathVariable int qtySold) throws Exception {
        saleService.addSale(titleId, storeId, qtySold);
        String msg = "Sales Inserted successfully";
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{tid}/{sid}/{qty}")
    public ResponseEntity<String> updateQtySold(@PathVariable("tid") int titleId, @PathVariable("sid") int storeId,
            @PathVariable("qty") int newQtySold) throws CustomException {
        saleService.updateQtySold(titleId, storeId, newQtySold);
        String msg = "details updated succesfully";
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @GetMapping
    public List<Sale> getAllSales() {
        return saleService.getAllSales();
    }

    @GetMapping("/title/{titleId}")
    public List<SaleDto> getAllSalesForGivenTitle(@PathVariable int titleId) throws CustomException {
        return saleService.getAllSalesForGivenTitle(titleId);
    }

    @DeleteMapping("/{titleId}/{storeId}")
    public ResponseEntity<String> deleteSale(@PathVariable int titleId, @PathVariable int storeId)
            throws CustomException {
        saleService.deleteSale(titleId, storeId);
        String msg = "details deleted succesfully";
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

}
