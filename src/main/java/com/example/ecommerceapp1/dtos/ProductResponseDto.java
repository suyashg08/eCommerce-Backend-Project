package com.example.ecommerceapp1.dtos;


import com.example.ecommerceapp1.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDto {
    private long id;
    private String name;
    private String description;
    private double price;
    private String category;
    private String imageUrl;

    public static ProductResponseDto from(Product product) {

        if(product == null) {
            return null;
        }

        ProductResponseDto productResponseDto = new ProductResponseDto();

        productResponseDto.setId(product.getId());
        productResponseDto.setName(product.getName());
        productResponseDto.setImageUrl(product.getImageUrl());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setDescription(product.getDescription());
        productResponseDto.setCategory(product.getCategory().getName());

        return productResponseDto;

    }
}
