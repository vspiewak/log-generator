package com.github.vspiewak.loggenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Utils {

    private static final String LOG_SEPARATOR = ",";
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

    public static String getRandomClient(long id) {
        String sex = "M";
        if (id % 3 == 0) sex = "F";
        return new StringBuilder()
                .append("email=")
                .append("client").append((id % 1000) + 1).append("@gmail.com")
                .append(LOG_SEPARATOR)
                .append("sex=")
                .append(sex)
                .toString();
    }

    public static String getRandomSell(long id) {
        return new StringBuilder()
                .append("id=")
                .append(id)
                .append(LOG_SEPARATOR)
                .append("ip=")
                .append(getRandomIP())
                .append(LOG_SEPARATOR)
                .append(getRandomClient(id))
                .append(LOG_SEPARATOR)
                .append(getRandomFromList(products))
                .toString();
    }

    public static String getRandomSearch(long id) {
        StringBuilder sb = new StringBuilder()
                .append("id=")
                .append(id)
                .append(LOG_SEPARATOR)
                .append("ip=")
                .append(getRandomIP())
                .append(LOG_SEPARATOR)
                .append("cat=")
                .append(getRandomFromArray(categories.values()));

        if (id % 4 == 0) {
            sb.append(LOG_SEPARATOR)
                    .append("mat=")
                    .append(getRandomFromArray(matierials.values()));
        }

        if (id % 3 == 0) {
            sb.append(LOG_SEPARATOR)
                    .append("color=")
                    .append(getRandomFromArray(colors.values()));
        }

        if (id % 2 == 0) {
            sb.append(LOG_SEPARATOR)
                    .append("size=")
                    .append(getRandomFromArray(sizes.values()));
        }

        return sb.toString();
    }

    private static void initProducts(int n) {
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder()
                    .append("ref=" + (i + 1))
                    .append(LOG_SEPARATOR)
                    .append("cat=")
                    .append(getRandomFromArray(categories.values()))
                    .append(LOG_SEPARATOR)
                    .append("mat=")
                    .append(getRandomFromArray(matierials.values()))
                    .append(LOG_SEPARATOR)
                    .append("color=")
                    .append(getRandomFromArray(colors.values()))
                    .append(LOG_SEPARATOR)
                    .append("size=")
                    .append(getRandomFromArray(sizes.values()))
                    .append(LOG_SEPARATOR)
                    .append("price=")
                    .append(getRandomPrice());
            products.add(sb.toString());
        }
    }

    private enum sizes {XS, S, M, L, XL}

    private enum colors {BLANC, NOIR, BLEU, VERT, ROSE, MARRON}

    private enum categories {TSHIRT, DEBARDEUR, PULL, BOXER, CALCON, SLIP}

    private enum matierials {COTON, SOIE, SYNTHETIQUE}

    static {
        readFromFile("ips.txt", ips);
        initProducts(100);
    }

}
