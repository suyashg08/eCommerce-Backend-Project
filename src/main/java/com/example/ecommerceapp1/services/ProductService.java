package com.example.ecommerceapp1.services;

import com.example.ecommerceapp1.exceptions.ProductNotFoundException;
import com.example.ecommerceapp1.models.Product;

import java.util.List;

public interface ProductService {
     Product getProductById(long id) throws ProductNotFoundException;
     List<Product> getAllProducts();
     Product createProduct(String name, String description, String category, double price, String imageUrl);
}
