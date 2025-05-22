package com.example.ecommerceapp1.calculator;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    public int addInService(int a, int b) {
        System.out.println("Service : some logic here");
        System.out.println("Service : some logic brfore add");
        int result = a + b;
        System.out.println("Service : some logic after add");

        return result;

    }
}
