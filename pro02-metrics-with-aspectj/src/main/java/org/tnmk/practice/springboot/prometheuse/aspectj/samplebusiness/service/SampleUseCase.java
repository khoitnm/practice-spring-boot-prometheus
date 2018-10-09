package org.tnmk.practice.springboot.prometheuse.aspectj.samplebusiness.service;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.search.Search;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SampleUseCase {
    private static final Logger logger = LoggerFactory.getLogger(SampleUseCase.class);
    private static final String METRIC_NAME = "sample_use_case_process";

    @Autowired
    private MeterRegistry meterRegistry;

    //This @Timed annotation will also count the number of times this method is triggered.
    @Timed(value = METRIC_NAME, extraTags = {"success", "true"}
            ,longTask = true
            ,histogram = true

            // Read this to understand more about percentiles: https://www.elastic.co/blog/averages-can-dangerous-use-percentile
            // Important: percentiles is very expensive for calculation, please only use it when necessary.
            ,percentiles = {0.1, 0.5, 0.95, 0.99}
    )
    public String process() {
        stimulateRunningLongTask();

        logger.info("SampleUseCase.process");

        Meter meter = meterRegistry.find(METRIC_NAME).meter();
        if (meter != null) {
            return meter.measure().toString();
        } else {
            return "No meter " + METRIC_NAME + " yet, you need to call this method one more.";
        }
    }

    private void stimulateRunningLongTask() {
        try {
            long runTime = new Random().nextInt(3000);
            Thread.sleep(runTime);
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
