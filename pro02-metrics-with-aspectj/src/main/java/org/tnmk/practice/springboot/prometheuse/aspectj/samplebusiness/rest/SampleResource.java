package org.tnmk.practice.springboot.prometheuse.aspectj.samplebusiness.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tnmk.practice.springboot.prometheuse.aspectj.samplebusiness.service.SampleMayFailUseCase;
import org.tnmk.practice.springboot.prometheuse.aspectj.samplebusiness.service.SampleUseCase;

/**
 * This REST API will be exposed via the port 8080
 * While metrics APIs will be exposed via the port 9090
 */
@RestController
public class SampleResource {

    private final SampleUseCase sampleUseCase;
    private final SampleMayFailUseCase sampleMayFailUseCase;

    @Autowired
    public SampleResource(SampleUseCase sampleUseCase, SampleMayFailUseCase sampleMayFailUseCase) {
        this.sampleUseCase = sampleUseCase;
        this.sampleMayFailUseCase = sampleMayFailUseCase;
    }

    @RequestMapping(value = {"/sample-api"})
    public String process() {
        return sampleUseCase.process();
    }

    @RequestMapping("/sample-api/manual-code-metric/{status}")
    public String processError(@PathVariable("status") String status) {
        return sampleMayFailUseCase.process(status);
    }
}