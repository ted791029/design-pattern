package com.ted.app.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class AppContext {

    private DictionaryQueryTechnique dictionaryQueryTechnique;

    private Random random;

    private Scanner scanner;

    private SceneManager manager;

    private Map<AppContextKey, Object> tempData = new HashMap<>();

    private WordRepository wordRepository;

    public AppContext(Scanner scanner, SceneManager manager, WordRepository wordRepository, DictionaryQueryTechnique dictionaryQueryTechnique,
            Random random) {
        this.scanner = scanner;
        setManager(manager);
        this.wordRepository = wordRepository;
        this.dictionaryQueryTechnique = dictionaryQueryTechnique;
        this.random = random;
    }

    public void putTempData(AppContextKey key, Object value) {
        tempData.put(key, value);
    }

    public void removeTempData(AppContextKey key) {
        tempData.remove(key);
    }


    // Getters and setters
    public DictionaryQueryTechnique getDictionaryQueryTechnique() {
        return dictionaryQueryTechnique;
    }

    public SceneManager getManager() {
        return manager;
    }
    
    public Random getRandom() {
        return random;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public Object getTempData(AppContextKey key) {
        return tempData.get(key);
    }

    public WordRepository getWordRepository() {
        return wordRepository;
    }

    public void setDictionaryQueryTechnique(DictionaryQueryTechnique dictionaryQueryTechnique) {
        this.dictionaryQueryTechnique = dictionaryQueryTechnique;
    }

    public void setManager(SceneManager manager) {
        this.manager = manager;
        manager.setContext(this);
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public void setTempData(Map<AppContextKey, Object> tempData) {
        this.tempData = tempData;
    }

    public void setWordRepository(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }
}
