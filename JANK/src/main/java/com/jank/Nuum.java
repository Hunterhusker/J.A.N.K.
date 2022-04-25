package com.jank;

public class Nuum {
    String word1;
    String word2;
    String word3;
    String word4;

    int count1, count2, count3, count4;

    public Nuum() {
        this.word1 = "";
        this.word2 = "";
        this.word3 = "";
        this.word4 = "";
        this.count1 = 0;
        this.count2 = 0;
        this.count3 = 0;
        this.count4 = 0;
    }

    public int getCount(int i) {
        switch (i) {
            case 0:
                return count1;
            case 1:
                return count2;
            case 2:
                return count3;
            case 3:
                return count4;
        }

        return -1;
    }

    public void setWord(int i, String word) {
        switch (i) {
            case 0:
                word1 = word;
                return;
            case 1:
                word2 = word;
                return;
            case 2:
                word3 = word;
                return;
            case 3:
                word4 = word;
                return;
        }
    }

    public void setCount(int i, int count) {
        switch (i) {
            case 0:
                count1 = count;
                return;
            case 1:
                count2 = count;
                return;
            case 2:
                count3 = count;
                return;
            case 3:
                count4 = count;
                return;
        }
    }
}
