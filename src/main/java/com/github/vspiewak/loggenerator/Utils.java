package com.github.vspiewak.loggenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Utils {

    private static final Logger log = LoggerFactory.getLogger(Utils.class);
    private static final List<String> ips = new ArrayList<String>();

    static {
        try {
            InputStream is = Utils.class.getClassLoader().getResourceAsStream("ips.txt");
            Scanner scan = new Scanner(is);
            while (scan.hasNext()) {
                String ip = scan.next().trim();
                if (ip.length() > 0)
                    ips.add(ip);
            }
        } catch (Exception e) {
            log.error("Error during ip read/parsing", e);
        }
    }

    public static String getRandomIP() {
        int rndInt = new Random().nextInt(ips.size());
        return ips.get(rndInt);
    }
}
