package com.Eight.IR.invertedIndex;

import java.util.Collection;
import java.util.HashMap;

public class DocumentStore {

    private HashMap<Integer, Document> docs = new HashMap<>();

    public void add(Document doc) {
        docs.put(doc.getDocId(), doc);
    }

    public Document get(int id) {
        return docs.get(id);
    }


    public Collection<Document> getAll() {
        return docs.values();
    }


}
