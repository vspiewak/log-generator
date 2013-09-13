package com.github.vspiewak.loggenerator;

import com.beust.jcommander.Parameter;

public class InputParameters {

    @Parameter(names = { "-log", "-n" }, description = "Number of logs to generate")
    public Long logs = 10L;

    @Parameter(names = { "-threads", "-t" }, description = "Number of threads to use")
    public Integer threads = 2;

    @Parameter(names = "--help", help = true)
    private boolean help;

}
