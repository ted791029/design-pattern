package com.ted.app.core;

public interface MenuOption {
    void action(AppContext context);

    String getOptionCustomKey();

    String getOptionLabel();

    boolean isComposite();

    boolean isVisible();

    void setVisible(boolean visible);
}
