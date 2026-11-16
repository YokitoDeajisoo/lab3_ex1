package org.example;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

public class Main extends Application {

    private final CalculatorLogic logic = new CalculatorLogic();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // 1. Обов'язкова довідка при запуску програми
        showHelpDialog();

        // 2. Створення панелі з двома вкладками
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Tab tab1 = new Tab("Завдання 1 (Середні значення)");
        tab1.setContent(createTask1Layout());

        Tab tab2 = new Tab("Завдання 2 (Поверх за квартирою)");
        tab2.setContent(createTask2Layout());

        tabPane.getTabs().addAll(tab1, tab2);

        Scene scene = new Scene(tabPane, 480, 320);
        primaryStage.setTitle("Практичне заняття №3 — Варіант 1");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Вікно довідки про призначення програми та порядок роботи.
     */
    private void showHelpDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Довідка про програму");
        alert.setHeaderText("Призначення та порядок використання застосунку");
        alert.setContentText(
                "Програма призначена для розв'язання двох задач (Варіант 1):\n" +
                        "1. Вкладка «Завдання 1»: обчислення середнього арифметичного та геометричного двох чисел.\n" +
                        "2. Вкладка «Завдання 2»: визначення поверху в 9-поверховому будинку за номером квартири.\n\n" +
                        "Порядок роботи:\n" +
                        "Оберіть потрібну вкладку, натисніть кнопку введення даних, введіть значення у діалогові вікна та перегляньте результат."
        );
        alert.showAndWait();
    }

    /**
     * Інтерфейс для Вкладки 1 (Завдання 1).
     */
    private VBox createTask1Layout() {
        Label titleLabel = new Label("Обчислення середнього арифметичного та геометричного");
        titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        Button inputBtn = new Button("Ввести числа a та b");
        Label resultLabel = new Label("Результат: очікується введення даних...");
        resultLabel.setStyle("-fx-font-size: 13px; -fx-text-fill: #1a5276;");

        inputBtn.setOnAction(e -> {
            Double a = askForDouble("Введення числа a", "Введіть перше число (a):", "3");
            if (a == null) return;

            Double b = askForDouble("Введення числа b", "Введіть друге число (b):", "27");
            if (b == null) return;

            try {
                double sa = logic.calcArithmeticMean(a, b);
                double sg = logic.calcGeometricMean(a, b);

                String output = String.format("Вхідні дані: a = %.2f, b = %.2f\nСереднє арифметичне (sa) = %.2f\nСереднє геометричне (sg) = %.2f", a, b, sa, sg);

                // Виведення у Label на формі
                resultLabel.setText(output);
                // Виведення у стандартне інформаційне вікно
                showInfoAlert("Результат Завдання 1", output);

            } catch (IllegalArgumentException ex) {
                showErrorAlert("Помилка обчислення", ex.getMessage());
            }
        });

        VBox layout = new VBox(15, titleLabel, inputBtn, resultLabel);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        return layout;
    }

    /**
     * Інтерфейс для Вкладки 2 (Завдання 2).
     */
    private VBox createTask2Layout() {
        Label titleLabel = new Label("Визначення поверху (9 поверхів, по 5 квартир на поверсі)");
        titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        Button inputBtn = new Button("Ввести номер квартири");
        Label resultLabel = new Label("Результат: очікується введення номера квартири...");
        resultLabel.setStyle("-fx-font-size: 13px; -fx-text-fill: #1a5276;");

        inputBtn.setOnAction(e -> {
            Integer apt = askForInteger("Номер квартири", "Введіть номер квартири (від 1 до 45):", "12");
            if (apt == null) return;

            try {
                int floor = logic.getFloorByApartment(apt);
                String output = String.format("Квартира №%d знаходиться на %d-му поверсі.", apt, floor);

                // Виведення у Label на формі
                resultLabel.setText(output);
                // Виведення у стандартне інформаційне вікно
                showInfoAlert("Результат Завдання 2", output);

            } catch (IllegalArgumentException ex) {
                showErrorAlert("Некоректні дані", ex.getMessage());
            }
        });

        VBox layout = new VBox(15, titleLabel, inputBtn, resultLabel);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        return layout;
    }

    // --- Допоміжні методи для діалогових вікон ---

    private Double askForDouble(String title, String content, String defaultValue) {
        TextInputDialog dialog = new TextInputDialog(defaultValue);
        dialog.setTitle(title);
        dialog.setHeaderText(null);
        dialog.setContentText(content);

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            try {
                return Double.parseDouble(result.get().replace(",", "."));
            } catch (NumberFormatException e) {
                showErrorAlert("Помилка вводу", "Будь ласка, введіть коректне число!");
            }
        }
        return null;
    }

    private Integer askForInteger(String title, String content, String defaultValue) {
        TextInputDialog dialog = new TextInputDialog(defaultValue);
        dialog.setTitle(title);
        dialog.setHeaderText(null);
        dialog.setContentText(content);

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            try {
                return Integer.parseInt(result.get().trim());
            } catch (NumberFormatException e) {
                showErrorAlert("Помилка вводу", "Будь ласка, введіть ціле число!");
            }
        }
        return null;
    }

    private void showInfoAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText("Обчислення виконано успішно");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText("Виникла помилка");
        alert.setContentText(message);
        alert.showAndWait();
    }
}