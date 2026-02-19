package com.ted.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MatrixManager {

    private Map<String, Matrix> matrixMap = new HashMap<>();

    private String resourcePath = "D:\\sourcetree\\design-pattern\\GYM9A-computation-model\\src\\main\\resources\\";

    private static final Lock lock = new ReentrantLock();

    private MatrixManager() {

    }

    public static final MatrixManager INSTANCE = new MatrixManager();

    public Double[][] getMatrix(String name) {

        if (!matrixMap.containsKey(name)) {

            lock.lock();

            try {

                if (!matrixMap.containsKey(name)) {
                    Double[][] data = loadFile(name);
                    matrixMap.put(name, new Matrix(data));
                }

            } finally {
                lock.unlock();
            }
        }

        return matrixMap.get(name).getData();

    }

    private Double[][] loadFile(String name) {
        System.out.println("進入耗費大量資源");
        String filePath = resourcePath + name;
        List<Double[]> rows = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue; // 跳過空行
                String[] parts = line.split("\\s+"); // 以空白分隔
                Double[] row = new Double[parts.length];
                for (int i = 0; i < parts.length; i++) {
                    row[i] = Double.parseDouble(parts[i]);
                }
                rows.add(row);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }

        Double[][] matrix = rows.toArray(new Double[0][]);

        return matrix;
    }
}
