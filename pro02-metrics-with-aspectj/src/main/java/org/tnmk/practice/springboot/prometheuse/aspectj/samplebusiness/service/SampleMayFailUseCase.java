package org.tnmk.practice.springboot.prometheuse.aspectj.samplebusiness.service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.search.RequiredSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.tnmk.practice.springboot.prometheuse.aspectj.metrics.MetricConstants.*;

@Service
public class SampleMayFailUseCase {
    private static final Logger logger = LoggerFactory.getLogger(SampleMayFailUseCase.class);

    @Autowired
    private MeterRegistry meterRegistry;


    public String process(String status) {
        long startTime = System.nanoTime();
        try {
            if (status.equalsIgnoreCase("success")) {
                //Timer must have a different name from Counter.
                //Note: timer includes count, so you don't actually need count anymore.
                Timer timer = meterRegistry.timer(METRIC_NAME, TAG_NAME_STATUS, TAG_VALUE_STATUS_SUCCESS);
                timer.record(() -> doSomething());

                return "Success Measure: " + timer.measure();
            } else {
                throw new RuntimeException("I fail for you.");
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);

            //The Counter could be the same name as the Timer, but the Tags must be different.
            Counter counter = meterRegistry.counter(METRIC_NAME, TAG_NAME_STATUS, TAG_VALUE_STATUS_FAIL);
            counter.increment();
            return "Fail Measure: " + counter.measure();

        }
    }

    private void doSomething() {
        long runningTime = new Random().nextInt(2000);
        try {
            Thread.sleep(runningTime);
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
