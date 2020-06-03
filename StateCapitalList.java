package Lab10_3;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;


public class StateCapitalList {

    private ArrayList<StateCapital> listy = new ArrayList<StateCapital>();

    public StateCapitalList(String fileName) throws FileNotFoundException{

        Scanner input = new Scanner(new FileReader(fileName));

        while (input.hasNextLine()){
            String line = input.nextLine();
            String [] parts = line.split(":");
            StateCapital sc = new StateCapital(parts[0], parts[1]);
            listy.add(sc);
            }
    } 

    public StateCapital getRandomState(){
        int leng = listy.size();
        Random rand = new Random();
        return listy.get(rand.nextInt(leng));
    }

    public int statesRemaining(){
        return listy.size();
    }

    public void remove(StateCapital sc){
        listy.remove(sc);
    }

}