package com.ted.app.core.factories;

import com.ted.app.core.AppContext;
import com.ted.app.core.DefaultMenuOptionFactory;
import com.ted.app.core.MenuOption;
import com.ted.app.core.commands.PreviousPage;

public class PreviousPageDefaultMenuOptionFactory implements DefaultMenuOptionFactory {
    @Override
    public MenuOption create(AppContext context) {
        return new PreviousPage();
    }
}
