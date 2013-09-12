package com.github.vspiewak.loggenerator;

import com.sun.org.apache.xml.internal.security.algorithms.implementations.SignatureDSA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utils {

    private static final Logger log = LoggerFactory.getLogger(Utils.class);
    private static final List<String> ips = new ArrayList<String>();

    static {
        try {
            URI uri = Thread.currentThread().getContextClassLoader().getResource("ips.txt").toURI();
            ips.addAll(Files.readAllLines(Paths.get(uri), StandardCharsets.UTF_8));
        } catch (Exception e) {
            log.error("Error during ip read/parsing", e);
        }
    }

    public static String getRandomIP() {
        int rndInt = new Random().nextInt(ips.size());
        return ips.get(rndInt);
    }
}
