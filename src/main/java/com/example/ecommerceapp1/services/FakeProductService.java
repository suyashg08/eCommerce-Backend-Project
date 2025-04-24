package com.example.ecommerceapp1.services;

import com.example.ecommerceapp1.dtos.FakeProductResponseDto;
import com.example.ecommerceapp1.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeProductService implements ProductService{

    RestTemplate restTemplate;

    public FakeProductService(RestTemplate restTemplate){
        this.restTemplate = new RestTemplate();
    }
    @Override
    public Product getProductById(long id) {

        FakeProductResponseDto fakeProductResponseDto = restTemplate
                .getForObject("https://fakestoreapi.com/products/" + id, FakeProductResponseDto.class );

        return fakeProductResponseDto.toProduct();
    }
}
