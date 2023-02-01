package com.example.demo.arithmetic;

import org.springframework.stereotype.Component;

@Component("divider")
public class Divider implements Operation{
    @Override
    public double getResult(double a, double b) {
        return a/b;
    }
}