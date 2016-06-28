/*
 * Copyright 2014 Intuit Inc. All rights reserved. Unauthorized reproduction
 * is a violation of applicable law. This material contains certain
 * confidential or proprietary information and trade secrets of Intuit Inc.
 */
package wordsquare;

import java.util.HashMap;
import java.util.Map;

/**
 * $DateTime$
 *
 * @Version $Header$
 * @Author $Author$
 */
public class WordSquare {

    boolean WordSquare(String l) {
        Double s = Math.sqrt(l.length());
        if (l.length() == 0 || s != Math.ceil(s)) { return false; }
        Map<Character, Integer> m = new HashMap<>();
        for (Character c : l.toCharArray()) {
            Integer i = m.get(c);
            if (i == null) { i = 0; }
            m.put(c, ++i);
        }
        int p = 0;
        while (s > 1) {
            p += ((s - 2) * 2) + 1;
            s -= 2;
        }
        for (Character c : m.keySet()) {
            while (p >= 0 && m.get(c) >= 2) {
                m.put(c, m.get(c) - 2);
                p--;
            }
            if (p <= 0) {
                break;
            }
        }
        return p <= 0;
    }
}
