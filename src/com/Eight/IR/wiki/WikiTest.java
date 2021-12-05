package com.Eight.IR.wiki;

import com.Eight.IR.index.InMemIndex;
import com.Eight.IR.invertedIndex.Document;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class WikiTest {
    public static void main(String[] args) throws FileNotFoundException, XMLStreamException {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        File file = new File("a.xml");
        XMLStreamReader streamReader = inputFactory.createXMLStreamReader(new FileInputStream(file));
        InMemIndex memIndex = new InMemIndex();
        String title = null;
        String abs = null;
        int counter = 0;
        long now = System.currentTimeMillis();


        while (streamReader.hasNext()) {
            if (streamReader.isEndElement() && streamReader.getLocalName().equals("doc")) {
                Document doc = new Document(title, abs);
                memIndex.add(doc);
                if (counter++ % 10000 == 0)
                    System.out.println(counter);
            } else if (streamReader.isStartElement()) {
                switch (streamReader.getLocalName()) {
                    case "title": {
                        title = streamReader.getElementText();
                        break;
                    }
                    case "abstract": {
                        abs = streamReader.getElementText();
                        break;
                    }
                }
            }


        }
        System.out.println("took:" + (System.currentTimeMillis() - now) / 1000);


        while (true) {
            System.out.println("Enter your query: ");
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine().toLowerCase();
            if (line.equals("quit"))
                return;

            ArrayList<Document> result = new ArrayList<>(memIndex.query(line));
            for (int i = 0; i < Math.min(result.size(), 10); i++) {
                System.out.printf("%d- %s\t%s\n", i, result.get(i).getName(), result.get(i).getBody());
            }
            System.out.println("######################################");
        }


    }

}
