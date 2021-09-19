package me.stonkscloud.systems.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CalculatorApplication extends Application {

    public static void run(String... arguments) {
        launch(arguments);
    }

    private Stage currentStage = null;
    private Double positionY, positionX;

    @Override
    public void start(Stage stage) throws Exception {
        this.setCurrentStage(stage);

        this.getCurrentStage().setTitle("Calculator");
        this.getCurrentStage().setFullScreen(false);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/frame.fxml"));
        this.getCurrentStage().setScene(new Scene(fxmlLoader.load()));
        this.getCurrentStage().initStyle(StageStyle.UNDECORATED);

        this.getCurrentStage().getScene().setOnMousePressed(mouseEvent -> {
            this.setPositionX(mouseEvent.getSceneX());
            this.setPositionY(mouseEvent.getSceneY());
        });
        this.getCurrentStage().getScene().setOnMouseDragged(mouseEvent -> {
            this.getCurrentStage().setX(mouseEvent.getScreenX() - this.getPositionX());
            this.getCurrentStage().setY(mouseEvent.getScreenY() - this.getPositionY());
        });

        this.getCurrentStage().show();
    }

    public Stage getCurrentStage() {
        return currentStage;
    }

    public Double getPositionY() {
        return positionY;
    }

    public Double getPositionX() {
        return positionX;
    }

    private void setCurrentStage(Stage currentStage) {
        this.currentStage = currentStage;
    }

    public void setPositionY(Double positionY) {
        this.positionY = positionY;
    }

    public void setPositionX(Double positionX) {
        this.positionX = positionX;
    }

}
