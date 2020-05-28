import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.shape.Rectangle;
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
        double wid = WIDTH / 2.75 / 620 * 100;
        double hig = HEIGHT / 781 * 100;

        Rectangle body = new Rectangle(wid / 2 - 10, hig - 10, 20, 90);
        body.setFill(Color.BLACK);
        pane.getChildren().add(body);

        Image img = new Image("wang.png");
        ImageView imgV = new ImageView(img);
        imgV.setFitWidth(WIDTH / 2.75 / 620 * 100);
        imgV.setFitHeight(HEIGHT / 781 * 100);
        pane.getChildren().add(imgV);

        pane.setOnMouseMoved(e ->{
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
            if(side1 != 0){
                double angle = Math.asin(side2 / side1);
                angle *= -100;
                System.out.printf("side1: %f, side2: %f, angle: %f\n", side1, side2, angle);
                if(e.getX() > centerX && e.getY() < centerY){
                    body.setRotate(angle);
                    imgV.setRotate(angle);
                }
                else if(e.getX() < centerX && e.getY() < centerY){
                    body.setRotate(angle * -1);
                    imgV.setRotate(angle * -1);
                }
            }
        });

        ps.initStyle(StageStyle.TRANSPARENT);
        ps.setAlwaysOnTop(true);
        Scene scene = new Scene(pane, 500, 500);
        scene.setFill(Color.TRANSPARENT);
        ps.setTitle("DesktopWang");
        ps.setScene(scene);
        ps.show();
    }
}