package org.tnmk.practice.springboot.prometheuse.simple.samplebusiness.service;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Timer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelFileLoaderUseCase {
    private static final Logger logger = LoggerFactory.getLogger(HotelFileLoaderUseCase.class);

    //Mostly we can measure the running time by @Timed. This is an example code for measuring time manually.
    private final Timer timer = Metrics.timer("hotel_file_loader_manually_timer");

    //An example code for creating a counter by Static.
    private final Counter errorCounter = Metrics.counter("hotel_file_loader_use_case_counter", "success", "false");

    //An example code for creating a counter by Dependency Injection.
    private final Counter successCounter;

    @Autowired
    public HotelFileLoaderUseCase(MeterRegistry registry) {
        this.successCounter = registry.counter("hotel_file_loader_use_case_counter", "success", "true");
    }

    //TODO  @Timed seems doesn't work in @Service class. We'll may need TimerAspect
    //      https://stackoverflow.com/questions/48704789/how-to-measure-service-methods-using-spring-boot-2-and-micrometer
    //      https://stackoverflow.com/questions/29896424/using-timed-in-service-method
    @Timed(value = "hotel_file_loader_use_case_process")
    public String loadHotelFile(boolean expectSuccess) {
        try {
            timer.record(() -> stimulateRunningLongTask(expectSuccess));
            successCounter.increment();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            errorCounter.increment();
        }
        return "HotelFileLoader.counter.measure: " + successCounter.measure().toString();
    }

    private void stimulateRunningLongTask(boolean expectSuccess) {
        try {
            if (expectSuccess) {
                logger.info("loadHotelFile");
                Thread.sleep(1000);
            } else {
                throw new RuntimeException("Throw exception because expectSuccess is " + expectSuccess);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
