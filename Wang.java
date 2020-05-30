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

public class Wang extends Pane{
    public Wang(double wid, double hig){
        Image img = new Image("wang.png");
        ImageView imgV = new ImageView(img);
        imgV.setFitWidth(wid);
        imgV.setFitHeight(hig);

        Line body = new Line(), armL = new Line(), armR = new Line(), legL = new Line(), legR = new Line();
        Line[] lines = {body, armL, armR, legL, legR};
        
        Group wang = new Group();
        
        for (int i = 0; i < lines.length; i++) {

            Line limb = lines[i];
            limb.setStrokeWidth(5);
            limb.setStroke(Color.BLACK);

            limb.startXProperty().bind(imgV.fitWidthProperty().divide(2));
        

            if (i == 0)
                limb.endXProperty().bind(body.startXProperty());
            else if (i == 1 || i == 3)
                limb.endXProperty().bind(body.startXProperty().subtract(20));
            else if (i == 2 || i == 4)
                limb.endXProperty().bind(body.startXProperty().add(20));
            
            if (i == 0)
                limb.startYProperty().bind(imgV.fitHeightProperty().add(hig/64));
            else if (i == 1 || i == 2)
                limb.startYProperty().bind((body.startYProperty().add(body.endYProperty()).divide(2)));
            else if (i == 3 || i == 4) 
                limb.startYProperty().bind(body.endYProperty());

            if (i == 0 || i == 1 || i == 2)
                limb.endYProperty().bind(body.startYProperty().add(50));
            else if (i == 3 || i == 4)
                limb.endYProperty().bind(body.endYProperty().add(50));

            wang.getChildren().add(limb);
        }

        wang.getChildren().add(imgV);
    }
}