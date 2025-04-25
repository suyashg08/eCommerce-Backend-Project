package com.example.ecommerceapp1.services;

import com.example.ecommerceapp1.dtos.FakeProductRequestDto;
import com.example.ecommerceapp1.dtos.FakeProductResponseDto;
import com.example.ecommerceapp1.exceptions.ProductNotFoundException;
import com.example.ecommerceapp1.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeProductService implements ProductService{

    RestTemplate restTemplate;

    public FakeProductService(RestTemplate restTemplate){
        this.restTemplate = new RestTemplate();
    }
    @Override
    public Product getProductById(long id) throws ProductNotFoundException {

        FakeProductResponseDto fakeProductResponseDto = restTemplate
                .getForObject("https://fakestoreapi.com/products/" + id, FakeProductResponseDto.class );

        if(fakeProductResponseDto == null) {
            throw new ProductNotFoundException("the product for " + id + " does not exist");
        }

        return fakeProductResponseDto.toProduct();
    }

    @Override
    public List<Product> getAllProducts() {
        FakeProductResponseDto[] fakeProductResponseDtos = restTemplate.getForObject(
                "https://fakestoreapi.com/products", FakeProductResponseDto[].class);

        List<Product> products = new ArrayList<>();

        for(FakeProductResponseDto fakeProductResponseDto : fakeProductResponseDtos) {
            Product product = fakeProductResponseDto.toProduct();
            products.add(product);
        }
        return  products;
    }

    @Override
    public Product createProduct(String name, String description, String category, double price,
                                 String imageUrl) {

        FakeProductRequestDto fakeProductRequestDto = new FakeProductRequestDto();
        fakeProductRequestDto.setTitle(name);
        fakeProductRequestDto.setPrice(price);
        fakeProductRequestDto.setDescription(description);
        fakeProductRequestDto.setCategory(category);
        fakeProductRequestDto.setImage(imageUrl);

        FakeProductResponseDto fakeProductResponseDto =  restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                fakeProductRequestDto,
                FakeProductResponseDto.class);

        return fakeProductResponseDto.toProduct();
    }
}
