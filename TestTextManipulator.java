/**
 * This file contains testing methods for the Text Manipulator project. These methods are intended
 * to serve several objectives: 1) provide an example of a way to incrementally test your code 2)
 * provide example method calls for the Text Manipulator methods
 * 
 * Some of the provided comments within this file explain Java code as they are intended to help you
 * learn Java. However, your comments and comments in professional code, should summarize the
 * purpose of the code, not explain the meaning of the specific Java constructs.
 * 
 */

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


/**
 * This class contains a few methods for testing methods in the Text Manipulator class as they are
 * developed. These methods are all private as they are only intended for use within this class.
 * 
 * @author Monica Schmidt
 *
 */
public class TestTextManipulator {

    /**
     * This is the main method that runs the various tests. Uncomment the tests when you are ready
     * for them to run.
     * 
     * @param args (unused)
     */
    public static void main(String[] args) {

        // Milestone 2
        testMatchCase();
        testTranslate();
        testManipulate(); 
        // Milestone 3
        testPigLatin();
        testReverse();
        testReverseLine();
      

    }



    /**
     * This runs some tests on the matchCase method. 
     * 1. The test checks to see if the case of the
     * two equal length words entered match 
     * 2.The test checks to see if the case of the template and
     * the case of the original entered match but original is longer than template 
     * 3.The test checks
     * to see if the case of the template and the case of the original entered match but template is
     * longer than template
     */
    private static void testMatchCase() {
        boolean error = false;
        String template = "MoNiCa";
        String original = "calina";
        String match = TextManipulator.matchCase(template, original);
        if (!(match.equals("CaLiNa"))) {
            error = true;
            System.out.println("testMatchCase 1: match=" + match + " expected CaLiNa");
        }
        original = "Marissa";
        match = TextManipulator.matchCase(template, original);
        if (!(match.equals("MaRiSsa"))) {
            error = true;
            System.out.println("testMatchCase 2: match=" + match + " expected MaRiSsa");
        }
        original = "Mary";
        match = TextManipulator.matchCase(template, original);
        if (!(match.equals("MaRy"))) {
            error = true;
            System.out.println("testMatchCase 3: match=" + match + " expected MaRy");
        }

        if (error) {
            System.out.println("testMatchCase: failed");
        } else {
            System.out.println("testMatchCase: passed");
        }

    }

    /**
     * This runs some tests on the Translate method. 
     * 1.The test checks to see the word is translated
     * correctly and case matches 
     * 2.The test checks to see the word of different case is translated
     * correctly and case matches 
     * 3.The test checks to see the word is translated correctly and case
     * matches translation includes a special character
     */
    private static void testTranslate() {
        boolean error = false;
        ArrayList<String[]> wordList = new ArrayList<String[]>();
        String[] toAdd = {"a", "un(e)"};
        wordList.add(toAdd);
        String[] toAdd1 = {"are", "être"};
        wordList.add(toAdd1);
        String[] toAdd2 = {"black", "noir(e)"};
        wordList.add(toAdd2);
        String word = "black";
        String translate = TextManipulator.translate(word, wordList);
        if (!(translate.equals("noir(e)"))) {
            error = true;
            System.out.println("testMatchCase 1: translate=" + translate + " expected noir(e)");
        }
        word = "Black";
        translate = TextManipulator.translate(word, wordList);
        if (!(translate.equals("Noir(e)"))) {
            error = true;
            System.out.println("testMatchCase 2: translate=" + translate + " expected Noir(e)");
        }
        word = "ARE";
        translate = TextManipulator.translate(word, wordList);
        if (!(translate.equals("ÊTRe"))) {
            error = true;
            System.out.println("testMatchCase 3: translate=" + translate + " expected ÊTRe");
        }

        if (error) {
            System.out.println("testTranslate: failed");
        } else {
            System.out.println("testTranslate: passed");
        }

    }

    /**
     * This runs some tests on the pigLatin method. 
     * 1.The test checks to see if a word starting with
     * a consonant translates to piglatin correctly and matches case 
     * 2.The test checks to see if a
     * word that starts with a vowel translates to piglatin correctly and matches case 
     * 3.The test
     * checks to see if a word that starts with a y translates to piglatin correctly and matches
     * case
     */
    private static void testPigLatin() {
        boolean error = false;
        String original = "Flower";
        String pigLatin = TextManipulator.pigLatin(original);
        if (!(pigLatin.equals("Owerflay"))) {
            error = true;
            System.out.println("testPigLatin 1: pigLatin=" + pigLatin + " expected Lowerfay");
        }
        original = "ApPlE";
        pigLatin = TextManipulator.pigLatin(original);
        if (!(pigLatin.equals("ApPlEway"))) {
            error = true;
            System.out.println("testPigLatin 2: pigLatin=" + pigLatin + " expected ApPlEway");
        }
        original = "your";
        pigLatin = TextManipulator.pigLatin(original);
        if (!(pigLatin.equals("yourway"))) {
            error = true;
            System.out.println("testPigLatin 3: pigLatin=" + pigLatin + " expected yourway");
        }
        original = "hello world''";
        pigLatin = TextManipulator.pigLatin(original);
        if (!(pigLatin.equals("ello world''hay"))) {
            error = true;
            System.out
                .println("testPigLatin 3: pigLatin=" + pigLatin + " expected ello world''hay");
        }
        if (error) {
            System.out.println("testpigLatin: failed");
        } else {
            System.out.println("testpigLatin: passed");
        }
    }

    /**
     * This runs some tests on the matchCase method. 
     * 1.The test checks to see if the last item in
     * the list is now in the front
     * 2.The test checks to see if the middle item is in the middle of
     * the list 
     * 3.The test checks to see if the first item in the list is now last 
     * 4.The test checks
     * to see if the last item in the list is now in the front 
     * 5.The test checks to see if the 2
     * middle items changed place 
     * 6.The test checks to see if the 2 middle items changed place 
     * 7.The
     * test checks to see if the first item in the list is now last
     */
    private static void testReverseLine() {
        boolean error = false;
        ArrayList<String> wordList = new ArrayList<String>();
        String toAdd = "a";
        wordList.add(toAdd);
        String toAdd1 = "are";
        wordList.add(toAdd1);
        String toAdd2 = "black";
        wordList.add(toAdd2);

        ArrayList<String> reverse = TextManipulator.reverse(wordList);
        if (!(reverse.get(0).equals("black"))) {
            error = true;
            System.out.println("testReverse 1: reverse=" + reverse + " expected black");
        }

        if (!(reverse.get(1).equals("are"))) {
            error = true;
            System.out.println("testReverse 2: reverse=" + reverse + " expected are");
        }

        if (!(reverse.get(2).equals("a"))) {
            error = true;
            System.out.println("testReverse 3: reverse=" + reverse + " expected a");
        }

        String toAdd3 = "red";
        wordList.add(toAdd3);

        reverse = TextManipulator.reverse(wordList);
        if (!(reverse.get(0).equals("red"))) {
            error = true;
            System.out.println("testReverse 4: reverse=" + reverse + " expected red");
        }

        if (!(reverse.get(1).equals("black"))) {
            error = true;
            System.out.println("testReverse 5: reverse=" + reverse + " expected black");
        }

        if (!(reverse.get(2).equals("are"))) {
            error = true;
            System.out.println("testReverse 6: reverse=" + reverse + " expected are");
        }
        if (!(reverse.get(3).equals("a"))) {
            error = true;
            System.out.println("testReverse 6: reverse=" + reverse + " expected a");
        }
        if (error) {
            System.out.println("testReverseLine: failed");
        } else {
            System.out.println("testReverseLine: passed");
        }
    }

    /**
     * This runs some tests on the matchCase method. 
     * 1.The test checks to see if flower is reversed
     * correctly and match case 
     * 2.The test checks to see if the flower is reversed correctly and
     * match case 
     * 3.The test checks to see if the punctuation reverses correctly
     * 4.Test from zybooks
     */
    private static void testReverse() {
        boolean error = false;
        String original = "Flower";
        String reverse = TextManipulator.reverse(original);
        if (!(reverse.equals("Rewolf"))) {
            error = true;
            System.out.println("testReverse 1: reverse=" + reverse + " expected rewolf");
        }
        original = "ApPlE";
        reverse = TextManipulator.reverse(original);
        if (!(reverse.equals("ElPpA"))) {
            error = true;
            System.out.println("testReverse 2: reverse=" + reverse + " expected ElPpA");
        }
        original = "you(r)";
        reverse = TextManipulator.reverse(original);
        if (!(reverse.equals(")r(uoy"))) {
            error = true;
            System.out.println("testReverse 3: reverse=" + reverse + " expected )r(uoy");
        }
        original = "ThIsisAtEsT";
        reverse = TextManipulator.reverse(original);
        if (!(reverse.equals("TsEtasIsIhT"))) {
            error = true;
            System.out.println("testReverse 4: reverse=" + reverse + " expected TsEtasIsIhT");
        }
        if (error) {
            System.out.println("testReverse: failed");
        } else {
            System.out.println("testReverse: passed");
        }
    }
    /**
     * This runs some tests on the matchCase method. 
     * 1.The test checks to see if each word in modFileBy line is 
     * translated correctly and case matches
     * 2.The test checks to see if each word in modFileBy line is 
     * translated and piglatined correctly and case matches
     * 3.The test checks to see if each word in modFileBy line is 
     * translated piglatined and reversed correctly and case matches
     * 4.The test checks to see if each word in modFileBy line is 
     * translated piglatined and reversed correctly and case matches 
     * and the words are in reverse order
     */
    private static void testManipulate() {
        boolean error = false;
        ArrayList<ArrayList<String>> fileByLine = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> modFileByLine = new ArrayList<ArrayList<String>>();
        ArrayList<String> wordList = new ArrayList<String>();
        ArrayList<String> wordList2 = new ArrayList<String>();
        ArrayList<String[]> dict = new ArrayList<String[]>();
        String toAdd = "One";
        wordList.add(toAdd);
        String toAdd1 = "Fish";
        wordList.add(toAdd1);
        fileByLine.add(wordList);
        String toAdd2 = "Two";
        wordList2.add(toAdd2);
        String toAdd3 = "Fish";
        wordList2.add(toAdd3);
        fileByLine.add(wordList2);
        boolean[] modFlags = new boolean[Config.NUM_MODS];
        modFlags[Config.MOD_TRANS] = true;
        String[] trans1 = {"One", "un(e)"};
        String[] trans2 = {"Fish", "poisson"};
        String[] trans3 = {"Two", "deux"};
        dict.add(trans1);
        dict.add(trans2);
        dict.add(trans3);


        modFileByLine = TextManipulator.manipulate(fileByLine, dict, modFlags);

        if (!modFileByLine.get(0).get(0).equals("Un(e)")) {
            error = true;
            System.out.println(
                "testManipulate 1.1: returned " + modFileByLine.get(0).get(0) + " expected Un(e)");
        }
        if (!modFileByLine.get(0).get(1).equals("Poisson")) {
            error = true;
            System.out.println("testManipulate 1.2: returned " + modFileByLine.get(0).get(1)
                + " expected Poisson");
        }
        if (!modFileByLine.get(1).get(0).equals("Deux")) {
            error = true;
            System.out.println(
                "testManipulate 1.3: returned " + modFileByLine.get(1).get(0) + " expected Deux");
        }
        if (!modFileByLine.get(1).get(1).equals("Poisson")) {
            error = true;
            System.out.println("testManipulate 1.4: returned " + modFileByLine.get(1).get(1)
                + " expected Poisson");
        }


        modFlags[Config.MOD_PIG] = true;
        modFileByLine = TextManipulator.manipulate(fileByLine, dict, modFlags);

        if (!modFileByLine.get(0).get(0).equals("Un(e)way")) {
            error = true;
            System.out.println("testManipulate 2.1: returned " + modFileByLine.get(0).get(0)
                + " expected Un(e)way");
        }
        if (!modFileByLine.get(0).get(1).equals("Oissonpay")) {
            error = true;
            System.out.println("testManipulate 2.2: returned " + modFileByLine.get(0).get(1)
                + " expected Oissonpay");
        }
        if (!modFileByLine.get(1).get(0).equals("Euxday")) {
            error = true;
            System.out.println(
                "testManipulate 2.3: returned " + modFileByLine.get(1).get(0) + " expected Euxday");
        }
        if (!modFileByLine.get(1).get(1).equals("Oissonpay")) {
            error = true;
            System.out.println("testManipulate 2.4: returned " + modFileByLine.get(1).get(1)
                + " expected Oissonpay");
        }

        modFlags[Config.MOD_REV_WORD] = true;
        modFileByLine = TextManipulator.manipulate(fileByLine, dict, modFlags);

        if (!modFileByLine.get(0).get(0).equals("Yaw)e(nu")) {
            error = true;
            System.out.println("testManipulate 3.1: returned " + modFileByLine.get(0).get(0)
                + " expected Yaw)e(nu");
        }
        if (!modFileByLine.get(0).get(1).equals("Yapnossio")) {
            error = true;
            System.out.println("testManipulate 3.2: returned " + modFileByLine.get(0).get(1)
                + " expected Yapnossio");
        }
        if (!modFileByLine.get(1).get(0).equals("Yadxue")) {
            error = true;
            System.out.println(
                "testManipulate 3.3: returned " + modFileByLine.get(1).get(0) + " expected Yadxue");
        }
        if (!modFileByLine.get(1).get(1).equals("Yapnossio")) {
            error = true;
            System.out.println("testManipulate 3.4: returned " + modFileByLine.get(1).get(1)
                + " expected Yapnossio");
        }


        modFlags[Config.MOD_REV_LINE] = true;
        modFileByLine = TextManipulator.manipulate(fileByLine, dict, modFlags);

        if (!modFileByLine.get(0).get(0).equals("Yapnossio")) {
            error = true;
            System.out.println("testManipulate 3.1: returned " + modFileByLine.get(0).get(0)
                + " expected Yapnossio ");
        }
        if (!modFileByLine.get(0).get(1).equals("Yaw)e(nu")) {
            error = true;
            System.out.println("testManipulate 3.2: returned " + modFileByLine.get(0).get(1)
                + " expected Yaw)e(nu");
        }
        if (!modFileByLine.get(1).get(0).equals("Yapnossio")) {
            error = true;
            System.out.println("testManipulate 3.3: returned " + modFileByLine.get(1).get(0)
                + " expected Yapnossio");
        }
        if (!modFileByLine.get(1).get(1).equals("Yadxue")) {
            error = true;
            System.out.println(
                "testManipulate 3.4: returned " + modFileByLine.get(1).get(1) + " expected Yadxue");
        }

        if (error) {
            System.out.println("testManipulate: failed");
        } else {
            System.out.println("testManipulate: passed");
        }
    }
}

