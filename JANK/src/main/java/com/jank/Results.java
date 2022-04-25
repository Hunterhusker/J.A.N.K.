package com.jank;

import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class Results {

    // Add vars
    private HashMap<String, Integer> wordCounts, gramCounts2, gramCounts3, wordGram2, wordGram3, puncCounts;
    private double uncommonRatio;
    private double avgSentenceWordLen;
    private double avgSentenceCharLen;
    private double avgWordLen;
    private double avgMessageWordLen;
    private String person;

    public double getAvgSentenceWordLen() {
        return avgSentenceWordLen;
    }

    public double getAvgSentenceCharLen() {
        return avgSentenceCharLen;
    }

    public double getAvgWordLen() {
        return avgWordLen;
    }

    public double getAvgMessageWordLen() {
        return avgMessageWordLen;
    }

    public double getAvgMessageCharLen() {
        return avgMessageCharLen;
    }

    private double avgMessageCharLen;

    public Results(String name) {
       person = name;
    }

    /// ToDo: Add getters/setters for everything

    public void printToFile() {
        // Makes the file for the person
        File outFile = new File(person + ".txt");

        try {
            FileWriter writer = new FileWriter(outFile.getName());
            writer.write("Average Sentence Word Length: " + avgSentenceWordLen + "\n");
            writer.write("Average Sentence Char Length: " + avgSentenceCharLen + "\n");
            writer.write("Average Word Length: " + avgWordLen + "\n");
            writer.write("Average Message Word Length: " + avgMessageWordLen + "\n");
            writer.write("Uncommon to Common Ratio: " + uncommonRatio + "\n\n");

            writer.write("\n\nWord Counts: -------\n");
            List<Map.Entry<String, Integer>> sortedWordList = sortHashMap(wordCounts);

            int stopper = sortedWordList.size();

            for (int x = sortedWordList.size() - 1; x > stopper - 101 && x > -1; x--) {
                Map.Entry<String, Integer> word = sortedWordList.get(x);
                writer.write(word + "\n");
            }

            //////////////////////
            writer.write("\n\n2-Grams -------\n");
            sortedWordList = sortHashMap(gramCounts2);

            stopper = sortedWordList.size();

            for (int x = sortedWordList.size() - 1; x > stopper - 181 && x > -1; x--) {
                Map.Entry<String, Integer> word = sortedWordList.get(x);
                writer.write(word + "\n");
            }

            /////////////////////////
            writer.write("\n\n3-Grams -------\n");
            sortedWordList = sortHashMap(gramCounts3);

            stopper = sortedWordList.size();

            for (int x = sortedWordList.size() - 1; x > stopper - 181 && x > -1; x--) {
                Map.Entry<String, Integer> word = sortedWordList.get(x);
                writer.write(word + "\n");
            }

            /////////////////////////
            writer.write("\n\n2-Grams Words-------\n");
            sortedWordList = sortHashMap(wordGram2);

            stopper = sortedWordList.size();

            for (int x = sortedWordList.size() - 1; x > stopper - 181 && x > -1; x--) {
                Map.Entry<String, Integer> word = sortedWordList.get(x);
                writer.write(word + "\n");
            }

            /////////////////////////
            writer.write("\n\n3-Grams Words-------\n");
            sortedWordList = sortHashMap(wordGram3);

            stopper = sortedWordList.size();

            for (int x = sortedWordList.size() - 1; x > stopper - 181 && x > -1; x--) {
                Map.Entry<String, Integer> word = sortedWordList.get(x);
                writer.write(word + "\n");
            }

            /////////////////////////
            writer.write("\n\nPunctuation Counts-------\n");
            sortedWordList = sortHashMap(puncCounts);

            stopper = sortedWordList.size();

            for (int x = sortedWordList.size() - 1; x > stopper - 101 && x > -1; x--) {
                Map.Entry<String, Integer> word = sortedWordList.get(x);
                writer.write(word + "\n");
            }

            writer.close();

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private List<Map.Entry<String, Integer>> sortHashMap(HashMap<String, Integer> map) {
        List<Map.Entry<String, Integer>> sortedList = new LinkedList<>(map.entrySet());

        // Sort the linked list
        Collections.sort(sortedList, (object1, object2) -> (object1.getValue()).compareTo(object2.getValue()));

        return sortedList;
    }

    public void setContentLenData(double[] vals) {
        avgSentenceWordLen = vals[0];
        avgSentenceCharLen = vals[1];
        avgWordLen = vals[2];
        avgMessageWordLen = vals[3];
        avgMessageCharLen = vals[4];
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
