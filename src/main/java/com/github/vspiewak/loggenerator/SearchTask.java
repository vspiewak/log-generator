package com.github.vspiewak.loggenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

public class SearchTask implements Callable<Long> {

    private static final Logger log = LoggerFactory.getLogger(SearchTask.class);
    private final long id;

        public SearchTask(final long id) {
            this.id = id;
        }

        @Override
        public Long call() throws Exception {
            log.info("{} - {}", id, Utils.getRandomIP());
            return id;
        }

}
