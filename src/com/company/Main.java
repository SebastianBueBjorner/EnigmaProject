package com.company;
import java.util.Locale;
import java.util.Scanner;


/*
Enigma project - with Number cipher, Caesar cipher and Vigenere cipher
 */


public class Main {

    //Indlæser to Scanner object, da de indgår i samme method og ellers laver fejl fordi første scanner allerede har en string værdi.
    public static Scanner input = new Scanner(System.in);
    public static Scanner in = new Scanner(System.in);
    public static Scanner inSecretWord = new Scanner(System.in);

    //Indlæser String med mulige bogstaver
    public static String wordIndex = " ABCDEFGHIJKLMNOPQRSTUVWXYZÆØÅ";

    //Velkomst og kald på menu()
    public static void main(String[] args) {
        //Velkomst og kald på menu
        System.out.print("\nVelkommen til Enigma projekt!\n");
        menu();
    }


    //MENU - Choose to "Number cipher", "Ceasar cipher", "Vigenere cipher" or "Exit program"
    public static void menu() {

        System.out.print("Vælg mellem følgende (indtast tallet):\n1) Number cipher\n2) Caesar cipher\n3) Vigenere cipher\n0) Exit\n");
        int inputFromUser = input.nextInt();

        //Program that runs depends on user input
        if (inputFromUser == 1) {
            //Number Cipher Begins
            numberCipherBeginsProgram();

        } else if (inputFromUser == 2) {
            //Caesar Cipher Begins
            caesarCipherBeginsProgram();

        } else if (inputFromUser == 3) {
            //Vifenere Cipher Begins
            vigenereCipherBeginsProgram();

        } else if (inputFromUser == 0) {
            //Exit program

        } else {
            System.out.println("Ugyldigt input...");
            menu();
        }
    }


    //________________________________________________________________________________

    //NUMBER CIPHER CHOOSEN

    //Method that begins number Cipher program, and restarts if necessary - and repeats program if user want to
    public static void numberCipherBeginsProgram () {

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
            numberCipherBeginsProgram();
        }


        //Repeat if user want to try again or send back to menu
        System.out.print("Vil du prøve igen? (y/n): ");
        String userDecides = input.next();

        if (userDecides.equals("y")) {
            numberCipherBeginsProgram();
        } else {
            System.out.print("Tak for at prøve! Du bliver sendt tilbage til menuen.\n");
            menu();
        }
    }


    //FOR ENCODE - NUMBER CIPHER (numberArrayToLetters get reused)

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


    // FOR DECODE - NUMBER CIPHER (All gets reused in Caesar cipher)

    //Methods converts a String to int array
    public static int[] stringToIntArray (String numberSequence) {

        //Removes curly braces from String - expecting always to be inputtet at first and last index in String
        numberSequence = numberSequence.substring(1,numberSequence.length()-1);

        if (numberSequence.contains(" ")) {  //If user inputs space between commas

            //Creates String array that divides each index between the ","
            String[] numberStrs = numberSequence.split(", ");

            //Initializing array
            int[] numberIntArray = new int[numberStrs.length];

            for (int i = 0; i< numberStrs.length; i++) {
                //For each String array index it converts the String number to int Number for the int Array
                numberIntArray[i] = Integer.parseInt(numberStrs[i]);
            }
            return numberIntArray;

        } else {  //If user does not use space between commas

            //Creates String array that divides each index between the ","
            String[] numberStrs = numberSequence.split(",");

            //Initializing array
            int[] numberIntArray = new int[numberStrs.length];

            for (int i = 0; i< numberStrs.length; i++) {
                //For each String array index it converts the String number to int Number for the int Array
                numberIntArray[i] = Integer.parseInt(numberStrs[i]);
            }
            return numberIntArray;
        }
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


    //________________________________________________________________________________

    //CAESAR CIPHER CHOOSEN

    //Method that begins Caesar Cipher program, and restarts if necessary - and repeats program if user want to
    public static void caesarCipherBeginsProgram () {

        System.out.print("Ønsker du at encode eller decode (tast e/d): ");

        String userInput = input.next();

        if (userInput.equals("e")) {
            //ENCODE BEGINS
            System.out.print("Du har valgt encode - indtast en tekst: ");

            //Input text from the user that will be encoded
            String wordCaesar = in.nextLine();
            wordCaesar = wordCaesar.toUpperCase(Locale.ROOT);

            //Input shift from user
            System.out.print("Indtast et shift: ");
            int shift = input.nextInt();

            //Initializing array that stores the letters corresponding index numbers at 'wordIndex'
            int[] numberArrayCaesar = new int[wordCaesar.length()];

            //Converts the word to numbers with shift and calls second method that prints it
            lettersToNumbersCaesar(wordCaesar, numberArrayCaesar, shift);


        } else if (userInput.equals("d")) {
            //DECODE BEGINS
            System.out.print("Du har valgt decode - indtast en liste af tal: ");

            //Input list of numbers from the user that will be decoded
            String numberSequenceCaesar = in.nextLine();

            //Input shift from user
            System.out.print("Indtast et shift: ");
            int shift = input.nextInt();

            //Converts the String to an int array with the numbers - int array does not include curly braces and commas.
            int[] intArrayCaesar = stringToIntArray(numberSequenceCaesar);

            //Converts the int Arrays values back to (minus shift)
            shiftIntBack(shift, intArrayCaesar);

            //Converts int array to char array, then calls another method that makes char array a string and prints.
            numbersToLetters(intArrayCaesar, wordIndex);


        } else {
            //If User input is not e/d
            System.out.print("Forstår ikke hvad du mener, indtast e/d (encode - indtast en tekst / decode - liste af tal): ");
            numberCipherBeginsProgram();
        }


        //Repeat if user want to try again or send back to menu
        System.out.print("Vil du prøve igen? (y/n): ");
        String userDecides = input.next();

        if (userDecides.equals("y")) {
            caesarCipherBeginsProgram();
        } else {
            System.out.print("Tak for at prøve! Du bliver sendt tilbage til menuen.\n");
            menu();
        }
    }


    //FOR ENCODE - CAESAR CIPHER

    //Method that convert a word to numbers with a user input shift
    public static void lettersToNumbersCaesar (String wordCaesar, int[] numberArrayCaesar, int shift) {

        for (int i = 0; i < wordCaesar.length(); i++) {
            char letter = wordCaesar.charAt(i);

            //Array at index i will get the value of where the user word is present on wordIndex + shift
            numberArrayCaesar[i] = wordIndex.indexOf(letter) + shift;
        }
        //Prints the new array when called
        numberArrayToLetters(numberArrayCaesar);
    }


    // FOR DECODE - CAESAR CIPHER

    //Method that shifts back the intarray value
    public static void shiftIntBack (int shift, int[] intArrayCaesar) {
        for (int i = 0; i < intArrayCaesar.length; i++) {
            intArrayCaesar[i] = intArrayCaesar[i] - shift;
        }
    }


    //________________________________________________________________________________

    //VIGENERE CIPHER CHOOSEN

    //Method that begins Caesar Cipher program, and restarts if necessary - and repeats program if user want to
    public static void vigenereCipherBeginsProgram () {

        System.out.print("Ønsker du at encode eller decode (tast e/d): ");

        String userInput = input.next();

        if (userInput.equals("e")) {
            //ENCODE BEGINS
            System.out.print("Du har valgt encode - indtast en tekst: ");

            //Input text from the user that will be encoded
            String wordVig = in.nextLine();
            wordVig = wordVig.toUpperCase(Locale.ROOT);

            //Input secretWord from user
            System.out.print("Indtast et secret word: ");
            String secretWord = inSecretWord.nextLine();
            secretWord = secretWord.toUpperCase(Locale.ROOT);

            //Converts secretWord to int array:
            int[] secretWordArray = secretWordToIntArray(secretWord);

            //Initializing array that stores the letters corresponding index numbers at 'wordIndex'
            int[] numberArrayVig = new int[wordVig.length()];

            //Converts the word to numbers with shift and calls second method that prints it
            lettersToNumbersVig(wordVig, numberArrayVig, secretWordArray);



        } else if (userInput.equals("d")) {
            //DECODE BEGINS
            System.out.print("Du har valgt decode - indtast en liste af tal: ");

            //Input list of numbers from the user that will be decoded
            String numberSequenceVig = in.nextLine();

            //Input secretWord from user
            System.out.print("Indtast et secret word: ");
            String secretWord = inSecretWord.nextLine();
            secretWord = secretWord.toUpperCase(Locale.ROOT);

            //Converts secretWord to int array:
            int[] secretWordArray = secretWordToIntArray(secretWord);

            //Converts the String to an int array with the numbers - int array does not include curly braces and commas.
            int[] intArrayVig = stringToIntArray(numberSequenceVig);

            //Converts the int Arrays value from user input back with secretWord array index values
            shiftIntBackVig(secretWordArray, intArrayVig);

            //Converts int array to char array, then calls another method that makes char array a string and prints.
            numbersToLetters(intArrayVig, wordIndex);


        } else {
            //If User input is not e/d
            System.out.print("Forstår ikke hvad du mener, indtast e/d (encode - indtast en tekst / decode - liste af tal): ");
            vigenereCipherBeginsProgram();
        }


        //Repeat if user want to try again or send back to menu
        System.out.print("Vil du prøve igen? (y/n): ");
        String userDecides = input.next();

        if (userDecides.equals("y")) {
            vigenereCipherBeginsProgram();
        } else {
            System.out.print("Tak for at prøve! Du bliver sendt tilbage til menuen.\n");
            menu();
        }
    }


    //FOR ENCODE - VIGENERE CIPHER

    //Method that converts the Secret word to int numbers array
    public static int[] secretWordToIntArray (String secretWord) {

        int[] secretWordArray = new int[secretWord.length()];

        for (int i = 0; i < secretWord.length(); i++) {
            char letter = secretWord.charAt(i);

            //Array at index i will get the value of where the user word is present on wordIndex
            secretWordArray[i] = wordIndex.indexOf(letter);

        }
        return secretWordArray;
    }


    //Method that convert a word to numbers with a user input secretWord (that shifts the word with secretWords index numbers)
    public static void lettersToNumbersVig (String wordVig, int[] numberArrayVig, int[] secretWordArray) {

        for (int i = 0; i < wordVig.length(); i++) {
            char letter = wordVig.charAt(i);

            //Array at index i will get the value of where the user word is present on wordIndex + secretWords wordIndex value, which repeats.
            numberArrayVig[i] = wordIndex.indexOf(letter) + secretWordArray[i% secretWordArray.length];

        }
        //Prints the new array when called
        numberArrayToLetters(numberArrayVig);
    }


    //FOR DECODE - VIGENERE CIPHER

    //Method that shifts back the int array value with the secretWordArray
    public static void shiftIntBackVig (int[] secretWordArray, int[] intArrayVig) {
        for (int i = 0; i < intArrayVig.length; i++) {
            intArrayVig[i] = intArrayVig[i] - secretWordArray[i%secretWordArray.length];
        }
    }

}
