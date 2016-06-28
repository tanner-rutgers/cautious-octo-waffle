/*
 * Copyright 2014 Intuit Inc. All rights reserved. Unauthorized reproduction
 * is a violation of applicable law. This material contains certain
 * confidential or proprietary information and trade secrets of Intuit Inc.
 */
package stackqueue;

import java.util.Stack;

/**
 * $DateTime$
 *
 * @Version $Header$
 * @Author $Author$
 */
public class StackQueue {

    Stack<Integer> leftStack;
    Stack<Integer> rightStack;

    public StackQueue() {
        this.leftStack = new Stack<>();
        this.rightStack = new Stack<>();
    }

    public void enqueue(Integer val) {
        if (rightStack.size() > 0) {
            moveLeft();
        }
        leftStack.push(val);
    }

    public Integer dequeue() {
        if (leftStack.size() > 0) {
            moveRight();
        }
        return rightStack.pop();
    }

    private void moveLeft() {
        while (rightStack.size() > 0) {
            leftStack.push(rightStack.pop());
        }
    }

    private void moveRight() {
        while (leftStack.size() > 0) {
            rightStack.push(leftStack.pop());
        }
    }
}
