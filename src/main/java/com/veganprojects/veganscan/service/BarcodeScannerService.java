package com.veganprojects.veganscan.service;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.veganprojects.veganscan.dto.ProductDto;
import com.veganprojects.veganscan.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.coderion.model.Ingredient;
import pl.coderion.model.Product;
import pl.coderion.model.ProductResponse;
import pl.coderion.service.impl.OpenFoodFactsWrapperImpl;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BarcodeScannerService {


    public String scanBarcode(BufferedImage image) throws NotFoundException {
        LuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result result = new MultiFormatReader().decode(bitmap);
        return result.getText();
    }

    public ProductDto getProductByBarcode(String barcode) {
        OpenFoodFactsWrapperImpl wrapper = new OpenFoodFactsWrapperImpl();
        ProductResponse productResponse = wrapper.fetchProductByCode(barcode);
        Product product = productResponse.getProduct();

        ProductDto productDto = new ProductDto();
        productDto.setName(product.getProductName());
        productDto.setBrand(product.getBrands());
        productDto.setIngredients(Arrays.stream(product.getIngredients())
                .map(Ingredient::getText)
                .collect(Collectors.toList()));
        productDto.setVegan(product.getLabels().contains("Vegan"));

        log.info("Label: {}, language: {}", product.getLabels(), product.getLang());
        return productDto;
    }

    //    public ResponseDto isVeganProduct(BufferedImage image) throws NotFoundException {
    public ResponseDto isVeganProduct(String barcode) throws NotFoundException {
        // String barcode = scanBarcode(image);
        ProductDto productDto = getProductByBarcode(barcode);
        log.info("Is vegan: {}", productDto.isVegan());
        return ResponseDto.builder().isVegan(productDto.isVegan()).build();
    }
}
