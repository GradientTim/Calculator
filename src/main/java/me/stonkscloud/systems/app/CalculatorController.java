package me.stonkscloud.systems.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CalculatorController {

    @FXML
    private Label result;

    @FXML
    public void handleCalculateNumber(ActionEvent actionEvent) {
        if (actionEvent.getSource() instanceof Button) {
            Button button = (Button) actionEvent.getSource();
            this.animateButtonClicked(button);

            switch (button.getText()) {
                case "C":
                    this.displayCalculated("clear");
                    break;
                case "⇚":
                    this.displayCalculated(result.getText().substring(0, result.getText().length() - 1));
                    break;
                case "x":
                    this.displayCalculated(result.getText() + "*");
                    break;
                case "=":
                    try {
                        String resultText = this.calculate(result.getText());
                        if (resultText == null || resultText.equals("0")) {
                            result.setText("0");
                        } else this.displayCalculated(resultText);
                    } catch (NumberFormatException numberFormatException) {
                        result.setText("0");
                    }
                    break;
                default:
                    this.displayCalculated(result.getText() + button.getText());
                    break;
            }
        }
    }

    @FXML
    public void onKeyDown(KeyEvent keyEvent) {
        System.out.println(keyEvent.getCode());
        switch (keyEvent.getCode()) {
            case NUMPAD0:
            case NUMPAD1:
            case NUMPAD2:
            case NUMPAD3:
            case NUMPAD4:
            case NUMPAD5:
            case NUMPAD6:
            case NUMPAD7:
            case NUMPAD8:
            case NUMPAD9:
            case DIGIT0:
            case DIGIT1:
            case DIGIT2:
            case DIGIT3:
            case DIGIT4:
            case DIGIT5:
            case DIGIT6:
            case DIGIT7:
            case DIGIT8:
            case DIGIT9:
                this.displayCalculated(result.getText() + keyEvent.getCode().getName().replace("NUMPAD", "").replace("DIGIT", ""));
                break;
            case BACK_SPACE:
                this.displayCalculated(result.getText().substring(0, result.getText().length() - 1));
                break;
            case PLUS:
                this.displayCalculated(result.getText() + "+");
                break;
            case MINUS:
                this.displayCalculated(result.getText() + "-");
                break;
            case X:
                this.displayCalculated(result.getText() + "*");
                break;
        }
    }

    public void displayCalculated(String format) {
        this.getResult().setText(format.equals("clear") || format.equals("") || format.equals(" ") ? "0" : (result.getText().startsWith("0") ? format.replace("0", "") : format));
    }

    public void animateButtonClicked(Control control) {
        String style = control.getStyle();
        control.setStyle("-fx-opacity: 0.75; -fx-border-color: none;");
        Executors.newSingleThreadScheduledExecutor().schedule(() -> control.setStyle(style), 100, TimeUnit.MILLISECONDS);
    }

    public String calculate(String value) {
        String[] numberStr = value.replaceAll("[+*/()-]+"," ").split(" ");
        String[] operatorStr = value.replaceAll("[0-9()]+","").split("");
        int total = Integer.parseInt(numberStr[0]);
        for (int i = 0; i < operatorStr.length; i++) {
            switch (operatorStr[i]) {
                case "+":
                    total += Integer.parseInt(numberStr[i + 1]);
                    break;
                case "-":
                    total -= Integer.parseInt(numberStr[i + 1]);
                    break;
                case "*":
                    total *= Integer.parseInt(numberStr[i + 1]);
                    break;
                case "/":
                    total /= Integer.parseInt(numberStr[i + 1]);
                    break;
                case "%":
                    total %= Integer.parseInt(numberStr[i + 1]);
                    break;
            }
            if (i +2 >= operatorStr.length) continue;
            numberStr[i + 1] = String.valueOf(total);
        }
        return String.valueOf(total);
    }

    public Label getResult() {
        return result;
    }

}
