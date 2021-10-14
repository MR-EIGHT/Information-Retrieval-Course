package com.Eight.IR.invertedIndex;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Search {


    public static void main(String[] args) throws IOException, URISyntaxException {


        List<String> bookNames = new ArrayList<>();

        DocumentStore store = new DocumentStore();
        InvertedIndex index = new InvertedIndex();

        File[] files = new File(Search.class.getResource("/txt").toURI()).listFiles();
        if (files == null) {
            throw new NullPointerException("There are no files in resources/txt path!");
        }

        for (File file : files) {
            String name = file.getName();
            bookNames.add(name);
        }


        for (String name : bookNames) {
            InputStream stream = Search.class.getResource("/txt/" + name).openStream();
            String body = new String(stream.readAllBytes()).toLowerCase();
            Document document = new Document(name,body);
            store.add(document);
            index.add(document);
        }

        while (true) {
            System.out.print("Enter your query: ");
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine().toLowerCase();
            if (line.equals("quit"))
                return;

           PostingList list = index.get(line);

            for (Integer docId : list.getDocIds()) {
                System.out.println(store.get(docId));
            }
            System.out.println();
        }
    }

}
