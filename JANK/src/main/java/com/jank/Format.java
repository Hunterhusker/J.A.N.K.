package com.jank;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Format {

    public static void main(String args[]) throws Exception{
        HashMap<String, Nuum> sortedStuff = new HashMap<>();
        ArrayList<String> person1 = new ArrayList<>();
        ArrayList<String> person2 = new ArrayList<>();
        ArrayList<String> person3 = new ArrayList<>();
        ArrayList<String> person4 = new ArrayList<>();

        // Read in all 4 files and get the word counts
        File file1 = new File("guzman-m.txt");
        File file2 = new File("holst-k.txt");
        File file3 = new File("lewis-a.txt");
        File file4 = new File("mims-thurston-p.txt");

        Scanner scan = new Scanner(file1);

        boolean done = false;

        while(scan.hasNext() && !done) {
            String line = scan.nextLine();

            if (line.equals("Word Counts: -------")) {
                for (int i = 0; i < 100; i++) {
                    String f = scan.nextLine();
                    person1.add(f);
                }
                done = true;
            }
        }
        scan.close();

        scan = new Scanner(file2);
        done = false;
        while(scan.hasNext() && !done) {
            String line = scan.nextLine();

            if (line.equals("Word Counts: -------")) {
                for (int i = 0; i < 100; i++) {
                    String f = scan.nextLine();
                    person2.add(f);
                }
                done = true;
            }
        }
        scan.close();


        scan = new Scanner(file3);
        done = false;
        while(scan.hasNext() && !done) {
            String line = scan.nextLine();

            if (line.equals("Word Counts: -------")) {
                for (int i = 0; i < 100; i++) {
                    String f = scan.nextLine();
                    person3.add(f);
                }
                done = true;
            }
        }
        scan.close();


        scan = new Scanner(file4);
        done = false;
        while(scan.hasNext() && !done) {
            String line = scan.nextLine();

            if (line.equals("Word Counts: -------")) {
                for (int i = 0; i < 100; i++) {
                    String f = scan.nextLine();
                    person4.add(f);
                }
                done = true;
            }
        }
        scan.close();


        // With all the emails...
        for (String word : person1) {
            String[] split = word.split("=");
            Nuum num = new Nuum();
            num.setCount(0, Integer.parseInt(split[1]));

            sortedStuff.put(split[0], num);
        }

        for (String word : person2) {
            String[] split = word.split("=");
            Nuum num;
            if (sortedStuff.containsKey(split[0])) {
                num = sortedStuff.get(split[0]);
                num.setCount(1, Integer.parseInt(split[1]));
            } else {
                num = new Nuum();
                num.setCount(1, Integer.parseInt(split[1]));
                sortedStuff.put(split[0], num);
            }

        }

        for (String word : person3) {
            String[] split = word.split("=");
            Nuum num;
            if (sortedStuff.containsKey(split[0])) {
                num = sortedStuff.get(split[0]);
                num.setCount(2, Integer.parseInt(split[1]));
            } else {
                num = new Nuum();
                num.setCount(2, Integer.parseInt(split[1]));
                sortedStuff.put(split[0], num);
            }
        }

        for (String word : person4) {
            String[] split = word.split("=");
            Nuum num;
            if (sortedStuff.containsKey(split[0])) {
                num = sortedStuff.get(split[0]);
                num.setCount(3, Integer.parseInt(split[1]));
            } else {
                num = new Nuum();
                num.setCount(3, Integer.parseInt(split[1]));
                sortedStuff.put(split[0], num);
            }
        }


        FileWriter writer = new FileWriter("NiceOutput.txt");

        for (Map.Entry<String, Nuum> entry : sortedStuff.entrySet()) {
            StringBuilder res = new StringBuilder();
            Nuum fff = entry.getValue();
            res.append(entry.getKey() + "," + fff.getCount(0) + "," + fff.getCount(1) + "," + fff.getCount(2) + "," + fff.getCount(3) + "\n");
            writer.write(res.toString());
        }

        writer.close();

    }

}

