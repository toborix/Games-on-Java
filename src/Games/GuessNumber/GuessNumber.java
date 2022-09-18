package Games.GuessNumber;
import javax.swing.*;

public class GuessNumber {
    public static void main(String[] args) {

        int computerNumber = (int) (Math.random()*100 + 1);
        String userAnswer = "0";

        System.out.println("The correct guess would be " + computerNumber);
        int count = 1;

        while (Integer.parseInt(userAnswer)!= computerNumber) {

             userAnswer = JOptionPane.showInputDialog(null,
                    "Enter a guess between 1 and 100", "Guessing Game", JOptionPane.QUESTION_MESSAGE);

            while(!isNumber(userAnswer)) {
                int resp = JOptionPane.showConfirmDialog(
                        null,
                        "Please only numbers",
                        "Guessing Game",
                        JOptionPane.DEFAULT_OPTION);

                if (resp == 0){
                    userAnswer = JOptionPane.showInputDialog(
                            null,
                            "Enter a guess between 1 and 100",
                            "Guessing Game",
                            JOptionPane.QUESTION_MESSAGE);
                }

            }

            JOptionPane.showMessageDialog(
                    null,
                    "" + determineGuess(Integer.parseInt(userAnswer),
                            computerNumber, count));

            count++;
            }
        }


    public static boolean isNumber(String n) {
        try{
            Integer.parseInt(n);
            return true;
        }
        catch(NumberFormatException e){
            return false;
        }
    }

    public static String determineGuess(int userAnswer, int computerNumber, int count) {

        if(userAnswer <= 0 || userAnswer > 100 ){
            return "Your guess is invalid";
        }
        if(userAnswer == computerNumber){
            return "Correct!\nTotal Guesses: " + count;
        }
        if(userAnswer > computerNumber) {
            return "Your guess is too high, try lower.\nTry Number: " + count;
        }
        else {
            return "Your guess is too low, try higher .\nTry Number: " + count;
        }

    }
}
