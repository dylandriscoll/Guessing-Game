//Programmer: Dylan Driscoll
//CS 145
//Date: 1/9/23
//Assignment: Lab 1 Guessing Game 


/*This program will allow the user to play a guessing game. It will first prompt the user to ask what range
of numbers they would like to play with and then start a game. If the user inputs something other than
a number or a negative number or a number outside the range they have selected. They will be forced to enter
a valid input. After they play a game, the user can decide if they want to play again or not. If they choose
to play again, they can select a new range to play with. If they decide not to play again, the program will
report stats to them including their total games, total guesses, guesses per game, best game, highest range in
a game they won, and the average range of the games played.*/
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

public class GuessingGame {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String play = "y";
        int gamesplayed = 0;
        int attempts = 0;
        int guessTotal = 0;
        int guesses = 0;
        int rangeTotal = 0;
        ArrayList < Integer > scores = new ArrayList < Integer > ();
        ArrayList < Integer > ranges = new ArrayList < Integer > ();
        introduction();
        boolean playAgain = true;
        while (playAgain == true) {
            int highRange = range(input);
            ranges.add(highRange);
            guesses = playOneGame(input, attempts, highRange);
            scores.add(guesses);
            System.out.printf("\n\nDo you want to play again?");
            play = input.next().toLowerCase();
            playAgain = playAgain(play);
            gamesplayed++;
        }/*this loop is the one we are required to have in main
        //it counts number of games played and accepts the return
        //from my playonegame method.*/
        for (int i: scores) {
            guessTotal += i;
        }/*this calculates the sum of all elements in my list which will be
        //the total number of guess through all the games the user plays.*/
        int bestScore = Collections.min(scores);
        double averageScore = averageScore(guessTotal, gamesplayed);
        for (int i: ranges) {
            rangeTotal += i;
        }
        int highestRange = Collections.max(ranges) - 1;
        double averageRange = averageScore(rangeTotal, gamesplayed);
        returnData(bestScore, gamesplayed, guessTotal, 
        averageScore, highestRange, averageRange);
        /*this method call prints the data for the user when they 
        //are done playing.*/
    }
    public static void introduction() {
        System.out.println("This program allows you to"); 
        System.out.println("play a guessing game.");
        System.out.println("I will think of a number");
        System.out.println("between 1 and a number");
        System.out.println("of your choice. You will then"); 
        System.out.println("guess until you");
        System.out.println("get it right. For each guess, I will");
        System.out.println("tell you whether ");
        System.out.println("he right answer is higher or");
        System.out.println("lower than your guess.");
        System.out.println("After your game, you can choose"); 
        System.out.println("to play again. If you");
        System.out.println("don't wish to, I will report the stats");
        System.out.println("from your play session");
    } //this method introduces the game to user
    public static int range(Scanner input) {
        String highRange = "";
        boolean intCheck = intCheck(highRange);
        System.out.println();
        System.out.println("Type the maximum of the range"); 
        System.out.println("you would like to play with. From 1 to:");
        do {
            highRange = input.next();
            if (highRange.contains("-") || highRange.startsWith("0")) {
                do {
                    System.out.println("Your number must be larger than 1. Please try again.");
                    highRange = input.next();
                } while (highRange.startsWith("-"));
            }
            try {
                return Integer.parseInt(highRange);
            } catch (NumberFormatException e) {
                System.out.println(highRange + " is not a valid number to play with.");
                System.out.println("Please enter a number to indicate the"); 
                System.out.println("maximum number in the range you want to play with.");
                highRange = input.nextLine();
                intCheck = intCheck(highRange);
            }
        } while (intCheck == false);
        return Integer.parseInt(highRange);
    }/*this method is for prompting the user to decide on a max number for their game.
    //it has different print statements to relay information to the user depending on
    //what the problem was with their input. It was tough to get this to work for cases
    //where the user inputs an error causing input repeatedly. It might be a little
    //bit overly complicated but since its working and I'm out of time I'm sticking\
    //with it.*/
    public static boolean intCheck(String highRange) {
        Scanner intChecker = new Scanner(highRange);
        if (intChecker.hasNextInt()) {
            return true;
        } else {
            return false;
        }
    }/*this method is simply using a scanner to check the string of either
    //the range input or the guess input to make sure it contains an integer.*/
    public static int guessCheck(String guess) {
        Scanner guessChecker = new Scanner(guess);
        if (guessChecker.hasNextInt()) {
            return guessChecker.nextInt();
        } else {
            return 0;
        }
    }/*if the String passes the boolean check of the previous method, it 
    //comes here and is converted to an int. Its like a double safety net
    //to ensure the program won't break and I needed a way to convert the
    //String to an int anyway.*/
    public static int playOneGame(Scanner input, int attempts, int highRange) {
        String play = "y";
        while (play.equals("y")) {
            String guess = "-1";
            Random rand = new Random();
            int answer = 1 + rand.nextInt(highRange);
            /*you can uncomment the line below if you want to use it to grade.
            //it will print the answer so its easier to play the game quickly.*/
            //System.out.println(answer);
            System.out.printf("\n\nI'm thinking of a number"); 
            System.out.println("between 1 and " + highRange + "...");

            while (play.startsWith("y")) {
                System.out.printf("\nYour guess? ");
                guess = input.next();
                attempts++;
                boolean guessCheck = intCheck(guess);
                int guessInt = guessCheck(guess);

                if (guessInt == answer && guessCheck == true && guessInt <= highRange) {
                    System.out.printf("\nYou got it right in " + attempts + " guesses");
                    play = "n";
                } else if (guessInt < answer && guessCheck == true && 
                guessInt <= highRange && guessInt > 0) {
                    System.out.println("It's higher.");
                } else if (guessInt > answer && guessCheck == true && 
                guessInt <= highRange && guessInt > 0) {
                    System.out.println("It's lower.");
                } else {
                    System.out.println();
                    System.out.println("The instructions were pretty"); 
                    System.out.println("clear... I'm counting that as a guess.");
                    System.out.println("Your guess must be in"); 
                    System.out.println("the range of numbers you chose.");
                    System.out.println("I'll say it one more"); 
                    System.out.println("time for you....");
                    System.out.printf("\n\nI AM THINKING OF A"); 
                    System.out.println("NUMBER BETWEEN 1 AND " + highRange + "...");
                }
            }
        }
        return attempts;
    }/*this is my play one game method. it first sets the string play equal
    //to y to make sure it runs. it uses while and if else to decide
    //what information to give to the user as they are playing
    //when the user finishes one game it sets play equal to n so that
    //the user can be asked if they want to play again.
    //this method is largely the same as it was originally but I added
    //some print statements to catch bad inputs that the user might be 
    //putting in and put them back on the right track.*/
    public static double averageScore(double totalGuesses, double gamesplayed) {
        double averageScore = totalGuesses / gamesplayed;
        return averageScore;
    }/*this method will take the total guess and dividie it by games played
    //and return a double which will be my users average score
    //since the math is the same, I also use this method to calculate the 
    //average range.*/ 
    public static void returnData(int bestScore, int totalGamesPlayed, int totalGuesses, 
    double averageScore, int highestRange, double averageRange) {
        System.out.printf("\n\nOverall results");
        System.out.printf("\n\n\ttotal games = " + totalGamesPlayed);
        System.out.printf("\n\n\ttotal guesses = " + totalGuesses);
        System.out.printf("\n\n\tguesses/game = %.2f", averageScore);
        System.out.printf("\n\n\tbest game = " + bestScore);
        highestRange = highestRange + 1;
        System.out.printf("\n\n\thighest range = 1 through " + highestRange);
        System.out.printf("\n\n\taverage range = %.2f", averageRange);
    }/*this method will print out all the data for the user
    //that we are required to store. it is passed the data
    //in 6 parameters.*/
    public static boolean playAgain(String play) {
        if (play.startsWith("y")) {
            return true;
        } else {
            return false;
        }    
    }/*this method simply accepts the string that the user is 
     //prompted for when asked if they want to play again. I 
     //have the string converted to lower case  before it gets
     //here and this just checks if it starts with y and returns
     //a boolean. A return of false will break the do whil loop
     //and move on to reporting stats.*/
}