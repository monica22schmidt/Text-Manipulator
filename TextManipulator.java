//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: TextManipulator
// Files: Config.java
// Course: CS 200, Fall, and 2017
//
// Author: Monica Schmidt
// Email: meschmidt6@wisc.edu
// Lecturer's Name: Jim Williams
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates
// strangers, etc do. If you received no outside help from either type of
// source, then please explicitly indicate NONE.
//
// Persons:
// Online Sources: (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Scanner;
import java.io.PrintStream;
import java.io.File;
import java.util.Arrays;
import java.io.FileReader;
import java.io.BufferedReader;
import java.lang.StringBuilder;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The Text Manipulator Class creates the a program that can alter files inputed. The class creates
 * all the necessary methods to successfully modify your files conveniently.
 *
 * Bugs: space prints out after every non-punctuation word even at end.
 *
 * @author Monica Schmidt
 */


public class TextManipulator {

    /**
     * Matches the case of the original string to that of the template as follows.
     *
     * 1) If the length of template is the same or longer than the length of the original, the
     * returned string will match the case of the prefix of the template up to the length of the
     * original string. For example: template: XXxxxXxX original: azertYU returns: AZertYu
     *
     * 2) If the length of the template is shorter than the length of the original, the prefix of
     * the returned string up to the length of the template will match the case of the template. The
     * remaining characters should all be lower case. For example: template: WxCv original: azertYU
     * returns: AzErtyu
     *
     * @param template Template of which characters should be which case.
     * @param original String to change the case of, based on the template.
     * @return A string containing the characters of original with the case matching that of
     *         template.
     */
    public static String matchCase(String template, String original) {
        String newOriginal = "";
        // Compares the lengths of the template and original
        if (template.length() == original.length() || template.length() >= original.length()) {
            // Iterates through the the length of template because it could be longer than original
            for (int i = 0; i < original.length(); ++i) {
                // Checks to see if the character in template at a certain index is uppercase.
                if (Character.isUpperCase(template.charAt(i))) {
                    // Creates a new string and adds characters according to case
                    newOriginal += Character.toUpperCase(original.charAt(i));

                } else {
                    // Creates a new string and adds characters according to case
                    newOriginal += Character.toLowerCase(original.charAt(i));
                }
            }
        }
        // Compares the lengths of the template and original
        if (template.length() < original.length()) {
            // Iterates through the the length of original because it could be longer than template
            for (int i = 0; i < template.length(); ++i) {
                // Checks to see if the character in template at a certain index is uppercase.
                if (Character.isUpperCase(template.charAt(i))) {
                    // Creates a new string and adds characters according to case
                    newOriginal += Character.toUpperCase(original.charAt(i));

                } else {
                    // Creates a new string and adds characters according to case
                    newOriginal += Character.toLowerCase(original.charAt(i));
                }
            }
            // Iterates through the characters left in original after the final index is reached of
            // template
            for (int j = template.length(); j < original.length(); ++j) {
                // Turns the character left into string to made lowercase
                String charOriginal = Character.toString(original.charAt(j));
                // Creates a new string and adds characters according to case
                newOriginal += charOriginal.toLowerCase();
            }
        }
        return newOriginal;
    }

    /**
     * Translates a word according to the data in wordList then matches the case. The parameter
     * wordList contains the mappings for the translation. The data is organized in an ArrayList
     * containing String arrays of length 2. The first cell (index 0) contains the word in the
     * original language, called the key, and the second cell (index 1) contains the translation.
     *
     * It is assumed that the items in the wordList are sorted in ascending order according to the
     * keys in the first cell.
     *
     * @param word The word to translate.
     * @param wordList An ArrayList containing the translation mappings.
     * @return The mapping in the wordList with the same case as the original. If no match is found
     *         in wordList, it returns a string of Config.LINE_CHAR of the same length as word.
     */
    public static String translate(String word, ArrayList<String[]> wordList) {
        String translation = "";
        // Assigns each character to a variable for easier use
        char ch = word.charAt(0);
        // Checks to see if the character is not a letter or an '
        if (!(Character.isLetter(ch)) || ch == '\'') {
            // returns the character which is punctuation so it is not translated
            return Character.toString(ch);
        }
        // iterates through the arrays within wordList
        for (String[] translations : wordList) {
            // iterates through the strings in the array translations
            for (int i = 0; i < translations.length; ++i) {
                // checks to see if the value of translations at index i matches the word use ignore
                // case to insure it is not case sensitive

                if (translations[i].equalsIgnoreCase(word)) {
                    // sets translation to the translated value
                    translation = translations[1];
                    // call match case to make sure the cases match when returned
                    translation = matchCase(word, translation);
                    return translation;
                }
            }
        }
        // if no translation LINE_CHAR is set to translation
        if (translation.equals("")) {
            // iterates through the characters of the word inputed to create dashes of the word
            // length

            for (int i = 0; i < word.length(); ++i) {
                translation += Config.LINE_CHAR;
            }

        }

        return translation;
    }

    /**
     * Converts a string to simplified Pig Latin then matching the case. The rules for simplified
     * Pig Latin are as follows: 1) If the word begins with a vowel (a, e, i, o, u, or y), then the
     * string "way" is appended. 2) If the word begins with a consonant (any letter that is not a
     * vowel as defined above), then the group of consonants at the beginning of the word are moved
     * to the end of the string, and then the string "ay" is appended. 3) If the word begins with
     * any other character, the original string is returned. Note 1: 'y' is always considered a
     * vowel. Note 2: An apostrophe is treated as a consonant.
     *
     * Some examples: Pig -> Igpay Latin -> Atinlay Scram -> Amscray I'd -> I'dway you -> youway
     * crypt -> yptcray
     *
     * @param word The word to covert to simplified Pig Latin.
     * @return The simplified Pig Latin of the parameter word with matching case.
     */
    public static String pigLatin(String word) {
        String newString = "";
        String newWord = word.toLowerCase();
        // Makes a character array in order to iterate through the characters
        char[] pigLatin = newWord.toCharArray();
        // Checks to see if the character is not a letter or ' (punctuation)
        if (!Character.isLetter(word.charAt(0)) && !(word.charAt(0) == '\'')) {
            return word;
        }
        // Checks to see if the first character is a vowel
        if (pigLatin[0] == 'a' || pigLatin[0] == 'e' || pigLatin[0] == 'i' || pigLatin[0] == 'o'
            || pigLatin[0] == 'u' || pigLatin[0] == 'y') {
            newString = newWord + "way";
        } else {
            int j = 0;
            String consonants = "";
            // iterates through the characters of piglatin if not a vowel
            for (; j < pigLatin.length; ++j) {
                // sets all the consonants before a vowel is found to consonants
                if (!(pigLatin[j] == 'a' || pigLatin[j] == 'e' || pigLatin[j] == 'i'
                    || pigLatin[j] == 'o' || pigLatin[j] == 'u' || pigLatin[j] == 'y')) {
                    consonants += pigLatin[j];
                } else {
                    // when a vowel is reached we must break from the for loop
                    break;
                }
            }
            // creates the new word with what was left of the original string the consonants and ay
            newString += (newWord.substring(j) + consonants + "ay");
        }
        newString = matchCase(word, newString);

        return newString;
    }

    /**
     * Reverses a String, then matches the case. For example: aZErty returns yTReza
     *
     * @param word The String to reverse.
     * @return The reverse of word with matching case.
     */
    public static String reverse(String word) {
        String newString = "";
        // Makes a character array in order to iterate through the characters
        char[] reverse = word.toCharArray();
        // iterates through the characters in the word in from back to front
        for (int j = (reverse.length - 1); j >= 0; --j) {
            // adds the letter to the new string in reverse order
            newString += reverse[j];
        }
        newString = matchCase(word, newString);
        return newString;
    }

    /**
     * Builds a new ArrayList of Strings that contains the items of the ArrayList passed in, arrL,
     * but in reverse order.
     *
     * @param arrL The ArrayList of Strings to reverse.
     * @return A new ArraList of Strings that is the reverse of arrL.
     */
    public static ArrayList<String> reverse(ArrayList<String> arrL) {
        // Makes a new arrayList in order to return
        ArrayList<String> arrayToReturn = new ArrayList<String>();
        // iterates through the strings in arrl from back to front
        for (int j = (arrL.size() - 1); j >= 0; --j) {
            // adds the strings to the new array list in reverse order
            arrayToReturn.add(arrL.get(j));
        }
        return arrayToReturn;
    }

    /**
     * The method that displays the main program menu and reads the user's menu choice. The full
     * menu has the following format where the (assuming that Config.MISSING_CHAR is '-'):
     * 
     * -------------------------------------------------------------------------------- Text
     * Manipulator Program
     * -------------------------------------------------------------------------------- Current
     * input file: --- Current output file: --- Current dictionary: --- Current mode: Interleaved
     * Current mods: TPWL
     * -------------------------------------------------------------------------------- Main menu:
     * 1) Display Output 2) Save Output Manipulations: T)ranslate P)ig latin W)ord reverse L)ine
     * reverse Configuration: I)nput file. O)utput file. D)ictionary file. M)ode Toggle. H)ide/show
     * Menu. Q)uit Enter action:
     *
     * Notes: - The lines consist of 80 Config.LINE_CHAR characters. - The input file, output file
     * and dictionary names are 3 Config.LINE_CHAR characters if appropriate value in files is null,
     * otherwise display the appropriate value in files. - The Current mode displays "Interleaved"
     * when modeBoth is true and "Modified" when false. - The Current mods displays (in the
     * following order) 'T' if translate is toggled, 'P' if Pig Latin is toggled, 'W' if word
     * reverse is toggled, and 'L' if line reverse is toggled. - The manipulation and configuration
     * options are preceded by a single tab. - There is no new line following the final "Enter
     * action: " prompt.
     *
     * @param sc The reference to the Scanner object for reading input from the user.
     * @param files A string array containing the input file name, the output file name, and the
     *        dictionary file name in that exact order. The entries may be null. The length of the
     *        array is Config.NUM_FILES. The input file name is at index Config.FILE_IN, the output
     *        file name is at index Config.FILE_OUT, and the dictionary file name is at index
     *        Config.FILE_DICT.
     * @param modFlags A boolean array where the values are true if the given modification is set to
     *        be applied.
     * @param modeBoth True if the display command will alternate lines from the modified text and
     *        the original text.
     * @param showMenu If true the entire menu is shown, otherwise only the "Enter Action: " line is
     *        shown.
     * @return The first character of the line inputed by the user.
     */
    public static char promptMenu(Scanner sc, String[] files, boolean[] modFlags, boolean modeBoth,
        boolean showMenu) {
        char userAction = ' ';
        String curFileName = files[Config.FILE_IN];
        String curDictName = files[Config.FILE_DICT];
        String curOutputName = files[Config.FILE_OUT];
        // initializes modShown to nothing if the actions aren't toggled
        String modsShown = "";
        // calls the function to print the dashes
        if (showMenu) {
            dashFun();
            System.out.println("Text Manipulator Program");
            dashFun();
            // Checks to see if the input file is empty
            if (curFileName == null) {
                System.out.println("Current input file: " + Config.LINE_CHAR + Config.LINE_CHAR
                    + Config.LINE_CHAR);
            } else {
                System.out.println("Current input file: " + curFileName);
            }
            // Checks to see if the output file is empty
            if (curOutputName == null) {
                System.out.println("Current output file: " + Config.LINE_CHAR + Config.LINE_CHAR
                    + Config.LINE_CHAR);
            } else {
                System.out.println("Current output file: " + curOutputName);
            }
            // Checks to see if the dictionary file is empty
            if (curDictName == null) {
                System.out.println("Current dictionary: " + Config.LINE_CHAR + Config.LINE_CHAR
                    + Config.LINE_CHAR);
            } else {
                System.out.println("Current dictionary: " + curDictName);
            }
            // Checks to see if modeBoth is true in order to display the correct mode
            if (modeBoth == true) {
                System.out.println("Current mode: Interleaved");
            } else {
                System.out.println("Current mode: Modified");
            }
            // Checks to see if translate has been toggled to true
            if (modFlags[Config.MOD_TRANS] == true) {
                modsShown += "T";
            }
            // Checks to see if pigLatin has been toggled to true
            if (modFlags[Config.MOD_PIG] == true) {
                modsShown += "P";
            }
            // Checks to see if word reverse has been toggled to true
            if (modFlags[Config.MOD_REV_WORD] == true) {
                modsShown += "W";
            }
            // Checks to see if line reverse has been toggled to true
            if (modFlags[Config.MOD_REV_LINE] == true) {
                modsShown += "L";
            }

            System.out.print("Current mods: " + modsShown);
            System.out.println();
            for (int i = 0; i < 80; i++) {
                System.out.print(Config.LINE_CHAR);
            }
            System.out.println();
            System.out.println("Main menu:");
            System.out.println("1) Display Output");
            System.out.println("2) Save Output");
            System.out.println("Manipulations:");
            System.out.println("\tT)ranslate");
            System.out.println("\tP)ig latin");
            System.out.println("\tW)ord reverse");
            System.out.println("\tL)ine reverse");
            System.out.println("Configuration:");
            System.out.println("\tI)nput file.");
            System.out.println("\tO)utput file.");
            System.out.println("\tD)ictionary file.");
            System.out.println("\tM)ode Toggle.");
            System.out.println("\tH)ide/show Menu.");
            System.out.println("Q)uit");
        }
        System.out.print("Enter action: ");
        String userInput = sc.nextLine();
        userAction = userInput.charAt(0);


        return userAction;
    }

    /**
     * Prompts the user for a new file name. The prompt should be: "Enter file name [curFileName]:
     * ", where curFileName is the value from the parameter of the same name. There should not be a
     * new line following the prompt.
     *
     * @param sc The reference to the Scanner object for reading input from the user.
     * @param curFileName The current file name.
     * @return The value input by the user excluding any leading or trailing white space. If the
     *         input is an empty string, then curFileName is returned.
     */
    public static String updateFileName(Scanner sc, String curFileName) {
        System.out.print("Enter file name [" + curFileName + "]: ");
        String fileName = sc.nextLine();
        if (fileName.equals("\n")) {
            return curFileName;
        } else {
            return fileName;
        }

    }

    /**
     * Created a new method in order to avoid repeating code. It creates the dashes that appear in
     * the prompt menu and the display
     * 
     * @param no parameters
     * @return return nothing
     */
    public static void dashFun() {
        // Creates 80 LINE_CHAR characters
        for (int i = 0; i < 80; i++) {
            System.out.print(Config.LINE_CHAR);
        }
        System.out.println();
    }

    /**
     * Opens and reads the contents of the dictionary file specified in fileName. The file is
     * assumed to be a text file encoded in UTF-8. It is assumed that there is one translation
     * mapping per line. Each line contains a key and its translation separated by a tab. Note: The
     * dictionary file is assumed to be sorted by the keys in ascending order.
     *
     * For each line in the dictionary file, an entry is added into wordList. The entry is a String
     * array of length 2, where the value of index 0 is the key and the value of index 1 is the
     * translation.
     *
     * When opening the file, any FileNotFoundException is caught and the error message "Exception:
     * File 'fileName' not found." followed by a new line is output, where fileName is the name of
     * the file that the method attempted to open.
     *
     * @param fileName
     * @param wordList Reference to ArrayList to contain the translation mappings.
     * @throws IOException if an I/O error occurs when closing the file. FileNotFoundException is
     *         caught when opening the file.
     */
    public static void readDictFile(String fileName, ArrayList<String[]> wordList)
        throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        line = reader.readLine();
        while (line != null) {
            // takes the string input and turns it into an array
            // words are split based on the tab in between them
            String[] words = line.split("\t");
            // Adds the array to the arrayList
            wordList.add(words);
            line = reader.readLine();
        }
        reader.close();

    }



    /**
     * Opens and reads the contents of the input file specified in fileName. The input file is read
     * line by line. Each line is split into words and punction (excluding the apostrophe) and
     * stored in an ArrayList of Strings. These ArrayLists representing the line are stored in an
     * ArrayList of ArrayLists of Strings. Specifically, they are put in the ArrayList fileByLine
     * that is passed in as a parameter.
     *
     * For example, a file containing the following: Lorem ipsum dolor sit amet, consectetur
     * adipiscing elit. Don'ec elementum tortor in mauris consequat vulputate.
     *
     * Would produce an ArrayList of ArrayLists containing 2 ArrayLists of Strings. The first
     * ArrayList would contain: "Lorem", "ipsum", "dolor", "sit", "amet", ",", "consectetur",
     * "adipiscing", "elit", ".", "Don'ec", "elementum", "tortor", "in", "mauris" The second
     * Arraylist would contain: "consequat", "vulputate", "."
     *
     * Note 1: The text file is assumed to be UTF-8. Note 2: There are no assumption about the
     * length of the file or the length of the lines. Note 3: All single quotes (') are assumed to
     * be apostrophes.
     *
     * When opening the file, any FileNotFoundException is caught and the error message "Exception:
     * File 'fileName' not found." followed by a new line is output, where fileName is the name of
     * the file that the method attempted to open.
     *
     * @param fileName The name of the input text file to parse.
     * @param fileByLine Reference to ArrayList to contain the contents of the file line by line,
     *        where each line is an ArrayList of Strings.
     * @throws IOException if an I/O error occurs when closing the file. FileNotFoundException is
     *         caught when opening the file.
     */
    public static void readInputFile(String fileName, ArrayList<ArrayList<String>> fileByLine)
        throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
        String getEachLine = fileReader.readLine();
        // Checks to see if there was another read able line
        while (getEachLine != null) {
            // Place to store the strings from the file reader
            ArrayList<String> editedArray = new ArrayList<String>();
            // creates and array to hold the string. The string is split into words by spaces
            String[] newArray = getEachLine.split(" ");
            // Iterates through the strings in the newArray
            for (int i = 0; i < newArray.length; ++i) {
                // temporary string to reconstruct the strings without punctuation.
                String tempString = "";
                // Iterates through the characters of the strings in newArray
                for (int j = 0; j < newArray[i].length(); ++j) {
                    // set each character to ch
                    char ch = newArray[i].charAt(j);
                    // checks to see if ch is a letter or '
                    if (Character.isLetter(ch) || ch == '\'') {
                        // Adds the letter to tempString
                        tempString += ch;
                    }
                    // If it is not a letter it is punctuation
                    else {
                        // must add the tempString first
                        editedArray.add(tempString);
                        // reinitializes tempString to nothing
                        tempString = "";
                        // adds the punctuation character as a string
                        editedArray.add("" + ch);
                    }
                }
                // if there is some thing in temp string it is added
                if (!tempString.equals("")) {
                    editedArray.add(tempString);
                }
            }
            // the edited arrayList is added to fileByLine so it can be accessed outside of the
            // method
            fileByLine.add(editedArray);
            getEachLine = fileReader.readLine();
        }

        if (fileReader != null) {
            fileReader.close();
        }
    }

    /**
     * Opens and writes (overwrites if the file already exits) the modified contents of the input
     * file specified contained in modFileByLine to a file called filename. modFileByLine is an
     * ArrayList containing one ArrayList of String for each output line.
     *
     * When producing the output file, each word (non-punctuation) should be separated by a space
     * and each line should be terminated by a new line. The spacing around the punctuation should
     * be as follows: - Excluding, double quotes ("), no space between the preceding string and the
     * punctuation, but a space following the punctuation. - Double quotes (") will be treated as
     * pairs: - the first double quote will be preceded by a space and will not have a space
     * following. - the next double quote will not be preceded by space and will have a space
     * following.
     *
     * If modFileByLine is an ArrayList of ArrayLists containt 2 ArrayLists of Strings, such that: -
     * The first ArrayList contains: "Lorem", "ipsum", "\"", "dolor", "sit", "\"", "amet", ",",
     * "consectetur", "adipiscing", "elit", ".", "Don'ec", "elementum", "tortor", "in", "mauris" -
     * The second Arraylist contains: "consequat", "vulputate", "."
     *
     * The output to the file would be: Lorem ipsum "dolor sit" amet, consectetur adipiscing elit.
     * Don'ec elementum tortor in mauris consequat vulputate.
     *
     * Note 1: The output to the file is UTF-8.
     *
     * When opening the file, any FileNotFoundException is caught and the error message "Exception:
     * File 'fileName' not found." followed by a new line is output, where fileName is the name of
     * the file that the method attempted to open.
     *
     * @param fileName The name of the output file.
     * @param modFileByLine Reference to ArrayList to contain the modified contents of the file line
     *        by line, where each line is an ArrayList of Strings.
     * @throws IOException if an I/O error occurs when closing the file. FileNotFoundException is
     *         caught when opening the file.
     */
    public static void saveToFile(String fileName, ArrayList<ArrayList<String>> modFileByLine)
        throws IOException {
        PrintStream writeToFile = new PrintStream(new File(fileName));
        // for loop to iterate through the ArrayLists within modFileByLine
        for (int i = 0; i < modFileByLine.size(); ++i) {
            // to recreate a string with modifications
            String lineToPrint = "";
            ArrayList<String> line = modFileByLine.get(i);
            // for loop to iterate through the strings within the ArrayLists within modFileByLine
            for (int j = 0; j < line.size(); ++j) {
                // to hold the next string in the ArrayList
                String getNext = "";
                String word = line.get(j);
                // Keeps getNext within the index of the ArrayListt
                if (j <= line.size() - 2) {
                    getNext = line.get(j + 1);
                    // Checks to see if the current item is not a punctuation including '
                    if (Character.isLetter(word.charAt(0)) || (word.charAt(0) == '\'')) {
                        // adds the word to lineToPrint
                        lineToPrint += word;
                    }
                    // Checks to see if the next item is a punctuation not including '
                    if (!Character.isLetter(getNext.charAt(0)) && !(getNext.charAt(0) == '\'')) {
                        // add the punctuation to lineToPrint with a space
                        lineToPrint += getNext + " ";
                    } else {
                        // if the current word is a punctuation not including ' do nothing
                        if (!Character.isLetter(word.charAt(0)) && !(word.charAt(0) == '\'')) {

                        } else {
                            // adds space to lineToPrint if the current item is a word
                            lineToPrint += " ";
                        }
                    }
                    // if the current word is a punctuation not including ' do nothing if last word
                    // in the ArrayList
                } else if (!Character.isLetter(word.charAt(0)) && !(word.charAt(0) == '\'')) {

                } else {
                    // adds space to lineToPrint if the current item is a word if last word in the
                    // ArrayList
                    lineToPrint += word + " ";
                }

            }
            writeToFile.println(lineToPrint);
        }
    }


    /**
     * Prints out the modified file (and possibly interleaved with the original file) to the screen.
     *
     * If modeBoth is false, then the contents of modFileByLine is output line by line. Each word is
     * output followed by a space except for the last word. Each line is terminated with a new line
     * character.
     *
     * For the non-interleaved mode, consider the following example: modFileByLine contains 2
     * ArrayList of Strings: 1: "Où", "est", "la", "bibliothèque", "?" 2: "Aucune", "idée", "."
     *
     * The output would be: Où est la bibliothèque ? Aucune idée .
     *
     * Otherwise, modeBoth is true, then the contents of modFileByLine is interleaved with
     * fileByline. Lines are printed out in order. First, a line from modFileByLine is output
     * followed by a new line character followed by the corresponding line in fileByLine. In order
     * to better match up the corresponding words in the corresponding lines, the short word is
     * appended with spaces to the length of the longer word. Between each word adjusted for length
     * is an additional space.
     *
     * For the interleaved mode, consider the following example:
     * 
     * modFileByLine contains 2 ArrayList of Strings: 1: "Où", "est", "la", "bibliothèque", "?" 2:
     * "Aucune", "idée", "." fileByLine contains 1 ArrayList of Strings: 1: "Where", "is", "the",
     * "library", "?" 2: "No", "idea", "."
     *
     * The output would be: Où est la bibliothèque ? Where is the library ? Aucune idée . No idea .
     * 
     * @param fileByLine An ArrayList of ArrayList of Strings containing the original content.
     * @param modFileByLine An ArrayList of ArrayList of Strings containing the modified content.
     * @param modeBoth Flag to indicate if the original file should be interleaved.
     * @throws Exception Throws an Exception with the message "Number of lines in modified version
     *         differs from original." if the size of fileByLine differes from modFileByLine.
     * @throws Exception Throws an Exception with the message "Number of words on line i in modified
     *         version differs from original.", where i should be the 0-based line index where the
     *         size of the ArrayList at index i in fileByLine differes from the ArrayList at index i
     *         in modFileByLine.
     */
    public static void display(ArrayList<ArrayList<String>> fileByLine,
        ArrayList<ArrayList<String>> modFileByLine, boolean modeBoth) throws Exception {
        // stores the array list of strings from fileByLine
        ArrayList<String> iterating = new ArrayList<String>();
        // stores the array list of strings from modFileByLine
        ArrayList<String> iteratingMod = new ArrayList<String>();
        int modLength = 0;
        int wordLength = 0;
        if (modeBoth == true) {
            // Iterates through the arrayList of strings based on fileByLine's size
            for (int i = 0; i < fileByLine.size(); ++i) {
                // Gets one of the arrayLists from fileByLine
                iterating = fileByLine.get(i);
                // Gets one of the arrayLists from modFileByLine
                iteratingMod = modFileByLine.get(i);
                // iterates through the strings from iteratingMod
                for (int l = 0; l < iteratingMod.size(); ++l) {
                    System.out.print(iteratingMod.get(l));
                    wordLength = iterating.get(l).length();
                    modLength = iteratingMod.get(l).length();
                    // adds spaces in between the words
                    if (l != iteratingMod.size() - 1) {
                        System.out.print(" ");
                        // If wordlLength is smaller than modLength the word in modIterating needs
                        // extra space.
                        if (wordLength > modLength) {
                            for (int m = 0; m < wordLength - modLength; ++m) {
                                System.out.print(" ");

                            }
                        }

                    }
                }
                System.out.println();
                // iterates through the strings from iterating
                for (int j = 0; j < iterating.size(); ++j) {
                    System.out.print(iterating.get(j));
                    wordLength = iterating.get(j).length();
                    modLength = iteratingMod.get(j).length();
                    // adds spaces in between the words
                    if (j != iterating.size() - 1) {
                        System.out.print(" ");
                        // If wordlLength is larger than modLength the word in iterating needs extra
                        // space.
                        if (wordLength < modLength) {
                            for (int m = 0; m < modLength - wordLength; ++m)
                                System.out.print(" ");
                        }
                    }
                }
                System.out.println();
                System.out.println();
            }
        } else {
            // checks to see if modFileByLine has contents
            if (!(modFileByLine.isEmpty())) {
                // iterates through the arrayList
                for (int i = 0; i < modFileByLine.size(); ++i) {
                    // assigns the arrayList of strings
                    iteratingMod = modFileByLine.get(i);
                    // iterates through the strings of arrayList
                    for (int j = 0; j < iteratingMod.size(); ++j) {
                        System.out.print(iteratingMod.get(j));
                        // adds spaces in between the words
                        if (j != iteratingMod.size() - 1) {
                            System.out.print(" ");
                        }
                    }
                    System.out.println();
                }
            } else {
                // iterates through the arrayList
                for (int i = 0; i < fileByLine.size(); ++i) {
                    // assigns the arrayList of strings
                    iterating = fileByLine.get(i);
                    // iterates through the strings of arrayList
                    for (int j = 0; j < iterating.size(); ++j) {
                        System.out.print(iterating.get(j));
                        // adds spaces in between the words
                        if (j != iterating.size() - 1) {
                            System.out.print(" ");
                        }
                    }
                    System.out.println();
                }
            }
        }
    }

    /**
     * Performs the actions specified by the modFlags to the input file stored in the ArrayList of
     * ArrayLists of Strings fileByLine. This method stores the modified string in a new ArrayList
     * of ArrayLists of Strings which it returns.
     *
     * There are 4 modifications that may be performed. They are to be process in the following
     * order if indicated in modFlags: 1 - Translation 2 - To Pig Latin 3 - Reverse the letters in
     * each word 4 - Reverse the words in each line
     *
     * @param fileByLine The original file stored as an ArrayList of ArrayLists of Strings.
     * @param dict An ArrayList of String arrays of length two. The ArrayList is assumed to be
     *        sorted in ascending order according to the strings at index 0. This will be the second
     *        argument for the translate method.
     * @param modFlags A boolean area of length Config.NUM_MODS that indicates which translation to
     *        perform by having a value of true in the appropriate cell as follows: Index
     *        Modification ------------------- -------------------------------- Config.MOD_TRANS
     *        Translation Config.MOD_PIG To Pig Latin Config.MOD_REV_WORD Reverse the letters in
     *        each word Config.MOD_REV_LINE Reverse the words in each line
     * @return An ArrayList of ArrayLists of Strings, where each internal ArrayList is a line which
     *         corresponds to the data in fileByLine but with the transformations applied in the
     *         order specified above.
     */
    public static ArrayList<ArrayList<String>> manipulate(ArrayList<ArrayList<String>> fileByLine,
        ArrayList<String[]> dict, boolean[] modFlags) {
        ArrayList<ArrayList<String>> newArray = new ArrayList<ArrayList<String>>();
        // iterates through fileByLine based on the arrayList of strings
        for (ArrayList<String> individualLines : fileByLine) {
            ArrayList<String> newIndividualLines = new ArrayList<String>();
            // iterates through individualLines based on the strings in it
            for (String word : individualLines) {
                // Checks to see if modFlags at index 0 is true
                if (modFlags[Config.MOD_TRANS]) {
                    word = translate(word, dict);
                }
                // Checks to see if modFlags at index 1 is true
                if (modFlags[Config.MOD_PIG]) {
                    word = pigLatin(word);
                }
                // Checks to see if modFlags at index 2 is true
                if (modFlags[Config.MOD_REV_WORD]) {
                    word = reverse(word);
                }
                newIndividualLines.add(word);
            }
            // Checks to see if modFlags at index 3 is true
            if (modFlags[Config.MOD_REV_LINE]) {
                newIndividualLines = reverse(newIndividualLines);
            }
            newArray.add(newIndividualLines);
        }
        return newArray;

        // You'll want to create a new ArrayList<ArrayList<String>> to store the modified contents
        // of fileByLine. Here's the basic idea of this method:
        // for each line in fileByLine
        // create a new ArrayList<String> to store modified contents of an individual line
        // for each word in a line
        // if a modification is true, perform that modification on the word (i.e. translate)
        // add the word to the ArrayList<String>
        // add the modified line to the ArrayList<ArrayList<String>>


        // Leave the method as is for Milestone 1.


        // For Milestone 2, you will need to build a new ArrayList<ArrayList<String>> that will be
        // returned. Go through the each string in fileByLine and, if the boolean at
        // Config.MOD_TRANS in modFlags is true, then call the translate method, storing the
        // modified
        // strings (otherwise store the original string) line by line as they are organized in
        // fileByLine.

        // For Milestone 3, you will need to build a new ArrayList<ArrayList<String>> that will be
        // returned. For each string in fileByLine, you will need to check the booleans at
        // Config.MOD_TRANS, Config.MOD_PIG, and Config.MOD_REV_WORD in modFlags in that order. For
        // each one that is true, apply the appropriate transformation, storing them as in
        // Milestone 2. After having processed each string, if the boolean at Config.MOD_REV_LINE
        // in modFlags is true, reverse each line in the returning ArrayList<ArrayList<String>>.

    }


    /**
     * This is the main method for the TextManipulator program. This method contains the loop that
     * runs the prompt. It handles the user response and calls the methods that are necessary in
     * order to perform the actions requested by the user.
     *
     * The initial default behavior of the program is to show the full menu, to be in the "Modified"
     * mode, to have no modifications selected, and to have no values for the various file names.
     *
     * @param args (unused)
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] userFiles = new String[Config.NUM_FILES];
        boolean[] modFlags = new boolean[Config.NUM_MODS];
        boolean modeBoth = false;
        boolean showMenu = true;
        String curFileName = null;
        String curDictName = null;
        String curOutputName = null;
        String updatedFileName = null;
        String updatedDictName = null;
        String updatedOutputName = null;
        char userAction = ' ';
        ArrayList<ArrayList<String>> fileByLine = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> modFileByLine = new ArrayList<ArrayList<String>>();
        ArrayList<String[]> wordList = new ArrayList<String[]>();

        userAction = promptMenu(sc, userFiles, modFlags, modeBoth, showMenu);
        // The program will run until q is inputed.
        while (userAction != 'q' || userAction != 'Q') {
            // holds actions for i
            if (userAction == 'I' || userAction == 'i') {
                updatedFileName = updateFileName(sc, curFileName);
                // If the user does not enter anything promptmenu is show
                if (updatedFileName.equals("")) {
                    userAction = promptMenu(sc, userFiles, modFlags, modeBoth, showMenu);
                }
                // Checks to see if curFileName and updatedFileName are the same or not
                if (curFileName != updatedFileName) {
                    curFileName = updatedFileName;
                    userFiles[Config.FILE_IN] = curFileName;
                    // readInputFile could throw and exception which is then caught
                    try {
                        readInputFile(curFileName, fileByLine);
                    } catch (IOException e) {
                        System.out.println("Exception: File '" + curFileName + "' not found.");
                    } finally {
                        userAction = promptMenu(sc, userFiles, modFlags, modeBoth, showMenu);
                    }
                }
            } else if (userAction == 'o' || userAction == 'O') {
                updatedOutputName = updateFileName(sc, curOutputName);
                if (updatedOutputName.equals("")) {
                    userAction = promptMenu(sc, userFiles, modFlags, modeBoth, showMenu);
                }
                if (curOutputName != updatedOutputName) {
                    curOutputName = updatedOutputName;
                    userFiles[Config.FILE_OUT] = curOutputName;
                    userAction = promptMenu(sc, userFiles, modFlags, modeBoth, showMenu);
                }

            } else if (userAction == 'w' || userAction == 'W') {
                // checks to see if reverse word is true
                if (modFlags[Config.MOD_REV_WORD]) {
                    // sets reverse word to false
                    modFlags[Config.MOD_REV_WORD] = !modFlags[Config.MOD_REV_WORD];
                    // opposite of above
                } else if (!modFlags[Config.MOD_REV_WORD]) {
                    modFlags[Config.MOD_REV_WORD] = !modFlags[Config.MOD_REV_WORD];
                    userAction = promptMenu(sc, userFiles, modFlags, modeBoth, showMenu);
                }

            } else if (userAction == 'p' || userAction == 'P') {
                // checks to see if piglatin is true
                if (modFlags[Config.MOD_PIG]) {
                    // sets pigLatin to false
                    modFlags[Config.MOD_PIG] = !modFlags[Config.MOD_PIG];
                    // opposite of above
                } else if (!modFlags[Config.MOD_PIG]) {
                    modFlags[Config.MOD_PIG] = !modFlags[Config.MOD_PIG];
                    userAction = promptMenu(sc, userFiles, modFlags, modeBoth, showMenu);
                }
            } else if (userAction == 'q' || userAction == 'Q') {
                // breaks out of the while loop when q is toggled
                break;
            } else if (userAction == 'L' || userAction == 'l') {
                // checks to see if line reverse is true
                if (modFlags[Config.MOD_REV_LINE]) {
                    // sets line reverse to false
                    modFlags[Config.MOD_REV_LINE] = !modFlags[Config.MOD_REV_LINE];
                    // opposite of above
                } else if (!modFlags[Config.MOD_REV_LINE]) {
                    modFlags[Config.MOD_REV_LINE] = !modFlags[Config.MOD_REV_LINE];
                    userAction = promptMenu(sc, userFiles, modFlags, modeBoth, showMenu);
                }

            } else if (userAction == 'D' || userAction == 'd') {
                updatedDictName = updateFileName(sc, curDictName);
                if (updatedDictName.equals("")) {
                    userAction = promptMenu(sc, userFiles, modFlags, modeBoth, showMenu);
                }
                if (curDictName != updatedDictName) {
                    curDictName = updatedDictName;
                    userFiles[Config.FILE_DICT] = curDictName;
                    try {
                        readDictFile(curDictName, wordList);
                    } catch (IOException e) {
                        System.out.println("Exception: File '" + curFileName + "' not found.");
                    } finally {
                        userAction = promptMenu(sc, userFiles, modFlags, modeBoth, showMenu);
                    }
                }
            } else if (userAction == 'M' || userAction == 'm') {
                // checks to see if modeBoth is false
                if (!modeBoth) {
                    // changes modeBoth to true
                    modeBoth = !(modeBoth);
                } else {
                    // opposite of above
                    modeBoth = !(modeBoth);
                }
                userAction = promptMenu(sc, userFiles, modFlags, modeBoth, showMenu);

            } else if (userAction == 'H' || userAction == 'h') {
                // checks to see if showMenu is true
                if (showMenu) {
                    // changes showMenu to false
                    showMenu = !(showMenu);
                    userAction = promptMenu(sc, userFiles, modFlags, modeBoth, showMenu);
                } else {
                    // opposite of above
                    showMenu = !(showMenu);
                    userAction = promptMenu(sc, userFiles, modFlags, modeBoth, showMenu);
                }
            } else if (userAction == '1') {
                modFileByLine = manipulate(fileByLine, wordList, modFlags);
                dashFun();
                try {
                    display(fileByLine, modFileByLine, modeBoth);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    dashFun();
                    userAction = promptMenu(sc, userFiles, modFlags, modeBoth, showMenu);
                }

            } else if (userAction == '2') {
                modFileByLine = manipulate(fileByLine, wordList, modFlags);
                try {
                    saveToFile(curOutputName, modFileByLine);
                } catch (IOException e) {
                    System.out.println("Exception: File '" + curFileName + "' not found.");

                } finally {
                    userAction = promptMenu(sc, userFiles, modFlags, modeBoth, showMenu);
                }

            } else if (userAction == 't' || userAction == 'T') {
                // checks to see if translate is true
                if (modFlags[Config.MOD_TRANS]) {
                    // changes translate to false
                    modFlags[Config.MOD_TRANS] = !modFlags[Config.MOD_TRANS];
                    // opposite of above
                } else if (!modFlags[Config.MOD_TRANS]) {
                    modFlags[Config.MOD_TRANS] = !modFlags[Config.MOD_TRANS];
                    userAction = promptMenu(sc, userFiles, modFlags, modeBoth, showMenu);
                }
            } else {
                System.out.println("Unknown option.");
                userAction = promptMenu(sc, userFiles, modFlags, modeBoth, showMenu);
            }
        }
    }
}


