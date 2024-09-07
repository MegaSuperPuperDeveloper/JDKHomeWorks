package org.example.HomeWork3;

import java.math.BigDecimal;

public class Calculator {

    public static <T extends Number, S extends Number> BigDecimal sum(T firstNum, S secondNum) {
        BigDecimal firstBigD = new BigDecimal(firstNum.toString());
        BigDecimal secondBigD = new BigDecimal(secondNum.toString());
        return firstBigD.add(secondBigD);
    }

    public static <T extends Number, S extends Number> BigDecimal multiply(T firstNum, S secondNum) {
        BigDecimal firstBigD = new BigDecimal(firstNum.toString());
        BigDecimal secondBigD = new BigDecimal(secondNum.toString());
        return firstBigD.multiply(secondBigD);
    }

    public static <T extends Number, S extends Number> BigDecimal divide(T firstNum, S secondNum) {
        BigDecimal firstBigD = new BigDecimal(firstNum.toString());
        BigDecimal secondBigD = new BigDecimal(secondNum.toString());
        return firstBigD.divide(secondBigD);
    }

    public static <T extends Number, S extends Number> BigDecimal subtract(T firstNum, S secondNum) {
        BigDecimal firstBigD = new BigDecimal(firstNum.toString());
        BigDecimal secondBigD = new BigDecimal(secondNum.toString());
        return firstBigD.subtract(secondBigD);
    }
}