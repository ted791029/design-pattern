package com.ted.app;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        // 建立輸入向量
        Double[] vector = new Double[1000];
        for (int i = 0; i < vector.length; i++) {
            vector[i] = 1.0;
        }

        // 建立執行緒池（例如 10 個執行緒）
        int threadCount = 10;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        // 提交 100 個任務，每個任務執行一次 model.linearTransformation
        for (int i = 0; i < 100; i++) {
            int taskNumber = i; // 用於顯示編號
            executor.submit(() -> {
                Models models = new ModelsCreator();
                Model model = models.createModel("Reflection.mat");
                model.linearTransformation(vector);
                System.out.println("Task " + taskNumber + " done by " + Thread.currentThread().getName());
            });
        }

        // 關閉執行緒池並等待全部任務完成
        executor.shutdown();
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

        System.out.println("All threads finished.");
    }

}
