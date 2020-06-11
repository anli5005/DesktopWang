import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
//import javafx.scene.media.Media;
// import javafx.scene.media.MediaPlayer;
// import javafx.scene.control.Alert;
// import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;  
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Screen;



public class Main extends Application{
    
    public static double clickCount = 0;
    public static int moves = 0;
    

    @Override
    public void start(Stage ps) {

        //showInformationDialog();
        

        BorderPane bPane = new BorderPane();
        
        Pane pane = new Pane();

        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        final double WIDTH = screenBounds.getWidth();
        final double HEIGHT = screenBounds.getHeight();
        final double wid = WIDTH / 2.75 / 620 * 100;
        final double hig = HEIGHT / 781 * 100;

        Wang wang = new Wang(wid, hig);
        //StateCapitalQuiz SCQ = new StateCapitalQuiz();
        // bPane.setTop(SCQ);
       
        pane.getChildren().add(wang);
        
        wang.start(0,0);

        pane.setOnMouseDragged(e -> {
            
            wang.follow(e.getX(), e.getY());

        });


        pane.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) 
                wang.animate(1);

            else if (e.getCode() == KeyCode.DOWN) 
                wang.animate(2);
            
            else if (e.getCode() == KeyCode.LEFT) {
                if (wang.animate(3) == 1) {
                    bPane.setLeft(wang.display());   
                    wang.toFront();
                    /*int delay = 5000;
                    long start = System.currentTimeMillis();
                    while (start >= System.currentTimeMillis() - delay);
                    bPane.setLeft(null);*/ 
                }
            }

            else if (e.getCode() == KeyCode.RIGHT) {
                if (wang.animate(4) == 2) {
                    bPane.setRight(wang.display());
                    wang.toFront();
                    /*int delay = 5000;
                    long start = System.currentTimeMillis();
                    while (start >= System.currentTimeMillis() - delay); 
                    bPane.setRight(null);*/
                }

            }

            moves++;
            if (moves == 5) {
                //wang.fade((ImageView) bPane.getLeft(), (ImageView) bPane.getRight());
                
                if (bPane.getLeft() != null)
                    bPane.setLeft(null);
                
                if (bPane.getRight() != null)
                    bPane.setRight(null);
                
                moves = 0;
            }     

            if (e.getText().equals("1")) {
                wang.sound();
            }

        });

        pane.setOnMouseClicked(e -> {
            clickCount++;
            System.out.println(clickCount);
            
            if(clickCount %4 == 2 || clickCount %4 == 3){
                System.out.println("Ok boomer");
                wang.drop(HEIGHT);
            }
        });
        
        bPane.setCenter(pane);
        bPane.getCenter().toFront();

        ps.initStyle(StageStyle.TRANSPARENT);
        ps.setAlwaysOnTop(true);
        Scene scene = new Scene(bPane, WIDTH, HEIGHT);
        scene.setFill(Color.TRANSPARENT);
        ps.setTitle("DesktopWang");
        ps.setScene(scene);
        ps.show();

        wang.requestFocus();
        //SCQ.requestFocus();
    }

    /*public static void showInformationDialog() {
        Alert alert = new Alert(AlertType.INFORMATION); 
        alert.setTitle("Info");
        alert.setHeaderText("Pee");
        alert.setContentText("Message");
        alert.show();
    }*/


}