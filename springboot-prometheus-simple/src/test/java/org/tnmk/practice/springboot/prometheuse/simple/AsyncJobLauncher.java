package org.tnmk.practice.springboot.prometheuse.simple;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * I just used this class for simulate the async requests.
 */
@Service
public class AsyncJobLauncher {

    @Async
    public Future<String> run(String inputFilePath, String outputPathWithFilePrefix) {
        return new AsyncResult<>("Finish " + outputPathWithFilePrefix);
    }
}
