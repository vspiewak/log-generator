package com.github.vspiewak.loggenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);
    private static AtomicLong counter = new AtomicLong(0);

    public static final long DEFAULT_NB_LOGS = 100;
    public static final int DEFAULT_NB_THREAD = 2;

    public static void main(String[] args) {

        long nbLogsToGenerate = DEFAULT_NB_LOGS;
        int nbThreads = DEFAULT_NB_THREAD;

        if(args.length > 0) {
            try {
                nbLogsToGenerate = Long.parseLong(args[0]);
            } catch(NumberFormatException e) {
                log.info("Usage: first argument must be the number of logs to generate");
                System.exit(1);
            }
        }

        log.trace("starting");

        long start_time = System.nanoTime();

        LogExecutor executor = new LogExecutor(nbThreads);

        while (counter.get() < nbLogsToGenerate) {
            long number = counter.incrementAndGet();
            SearchTask aSearchTask = new SearchTask(number);
            SellTask aSellTask = new SellTask(number);
            executor.addAll(Arrays.asList(aSearchTask, aSellTask));
        }

        executor.execute();

        long end_time = System.nanoTime();
        double difference = (end_time - start_time)/1e6;

        log.trace("generated {} logs in {}ms using {} threads", counter.get(), (int)difference, nbThreads);
        log.trace("shutdown");

    }

}
