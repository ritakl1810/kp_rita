package by.builder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EasyLevelBuilder extends RiddleBuilder{
    @Override
    public void buildFirst() {
        readFile();
        riddle.setFirstOption(first);
    }

    @Override
    public void buildSecond() {
        riddle.setSecondOption(second);
    }

    @Override
    public void buildThird() {
        riddle.setThirdOption(third);
    }

    @Override
    public void buildCorrect() {
        riddle.setCorrectAnswer(correct);
        riddle.setPicture(picture);
    }

    private void readFile(){
        int riddleString = (int) (Math.random() * 5);
        int amount = 0;
        String riddleText = "";
        BufferedReader objReader = null;
        try {
            String strCurrentLine;
            objReader = new BufferedReader(new FileReader("E:\\riddle-easy.txt"));
            while ((strCurrentLine = objReader.readLine())!=null) {
                for(int i=0;i<5;i++){
                    if(i==riddleString){
                        riddleText = strCurrentLine;
                    }
                }
            }
            String[] array = riddleText.split(", ");
            first = array[0];
            second = array[1];
            third = array[2];
            correct = array[3];
            picture = array[4];
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (objReader != null)
                    objReader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }
}
