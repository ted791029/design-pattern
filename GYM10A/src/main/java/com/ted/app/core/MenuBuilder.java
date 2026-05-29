package com.ted.app.core;

import java.util.ArrayList;
import java.util.List;

public class MenuBuilder {

    private List<MenuOption> options = new ArrayList<>();

    public MenuBuilder() {}

    public MenuBuilder add(MenuOption option) {
        options.add(option);
        return this;
    }

    public Menu build() {
        return new Menu(options.toArray(new MenuOption[0]));
    }
}
