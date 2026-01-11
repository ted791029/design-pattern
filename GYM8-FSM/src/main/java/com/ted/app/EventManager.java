package com.ted.app;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import com.ted.bot.Bot;
import com.ted.bot.BotEvent;
import com.ted.bot.BotFacade;
import com.ted.bot.Community;

public class EventManager {

    private BotFacade botFacade;

    private static final BlockingQueue<BotEvent> queue = new LinkedBlockingQueue<>();
    private static ExecutorService executor = Executors.newSingleThreadExecutor();

    private BotResponseHandler botResponseHandler;

    private Printer printer;

    public EventManager(BotFacade botFacade, BotResponseHandler botResponseHandler, Printer printer) {
        this.botFacade = botFacade;
        this.botResponseHandler = botResponseHandler;
        this.printer = printer;
        autoWorkerQueue();
    }

    public static void cleanup() {
        queue.clear();
        if (executor != null && !executor.isShutdown()) {
            executor.shutdownNow();
            try {
                // 等待執行器完全關閉，最多等待 2 秒
                if (!executor.awaitTermination(2, java.util.concurrent.TimeUnit.SECONDS)) {
                    // 如果 2 秒內沒有關閉，強制關閉
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                // 如果等待過程中被中斷，強制關閉
                executor.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
        // 重新創建執行器以供後續測試使用
        executor = Executors.newSingleThreadExecutor();
    }

    public static void submit(BotEvent event) throws InterruptedException {
        queue.put(event); // 若滿則阻塞
    }

    public void shutdown() {
        executor.shutdownNow();
    }

    private void autoWorkerQueue() {
        // 啟動消費者執行緒
        executor.submit(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    // take() 會阻塞直到有資料
                    BotEvent event = queue.take();
                    printer.print(event);
                    Map<String, String> resultMap =  botFacade.sendEvent(event);
                    botResponseHandler.handle(resultMap);
                } catch (InterruptedException e) {
                    // 線程被中斷，重新設置中斷標誌並退出
                    Thread.currentThread().interrupt();
                    break;
                } catch (Exception e) {
                    // 記錄其他異常但不終止執行緒，繼續處理下一個事件
                    e.printStackTrace();
                }
            }
        });
    }

    //=================================================


    public BotFacade getBotFacade() {
        return botFacade;
    }

    public void setBotFacade(BotFacade botFacade) {
        this.botFacade = botFacade;
    }

    public static ExecutorService getExecutor() {
        return executor;
    }

    public static void setExecutor(ExecutorService executor) {
        EventManager.executor = executor;
    }

    public BotResponseHandler getBotResponseHandler() {
        return botResponseHandler;
    }

    public void setBotResponseHandler(BotResponseHandler botResponseHandler) {
        this.botResponseHandler = botResponseHandler;
    }

    public Printer getPrinter() {
        return printer;
    }

    public void setPrinter(Printer printer) {
        this.printer = printer;
    }
}
