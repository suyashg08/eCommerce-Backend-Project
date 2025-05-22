package com.example.ecommerceapp1.controllers;


import com.example.ecommerceapp1.dtos.ProductResponseDto;
import com.example.ecommerceapp1.exceptions.ProductNotFoundException;
import com.example.ecommerceapp1.models.Category;
import com.example.ecommerceapp1.models.Product;
import com.example.ecommerceapp1.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductControllerTest {

    @MockitoBean
    @Qualifier("productDBService")
    public ProductService productService;
    @Autowired
    public ProductController productController;

    @Test
    public void testGetProductByIdReturnsProductResponseDto() throws ProductNotFoundException {
        Product dummyProduct = new Product();
        dummyProduct.setId(1L);
        dummyProduct.setName("name");
        dummyProduct.setDescription("description");
        dummyProduct.setPrice(12.5);
        dummyProduct.setImageUrl("img.jpg");


        Category dummyCategory = new Category();
        dummyCategory.setId(1L);
        dummyCategory.setName("category");
        dummyCategory.setDescription("description");

        dummyProduct.setCategory(dummyCategory);

        when(productService.getProductById(1L)).thenReturn(dummyProduct);

        ProductResponseDto productResponseDto
                = productController.getProductById(1L);

        assertEquals(1L, productResponseDto.getId());
        assertEquals("name", productResponseDto.getName());
        assertEquals("description", productResponseDto.getDescription());
        assertEquals(12.5, productResponseDto.getPrice());

    }

    @Test
    public void testGetProductByIdReturnsNull() throws ProductNotFoundException {

        when(productService.getProductById(1L)).thenReturn(null);

        ProductResponseDto productResponseDto = productController.getProductById(1L);

        assertEquals(null, productResponseDto);

    }

}
