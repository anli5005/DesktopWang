import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;  
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Screen;



public class Main extends Application{
    public static double clickCount = 0;

    @Override
    public void start(Stage ps) {
        BorderPane bPane = new BorderPane();

        Pane pane = new Pane();

        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        final double WIDTH = screenBounds.getWidth();
        final double HEIGHT = screenBounds.getHeight();
        final double wid = WIDTH / 2.75 / 620 * 100;
        final double hig = HEIGHT / 781 * 100;

        Wang wang = new Wang(wid, hig);
        pane.getChildren().add(wang);
        
        Bounds bounds = wang.localToScene(wang.getBoundsInLocal());        

        wang.start(WIDTH,HEIGHT);

        wang.walk(700);
        //double centerX = (boundsInScreen.getMaxX() + boundsInScreen.getMinX()) / 2;
        //double centerY = (boundsInScreen.getMaxY() + boundsInScreen.getMinY()) / 2;
        //double centerX = boundsInScreen.getWidth();
        //double centerY = boundsInScreen.getHeight();

        /*Rectangle screen = new Rectangle(-100, -100, WIDTH, HEIGHT);
        screen.setFill(Color.WHITE);
        pane.getChildren().add(screen);
        

        Rectangle body = new Rectangle(wid / 2 - 10, hig - 10, 20, 90);
        body.setFill(Color.BLACK);
        pane.getChildren().add(body);
        */

        //screen.setOnMouseClicked(e -> {
          //  wang.animate(boundsInScreen.getWidth(), e.getX(), boundsInScreen.getHeight(), e.getY());
        //});
        

        pane.setOnMouseDragged(e -> {
            // int delay = 1000;
            // long start = System.currentTimeMillis();
            // while (start >= System.currentTimeMillis() - delay); // sleeps for 1 second
            
            wang.follow(e.getX(), e.getY());
        });

        pane.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) 
                wang.animate(1);
        
            else if (e.getCode() == KeyCode.DOWN) 
                wang.animate(2);
            
            else if (e.getCode() == KeyCode.LEFT) 
                wang.animate(3);
            
            else if (e.getCode() == KeyCode.RIGHT) 
                wang.animate(4);
            
            //else if (e.getText().equals("1"))
              //  pane.getChildren().add(wang.display());
            // System.out.println(wang.getLayoutX() + " " + wang.getLayoutY());
        });

        // pane.setOnMouseClicked(e -> {
        //     clickCount++;
        //     System.out.println(clickCount);
        //     Line legR = (Line) wang.getChildren().get(2);
        //     if(clickCount %4 == 2 || clickCount %4 != 3){
        //         System.out.println("Ok boomer");
        //         while (legR.getEndY() > HEIGHT){
        //             wang.setLayoutY(wang.getLayoutY() - 50);
        //         }
        //     }
        // });
        
        /*pane.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) 
                wang.setLayoutY(wang.getLayoutY()-50);
            if (e.getCode() == KeyCode.DOWN)
                wang.setLayoutY(wang.getLayoutY()+50);
            if (e.getCode() == KeyCode.LEFT)
                wang.setLayoutX(wang.getLayoutX()-100);
            if (e.getCode() == KeyCode.RIGHT)
                wang.setLayoutX(wang.getLayoutX()+100);            
        });*/
        

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
        Scene scene = new Scene(pane, WIDTH, HEIGHT);
        scene.setFill(Color.TRANSPARENT);
        ps.setTitle("DesktopWang");
        ps.setScene(scene);
        ps.show();

        wang.requestFocus();
    }
}