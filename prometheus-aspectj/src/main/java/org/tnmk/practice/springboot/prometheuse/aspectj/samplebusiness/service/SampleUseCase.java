package org.tnmk.practice.springboot.prometheuse.aspectj.samplebusiness.service;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SampleUseCase {
    private static final Logger logger = LoggerFactory.getLogger(SampleUseCase.class);
    private static final String METRIC_NAME = "sample_use_case_process";


    @Timed(value = METRIC_NAME, longTask = true, histogram = true, extraTags = {"success", "true"})
    public String process() {
        stimulateRunningLongTask();

        logger.info("SampleUseCase.process");
        return Metrics.counter(METRIC_NAME+"_seconds", "success", "true").measure().toString();
    }

    private void stimulateRunningLongTask() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
