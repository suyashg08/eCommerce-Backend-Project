package com.example.ecommerceapp1.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateFakeProductReqDto {
    private String name;
    private String description;
    private String category;
    private double price;
    private String image;
}
