package com.example.ecommerceapp1.calculator;

import org.springframework.stereotype.Controller;

@Controller
public class CalculatorController {

    public CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    public int add(int a, int b) {
        System.out.println("Controller : some logic here");
        System.out.println("Controller : some logic before add");
        int result = calculatorService.addInService(a,b);
        System.out.println("Controller : some logic after add");

        return result;

    }

}
