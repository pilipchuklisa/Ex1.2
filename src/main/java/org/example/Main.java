package org.example;

import com.clearspring.analytics.stream.cardinality.HyperLogLog;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        String path = "src/main/resources/ip.txt";

        try {
            long uniqueIPCount = countUniqueIPs(path);
            System.out.println("Количество уникальных IP адресов: ~" + uniqueIPCount);
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }
    }

    private static long countUniqueIPs(String inputFilename) throws IOException {

        HyperLogLog hyperLogLog = new HyperLogLog(0.001);

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilename))) {
            String ip;
            while ((ip = reader.readLine()) != null) {
                hyperLogLog.offer(ip.trim());
            }
        }

        return hyperLogLog.cardinality();
    }
}
