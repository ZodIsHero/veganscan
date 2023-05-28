package com.veganprojects.veganscan.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
@ToString
public class ProductDto {
    private String name;
    private String brand;
    private List<String> ingredients;
    private boolean isVegan;
}
