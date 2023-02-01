package com.example.demo.arithmetic;

import org.springframework.stereotype.Component;

@Component
public interface Operation {
    double getResult(double a, double b);
}