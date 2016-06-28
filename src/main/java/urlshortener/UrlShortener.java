/*
 * Copyright 2014 Intuit Inc. All rights reserved. Unauthorized reproduction
 * is a violation of applicable law. This material contains certain
 * confidential or proprietary information and trade secrets of Intuit Inc.
 */
package urlshortener;

/**
 * $DateTime$
 *
 * @Version $Header$
 * @Author $Author$
 */
public class UrlShortener {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int    BASE     = ALPHABET.length();

    public static String encode(int num) {
        StringBuilder sb = new StringBuilder();
        while ( num > 0 ) {
            sb.append( ALPHABET.charAt( num % BASE ) );
            num /= BASE;
        }
//        return sb.reverse().toString();
        return sb.toString();
    }

    public static int decode(String str) {
        int num = 0;
        for ( int i = 0; i < str.length(); i++ ) {
            num += ALPHABET.indexOf(str.charAt(i)) * Math.pow(BASE, i);
        }
        return num;
    }

    public static void main(String[] args) {
        String encoded = encode(123456);
        int decoded = decode(encoded);
        System.out.println("123456 encoded: " + encoded);
        System.out.println(encoded + " decoded: " + decoded);
    }
}
