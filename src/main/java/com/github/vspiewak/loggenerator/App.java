package com.github.vspiewak.loggenerator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);
    private static AtomicLong counter = new AtomicLong(0);

    public static void main(String[] args) {

        InputParameters params = new InputParameters();
        JCommander commander = new JCommander(params);
        try {
            commander.parse(args);
        } catch (ParameterException e) {
            commander.usage();
            System.exit(1);
        }

        log.trace("initialization");

        LogExecutor executor = new LogExecutor(params.threads);

        while (counter.get() < params.logs) {
            SearchTask aSearchTask = new SearchTask(counter.incrementAndGet());
            SellTask aSellTask = new SellTask(counter.incrementAndGet());
            executor.addAll(Arrays.asList(aSearchTask, aSellTask));
        }

        log.trace("initialization done");

        long start_time = System.nanoTime();
        executor.execute();
        long elapsed_time = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start_time);

        log.trace("generated {} logs in {}ms using {} threads", counter.get(), elapsed_time, params.threads);
        log.trace("shutdown");

    }

}
