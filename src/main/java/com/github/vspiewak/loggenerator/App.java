package com.github.vspiewak.loggenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicLong;

public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);
    private static AtomicLong counter = new AtomicLong(0);

    public static final long NB_LOGS = 10;

    public static void main(String... args) {

        log.debug("starting");

        LogExecutor executor = new LogExecutor(5);

        while (counter.get() < NB_LOGS) {
            long number = counter.incrementAndGet();
            SellTask task1 = new SellTask(number);
            SearchTask task2 = new SearchTask(number);

            executor.add(task1)
                    .add(task2);
        }

        executor.execute();

        log.debug("shutdown");

    }

}
