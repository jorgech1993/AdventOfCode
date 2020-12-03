import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class PasswordDetails {
    public static int firstNumber;
    public static int secondNumber;
    public static String letter;
    public static String password;

    public void setFirstNumber(int firstNum){
        this.firstNumber = firstNum;
    }
    public int getFirstNumber(){
        return firstNumber;
    }
    public void setSecondNumber(int secondNum){
        this.secondNumber = secondNum;
    }
    public  int getSecondNumber(){
        return secondNumber;
    }
    public void setLetter(String pickedLetter){
        this.letter = pickedLetter;
    }
    public String getLetter(){
       return this.letter;
    }
    public void setPassword(String pass){
        this.password = pass;
    }
    public String getPassword(){
        return this.password;
    }
}

public class Day2 {
    public static int totalNumberOfValidPasswordsPart1;
    public static int totalNumberOfValidPasswordsPart2;

    public static void processPasswordPart1(String password){

        PasswordDetails passwordDetails = breakUpNumbersAndLetter(password);

        int totalForSpecificLetter = 0;
        String passwordValue = passwordDetails.getPassword();
        char letterToChar = passwordDetails.getLetter().charAt(0);
        for(int i = 0; i < passwordValue.length(); i++)
        {
            if(passwordValue.charAt(i) == letterToChar){
                totalForSpecificLetter++;
            }
        }

        if(totalForSpecificLetter >= passwordDetails.getFirstNumber() && totalForSpecificLetter <= passwordDetails.getSecondNumber())
        {
            totalNumberOfValidPasswordsPart1++;
        }
    }

    public static void processPasswordPart2(String password){

        PasswordDetails passwordDetails = breakUpNumbersAndLetter(password);

        String passwordValue = passwordDetails.getPassword();
        char letterToChar = passwordDetails.getLetter().charAt(0);
        Boolean firstNumberFlag = false;
        Boolean secondNumberFlag = false;

        if(passwordValue.length() >= passwordDetails.getFirstNumber()){
            if(passwordValue.charAt(passwordDetails.getFirstNumber()) == letterToChar)
            {
                firstNumberFlag = true;
            }
        }
        if(passwordValue.length() >= passwordDetails.getSecondNumber()){
            if(passwordValue.charAt(passwordDetails.getSecondNumber()) == letterToChar)
            {
                secondNumberFlag = true;
            }
        }

        if(firstNumberFlag && !secondNumberFlag){
            totalNumberOfValidPasswordsPart2++;
        }
        else if(!firstNumberFlag && secondNumberFlag){
            totalNumberOfValidPasswordsPart2++;
        }
    }

    public static PasswordDetails breakUpNumbersAndLetter(String password){
        String[] passwordSplit = password.split(":");
        PasswordDetails passwordDetails = new PasswordDetails();

        passwordDetails.setPassword(passwordSplit[1]);
        String[] splitPolicyBySpace = passwordSplit[0].split(" ");
        passwordDetails.setLetter(splitPolicyBySpace[1]);

        String[] splitNumberPortion = splitPolicyBySpace[0].split("-");
        passwordDetails.setFirstNumber(Integer.parseInt(splitNumberPortion[0]));
        passwordDetails.setSecondNumber(Integer.parseInt(splitNumberPortion[1]));

        return passwordDetails;
    }

    public static void readFile(){
        try {
            File myObj = new File("src/Input.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String password = myReader.nextLine();
                processPasswordPart1(password);
                processPasswordPart2(password);
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void main (String[] args){
        readFile();

        System.out.println("Total Number of Valid Passwords(Part 1): " + totalNumberOfValidPasswordsPart1);
        System.out.println("Total Number of Valid Passwords(Part 2): " + totalNumberOfValidPasswordsPart2);

    }
}
