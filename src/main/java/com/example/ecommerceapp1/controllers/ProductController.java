package com.example.ecommerceapp1.controllers;

import com.example.ecommerceapp1.dtos.CreateFakeProductReqDto;
import com.example.ecommerceapp1.dtos.ErrorDto;
import com.example.ecommerceapp1.dtos.ProductResponseDto;
import com.example.ecommerceapp1.exceptions.ProductNotFoundException;
import com.example.ecommerceapp1.models.Product;
import com.example.ecommerceapp1.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController {

    ProductService productService;

    public ProductController(@Qualifier("productDBService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/{id}")
    public ProductResponseDto getProductById(
            @PathVariable("id") long id) throws ProductNotFoundException {

        Product product = productService.getProductById(id);

        ProductResponseDto productResponseDto = ProductResponseDto.from(product);

//        ResponseEntity<ProductResponseDto> responseEntity =
//                new ResponseEntity<>(productResponseDto, HttpStatus.OK);

        return productResponseDto;
    }

    @GetMapping("/products")
    public List<ProductResponseDto> getAllProducts(){
        List<Product> products = productService.getAllProducts();

//        List<ProductResponseDto> productResponseDtos =
//                products.stream().map(ProductResponseDto::from)
//                        .collect(Collectors.toList());

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
                            createFakeProductReqDto.getName(),
                            createFakeProductReqDto.getDescription(),
                            createFakeProductReqDto.getCategory(),
                            createFakeProductReqDto.getPrice(),
                            createFakeProductReqDto.getImage()
                        );

        ProductResponseDto productResponseDto = ProductResponseDto.from(product);

        return productResponseDto;
    }


}
