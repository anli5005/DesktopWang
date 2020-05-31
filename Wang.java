import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.Group;
//import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Wang extends Pane{

    private Group wang = new Group();
    private final double dx = 100, dy = 65;
    private double x, y;

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
    
    public void animate(double x, double newX, double y, double newY) {
        Line line = new Line(x, newX, y, newY);

        PathTransition pt = new PathTransition();
        pt.setDuration(Duration.millis(500));
        pt.setPath(line);
        pt.setNode(wang);
        pt.setCycleCount(1);
        pt.play();

    }

    public double getCenterX() {
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


    public void animate(int z) {
        
        double change = 0;
        
        Line line = new Line(getCenterX(), getCenterY(), getCenterX(), getCenterY());
        if (z == 1) { 
            line.setEndY(getCenterY() - dy);
            change = line.getEndY();
            setCenterY(getCenterY() + change);
        }
        else if (z == 2) {
            line.setEndY(getCenterY() + dy);
            change = line.getEndY();
            setCenterY(getCenterY() + change);
        }
        else if (z == 3) {
            line.setEndX(getCenterX() - dx);
            change = line.getEndX();
            setCenterX(getCenterX() + change);
        }
        else if (z == 4) {
            line.setEndX(getCenterX() + dx);
            change = line.getEndX();
            setCenterX(getCenterX() + change);
        }

        //System.out.println(line.getStartX() + " " + line.getStartY() + " " + line.getEndX() + " " + line.getEndY());
        System.out.println(line.getStartX() + ", " + line.getStartY() + ", " + line.getEndX() + ", " + line.getEndY());
        PathTransition pt = new PathTransition();
        pt.setDuration(Duration.millis(500));
        pt.setPath(line);
        pt.setNode(wang.getChildren().get(0));
        pt.setCycleCount(1);
        pt.play();
        
    }

}