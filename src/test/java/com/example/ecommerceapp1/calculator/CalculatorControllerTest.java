package com.example.ecommerceapp1.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AutoClose;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CalculatorControllerTest {

//    CalculatorService calculatorService
//            = Mockito.mock(CalculatorService.class);
//
//    CalculatorController calculatorController
//            = new CalculatorController(calculatorService);

    @MockitoBean
    CalculatorService calculatorService;

    @Autowired
    CalculatorController calculatorController;



    @Test
    public void testAddWhenTwoIntegersArePassedReturnsInteger() {

        when(calculatorService.addInService(5, 10))
                .thenReturn(15);

        //Arrange
        int a = 5;
        int b = 10;
        int expectedResult = 15;

        //Act
        int actualResult = calculatorController.add(a,b);

        //Assert
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testAddWhenAnyTwoIntegersArePassedReturnsInteger() {

        when(calculatorService.addInService(anyInt(), anyInt()))
                .thenReturn(15);

        //Arrange
        int a = 5;
        int b = 10;
        int expectedResult = 15;

        //Act
        int actualResult = calculatorController.add(a,b);

        //Assert
        Assertions.assertEquals(expectedResult, actualResult);
    }
}
