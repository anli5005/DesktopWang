import javafx.application.Application;

import javafx.geometry.Rectangle2D;

import javafx.scene.Scene;

import javafx.scene.layout.Pane;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.paint.Color;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Screen;

public class Main extends Application{

    @Override
    public void start(Stage ps) {
        Pane pane = new Pane();

        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        final double WIDTH = screenBounds.getWidth();
        final double HEIGHT = screenBounds.getHeight();

        Image img = new Image("wang.png");
        ImageView imgV = new ImageView(img);
        imgV.setFitWidth(WIDTH / 2.75 / 620 * 100);
        imgV.setFitHeight(HEIGHT / 781 * 100);
        pane.getChildren().add(imgV);

        ps.initStyle(StageStyle.TRANSPARENT);
        ps.setAlwaysOnTop(true);
        Scene scene = new Scene(pane);
        scene.setFill(Color.TRANSPARENT);
        ps.setTitle("DesktopWang");
        ps.setScene(scene);
        ps.show();
    }
}