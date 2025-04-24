package com.example.ecommerceapp1.dtos;

import com.example.ecommerceapp1.models.Category;
import com.example.ecommerceapp1.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeProductResponseDto {
    private long id;
    private  String title;
    private double price;
    private  String description;
    private String category;
    private String image;

    public Product toProduct() {
        Product product = new Product();
        product.setId(id);
        product.setDescription(description);
        product.setName(title);
        product.setImageUrl(image);
        product.setPrice(price);

        Category category1 = new Category();
        category1.setName(category);

        product.setCategory(category1);

        return product;
    }
}
