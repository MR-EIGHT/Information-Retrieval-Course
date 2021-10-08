package com.Eight.IR.invertedIndex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PostingList {
    private List<Integer> docIds = new ArrayList<>();

    PostingList() {

    }

    PostingList(int... ids) {
        for (int id : ids) {
            docIds.add(id);
        }
    }

    public static void main(String[] args) {
        PostingList l1 = new PostingList(1, 3, 5, 8, 9);
        System.out.println(l1);
        PostingList l2 = new PostingList(0, 3, 8, 15, 20, 30);
        System.out.println(l2);
        System.out.println();
        System.out.println(l1.and(l2));
        System.out.println(l1.or(l2));


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
        int max = Math.max(size(), otherList.size());

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
