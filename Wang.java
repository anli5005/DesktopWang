import java.util.Random;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.Group;
//import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import javafx.scene.shape.Circle;

public class Wang extends Pane{

    private Group wang = new Group();
    private final double dx = 100, dy = 65;
    private double x, y;
    private String[] external = {"wang.png"};

    public Wang(double wid, double hig){
        ImageView imgV = new ImageView(new Image("wang.png"));
        imgV.setFitWidth(wid);
        imgV.setFitHeight(hig);

        Line body = new Line(), armL = new Line(), armR = new Line(), legL = new Line(), legR = new Line();
        Line[] lines = {body, armL, armR, legL, legR};
                
        for (int i = 0; i < lines.length; i++) {

            Line limb = lines[i];
            limb.setStrokeWidth(5);
            limb.setStroke(Color.BLACK);

            limb.startXProperty().bind(imgV.fitWidthProperty().divide(2));
            x = limb.getStartX();

            if (i == 0)
                limb.endXProperty().bind(body.startXProperty());
            else if (i == 1 || i == 3)
                limb.endXProperty().bind(body.startXProperty().subtract(10));
            else if (i == 2 || i == 4)
                limb.endXProperty().bind(body.startXProperty().add(10));
            
            if (i == 0)
                limb.startYProperty().bind(imgV.fitHeightProperty().add(hig/64));
            else if (i == 1 || i == 2) {
                limb.startYProperty().bind((body.startYProperty().add(body.endYProperty()).divide(2.125)));
                y = limb.getStartY();
            }
            else if (i == 3 || i == 4) 
                limb.startYProperty().bind(body.endYProperty());

            if (i == 0 || i == 1 || i == 2)
                limb.endYProperty().bind(body.startYProperty().add(25));
            else if (i == 3 || i == 4)
                limb.endYProperty().bind(body.endYProperty().add(25));

            wang.getChildren().add(limb);
        }

        wang.getChildren().add(imgV);
        getChildren().add(wang);

        
    }

    /*public double getCenterX() {
        return x;
    }

    public void setCenterX(double newX) {
        x = newX;
    }

    public double getCenterY() {
        return y;
    }

    public void setCenterY(double newY) {
        y = newY;
    }
    */

    public double animate(double x, double y, int z) {

        double change = 0;

        Line line = new Line(x,y,x,y);
        getChildren().add(line);
        line.setStroke(Color.CYAN);
        if (z == 1) { 
            line.setEndY(y-dy);
            change = y - dy;
        }
        else if (z == 2) {
            line.setEndY(y+dy);
            change = y + dy;
        }
        else if (z == 3) {
            line.setEndX(x-dx);
            change = x - dx;
        }
        else if (z == 4) {
            line.setEndX(x+dx);
            change = x + dx;
        }

        if (z == 1 || z == 2)
            wang.setLayoutY(wang.getLayoutY() + change);
        else   
            wang.setLayoutX(wang.getLayoutX() + change);

        PathTransition pt = new PathTransition();
        pt.setDuration(Duration.millis(500));
        pt.setPath(line);
        pt.setNode(wang);
        pt.setCycleCount(1);
        pt.play();
        return change;
    }

    public void animate(double x, double y) {
        Line line = new Line(x, y, 100, 100);
        getChildren().add(line);
        line.setStroke(Color.CYAN);
        PathTransition pt = new PathTransition();
        pt.setDuration(Duration.millis(2000));
        pt.setPath(line);
        pt.setNode(wang);
        pt.setCycleCount(1);
        pt.play();

    }

    public void walk(double speed) {
        Circle circle = new Circle(120, 120, 1);
        
        // circle.centerXProperty().bind(wang.getChildren().get(3).startX());
        // circle.centerYProperty().bind(wang.getChildren().get(3).startY());
        if (wang.getChildren().get(3) instanceof Line) {
            Line legR = (Line) wang.getChildren().get(3);
        }
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.TRANSPARENT);
        getChildren().add(circle);
        PathTransition walking = new PathTransition();
        walking.setDuration(Duration.millis(speed));
        walking.setPath(circle);
        walking.setNode(wang.getChildren().get(3));
        walking.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        walking.setCycleCount(Timeline.INDEFINITE);
        walking.setAutoReverse(true);
        walking.play();
    }

    public ImageView display() {
        Random rand = new Random();
        String file = external[rand.nextInt(external.length)];
        ImageView imgV = new ImageView(new Image(file));
        imgV.setFitHeight(500);
        imgV.setFitWidth(500);
        return imgV;
    }

    /*public void p() {
        Line line = new Line(0,0,1536,864);
        PathTransition pt = new PathTransition();
        pt.setDuration(Duration.seconds(4));
        pt.setPath(line);
        pt.setNode(wang);
        pt.setCycleCount(1);
        pt.play();
        wang.setLayoutX(line.getEndX() - 100);
        wang.setLayoutY(line.getEndY() - 100);
    }*/

}