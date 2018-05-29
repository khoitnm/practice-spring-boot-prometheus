package org.tnmk.practice.springboot.prometheuse.simple;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RunJobsConcurrencyApplicationTests {

    @Autowired
    AsyncJobLauncher asyncJobLauncher;

    //TODO At this moment, somehow running 2 job instances concurrency get stuck!!!
    @Test
    public void startAsyncJobs() throws InterruptedException, ExecutionException {
        Future<String> future = asyncJobLauncher.run("/users.csv", "out/csv/users.processed.");
        Future<String> future2 = asyncJobLauncher.run("/users_7K.csv", "out/csv_7K/users.processed.");

        while (true) {
            if (future.isDone() && future2.isDone()) {
                System.out.println("Result from asynchronous process - " + future.get() + "& " + future2.get());
                break;
            }
            System.out.println("Continue doing something else. ");
            Thread.sleep(1000);
        }

    }

}
