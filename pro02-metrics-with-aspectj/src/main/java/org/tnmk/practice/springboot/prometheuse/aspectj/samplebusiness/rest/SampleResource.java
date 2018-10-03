package org.tnmk.practice.springboot.prometheuse.aspectj.samplebusiness.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tnmk.practice.springboot.prometheuse.aspectj.samplebusiness.service.SampleUseCase;

/**
 * This REST API will be exposed via the port 8080
 * While metrics APIs will be exposed via the port 9090
 */
@RestController
public class SampleResource {

    private final SampleUseCase sampleUseCase;

    @Autowired
    public SampleResource(SampleUseCase sampleUseCase) {
        this.sampleUseCase = sampleUseCase;
    }

    @RequestMapping(value = {"/sample-api"})
    public String process() {
        return sampleUseCase.process();
    }
}