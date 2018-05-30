package org.tnmk.practice.springboot.prometheuse.aspectj.samplebusiness.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tnmk.practice.springboot.prometheuse.aspectj.samplebusiness.service.HotelFileLoaderUseCase;

/**
 * This REST API will be exposed via the port 8080
 * While metrics APIs will be exposed via the port 9090
 */
@RestController
public class HotelFileLoaderResource {

    private final HotelFileLoaderUseCase hotelFileLoaderUseCase;

    @Autowired
    public HotelFileLoaderResource(HotelFileLoaderUseCase hotelFileLoaderUseCase) {
        this.hotelFileLoaderUseCase = hotelFileLoaderUseCase;
    }

    @RequestMapping(value = {"/hotel-file"})
    public String loadHotelFile() {
        return hotelFileLoaderUseCase.loadHotelFile();
    }
}