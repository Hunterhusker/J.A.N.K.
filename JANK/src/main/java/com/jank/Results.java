package com.jank;

import java.util.HashMap;

public class Results {

    // Add vars
    private HashMap<String, Integer> wordCounts, gramCounts2, gramCounts3, wordGram2, wordGram3, puncCounts;
    private double uncommonRatio;

    public Results() {
        // blank for now
    }

    /// ToDo: Add getters/setters for everything

    public void printToFile() {
        // Makes the file for the person
    }

    public HashMap<String, Integer> getWordCounts() {
        return wordCounts;
    }

    public void setWordCounts(HashMap<String, Integer> wordCounts) {
        this.wordCounts = wordCounts;
    }

    public HashMap<String, Integer> getGramCounts2() {
        return gramCounts2;
    }

    public void setGramCounts2(HashMap<String, Integer> gramCounts2) {
        this.gramCounts2 = gramCounts2;
    }

    public HashMap<String, Integer> getGramCounts3() {
        return gramCounts3;
    }

    public void setGramCounts3(HashMap<String, Integer> gramCounts3) {
        this.gramCounts3 = gramCounts3;
    }

    public HashMap<String, Integer> getWordGram2() {
        return wordGram2;
    }

    public void setWordGram2(HashMap<String, Integer> wordGram2) {
        this.wordGram2 = wordGram2;
    }

    public HashMap<String, Integer> getWordGram3() {
        return wordGram3;
    }

    public void setWordGram3(HashMap<String, Integer> wordGram3) {
        this.wordGram3 = wordGram3;
    }

    public HashMap<String, Integer> getPuncCounts() {
        return puncCounts;
    }

    public void setPuncCounts(HashMap<String, Integer> puncCounts) {
        this.puncCounts = puncCounts;
    }

    public double getUncommonRatio() {
        return uncommonRatio;
    }

    public void setUncommonRatio(double uncommonRatio) {
        this.uncommonRatio = uncommonRatio;
    }
}
