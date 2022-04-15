package com.jank;

import java.io.*;
import java.util.*;

public class Analyzer {

    private ArrayList<ArrayList<String>> emails;
    private ArrayList<Results> results;

    public Analyzer(ArrayList<ArrayList<String>> emails) {
        this.emails = emails;
        results = new ArrayList<>();

        // Populate w/ empty results
        for (int i = 0; i < emails.size(); i++) {
            results.add(new Results());
        }
    }

    public int runAnalysis() {
        // Method to call the different things to analyze the emails
        //results.get(0).setWordCounts(wordCounts(emails.get(0)));
        //results.get(0).setGramCounts2(nCharacterGram(2, emails.get(0)));
        //results.get(0).setGramCounts3(nCharacterGram(3, emails.get(0)));

        //results.get(0).setWordGram2(nWordGram(2, emails.get(0)));
        //results.get(0).setWordGram3(nWordGram(3, emails.get(0)));

        //results.get(0).setPuncCounts(puncCounts(emails.get(0)));

        try {
            results.get(0).setUncommonRatio(uncommonWords(emails.get(0)));
        } catch (IOException e) {

        }

        printAnalysis(); // output to the file
        return 0;
    }

    private void printAnalysis() {

        // Sort and output a hashmap
        //sortAndOutputHashMap(results.get(0).getWordCounts());

        //sortAndOutputHashMap(results.get(0).getGramCounts2());
        //sortAndOutputHashMap(results.get(0).getGramCounts3());

        //sortAndOutputHashMap(results.get(0).getWordGram2());
        //sortAndOutputHashMap(results.get(0).getWordGram3());

        //sortAndOutputHashMap(results.get(0).getPuncCounts());

        System.out.println("The ratio: " + results.get(0).getUncommonRatio());

        //        for (int i = 0; i < emails.size(); i++) {
//            results.get(i).printToFile();
//        }
    }

    private void sortAndOutputHashMap(HashMap<String, Integer> hm) {
        List<Map.Entry<String, Integer>> sortedWordList = sortHashMap(hm);
        for (Map.Entry<String, Integer> word : sortedWordList) {
            System.out.println(word.getKey() + " | " + word.getValue());
        }
    }

    private List<Map.Entry<String, Integer>> sortHashMap(HashMap<String, Integer> map) {
        List<Map.Entry<String, Integer>> sortedList = new LinkedList<>(map.entrySet());

        // Sort the linked list
        Collections.sort(sortedList, (object1, object2) -> (object1.getValue()).compareTo(object2.getValue()));

        return sortedList;
    }

    /**
     * Given an email, removes punctuation, hidden characters, and other junk
     * @param email
     * @return
     */
    private String cleanEmail(String email) {
        String res;
        // Bye bye punctiation
        res = email.replaceAll("\\p{Punct}", "");
        res = res.replaceAll("\n", " ");
        res = res.replaceAll("\t", " ");
        res = res.toLowerCase();

        return res;
    }

    // Sentences
    private double avgSentenceLen(ArrayList<String> emails) { // Hunter
        // count len

        return 0;
    }

    private double avgSentenceCharLen(ArrayList<String> emails) { // Hunter
        return 0;
    }

    // Words
    private double avgWordLen(ArrayList<String> emails) { // Hunter
        return 0;
    }

    // DONE
    // uncommon word count / common word count: Ratio of uncommon to common
    private double uncommonWords(ArrayList<String> emails) throws IOException { // Robbie TODO
        HashMap<String, Integer> commonWords = new HashMap<>();
        double uncommon = 0;
        double common = 0;
        double total = 0;

        try {
            // Read in all of the things
            BufferedReader reader = new BufferedReader(new FileReader("src/resources/1-1000.txt"));
            String line = reader.readLine();

            while (line != null) {
                commonWords.put(line, 0);
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }

        for (String email : emails) {
            String newEmail = cleanEmail(email);

            String[] split = newEmail.split(" ");

            for (String s : split) {
                if (commonWords.containsKey(s)) {
                    common++;
                } else {
                    uncommon++;
                }
                total++;
            }
        }


        return (uncommon / total);
    }

    // DONE
    private HashMap<String, Integer> wordCounts(ArrayList<String> emails) { // Robbie https://gist.github.com/deekayen/4148741
        HashMap<String, Integer> countMap = new HashMap<>();

        for (String email : emails) {
            String newEmail = cleanEmail(email);

            // Split the email into words based on the spaces
            String[] words = newEmail.split(" ");

            // For every word, count them by putting them in the hashmap and updating their value
            for (String word : words) {
                if (!word.equals("")) {
                    if (countMap.containsKey(word)) {
                        countMap.put(word, countMap.get(word) + 1);
                    } else {
                        // Otherwise and the word to the word list
                        countMap.put(word, 1);
                    }
                }
            }

        }

        return countMap;
    }

    // N-grams DONE
    private HashMap<String, Integer> nCharacterGram(int n, ArrayList<String> emails) { // Robbie
        HashMap<String, Integer> resultMap = new HashMap<>();

        for (String email : emails) {
            String newEmail = cleanEmail(email);

            char[] buffer = new char[n];

            for (int i = 0; i < newEmail.length() - (n - 1); i++) {

                // Fill the buffer with the characters
                for (int x = 0; x < n; x++) {
                    buffer[x] = newEmail.charAt(i + x);
                }

                boolean noGood = false;

                // If we find a no good character, mark it
                for (int x = 0; x < buffer.length; x++) {
                    if (buffer[x] == '\n' || buffer[x] == ' ' || buffer[x] == '\t') {
                        noGood = true;
                        break;
                    }
                }

                if (!noGood) {
                    String gram = "";

                    // Construct a string from the buffer
                    for (int x = 0; x < buffer.length; x++) {
                        gram += buffer[x];
                    }

                    if (resultMap.containsKey(gram)) {
                        resultMap.put(gram, resultMap.get(gram) + 1);
                    } else {
                        // Otherwise and the word to the word list
                        resultMap.put(gram, 1);
                    }
                }


            }
        }

        return resultMap;
    }

    // DONE
    private HashMap<String, Integer> nWordGram(int n, ArrayList<String> emails) { // Robbie
        HashMap<String, Integer> resultMap = new HashMap<>();

        for (String email : emails) {
            String newEmail = cleanEmail(email);

            String[] buffer = new String[n];

            String[] splitEmail = newEmail.split(" ");

            for (int i = 0; i < splitEmail.length - (n - 1); i++) {

                boolean noGood = false;
                // Fill the buffer with n strings
                for (int x = 0; x < n; x++) {
                    buffer[x] = splitEmail[i + x];
                    if (buffer[x].equals("") || buffer[x].equals("\t") || buffer[x].equals("\n")) {
                        noGood = true;
                    }
                }


                String gram = "";
                if (!noGood) {
                    // Construct a string from the buffer
                    for (int x = 0; x < buffer.length; x++) {
                        gram += buffer[x];
                        if (x != buffer.length - 1) {
                            gram += " ";
                        }
                    }

                    if (resultMap.containsKey(gram)) {
                        resultMap.put(gram, resultMap.get(gram) + 1);
                    } else {
                        // Otherwise and the word to the word list
                        resultMap.put(gram, 1);
                    }
                }

            }

        }
        return resultMap;
    }

    // Punctuation
    // DONE
    private HashMap<String, Integer> puncCounts(ArrayList<String> emails) { // Robbie
        HashMap<String, Integer> resultMap = new HashMap<>();

        for (String email : emails) {
            // 14 punctuations
            for (int i = 0; i < email.length(); i++) {

                char curChar = email.charAt(i);

                if (curChar == '!' || curChar == '.' || curChar == '?' || curChar == ',' || curChar == ';' ||
                        curChar == ':' || curChar == '-' || curChar == '[' || curChar == ']' || curChar == '(' ||
                        curChar == ')' || curChar == '{' || curChar == '}' || curChar == '\'' || curChar == '\"') {

                    String key = "" + curChar;

                    if (resultMap.containsKey(key)) {
                        resultMap.put(key, resultMap.get(key) + 1);
                    } else {
                        // Otherwise and the word to the word list
                        resultMap.put(key, 1);
                    }
                }
            }
        }


        return resultMap;
    }

    // Message
    private int messageLen(ArrayList<String> emails) { // Hunter
        return 0;
    }

    private int messageCharLen(ArrayList<String> emails) { // Hunter
        return 0;
    }

    private ArrayList<String> signOffs(ArrayList<String> emails) { // Hunter
        return null;
    }

    // Grapheme count (https://www.dyslexia-reading-well.com/44-phonemes-in-english.html)
    //ToDo: guess
}
