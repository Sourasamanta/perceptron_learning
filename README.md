# perceptron_learning
It is a perceptron learning simulation made in Kotlin

---

# 🔍 Perceptron Learning in Java and kotlin

A simple **Perceptron implementation** in Java and kotlin for binary classification problems. This project demonstrates the fundamental building blocks of a single-layer neural network, including weight updates, bias adjustment, and iterative training.

---

## 🚀 Features

✅ Supports arbitrary input size
✅ Binary classification using -1/1 convention
✅ Adjustable learning rate
✅ Predict function for inference
✅ Train function with iterative weight updates until convergence
✅ Access to learned weights and bias after training

---

## 📂 Project Structure

```
├── Perceptron.java       # Core perceptron implementation
├── Main.java             # Sample usage / test cases
├── README.md             # Documentation (this file)
```

---

## 🛠️ How to Compile and Run

### 🧱 Compile

```bash
javac Perceptron.java Main.java
```

### ▶️ Run

```bash
java Main
```

Ensure your `Main.java` includes example inputs for training and prediction.

---

## 🧪 Test Cases

### ✅ Test Case 1: AND Logic

```java
int[][] inputs = {{0,0}, {0,1}, {1,0}, {1,1}};
int[] targets = {-1, -1, -1, 1};
Perceptron p = new Perceptron(2, 0.1);
for(int i=0;i<inputs.length;i++) p.train(inputs[i], targets[i]);
```

**Output:** Correct predictions for AND logic after training.

### ✅ Test Case 2: OR Logic

```java
int[][] inputs = {{0,0}, {0,1}, {1,0}, {1,1}};
int[] targets = {-1, 1, 1, 1};
Perceptron p = new Perceptron(2, 0.1);
for(int i=0;i<inputs.length;i++) p.train(inputs[i], targets[i]);
```

**Output:** Correct predictions for OR logic after training.

### ⚠️ Limitations

* Only single-layer perceptron (cannot solve non-linearly separable problems like XOR)
* Input features must be numeric and preprocessed appropriately
* No batch training — updates are made per sample
* No advanced optimization or momentum

---

## 🧾 Sample Output

```
Training perceptron for AND logic...
Predicted output for [0,1]: -1
Predicted output for [1,1]: 1
Final weights: [0.1, 0.1]
Final bias: -0.05
```

---

## 👨‍💻 Author

**Sourajit Samanta**
Machine Learning & Neural Networks Enthusiast | BTech CSE

---
