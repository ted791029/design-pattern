package com.ted.app.core;

public abstract class Command implements MenuOption {

    private String optionCustomKey;

    private String optionLabel;

    private boolean visible;

    public Command(String optionCustomKey, String optionLabel) {
        this(optionCustomKey, optionLabel, true);
    }

    public Command(String optionCustomKey, String optionLabel, boolean visible) {
        this.optionCustomKey = optionCustomKey;
        this.optionLabel = optionLabel;
        this.visible = visible;
    }

    @Override
    public abstract void action(AppContext context);

    @Override
    public boolean isComposite() {
        return false;
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    // Getters and setters
    @Override
    public String getOptionCustomKey() {
        return optionCustomKey;
    }

    @Override
    public String getOptionLabel() {
        return optionLabel;
    }

    public void setOptionCustomKey(String optionCustomKey) {
        this.optionCustomKey = optionCustomKey;
    }

    public void setOptionLabel(String optionLabel) {
        this.optionLabel = optionLabel;
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
