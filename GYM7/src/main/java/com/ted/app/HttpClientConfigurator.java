package com.ted.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HttpClientConfigurator {

    private String configPath;

    private String blacklistConfigPath;

    public HttpClientConfigurator(String configPath, String blacklistConfigPath) {
        this.configPath = configPath;
        this.blacklistConfigPath = blacklistConfigPath;
    }

    public List<String> loadBlacklistConfig(){
        List<String> configs = loadFile(blacklistConfigPath);
        return configs;
    }

    public Map<String, List<IP>> loadConfig(){
        List<String> configs = loadFile(configPath);
        Map<String, List<IP>> hostConfigMap = new HashMap<>();

        for (String config : configs) {
            String[] parts = config.split(":");
            if (parts.length != 2) continue;

            String host = parts[0].trim();
            List<IP> ips = new ArrayList<>();

            for (String ip : parts[1].split(",")) {
                ips.add(new IP(ip.trim()));
            }

            hostConfigMap.put(host, ips);
        }

        return hostConfigMap;
    }

    private List<String> loadFile(String path){
        List<String> result = null;
        try {
            result = Files.lines(Paths.get(path))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    //===============================

    public String getConfigPath() {
        return configPath;
    }

    public void setConfigPath(String configPath) {
        this.configPath = configPath;
    }

    public String getBlacklistConfigPath() {
        return blacklistConfigPath;
    }

    public void setBlacklistConfigPath(String blacklistConfigPath) {
        this.blacklistConfigPath = blacklistConfigPath;
    }
}
