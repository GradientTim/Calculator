package me.stonkscloud.systems;

import me.stonkscloud.systems.app.CalculatorApplication;

public class Calculator {

    private final CalculatorApplication application = new CalculatorApplication();

    public void initialize(String... arguments) {
        CalculatorApplication.run(arguments);
    }

    public CalculatorApplication getApplication() {
        return application;
    }

}
