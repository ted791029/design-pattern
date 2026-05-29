package com.ted.app.core;

public class VocabularyLearningSystem {

    public void start(SceneManager manager) {
        manager.push(SceneId.HOME);
        while (manager.getIsRunning()) {
            Scene current = manager.current();
            if (current == null) {
                break;
            }

            current.render();
            if (!manager.getContext().getScanner().hasNextLine()) {
                break;
            }

            String input = manager.getContext().getScanner().nextLine();
            String normalized = CliText.normalizeCommand(input);
            current.handleInput(normalized);
        }
    }
}
