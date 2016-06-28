/*
 * Copyright 2014 Intuit Inc. All rights reserved. Unauthorized reproduction
 * is a violation of applicable law. This material contains certain
 * confidential or proprietary information and trade secrets of Intuit Inc.
 */
package searchtree;

/**
 * $DateTime$
 *
 * @Version $Header$
 * @Author $Author$
 */
public class TreeFactory {

    public static SearchTree getTree() {
        SearchTree foo = new SearchTree("foo");
        SearchTree bar = new SearchTree("bar");
        SearchTree bat = new SearchTree("bat");
        SearchTree baz = new SearchTree("baz");
        SearchTree left = new SearchTree(5, foo, bar);
        SearchTree right = new SearchTree(15, bat, baz);
        return new SearchTree(10, left, right);
    }
}
