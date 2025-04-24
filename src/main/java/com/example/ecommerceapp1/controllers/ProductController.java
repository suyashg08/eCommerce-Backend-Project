package com.example.ecommerceapp1.controllers;

import com.example.ecommerceapp1.dtos.ProductResponseDto;
import com.example.ecommerceapp1.models.Product;
import com.example.ecommerceapp1.services.FakeProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    FakeProductService fakeProductService;

    public ProductController(FakeProductService fakeProductService) {
        this.fakeProductService = fakeProductService;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable long id){
        Product product = fakeProductService.getProductById(id);

        ProductResponseDto productResponseDto = ProductResponseDto.from(product);

        ResponseEntity<ProductResponseDto> responseEntity =
                new ResponseEntity<>(productResponseDto, HttpStatus.OK);

        return responseEntity;
    }
}
