import java.awt.AWTException;
import java.security.Key;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Modality;
//import sun.awt.WindowClosingListener;
import javafx.stage.Screen;

public class Main extends Application {

    public static double clickCount = 0;

    @Override
    public void start(Stage ps) {

        Pane pane = new Pane();
        
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        final double WIDTH = screenBounds.getWidth();
        final double HEIGHT = screenBounds.getHeight();
        final double wid = WIDTH / 2.75 / 620 * 100;
        final double hig = HEIGHT / 781 * 100;

        Wang wang = new Wang(wid, hig);
        
        wang.start(WIDTH,HEIGHT, ps);
        pane.getChildren().add(wang);

        //wang.wander();

        wang.walk(700);
        //wang.wander();

        
        pane.setOnMouseDragged(e -> {

            wang.follow(e.getX(), e.getY());

        });

        pane.setOnMouseMoved(e -> {
            try {
                wang.captureMouse(e.getX(), e.getY());
            } catch (AWTException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        pane.setOnKeyPressed(e -> {
            /*if (e.getCode() == KeyCode.UP) {
                if (wang.animate(1) == 3)
                    new Videos();
            }
            else if (e.getCode() == KeyCode.DOWN)
                wang.animate(2);
            else if (e.getCode() == KeyCode.LEFT) {
                if (wang.animate(3) == 1) {
                    new Images(WIDTH,HEIGHT);
                }
            } else if (e.getCode() == KeyCode.RIGHT) {
                if (wang.animate(4) == 2) {
                    new Images(WIDTH,HEIGHT);
3            } */
            if ((e.getCode() == KeyCode.E) || (e.getCode() == KeyCode.ESCAPE)) {
                System.exit(0);
            } if (e.getText().equals("1")) {
                wang.sound();
            }

        });

        /*pane.setOnMouseClicked(e -> {
            clickCount++;
            //System.out.println(clickCount);
            if (clickCount % 4 == 2 || clickCount % 4 == 3) {
                // System.out.println("Ok boomer");
                wang.drop(HEIGHT);
            }
        }); */

        
        ps.initStyle(StageStyle.TRANSPARENT);
        ps.setAlwaysOnTop(true);
        Scene scene = new Scene(pane, WIDTH, HEIGHT);
        scene.setFill(Color.TRANSPARENT);
        ps.setTitle("DesktopWang");
        ps.setScene(scene);
        ps.show();
        wang.requestFocus();
    }
}