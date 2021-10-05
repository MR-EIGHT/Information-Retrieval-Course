package com.Eight.IR.invertedIndex;

import java.util.concurrent.atomic.AtomicInteger;

public class Document {
    private static final AtomicInteger lastId = new AtomicInteger(0);

    private final int docId;
    private final String name;
    private final String body;

    public Document(String name, String body) {
        this.docId = lastId.incrementAndGet();
        this.name = name;
        this.body = body;
    }

    public static AtomicInteger getLastId() {
        return lastId;
    }

    public int getDocId() {
        return docId;
    }

    public String getName() {
        return name;
    }

    public String getBody() {
        return body;
    }


    public static void main(String[] args) {

    }
}
