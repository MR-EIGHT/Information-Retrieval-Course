package com.Eight.IR.search;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.*;

public class SimpleSearch {


    public static void main(String[] args) throws IOException, URISyntaxException {
        // TODO get .txt files in resources folder nad put it in texts


        List<String> bookNames = new ArrayList<>();
        File[] files = new File(SimpleSearch.class.getResource("/txt").toURI()).listFiles();
        if (files == null) {
            throw new NullPointerException("There are no files in resources/txt path!");
        }

        for (File file : files) {
            String name = file.getName();
            bookNames.add(name);
        }


        HashMap<String, String> books = new HashMap<>();

        for (String name : bookNames) {
            InputStream stream = SimpleSearch.class.getResource("/txt/" + name).openStream();
            String fullText = new String(stream.readAllBytes()).toLowerCase();
            books.put(name, fullText);
        }

        while (true) {
            System.out.println("Enter your query: ");
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine().toLowerCase();
            if (line.equals("quit"))
                return;

            for (Map.Entry<String, String> entry : books.entrySet()) {
                if (entry.getValue().contains(line))
                    System.out.printf("Match: %s\n", entry.getKey());
            }
        }
    }

}
