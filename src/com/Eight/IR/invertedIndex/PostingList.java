package com.Eight.IR.invertedIndex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PostingList {
    private List<Integer> docIds = new ArrayList<>();

    PostingList() {

    }

    public List<Integer> getDocIds() {
        return docIds;
    }

    PostingList(int... ids) {
        for (int id : ids) {
            docIds.add(id);
        }
    }



    public void add(int id) {
        docIds.add(id);
    }

    public void sort() {
        Collections.sort(docIds);
    }

    public int size() {
        return docIds.size();
    }

    @Override
    public String toString() {
        return Arrays.toString(docIds.toArray());
    }

    public PostingList and(PostingList otherList) {
        PostingList result = new PostingList();
        int i = 0, j = 0;

        while (i < size() && j < otherList.size()) {
            int a = docIds.get(i);
            int b = otherList.docIds.get(j);
            if (a == b) {
                result.add(a);
                i++;
                j++;
            } else if (a < b) {
                i++;
            } else j++;
        }
        return result;
    }


    public PostingList or(PostingList otherList) {
        PostingList result = new PostingList();
        int i = 0, j = 0;

        while (i < size() && j < otherList.size()) {
            int a = docIds.get(i);
            int b = otherList.docIds.get(j);


            if (a < b) {
                result.add(a);
                i++;
            } else if (b < a) {
                result.add(b);
                j++;
            } else {
                result.add(a);
                i++;
                j++;
            }


        }


        if (i < size())
            for (; i < size(); i++)
                result.add(docIds.get(i));


        else if (j < size())
            for (; j < otherList.size(); j++)
                result.add(otherList.docIds.get(j));


        return result;


    }
}
