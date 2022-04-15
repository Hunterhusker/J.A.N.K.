package com.jank;

import java.util.ArrayList;
import java.util.HashMap;

public class Analyzer {

    private ArrayList<ArrayList<String>> emails;
    private ArrayList<Results> results;

    public Analyzer(ArrayList<ArrayList<String>> emails) {
        this.emails = emails;

        // Populate w/ empty results
        for (int i = 0; i < emails.size(); i++) {
            results.add(new Results());
        }
    }

    public int runAnalysis() {
        // Method to call the different things to analyze the emails

        printAnalysis(); // output to the file
        return 0;
    }

    private void printAnalysis() {
        for (int i = 0; i < emails.size(); i++) {
            results.get(i).printToFile();
        }
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

    // uncommon word count / common word count: Ratio of uncommon to common
    private double uncommonWords(ArrayList<String> emails) { // Robbie
        return 0;
    }

    private HashMap<String, Integer> wordCounts(ArrayList<String> emails) { // Robbie https://gist.github.com/deekayen/4148741
        return null;
    }

    // N-grams
    private HashMap<String, Integer> nCharacterGram(int n, ArrayList<String> emails) { // Robbie
        return null;
    }

    private HashMap<String, Integer> nWordGram(int n, ArrayList<String> emails) { // Robbie
        return null;
    }

    // Punctuation
    private HashMap<String, Integer> puncCounts(ArrayList<String> emails) { // Robbie
        return null;
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
