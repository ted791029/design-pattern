package com.ted.app;

public class Matrix {

    private Double[][] data;

    public Matrix(Double[][] data) {
        this.data = data;
    }

    //========================

    public Double[][] getData() {
        return data;
    }

    public void setData(Double[][] data) {
        this.data = data;
    }
}
