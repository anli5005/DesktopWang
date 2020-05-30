package DesktopWang;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.geometry.Rectangle2D;
//import javafx.scene.shape.Rectangle;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
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
        final double wid = WIDTH / 2.75 / 620 * 100;
        final double hig = HEIGHT / 781 * 100;

        /*Rectangle screen = new Rectangle(-100, -100, WIDTH, HEIGHT);
        screen.setFill(Color.WHITE);
        pane.getChildren().add(screen);
        

        Rectangle body = new Rectangle(wid / 2 - 10, hig - 10, 20, 90);
        body.setFill(Color.BLACK);
        pane.getChildren().add(body);
        */

        Wang wang = new Wang(wid, hig);
        pane.getChildren().add(wang);
        
        /*screen.setOnMouseMoved(e ->{
            double centerY = body.getY() + (body.getHeight() / 2);
            double side1 = Math.abs(e.getY() - centerY);
            double centerX = body.getX() + (body.getWidth() / 2);
            double side2 = Math.abs(e.getX() - centerX);
            // if(side1 < side2){
            //     double temp;
            //     temp = side1;
            //     side1 = side2;
            //     side2 = temp;
            // }
            if(side1 != 0 && side2 != 0){
                double angle = 0;
                if(side1 > side2){
                    angle = Math.asin(side2 / side1);
                }
                else{
                    angle = Math.asin(side1 / side2);
                }
                angle *= 100;
                System.out.printf("side1: %f, side2: %f, angle: %f\n", side1, side2, angle);
                if(e.getX() > centerX && e.getY() < centerY){
                    body.setRotate(angle * -1);
                    imgV.setRotate(angle * -1);
                }
                else if(e.getX() < centerX && e.getY() < centerY){
                    body.setRotate(angle);
                    imgV.setRotate(angle);
                }
                else if(e.getX() > centerX && e.getY() > centerY){
                    body.setRotate(angle);
                    imgV.setRotate(angle);
                }
                else if(e.getX() < centerX && e.getY() > centerY){
                    body.setRotate(angle * -1);
                    imgV.setRotate(angle * -1);
                }
            }
        });
        */
        ps.initStyle(StageStyle.TRANSPARENT);
        ps.setAlwaysOnTop(true);
        Scene scene = new Scene(pane);
        scene.setFill(Color.TRANSPARENT);
        ps.setTitle("DesktopWang");
        ps.setScene(scene);
        ps.show();

        wang.requestFocus();
    }
}