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
        Arrays.fill(weights, 0); // start from 0 or random
    }

    public int predict(int[] x) {
        double out = bias;
        for (int i = 0; i < x.length; i++) {
            out += weights[i] * x[i];
        }
        y = out;
        return (out >= 0) ? 1 : -1; // -1/1 convention
    }

    public void train(int[] x, int target) {
        int out = predict(x);
        while (out != target) {
            for (int i = 0; i < x.length; i++) {
                weights[i] += learningRate * (target - out) * x[i];
            }
            bias += learningRate * (target - out);
            out = predict(x);
        }
    }

    public double[] getWeights() {
        return weights;
    }

    public double getBias() {
        return bias;
    }
}
