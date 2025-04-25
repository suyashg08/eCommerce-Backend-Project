package com.example.ecommerceapp1.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeProductRequestDto {
//    private long id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;

}
