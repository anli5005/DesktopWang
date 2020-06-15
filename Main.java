import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Screen;

public class Main extends Application {

    public static double clickCount = 0;

    @Override
    public void start(Stage ps) {

        // showInformationDialog();
        Pane pane = new Pane();

        /*Label label = new Label("path2fx");
        Popup popup = new Popup();
        popup.getContent().add(label);
        */

        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        final double WIDTH = screenBounds.getWidth();
        final double HEIGHT = screenBounds.getHeight();
        final double wid = WIDTH / 2.75 / 620 * 100;
        final double hig = HEIGHT / 781 * 100;

        Wang wang = new Wang(wid, hig);
        // StateCapitalQuiz SCQ = new StateCapitalQuiz();
        // bPane.setTop(SCQ);

        pane.getChildren().add(wang);

        wang.start(WIDTH, HEIGHT);

        wang.walk(700);
        //wang.activate();
        wang.wander();

        pane.setOnMouseDragged(e -> {

            wang.follow(e.getX(), e.getY());

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
                }
<<<<<<< HEAD
=======

>>>>>>> da0f38e6b02376970c1e2be145c12d48a3c1c5bd
            } */if (e.getText().equals("1")) {
                wang.sound();
            } if (e.getText().equals("2")) {
                //popup.show(ps);
            } if (e.getText().equals("3")) {
                wang.wander();
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
        // SCQ.requestFocus();
    }
}