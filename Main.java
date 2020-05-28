import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application{

    @Override
    public void start(Stage ps) {
        Pane pane = new Pane();
        Image img = new Image("wang.png");
        ImageView imgV = new ImageView(img);
        imgV.setFitWidth(100);
        imgV.setFitHeight(100);  
        imgV.setSmooth(true);
        pane.getChildren().add(imgV);

        ps.initStyle(StageStyle.TRANSPARENT);
        ps.setAlwaysOnTop(true);
        Scene scene = new Scene(pane);
        ps.setTitle("DesktopWang");
        ps.setScene(scene);
        ps.show();
    }
    
}