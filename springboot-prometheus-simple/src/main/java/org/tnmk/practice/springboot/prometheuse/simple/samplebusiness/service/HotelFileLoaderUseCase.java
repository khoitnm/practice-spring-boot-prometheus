package org.tnmk.practice.springboot.prometheuse.simple.samplebusiness.service;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelFileLoaderUseCase {
    private static final Logger logger = LoggerFactory.getLogger(HotelFileLoaderUseCase.class);

    private final Counter counter;

    @Autowired
    public HotelFileLoaderUseCase(MeterRegistry registry) {
        this.counter = registry.counter("hotel_file_loader_use_case_counter");
    }

    //This annotation seems doesn't work here
    @Timed(value = "hotel_file_loader_use_case_process", longTask = true, histogram = true)
    public String loadHotelFile() {
        //Count the number of request to this method
        this.counter.increment();

        stimulateRunningLongTask();

        logger.info("loadHotelFile");
        return "HotelFileLoader.counter.measure: " + counter.measure().toString();
    }

    private void stimulateRunningLongTask(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
