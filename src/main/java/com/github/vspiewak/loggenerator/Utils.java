package com.github.vspiewak.loggenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Utils {

    private static final String LOG_SEPARATOR = " - ";
    private static final Logger log = LoggerFactory.getLogger(Utils.class);
    private static final List<String> ips = new ArrayList<String>();
    private static final List<String> products = new ArrayList<String>();

    private static void readFromFile(String file, List<String> list) {
        try {
            InputStream is = Utils.class.getClassLoader().getResourceAsStream(file);
            Scanner scan = new Scanner(is);
            while (scan.hasNext()) {
                String line = scan.next().trim();
                if (line.length() > 0)
                    list.add(line);
            }
        } catch (Exception e) {
            log.error("Error during read/parse of file: ", file);
        }
    }

    private static <E> E getRandomFromList(List<E> list) {
        int index = new Random().nextInt(list.size());
        return list.get(index);
    }

    private static <E> E getRandomFromArray(E[] array) {
        int index = new Random().nextInt(array.length);
        return array[index];
    }

    private static String getRandomIP() {
        return getRandomFromList(ips);
    }

    private static double getRandomPrice() {
        int min = 30;
        int max = 99;
        return min + new Random().nextInt(max - min) + 0.99;
    }

    public static String getRandomSearch() {
        return getRandomIP() + LOG_SEPARATOR + getRandomSearch(new Random().nextInt(10));
    }

    private static String getRandomSearch(int seed) {

        StringBuilder sb = new StringBuilder();

        sb.append(LOG_SEPARATOR);
        sb.append(getRandomFromArray(categories.values()));

        sb.append(LOG_SEPARATOR);
        if (seed > 7) {
            sb.append(getRandomFromArray(matierials.values()));
        }

        sb.append(LOG_SEPARATOR);
        if (seed > 4) {
            sb.append(getRandomFromArray(colors.values()));
        }

        sb.append(LOG_SEPARATOR);
        if (seed > 3) {
            sb.append(getRandomFromArray(sizes.values()));
        }

        return sb.toString();
    }

    public static String getRandomClient() {
        int rnd = new Random().nextInt(500);
        String sex = "M";
        if(rnd > 300) sex = "F";
        StringBuffer sb = new StringBuffer();
        sb.append("client").append(rnd).append("@gmail.com")
                .append(LOG_SEPARATOR)
                .append(sex);
        return sb.toString();
    }

    public static String getRandomSell() {
        return new StringBuffer()
                .append(getRandomIP())
                .append(LOG_SEPARATOR)
                .append(getRandomClient())
                .append(LOG_SEPARATOR)
                .append(getRandomFromList(products))
                .toString();
    }

    private static void initProducts(int n) {
        for (int i = 0; i < n; i++) {
            StringBuffer sb = new StringBuffer()
                    .append("ref:"+ (i + 1))
                    .append(getRandomSearch(100)).append(LOG_SEPARATOR)
                    .append(getRandomPrice());
            products.add(sb.toString());
        }
    }

    private enum sizes { XS, S, M, L, XL }

    private enum colors { BLANC, NOIR, BLEU, VERT, ROSE, MARRON }

    private enum categories { TSHIRT, DEBARDEUR, PULL, BOXER, CALCON, SLIP }

    private enum matierials { COTON, SOIE, SYNTHETIQUE }

    static {
        readFromFile("ips.txt", ips);
        initProducts(100);
    }

}
