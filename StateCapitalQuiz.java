import java.io.*;

import java.util.Random;
import java.util.Optional;
import java.util.Random;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.TextInputDialog;


public class StateCapitalQuiz {

    private String STATE;
    private String CAPITAL;    

    StateCapitalQuiz() {

        Stage subStage = new Stage();
        subStage.setTitle("State Capital Quiz");
        
        Random rand = new Random();
        //https://www.codespeedy.com/read-a-specific-line-from-a-text-file-in-java/
        String text = "";
        int index = rand.nextInt(50);
        try {
            FileReader readfile = new FileReader("states_all.txt");
            BufferedReader readbuffer = new BufferedReader(readfile);  
            for (int line = 1; line < 50; line++) {
                if (line == index) {
                    text = readbuffer.readLine();
                    readbuffer.close(); 
                } else { 
                    readbuffer.readLine();
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();    
        } 

        String[] parts = text.split(":");
        STATE = parts[0];
        CAPITAL = parts[1];
        
        showInputDialog();
    }
    
    public void showInputDialog() {
        boolean correct = false;
        
        while (! correct) {
            TextInputDialog dialog = new TextInputDialog("path2fx");
            dialog.setTitle("State Capital Quiz");
            dialog.setContentText("What is the capital of " + STATE);

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                if (result.get().equals(CAPITAL)) {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("State Capital Quiz");
                    alert.setHeaderText("Correct");
                    alert.showAndWait();
                    correct = true;
                } else {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("State Capital Quiz");
                    alert.setHeaderText("Incorrect!");
                    alert.setContentText("The Capital of " + STATE + " is " + CAPITAL + " you uncultured swine");
                    alert.showAndWait();
                    
                }
                                
            }

        }

    }

}