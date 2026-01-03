# Perceptron Learning Simulator — Android (Kotlin | Jetpack Compose)

A **modern Android app** demonstrating a **single-layer Perceptron** for binary classification. Built with **Kotlin** and **Jetpack Compose**, it emphasizes **state-driven UI**, **clean separation of logic**, and **interactive experimentation** with weights, bias, and learning rates.

---

## 🚀 Overview

This project is designed to showcase **Android engineering skills** relevant for internship and junior roles:

- **Declarative UI** with Jetpack Compose (Material 3)  
- **State-driven rendering** using `remember` and `mutableStateOf`  
- **Separation of UI and business logic** (Perceptron model in pure Kotlin)  
- **Interactive training and prediction** for AND/OR logic  
- **Live visualization** of weights and bias updates  

The app is **educational, interactive, and production-inspired**, demonstrating how ML logic can integrate cleanly into Android apps.

---

## 🔹 Problem Solved

Traditional Perceptron simulations are often:

- Console-based or static  
- Hard to experiment with parameters  
- Coupled between UI and logic  

This app fixes those issues by:

- Providing **real-time training UI**  
- Allowing **adjustable learning rate and iterations**  
- Displaying **live weight/bias updates**  
- Ensuring **safe numeric input handling**  

---

## ⚡ Key Features

- Supports **arbitrary input size**  
- **Binary classification** with -1/1 convention  
- **Train function** with iterative weight updates  
- **Predict function** for inference  
- Adjustable **learning rate** and **training iterations**  
- **Live display** of learned weights and bias  
- Material 3 UI with **modern top bar, cards, and interactive layouts**  
- **Safe input handling** to prevent crashes  

---

## 💡 Perceptron Logic

The perceptron follows the standard learning model:

```text
output = sign(sum(weight_i * input_i) + bias)
weight_i = weight_i + learning_rate * (target - output) * input_i
bias = bias + learning_rate * (target - output)
```

✅ Predictable, testable, and fully isolated from UI  
✅ Easy to extend to multi-layer perceptrons in the future  

---

## 🛠️ Android-Specific Highlights

- **Compose State Management**
  - `remember` + `mutableStateOf` for dynamic UI  
  - Stateless composables wherever possible  

- **Input Safety**
  - Sanitized numeric input before model updates  
  - Prevents crashes or inconsistent predictions  

- **UI & Architecture**
  - Clean separation between **UI** (Compose) and **business logic** (Perceptron model)  
  - Allows easy swapping of ML models or training logic  

- **Material 3 Theming**
  - Consistent colors, elevation, typography  
  - Card-based display for weights, bias, and predictions  

---

## 🧪 Test Cases / Examples

### AND Logic
**Input:** `[[0,0],[0,1],[1,0],[1,1]]`  
**Target:** `[-1,-1,-1,1]`  
**Outcome:** Correct predictions after training  

### OR Logic
**Input:** `[[0,0],[0,1],[1,0],[1,1]]`  
**Target:** `[-1,1,1,1]`  
**Outcome:** Correct predictions after training  

### Dynamic Learning Rate
- Adjust learning rate in real time  
- Observe **immediate changes** in weight/bias visualization  

---

## 📸 Screenshots

### Training & Weight Updates
<p align="center">
  <img
    src="https://github.com/Sourasamanta/ScreenShots/blob/main/PerceptronLearning/Perceptron_learning1.jpeg"
    width="240"
    alt="Perceptron Training Screen"
  />
</p>

### Prediction Output View
<p align="center">
  <img
    src="https://github.com/Sourasamanta/ScreenShots/blob/main/PerceptronLearning/Perceptron_learning2.jpeg"
    width="240"
    alt="Perceptron Prediction Screen"
  />
</p>

---

## 🖼️ Demo

### Interactive GIF Demonstration

<p align="center">
  <img
    src="https://github.com/Sourasamanta/ScreenShots/blob/main/PerceptronLearning/Perceptron_learningDemo.gif"
    width="240"
    alt="Perceptron Learning Demo"
  />
</p>

<em>
Shows training, live weight and bias updates, OR predictions, and adjustable learning rate in real time.
</em>

---

## 💻 Tech Stack

- **Language:** Kotlin  
- **UI:** Jetpack Compose (Material 3)  
- **Architecture:** Clean separation of UI and business logic  
- **Platform:** Android (emulator or device)

---

## ⚙️ Installation

### Requirements
- Android Studio (latest stable)  
- Android SDK  
- Emulator or physical device  

### Build & Run
```bash
./gradlew clean assembleDebug
./gradlew installDebug
```

---

## ⚠️ Limitations

- Only **single-layer perceptron** (cannot solve XOR)  
- No batch training — updates per sample  
- Focused on **educational simulation**  
- Numeric inputs must be preprocessed  

---

## 🛣️ Roadmap

- Multi-layer perceptron support  
- Decision boundary visualization  
- Additional learning parameters (momentum, decay)  
- Unit and UI tests  
- Accessibility improvements  

---

## 🤝 Contributing

Contributions are welcome.  
Fork, create a branch, and submit a PR with clear explanation of changes.  

---

## 📝 License

MIT License
