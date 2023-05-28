package com.veganprojects.veganscan.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
public class Product {
    private String name;
    private String brand;
    private List<String> ingredients;
    private boolean isVegan;
}
