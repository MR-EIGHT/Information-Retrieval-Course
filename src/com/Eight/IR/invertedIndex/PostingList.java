package com.Eight.IR.invertedIndex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PostingList {
    private List<Integer> docIds = new ArrayList<>();

    public void add(int id){
        docIds.add(id);
    }

    public void sort(){
        Collections.sort(docIds);
    }

    public PostingList and(PostingList list){
        int i= 0;
        int j = 0;

    }

}
