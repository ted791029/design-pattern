package com.ted.app;

public class ModelEntity implements Model {

    private String name;

    private MatrixManager matrixFileManager;

    private Double[][] matrix;

    public ModelEntity(String name) {
        matrixFileManager = MatrixManager.INSTANCE;
        this.name = name;
    }

    @Override
    public Double[] linearTransformation(Double[] vector) {

        if (vector == null) {
            throw new RuntimeException("vector 為NULL");
        }

        if (matrix == null) {
            matrix = matrixFileManager.getMatrix(name);
        }

        if (vector.length != matrix.length) {
            throw new RuntimeException("矩陣的列數必須與向量長度相同");
        }


        int cols = matrix[0].length;
        Double[] result = new Double[cols];


        for (int j = 0; j < cols; j++) {
            double sum = 0;
            for (int i = 0; i < vector.length; i++) {
                sum += vector[i] * matrix[i][j];
            }
            result[j] = sum;
        }

        return result;
    }

}
