package Lab10_3;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import java.util.Optional;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class StateCapitalQuiz extends Application {

    public static void main(String[] args) throws FileNotFoundException{
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException{
        showInformationDialog();
 
    }
    public static void showInformationDialog() throws FileNotFoundException{
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("State Capital Quiz");
        alert.setHeaderText("Welcome to the State Capital Quiz Challenge");
        alert.showAndWait();
        showChoiceDialog();
    }
    public static void showChoiceDialog() throws FileNotFoundException{
        ArrayList<String> choices = new ArrayList<>();
        choices.add("states_small_test.txt");
        choices.add("states_all.txt");
        choices.add("states_central.txt");
        choices.add("states_east.txt");
        choices.add("states_west.txt");
        choices.add("states_south.txt");
 
 
        ChoiceDialog<String> dialog = new ChoiceDialog<>("states_all.txt", choices);
        dialog.setTitle("State Capital Quiz");
        dialog.setHeaderText("Choose Your Quiz");
        dialog.setContentText("Pick a text file:");
 
        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            System.out.print("Push 100 epic");
            StateCapitalList scList = new StateCapitalList(result.get());
            System.out.print("Push 100 epic");
            showTextInputDialog(scList);
            System.out.print("Push 100 epic");
        } else{
            showErrorDialog();
        }
    }
    public static void showErrorDialog() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("State Capital Quiz");
        alert.setHeaderText("You did not pick a file. Exiting."); 
        alert.showAndWait();
    }
    public static void showTextInputDialog(StateCapitalList scList){
        int correct = 0;
        int guesses = 0;
        System.out.print("Push 100 epic");
        boolean gameOver = false;
        while(scList.statesRemaining() > 0 && gameOver == false){
            TextInputDialog dialog = new TextInputDialog("");
            dialog.setTitle("State Capital Quiz");
            dialog.setHeaderText(null);
            StateCapital randomState = scList.getRandomState();
            dialog.setContentText(String.format("What is the capital of %s", randomState.getState()));
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()){
                guesses++;
                if(result.get().equals(randomState.getCapital())){
                    correct++;
                    displayCorrect(randomState);
                    scList.remove(randomState);
                }
                else{
                    displayWrong(result.get(), randomState);
                }
            }
            else{
                gameOver = true;
                showWarningDialog(scList, correct, guesses);
            }
        }
        if(gameOver == false){
            displayVictory(correct, guesses);
        }
        
    }
    public static void displayVictory(int correct, int guesses){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("State Capital Quiz");
        alert.setHeaderText("Congratulations");
        alert.setContentText(String.format("You correctly named %d capitals in %d guesses.", correct, guesses));
        alert.showAndWait();
    }

    public static void showWarningDialog(StateCapitalList scList, int correct, int guesses){
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("State Capital Quiz");
        alert.setHeaderText(String.format("You exited early with %d state(s) remaining", scList.statesRemaining()));
        alert.setContentText(String.format("You correctly named %d capitals in %d guesses", correct, guesses));
        alert.showAndWait();
    }

    public static void displayWrong(String guess, StateCapital correctSC){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("State Capital Quiz");
        alert.setHeaderText("Wrong!");
        alert.setContentText(String.format("The capital of %s is not '%s', but actually %s", correctSC.getState(), guess, correctSC.getCapital()));
        alert.showAndWait();
    }
    public static void displayCorrect(StateCapital correct){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("State Capital Quix");
        alert.setHeaderText("Correct!");
        alert.setContentText(String.format("The capital of %s is %s", correct.getState(), correct.getCapital()));
        alert.showAndWait();
    }
}