import java.util.Random;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;


class Videos {

    Videos() {
        Random rand = new Random();

        Stage subStage = new Stage();
        subStage.setTitle("New Stage");

        Media media = new Media((this.getClass().getResource("vids/" + (rand.nextInt(4) + 1) + ".mp4").toExternalForm()));
        MediaPlayer mp = new MediaPlayer(media);
        mp.setAutoPlay(true);
        MediaView mv = new MediaView(mp);

        Group root = new Group();  
        root.getChildren().add(mv);

        Scene scene = new Scene(root,600,360);  
        subStage.setScene(scene);  
        subStage.setTitle("Im sorry if this is a Rick Roll");  
        subStage.show();  
    }  
    

}