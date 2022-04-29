package com.jank;

import java.io.*;
import java.util.*;

public class Analyzer {

    private ArrayList<ArrayList<String>> emails;
    private ArrayList<Results> results;

    public Analyzer(ArrayList<ArrayList<String>> emails, ArrayList<String> names) {
        this.emails = emails;
        results = new ArrayList<>();

        // Populate w/ empty results
        for (int i = 0; i < emails.size(); i++) {
            results.add(new Results(names.get(i)));
        }
    }

    public int runAnalysis() {
        for (int i = 0; i < emails.size(); i++) {
            // Method to call the different things to analyze the emails
            results.get(i).setWordCounts(wordCounts(emails.get(i)));
            results.get(i).setGramCounts2(nCharacterGram(2, emails.get(i)));
            results.get(i).setGramCounts3(nCharacterGram(3, emails.get(i)));

            results.get(i).setWordGram2(nWordGram(2, emails.get(i)));
            results.get(i).setWordGram3(nWordGram(3, emails.get(i)));

            results.get(i).setPuncCounts(puncCounts(emails.get(i)));

            results.get(i).setContentLenData(avgParser(emails.get(i)));

            try {
                results.get(i).setUncommonRatio(uncommonWords(emails.get(i)));
            } catch (IOException e) {

            }

            results.get(i).setUniqueWords(uncommonWordUse(emails.get(i)));

            results.get(i).printToFile();
        }

        return 0;
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
        res = res.replaceAll("\r", " ");
        res = res.toLowerCase();

        return res;
    }

    // Sentences

    private double[] avgParser(ArrayList<String> emails) {
        double returnVal[] = new double[5];

        ArrayList<Integer> wordLen = new ArrayList<>();
        ArrayList<Integer> charLen = new ArrayList<>();
        ArrayList<Integer> wordCharLen = new ArrayList<>();
        double totalWords = 0, totalChars = 0;

        String str = "This is a test. Hopefully this works the way I want. Or else I will cry a lot.";

        // for each email in the email list
        for (String email : emails) {
            Scanner sc = new Scanner(email);
            sc.useDelimiter(" |\\n");

            int words = 0, chars = 0;

            while (sc.hasNext()) {
                String tmp = sc.next();

                // If tmp ends w/ a period, question mark, or an exclamation point end the sentence counting
                if (tmp.length() != 0 && (tmp.contains(".") || tmp.contains("?") || tmp.contains("!"))) {//(tmp.charAt(tmp.length() - 1) == '.' || tmp.charAt(tmp.length() - 1) == '?')) {
                    wordLen.add(++words);

                    chars += tmp.length();
                    charLen.add(chars);
                    wordCharLen.add(tmp.length() - 1);

                    totalChars += chars;
                    totalWords += words;

                    words = 0;
                    chars = 0;
                }

                // If we are reading a sentence again
                if (!tmp.equals("")) {
                    words++;
                    chars += tmp.length() + 1; // Plus one for the space after
                    wordCharLen.add(tmp.length());
                }
            }
        }

        returnVal[0] = avgHelper(wordLen); // words per sentence
        returnVal[1] = avgHelper(charLen); // total characters in sentence
        returnVal[2] = avgHelper(wordCharLen); // average characters per word
        returnVal[3] = totalWords / emails.size(); // average words / email
        returnVal[4] = totalChars / emails.size(); // average chars per email

        return returnVal;
    }

    private double avgHelper(ArrayList<Integer> ints) {
        double sum = 0;

        for (int i = 0; i < ints.size(); i++) {
            sum += ints.get(i);
        }

        return sum / ints.size();
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
            BufferedReader reader = new BufferedReader(new FileReader("JANK/src/google-10000-english-usa.txt"));
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

    private HashMap<String, Integer> uncommonWordUse(ArrayList<String> emails) {
        HashMap<String, Integer> uncommonWords = new HashMap<>();
        ArrayList<String> commons = new ArrayList<>();

        try {
            // Read in all of the common words
            BufferedReader reader = new BufferedReader(new FileReader("JANK/src/google-10000-english-usa.txt"));
            String line = reader.readLine();

            while (line != null) {
                commons.add(line);
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
                if (!commons.contains(s)) {
                    if (uncommonWords.containsKey(s) && !s.equals("")) {
                        uncommonWords.put(s, uncommonWords.get(s) + 1);
                    } else {
                        uncommonWords.put(s, 1);
                    }
                }
            }
        }

        return uncommonWords;
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

    private ArrayList<String> signOffs(ArrayList<String> emails) { // Hunter
        return null;
    }

    // Grapheme count (https://www.dyslexia-reading-well.com/44-phonemes-in-english.html)
    //ToDo: guess
}
