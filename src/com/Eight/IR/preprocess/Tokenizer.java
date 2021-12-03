package com.Eight.IR.preprocess;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {

    private static final String punctuations = ".,:;!#@$%^&*()-'\"\\";

    private static boolean isSplitter(char c) {
        return Character.isWhitespace(c) || punctuations.indexOf(c) >= 0;
    }


    private static boolean isAcceptable(char c) {
        return (c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }

    public static List<String> tokenizeByBlackList(String s) {
        ArrayList<String> tokens = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (isSplitter(c) && builder.length() > 0) {
                tokens.add(builder.toString());
                builder.setLength(0);
            } else {
                if (!isSplitter(c))
                    builder.append(c);
            }
        }
        if (builder.length() > 0)
            tokens.add(builder.toString());
        return tokens;
    }


    public static List<String> tokenizeByWhiteList(String s) {
        ArrayList<String> tokens = new ArrayList<>();
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!isAcceptable(c) && builder.length() > 0) {
                tokens.add(builder.toString());
                builder.setLength(0);
            } else {
                if (isAcceptable(c))
                    builder.append(c);
            }
        }
        if (builder.length() > 0)
            tokens.add(builder.toString());
        return tokens;
    }

    public static void main(String[] args) {
        String str = "Hello^#&%* World!#$$%!#%!";
        System.out.println("By BlackList:");
        for (String s : tokenizeByBlackList(str))
            System.out.println(s);
        System.out.println("\n");
        System.out.println("By WhiteList:");
        for (String s : tokenizeByWhiteList(str))
            System.out.println(s);
    }

}
