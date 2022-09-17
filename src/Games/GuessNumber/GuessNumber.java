package Games.GuessNumber;
import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GuessNumber {
    public static void main(String[] args) {
        int computerNumber = (int) (Math.random()*100 + 1);
        int userAnswer = 0;

        System.out.println("The correct guess would be " + computerNumber);
        int count = 1;

            while (userAnswer!= computerNumber) {

                String responce = JOptionPane.showInputDialog(null,
                        "Enter a guess between 1 and 100", "Guessing Game", JOptionPane.QUESTION_MESSAGE);

                userAnswer = Integer.parseInt(responce);
                Pattern p = Pattern.compile("[A-Z,a-z,&%$#@!()*^]");
                Matcher m = p.matcher(responce);
                if (m.find()) {
                    JOptionPane.showMessageDialog(null, "Please enter only numbers");
                }

                else {
                    JOptionPane.showMessageDialog(null,
                            "" + determineGuess(userAnswer, computerNumber, count));

                    count++;
                }
            }


    }

//    public static boolean isNumber(String n) {
//        try{
//            Integer.parseInt(n);
//            return true;
//        }
//        catch(NumberFormatException e){
//            return false;
//        }
//    }

    public static String determineGuess(int userAnswer, int computerNumber, int count) {


        if(userAnswer <= 0 || userAnswer > 100 ){
            return "Your guess is invalid";
        }
            else if(userAnswer == computerNumber){
                return "Correct!\nTotal Guesses: " + count;
            }
                else if(userAnswer > computerNumber) {
                    return "Your guess is too high, try lower.\nTry Number: " + count;
                }
                    else {
                        return "Your guess is too low, try higher .\nTry Number: " + count;
                    }

    }
}
