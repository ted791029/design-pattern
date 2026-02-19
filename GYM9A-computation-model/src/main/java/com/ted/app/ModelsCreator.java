package com.ted.app;

public class ModelsCreator implements Models{

    @Override
    public Model createModel(String name) {
        return new ModelEntity(name);
    }

}
