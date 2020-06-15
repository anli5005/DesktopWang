import javafx.util.Duration;
import java.util.Random;

import javax.sound.sampled.SourceDataLine;

import java.awt.*;

import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.effect.Light.Point;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Arc;

import java.io.File;
import java.lang.Thread;

public class Wang extends Pane {

    private Group wang = new Group();
    private final double dx = 150, dy = 65;
    private double x, y;
    private double WIDTH,HEIGHT;


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

            if (i == 0)
                limb.endXProperty().bind(body.startXProperty());
            else if (i == 1 || i == 3)
                limb.endXProperty().bind(body.startXProperty().subtract(10));
            else if (i == 2 || i == 4)
                limb.endXProperty().bind(body.startXProperty().add(10));
            
            if (i == 0)
                limb.startYProperty().bind(imgV.fitHeightProperty().add(hig/64));
            else if (i == 1 || i == 2) 
                limb.startYProperty().bind((body.startYProperty().add(body.endYProperty()).divide(2.125)));
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

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void wander() {
        Random rand = new Random();
        int randX;
        int randY;
        int randChoice = rand.nextInt(10);
        //System.out.println(randChoice);

        if (randChoice < 2) {
            if (randChoice == 0) {
                randX = 0;
                randY = rand.nextInt((int) HEIGHT);

            } else {
                randX = (int) WIDTH;
                randY = rand.nextInt((int) HEIGHT);
            }
            new Images(WIDTH,HEIGHT);
        }

        else if (randChoice == 3) {
            randX = rand.nextInt((int) WIDTH);
            randY = 0;
            new Videos(WIDTH,HEIGHT);
        }

        else if (randChoice == 4) {
            randX = rand.nextInt((int) WIDTH);
            randY = (int) HEIGHT;
            sound();
        }

        else {
            randX = rand.nextInt((int) WIDTH);
            randY = rand.nextInt((int) HEIGHT);
        }
        Line line = new Line(getX(),getY(),randX,randY);
        PathTransition pt = new PathTransition();
        pt.setDuration(Duration.millis(3000));
        pt.setPath(line);
        pt.setNode(wang);
        pt.setCycleCount(1);
        pt.play();

        /*if (randChoice < 2) 
            new Images(WIDTH,HEIGHT);
        else if (randChoice == 3)
            new Videos();
        else if (randChoice == 4)
            sound();
*/
        setX(line.getEndX());
        setY(line.getEndY());
        
        wang.toFront();

        pt.setOnFinished(e -> wander());

    }
    
    /*public void activate() {
        Random rand = new Random();
        int selection = rand.nextInt(20);
        if(selection < 5){
            wander();
        } 
        else if (selection == 12){
            sound();
            activate();
        } else if(selection == 13){
            new Videos();
            activate();
        } else {
            System.out.println(selection);
            activate();
        }
    }
    */

    public int animate(int z) {

        Line line = new Line(x,y,x,y);
        line.setStroke(Color.CYAN);

        if (z == 1)
            line.setEndY(y-dy);
        else if (z == 2) 
            line.setEndY(y+dy);
        else if (z == 3) 
            line.setEndX(x-dx);
        else if (z == 4) 
            line.setEndX(x+dx);

        if (line.getEndX() > WIDTH) { 
            line.setEndX(WIDTH/2);
            return 2;
        } else if (line.getEndX() < 0) {
            line.setEndX(WIDTH/2);
            return 1;
        } else if (line.getEndY() > HEIGHT) {
            line.setEndY(HEIGHT/2);
        } else if (line.getEndY() < 0) {
            line.setEndY(HEIGHT/2);
            return 3;
        }
        
        PathTransition pt = new PathTransition();
        pt.setDuration(Duration.millis(500));
        pt.setPath(line);
        pt.setNode(wang);
        pt.setCycleCount(1);
        pt.play();

        x = line.getEndX();
        y = line.getEndY();

        
        return -1;
    }

    public void follow(double x, double y) {
        Line line = new Line(this.x, this.y, x, y);
        line.setStroke(Color.CYAN);
        PathTransition pt = new PathTransition();
        pt.setDuration(Duration.millis(2000));
        pt.setPath(line);
        pt.setNode(wang);
        pt.setCycleCount(1);
        pt.play();
        this.x = x;
        this.y = y;

    }

    public void captureMouse(double x, double y) throws AWTException {
        Robot robot = new Robot();
        robot.mouseMove(300, 300);
         java.awt.Point p = MouseInfo.getPointerInfo().getLocation();
         double mouseX = p.getX();
         double mouseY = p.getY();
         System.out.println ("(" + mouseX + "," + mouseY + ")");
         System.out.println ("(" + this.x + "," + this.y + ")\n");

         /*if (((int) mouseX == (int) x) && ((int) mouseY == (int) y)) {
            System.out.println("CAPTURED");
             for (int i=0; i<1000; i++) {
                 
                 mouseX = this.x;
                 mouseY = this.y;
             }
         }*/

          
    }

    /*public void drop(double HEIGHT){
        //System.out.println("You're finally awake");
        Line legR = (Line) wang.getChildren().get(4);
        //System.out.println("Leg:" + y);
        if (y < HEIGHT){
            //System.out.println("gello");
            animate(2);
        }
    }*/

    public void walk(double speed) {
        Arc arc = new Arc(0, 0, 10, 16, 250, 60);
        Arc arc2 = new Arc(0, 0, 10, 16, 300, -60);
        Line legL = (Line) wang.getChildren().get(3);
        Line legR = (Line) wang.getChildren().get(4);

        arc.centerXProperty().bind(legL.startXProperty());
        arc.centerYProperty().bind(legL.startYProperty());
        arc2.centerXProperty().bind(legR.startXProperty());
        arc2.centerYProperty().bind(legR.startYProperty());
        
        arc.setFill(Color.TRANSPARENT);
        arc.setStroke(Color.TRANSPARENT);
        getChildren().add(arc);
        arc2.setFill(Color.TRANSPARENT);
        arc2.setStroke(Color.TRANSPARENT);
        getChildren().add(arc2);

        PathTransition walking1 = new PathTransition();
        walking1.setDuration(Duration.millis(speed));
        walking1.setPath(arc);
        walking1.setNode(legL);
        walking1.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        walking1.setCycleCount(Timeline.INDEFINITE);
        walking1.setAutoReverse(true);
        walking1.play();

        PathTransition walking2 = new PathTransition();
        walking2.setDuration(Duration.millis(speed));
        walking2.setPath(arc2);
        walking2.setNode(legR);
        walking2.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        walking2.setCycleCount(Timeline.INDEFINITE);
        walking2.setAutoReverse(true);
        walking2.play();
    }



    public void sound() {
        Random rand = new Random();
        int upperBound = new File("asmr").listFiles().length;

        AudioClip sound = new AudioClip(this.getClass().getResource("asmr/" + (rand.nextInt(upperBound) + 1) + ".m4a").toString());
        sound.play();
        //activate();
    }

    public void start(double width, double height) {
        
        Line line = new Line(width/2, height + 50, width/2, height);
        PathTransition pt = new PathTransition();
        pt.setDuration(Duration.millis(2000));
        pt.setPath(line);
        pt.setNode(wang);
        pt.setAutoReverse(true);
        pt.setCycleCount(1);
        pt.play();
        
        Line line2 = new Line(width/2, height, width/2, height / 2);
        PathTransition pt2 = new PathTransition();
        pt2.setDuration(Duration.millis(1000));
        pt2.setPath(line2);
        pt2.setNode(wang);
        pt2.setCycleCount(1);

        SequentialTransition seqT = new SequentialTransition(pt,pt2);
        seqT.play();

        x = width/2;
        y = height/2;

        WIDTH = width;
        HEIGHT = height;
    }

    public void shadowClone(double x, double y) {
        Wang wang1 = new Wang(x,y);
        Wang wang2 = new Wang(x,y);
        
        wang.getChildren().add(wang1);
        wang.getChildren().add(wang2);
        
        
    }
    
}