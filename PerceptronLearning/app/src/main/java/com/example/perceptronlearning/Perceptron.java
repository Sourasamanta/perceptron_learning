package com.example.perceptronlearning;

import java.util.Arrays;

public class Perceptron {
    private double[] weights;
    private double bias;
    public double y;
    private double learningRate;

    public Perceptron(int inputSize, double learningRate) {
        this.learningRate = learningRate;
        this.weights = new double[inputSize];
        this.bias = Math.random() * 2 - 1;
        this.y = -1;

        for (int i = 0; i < weights.length; i++) {
            weights[i] = Math.random() * 2 - 1;
        }
    }

    public int predict(int[] x) {
        double out = bias;
        for (int i = 0; i < x.length; i++) {
            out += weights[i] * x[i];
        }
        y = out;
        return (out >= 0) ? 1 : -1;
    }

    public void train(int[] x, int target) {
        int prediction = predict(x);

        if (prediction != target) {
            for (int i = 0; i < x.length; i++) {
                weights[i] += learningRate * (target - prediction) * x[i];
            }
            bias += learningRate * (target - prediction);
        }
    }

    public double[] getWeights() {
        return weights;
    }

    public double getBias() {
        return bias;
    }
}