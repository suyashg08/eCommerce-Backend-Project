package com.example.ecommerceapp1.controllers;

import com.example.ecommerceapp1.dtos.CreateFakeProductReqDto;
import com.example.ecommerceapp1.dtos.ErrorDto;
import com.example.ecommerceapp1.dtos.ProductResponseDto;
import com.example.ecommerceapp1.exceptions.ProductNotFoundException;
import com.example.ecommerceapp1.models.Product;
import com.example.ecommerceapp1.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable("id") long id) throws ProductNotFoundException {
        Product product = productService.getProductById(id);

        ProductResponseDto productResponseDto = ProductResponseDto.from(product);

        ResponseEntity<ProductResponseDto> responseEntity =
                new ResponseEntity<>(productResponseDto, HttpStatus.OK);

        return responseEntity;
    }

    @GetMapping("/products")
    public List<ProductResponseDto> getAllProducts(){
        List<Product> products = productService.getAllProducts();

        List<ProductResponseDto> productResponseDtos = new ArrayList<>();

        for(Product product : products) {
            ProductResponseDto productResponseDto = ProductResponseDto.from(product);
            productResponseDtos.add(productResponseDto);
        }

        return productResponseDtos;

    }

    @PostMapping("/products")
    public ProductResponseDto createProduct(@RequestBody CreateFakeProductReqDto createFakeProductReqDto) {
        Product product =  productService.createProduct(
                            createFakeProductReqDto.getTitle(),
                            createFakeProductReqDto.getDescription(),
                            createFakeProductReqDto.getCategory(),
                            createFakeProductReqDto.getPrice(),
                            createFakeProductReqDto.getImage()
                        );

        ProductResponseDto productResponseDto = ProductResponseDto.from(product);

        return productResponseDto;
    }


}
