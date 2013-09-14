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
        int min = 40;
        int max = 200;
        return min + new Random().nextInt(max - min) + 0.99;
    }

    public static String getRandomSearch() {
        return getRandomSearch(new Random().nextInt(10));
    }

    private static String getRandomSearch(int seed) {

        StringBuilder sb = new StringBuilder();

        sb.append(getRandomIP());

        sb.append(LOG_SEPARATOR);
        sb.append(getRandomFromArray(cat.values()));

        sb.append(LOG_SEPARATOR);
        if (seed > 7) {
            sb.append(getRandomFromArray(matiere.values()));
        }

        sb.append(LOG_SEPARATOR);
        if (seed > 4) {
            sb.append(getRandomFromArray(colors.values()));
        }

        sb.append(LOG_SEPARATOR);
        if (seed > 3) {
            sb.append(getRandomFromArray(size.values()));
        }

        return sb.toString();
    }

    public static String getRandomSell() {
        return getRandomFromList(products);
    }

    private static void initProducts(int n) {
        for (int i = 0; i < n; i++) {
            products.add((i + 1) + LOG_SEPARATOR + getRandomSearch(100) + LOG_SEPARATOR + getRandomPrice());
        }
    }

    private enum size {XS, S, M, L, XL}

    private enum colors {BLANC, NOIR, BLEU, VERT, ROSE, MARRON}

    private enum cat {TSHIRT, DEBARDEUR, PULL, BOXER, CALCON, SLIP}


    private enum matiere {COTON, SOIE, SYNTHETIQUE}

    static {
        readFromFile("ips.txt", ips);
        initProducts(100);
    }

}
