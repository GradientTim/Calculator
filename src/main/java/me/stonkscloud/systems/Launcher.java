package me.stonkscloud.systems;

public class Launcher {

    public static Launcher launcher = new Launcher();

    private final Calculator calculator = new Calculator();

    public static void main(String[] args) {
        getLauncher().getCalculator().initialize(args);
    }

    public Calculator getCalculator() {
        return calculator;
    }

    public static Launcher getLauncher() {
        return launcher;
    }

}
