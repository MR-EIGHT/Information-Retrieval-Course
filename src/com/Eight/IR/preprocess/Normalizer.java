package com.Eight.IR.preprocess;

import java.util.HashMap;
import java.util.Map;

public class Normalizer {
    private static Map<Character, Character> table = new HashMap<>();

    static {
        table.put('ك', 'ک');
        table.put('ي', 'ی');

    }


    public static String normalize(String s) {
        StringBuilder builder = new StringBuilder(s.length());
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            Character converted = table.get(c);
            if (converted != null)
                c = converted;
            builder.append(Character.toLowerCase(c));
        }
        return builder.toString();
    }


    public static void main(String[] args) {
        System.out.println(normalize("BoOk"));
        System.out.println(normalize("پیاده سازي تستي از ماك"));
    }
}
