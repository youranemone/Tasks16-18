package com.example.demo.arithmetic;

import org.springframework.stereotype.Component;

@Component("substractor")
public class Substractor implements Operation{
    @Override
    public double getResult(double a, double b) {
        return a-b;
    }
}
