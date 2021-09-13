package com.company;
import java.util.Locale;
import java.util.Scanner;


/*
Vertical Prototype of Enigma project
 */


public class Main {

    //Indlæser to Scanner object, da de indgår i samme method og ellers laver fejl fordi første scanner allerede har en string værdi.
    public static Scanner input = new Scanner(System.in);
    public static Scanner in = new Scanner(System.in);

    //Indlæser String med mulige bogstaver
    public static String wordIndex = " ABCDEFGHIJKLMNOPQRSTUVWXYZÆØÅ";


    public static void main(String[] args) {

        //Velkomst og kald på start
        System.out.printf("\nVelkommen til Enigma projekt!\n");
        beginsProgram();
    }



    //Method that begins program, and restarts if necessary - and repeats program if user want to
    public static void beginsProgram () {

        System.out.print("Ønsker du at encode eller decode (tast e/d): ");

        String userInput = input.next();

        if (userInput.equals("e")) {
            //ENCODE BEGINS
            System.out.print("Du har valgt encode - indtast en tekst: ");

            //Input text from the user that will be encoded
            String word = in.nextLine();
            word = word.toUpperCase(Locale.ROOT);

            //Initializing array that stores the letters corresponding index numbers at 'wordIndex'
            int[] numberArray = new int[word.length()];

            //Converts the word to numbers and calls second method that prints it
            lettersToNumbers(word, wordIndex, numberArray);


        } else if (userInput.equals("d")) {
            //DECODE BEGINS
            System.out.print("Du har valgt decode - indtast en liste af tal: ");

            //Input list of numbers from the user that will be decoded
            String numberSequence = in.nextLine();

            //Converts the String to an int array with the numbers - int array does not include curly braces and commas.
            int[] intArray = stringToIntArray(numberSequence);

            //Converts int array to char array, then calls another method that makes char array a string and prints.
            numbersToLetters(intArray, wordIndex);


        } else {
            //If User input is not e/d
            System.out.print("Forstår ikke hvad du mener, indtast e/d (encode - indtast en tekst / decode - liste af tal): ");
            beginsProgram();
        }


        //Repeat if user want to try again
        System.out.print("Vil du prøve igen? (y/n): ");
        String userDecides = input.next();

        if (userDecides.equals("y")) {
            beginsProgram();
        } else {
            System.out.print("Tak for at prøve!");
        }
    }




    //FOR ENCODE



    //Method that converts a word to numbers
    public static void lettersToNumbers (String word, String wordIndex, int[] numberArray) {

        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);

            //Array at index i will get the value of where the user word is present on wordIndex
            numberArray[i] = wordIndex.indexOf(letter);
        }

        //Prints the new array when called
        numberArrayToLetters(numberArray);
    }



    //Method that prints out the numbers that are converted
    public static void numberArrayToLetters (int[] numberArray) {
        System.out.print("{" + numberArray[0]);
        for (int i = 1; i < numberArray.length; i++) {
            System.out.print(", " + numberArray[i]);
        }
        System.out.println("}");
    }




    // FOR DECODE



    //Methods converts a String to int array
    public static int[] stringToIntArray (String numberSequence) {

        //Removes curly braces from String - expecting always to be inputtet at first and last index in String
        numberSequence = numberSequence.substring(1,numberSequence.length()-1);

        //Initializing array
        int[] numberIntArray = new int[numberSequence.length()];


        if (numberSequence.contains(" ")) {  //If user inputs space between commas

            //Creates String array that divides each index between the ","
            String[] numberStrs = numberSequence.split(", ");

            for (int i = 0; i< numberStrs.length; i++) {
                //For each String array index it converts the String number to int Number for the int Array
                numberIntArray[i] = Integer.parseInt(numberStrs[i]);
            }

        } else {  //If user does not use space between commas

            //Creates String array that divides each index between the ","
            String[] numberStrs = numberSequence.split(",");

            for (int i = 0; i< numberStrs.length; i++) {
                //For each String array index it converts the String number to int Number for the int Array
                numberIntArray[i] = Integer.parseInt(numberStrs[i]);
            }
        }
        return numberIntArray;
    }



    //Method that converts a number array to a char array
    public static void numbersToLetters (int[] intArray, String wordIndex) {

        char[] wordArray = new char[intArray.length];

        for (int i = 0; i < intArray.length; i++) {
            wordArray[i] = wordIndex.charAt(intArray[i]);

        }
        //Call will output the converted char array to a text - after making the characters in the array to a string
        charactersToString(wordArray);
    }



    //Method that makes an array of characters to a string and output
    public static  void charactersToString (char[] wordArray) {
        String fullString = String.valueOf(wordArray);
        System.out.println(fullString);
    }
}
