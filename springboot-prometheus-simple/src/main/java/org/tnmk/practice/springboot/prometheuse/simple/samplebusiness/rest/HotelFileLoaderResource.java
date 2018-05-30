package org.tnmk.practice.springboot.prometheuse.simple.samplebusiness.rest;

import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tnmk.practice.springboot.prometheuse.simple.samplebusiness.service.HotelFileLoaderUseCase;

/**
 * This REST API will be exposed via the port 8080
 * While metrics APIs will be exposed via the port 9090
 */
@RestController
//@Timed //enable timings on every request handler in the controller.
public class HotelFileLoaderResource {

    private final HotelFileLoaderUseCase hotelFileLoaderUseCase;

    @Autowired
    public HotelFileLoaderResource(HotelFileLoaderUseCase hotelFileLoaderUseCase) {
        this.hotelFileLoaderUseCase = hotelFileLoaderUseCase;
    }

    // Enable timings for an individual endpoint.
    // This is not necessary if you have it on the class,
    // but can be used to further customize the timer for this particular endpoint.
    @Timed("hotel_file_loader_resource")
    @RequestMapping(value = {"/hotel-file"})
    public String loadHotelFile() {
        return hotelFileLoaderUseCase.loadHotelFile();
    }
}