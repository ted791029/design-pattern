package com.ted;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ted.app.BotResponseHandler;
import com.ted.app.Chanel;
import com.ted.app.EventManager;
import com.ted.app.Message;
import com.ted.app.Post;
import com.ted.app.Printer;
import com.ted.app.Speak;
import com.ted.app.Tag;
import com.ted.app.User;
import com.ted.app.WaterballCommunity;
import com.ted.app.chanel.Broadcast;
import com.ted.app.chanel.ChatRoom;
import com.ted.app.chanel.Forum;
import com.ted.bot.BotEvent;
import com.ted.bot.BotEventName;
import com.ted.bot.BotFacade;

public class TestCaseRunner {

    private WaterballCommunity community;
    private EventManager eventManager;
    private ChatRoom chatRoom;
    private Forum forum;
    private Broadcast broadcast;
    private Map<String, User> users;
    private String inputFilePath;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;
    private BotResponseHandler botResponseHandler;
    private Printer printer;

    public TestCaseRunner(String inputFilePath) {
        this.inputFilePath = inputFilePath;
    }

    public static void main(String[] args) {
        // 設置控制台輸出編碼為 UTF-8（如果系統支持）
        try {
            System.setProperty("file.encoding", "UTF-8");
            System.setProperty("console.encoding", "UTF-8");
        } catch (Exception e) {
            // 忽略設置失敗
        }

        String testCasesDir = "D:\\sourcetree\\design-pattern\\GYM8-FSM\\src\\test\\resources\\testcases";

        try {
            // 掃描 testcases 目錄下的所有 .in 文件
            List<File> testFiles = findTestFiles(testCasesDir);

            if (testFiles.isEmpty()) {
                System.err.println("未找到任何測試文件 (.in) 在目錄: " + testCasesDir);
                System.exit(1);
            }

            System.out.println("找到 " + testFiles.size() + " 個測試文件，開始執行測試...\n");

            int passedCount = 0;
            int failedCount = 0;

            for (File testFile : testFiles) {
                String testFileName = testFile.getName();
                System.out.println("執行測試: " + testFileName);

                TestCaseRunner runner = new TestCaseRunner(testFile.getPath());
                try {
                    boolean passed = runner.runTestCase();
                    if (passed) {
                        passedCount++;
                        System.out.println("✓ " + testFileName + " - 通過\n");
                    } else {
                        failedCount++;
                        System.out.println("✗ " + testFileName + " - 失敗\n");
                    }
                } catch (Exception e) {
                    failedCount++;
                    System.err.println("✗ " + testFileName + " - 執行錯誤:");
                    e.printStackTrace();
                    System.out.println();
                }
            }

            // 輸出測試摘要
            System.out.println("=== 測試摘要 ===");
            System.out.println("總計: " + testFiles.size() + " 個測試");
            System.out.println("通過: " + passedCount);
            System.out.println("失敗: " + failedCount);

            System.exit(failedCount > 0 ? 1 : 0);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static List<File> findTestFiles(String directory) {
        List<File> testFiles = new ArrayList<>();
        File dir = new File(directory);

        if (!dir.exists() || !dir.isDirectory()) {
            return testFiles;
        }

        File[] files = dir.listFiles((dir1, name) -> name.endsWith(".in"));
        if (files != null) {
            for (File file : files) {
                testFiles.add(file);
            }
        }

        return testFiles;
    }

    public boolean runTestCase() throws IOException, InterruptedException {
        // 清理之前的測試狀態（清空靜態隊列）
        cleanupPreviousTest();

        // 設置輸出捕獲
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        PrintStream captureStream = new PrintStream(outputStream, true, "UTF-8");
        System.setOut(captureStream);

        try {
            // 執行測試
            List<String> lines = readFile(inputFilePath);

            for (String line : lines) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }

                processLine(line);
            }

            // 等待所有事件處理完成
            // 使用固定時間等待，因為 EventManager 的 queue 和 executor 是靜態共享的
            Thread.sleep(2000);

            // 獲取實際輸出
            String actualOutput = outputStream.toString("UTF-8");

            // 恢復原始輸出（在比對前恢復，以便比對結果能正常輸出）
            System.setOut(originalOut);

            // 讀取期望輸出
            String expectedOutputPath = getExpectedOutputPath(inputFilePath);
            String expectedOutput = readExpectedOutput(expectedOutputPath);

            // 比對輸出
            return compareOutput(actualOutput, expectedOutput);
        } catch (Exception e) {
            // 確保在異常時也恢復輸出
            System.setOut(originalOut);
            throw e;
        }
    }

    private void cleanupPreviousTest() {
        // 清理之前測試的靜態狀態
        EventManager.cleanup();
        // 等待執行器完全關閉
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private String getExpectedOutputPath(String inputPath) {
        if (inputPath.endsWith(".in")) {
            return inputPath.replace(".in", ".out");
        }
        return inputPath + ".out";
    }

    private String readExpectedOutput(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            System.err.println("警告: 期望輸出文件不存在: " + filePath);
            return "";
        }
        return readFileContent(filePath);
    }

    private String readFileContent(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }

    private boolean compareOutput(String actual, String expected) {
        // 標準化輸出：移除每行末尾的空白字符，然後移除末尾的空白行
        actual = normalizeOutput(actual);
        expected = normalizeOutput(expected);

        if (actual.equals(expected)) {
            return true;
        } else {
            System.out.println("輸出不匹配！");
            System.out.println("\n=== 期望輸出 ===");
            System.out.println(expected);
            System.out.println("\n=== 實際輸出 ===");
            System.out.println(actual);
            System.out.println("\n=== 差異詳情 ===");
            printDiff(actual, expected);
            return false;
        }
    }

    private String normalizeOutput(String output) {
        // 去除每行末尾的空白字符，然後移除整個字符串末尾的空白
        String[] lines = output.split("\n");
        StringBuilder normalized = new StringBuilder();
        for (int i = 0; i < lines.length; i++) {
            if (i > 0) {
                normalized.append("\n");
            }
            normalized.append(lines[i].replaceAll("\\s+$", "")); // 去除行尾空白
        }
        return normalized.toString().trim(); // 移除整體末尾空白
    }

    private void printDiff(String actual, String expected) {
        String[] actualLines = actual.split("\n");
        String[] expectedLines = expected.split("\n");

        int maxLines = Math.max(actualLines.length, expectedLines.length);
        for (int i = 0; i < maxLines; i++) {
            String actualLine = i < actualLines.length ? actualLines[i] : "<缺失>";
            String expectedLine = i < expectedLines.length ? expectedLines[i] : "<缺失>";

            if (!actualLine.equals(expectedLine)) {
                System.out.println("行 " + (i + 1) + ":");
                System.out.println("  期望: " + expectedLine);
                System.out.println("  實際: " + actualLine);
            }
        }
    }

    private List<String> readFile(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

    private void processLine(String line) throws InterruptedException {
        Thread.sleep(100);
        if (line.equals("[end]")) {
            return;
        }

        // 處理時間流逝事件
        if (line.matches("\\[\\d+\\s+(second|seconds|minute|minutes|hour|hours)\\s+elapsed\\]")) {
            handleTimeElapsed(line);
            return;
        }

        // 解析命令格式：[command] {json}
        int jsonStart = line.indexOf('{');
        if (jsonStart == -1) {
            return;
        }

        String command = line.substring(0, jsonStart).trim();
        String jsonStr = line.substring(jsonStart);

        // 移除命令的方括號
        command = command.replace("[", "").replace("]", "");

        JsonObject json = JsonParser.parseString(jsonStr).getAsJsonObject();

        switch (command) {
            case "started":
                handleStarted(json);
                break;
            case "login":
                handleLogin(json);
                break;
            case "logout":
                handleLogout(json);
                break;
            case "new message":
                handleNewMessage(json);
                break;
            case "new post":
                handleNewPost(json);
                break;
            case "speak":
                handleSpeak(json);
                break;
            case "go broadcasting":
                handleGoBroadcasting(json);
                break;
            case "stop broadcasting":
                handleStopBroadcasting(json);
                break;
        }
    }

    private void handleStarted(JsonObject json) throws InterruptedException {
        community = new WaterballCommunity();
        users = new HashMap<>();
        int quota = json.get("quota").getAsInt();
        BotFacade botFacade = new BotFacade(community, quota);
        botResponseHandler = new BotResponseHandler();
        printer = new Printer();
        eventManager = new EventManager(botFacade, botResponseHandler, printer);
        chatRoom = new ChatRoom();
        forum = new Forum();
        broadcast = new Broadcast();
        Map<String, Chanel> chanelMap = new HashMap<>();
        chanelMap.put("ChatRoom", chatRoom);
        chanelMap.put("Forum", forum);
        chanelMap.put("Broadcast", broadcast);
        community.setChanelMap(chanelMap);
        community.setManager(eventManager);
        community.setOnlineUsers(users);
    }

    private void handleLogin(JsonObject json) throws InterruptedException {
        String userId = json.get("userId").getAsString();
        boolean isAdmin = json.get("isAdmin").getAsBoolean();

        User user = new User(userId, isAdmin);
        community.login(user);
    }

    private void handleLogout(JsonObject json) throws InterruptedException {
        String userId = json.get("userId").getAsString();
        User user = users.get(userId);
        if (user != null) {
            community.logout(user);
        }
    }

    private void handleNewMessage(JsonObject json) throws InterruptedException {
        String authorId = json.get("authorId").getAsString();
        String content = json.get("content").getAsString();
        JsonArray tagsArray = json.getAsJsonArray("tags");

        User user = users.get(authorId);
        if (user == null) {
            return;
        }

        List<Tag> tags = new ArrayList<>();
        for (int i = 0; i < tagsArray.size(); i++) {
            String tagValue = tagsArray.get(i).getAsString();
            tags.add(new Tag(tagValue));
        }

        Message message = new Message(content, tags, user);
        chatRoom.send(message);
    }

    private void handleNewPost(JsonObject json) throws InterruptedException {
        String id = json.get("id").getAsString();
        String authorId = json.get("authorId").getAsString();
        String title = json.get("title").getAsString();
        String content = json.get("content").getAsString();
        JsonArray tagsArray = json.getAsJsonArray("tags");

        User user = users.get(authorId);
        if (user == null) {
            return;
        }

        List<Tag> tags = new ArrayList<>();
        for (int i = 0; i < tagsArray.size(); i++) {
            String tagValue = tagsArray.get(i).getAsString();
            tags.add(new Tag(tagValue));
        }

        Post post = new Post(content, id, tags, title, user);
        forum.send(post);
    }

    private void handleSpeak(JsonObject json) throws InterruptedException {
        String speakerId = json.get("speakerId").getAsString();
        String content = json.get("content").getAsString();

        User user = users.get(speakerId);
        if (user == null) {
            return;
        }

        Speak speak = new Speak(content, user);
        broadcast.send(speak);
    }

    private void handleGoBroadcasting(JsonObject json) throws InterruptedException {
        // 根據 Broadcast 類的實現，調用 goBroadcasting 方法
        String speakerId = json.get("speakerId").getAsString();
        User user = users.get(speakerId);
        if (user == null) {
            return;
        }

        broadcast.goBroadcasting(user);
    }

    private void handleStopBroadcasting(JsonObject json) throws InterruptedException {
        // 根據 Broadcast 類的實現，調用 stopBroadcasting 方法
        String speakerId = json.get("speakerId").getAsString();
        User user = users.get(speakerId);
        if (user == null) {
            return;
        }
        broadcast.stopBroadcasting(user);
    }

    private void handleTimeElapsed(String line) throws InterruptedException {
        // 解析格式：[5 seconds elapsed] 或 [2 minutes elapsed] 或 [1 hours elapsed]
        // 移除方括號和 "elapsed"
        String content = line.replace("[", "").replace("]", "") + "...";
        String time = line.replace("[", "").replace("]", "").replace(" elapsed", "").trim();
        
        // 提取數字和單位
        String[] parts = time.split("\\s+");
        if (parts.length != 2) {
            return; // 格式不正確，忽略
        }
        
        try {
            int value = Integer.parseInt(parts[0]);
            String unit = parts[1].toLowerCase();
            
            // 轉換為毫秒
            long milliseconds = 0;
            switch (unit) {
                case "second":
                case "seconds":
                    milliseconds = value * 1000L;
                    break;
                case "minute":
                case "minutes":
                    milliseconds = value * 60L * 1000L;
                    break;
                case "hour":
                case "hours":
                    milliseconds = value * 60L * 60L * 1000L;
                    break;
                default:
                    return; // 未知單位，忽略
            }
            
            // 創建 JSON payload
            JsonObject payloadJson = new JsonObject();
            payloadJson.addProperty("time", milliseconds);
            payloadJson.addProperty("content", content);
            String payload = payloadJson.toString();

            if(unit.equals("seconds")){
                Thread.sleep(1000);
            }
            
            // 創建並提交 TIME_ELAPSED 事件
            EventManager.submit(new BotEvent(BotEventName.TIME_ELAPSED.getName(), payload));
        } catch (NumberFormatException e) {
            // 數字解析失敗，忽略
            return;
        }
    }
}
