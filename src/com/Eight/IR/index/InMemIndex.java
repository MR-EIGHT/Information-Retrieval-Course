package com.Eight.IR.index;

import com.Eight.IR.invertedIndex.Document;
import com.Eight.IR.preprocess.Normalizer;
import com.Eight.IR.preprocess.Tokenizer;

import java.util.*;

public class InMemIndex {
    private Map<String, Set<Document>> table = new HashMap<>();

    public void add(Document doc) {
        String normalized = Normalizer.normalize(doc.getBody());
        List<String> tokens = Tokenizer.tokenizeByBlackList(normalized);
        for (String token : tokens) {
            table.putIfAbsent(token, new HashSet<>());
            table.get(token).add(doc);
        }
    }

    public Set<Document> query(String q) {
        String normalized = Normalizer.normalize(q);
        List<String> tokens = Tokenizer.tokenizeByBlackList(normalized);
        Set<Document> result = new HashSet<>();
        if (tokens.isEmpty())
            return result;
        result = table.getOrDefault(tokens.get(0), new HashSet<>());
        for (int i = 1; i < tokens.size(); i++) {
            result.retainAll(table.getOrDefault(tokens.get(i), new HashSet<>()));
        }
        return result;
    }

}
