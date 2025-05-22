package com.example.ecommerceapp1.services;

import com.example.ecommerceapp1.dtos.FakeProductResponseDto;
import com.example.ecommerceapp1.exceptions.ProductNotFoundException;
import com.example.ecommerceapp1.models.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest
public class FakeProductServiceTest {

    RestTemplate restTemplate = Mockito.mock(RestTemplate.class);

    FakeProductService fakeProductService = new FakeProductService(restTemplate);

    @Test
    public void testGetProductByIdReturnsProduct() throws ProductNotFoundException {

        FakeProductResponseDto dummyResponse = new FakeProductResponseDto();
        dummyResponse.setId(1L);
        dummyResponse.setTitle("title");
        dummyResponse.setDescription("description");
        dummyResponse.setPrice(12.5);
        dummyResponse.setImage("img.jpg");
        dummyResponse.setCategory("category");

        when(restTemplate.getForObject("https://fakestoreapi.com/products/1", FakeProductResponseDto.class))
                .thenReturn(dummyResponse);

        Product product = fakeProductService.getProductById(1L);

        assertEquals(1L, product.getId());
        assertEquals("title", product.getName());
    }

    @Test
    public void testGetProductByIdWithNullProductThrowingException() throws ProductNotFoundException {

        when(restTemplate.getForObject(
                "https://fakestoreapi.com/products/1",
                FakeProductResponseDto.class)).thenReturn(null);

        assertThrows(ProductNotFoundException.class,
                () -> fakeProductService.getProductById(1L));


    }

    @Test
    public void testCreateProductReturnsProductWithId() {

        FakeProductResponseDto dummyResponse = new FakeProductResponseDto();
        dummyResponse.setId(1L);
        dummyResponse.setTitle("title");
        dummyResponse.setDescription("description");
        dummyResponse.setPrice(12.5);
        dummyResponse.setImage("img.jpg");
        dummyResponse.setCategory("category");

        when(restTemplate.postForObject(
                eq("https://fakestoreapi.com/products"),
                any(),
                eq(FakeProductResponseDto.class)))
                .thenReturn(dummyResponse);

        Product product = fakeProductService.createProduct(
                "title", "description", "category",
                12.5, "img.jpg");

        assertEquals(1L, product.getId());
        assertEquals("title", product.getName());
        assertEquals("description", product.getDescription());
    }

    @Test
    public void testCreateProductVerifyAPICalls() {

        FakeProductResponseDto dummyResponse = new FakeProductResponseDto();
        dummyResponse.setId(1L);
        dummyResponse.setTitle("title");
        dummyResponse.setDescription("description");
        dummyResponse.setPrice(12.5);
        dummyResponse.setImage("img.jpg");
        dummyResponse.setCategory("category");

        when(restTemplate.postForObject(
                eq("https://fakestoreapi.com/products"),
                any(),
                eq(FakeProductResponseDto.class)))
                .thenReturn(dummyResponse);

        Product product = fakeProductService.createProduct(
                "title", "description", "category",
                12.5, "img.jpg");

        verify(restTemplate, times(1)).postForObject(
                eq("https://fakestoreapi.com/products"),
                any(),
                eq(FakeProductResponseDto.class));
    }
}
