package com.veganprojects.veganscan.controller;

import com.google.zxing.NotFoundException;
import com.veganprojects.veganscan.service.BarcodeScannerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class MainController {
    private final BarcodeScannerService barcodeScannerService;

    public MainController(BarcodeScannerService barcodeScannerService) {
        this.barcodeScannerService = barcodeScannerService;
    }

    @GetMapping("/image")
//    public ResponseDto getInfoProduct(BufferedImage image) throws NotFoundException {
   public String getInfoProduct(String image) throws NotFoundException {
        return barcodeScannerService.isVeganProduct(image).toString();
    }
}
