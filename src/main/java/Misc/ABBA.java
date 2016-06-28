package main.java.Misc;

import java.util.concurrent.RecursiveTask;

public class ABBA {

    public String canObtain(String initial, String target) {
        ABBATask abbaTask = new ABBATask(initial, target);
        if (abbaTask.compute()) {
            return "Possible";
        } else {
            return "Impossible";
        }
    }

    boolean isPossible(String word, String target) {
        if (word.length() == target.length()) {
            return word.equals(target);
        }
        String word1 = word + "A";
        String word2 = reverse(word) + "B";
        return isPossible(word1, target) || isPossible(word2, target);
    }

    String reverse(String word) {
        return new StringBuilder(word).reverse().toString();
    }

    private class ABBATask extends RecursiveTask<Boolean> {

        String word;
        String target;

        public ABBATask(String word, String target) {
            this.word = word;
            this.target = target;
        }

        public Boolean compute() {
            if (word.length() == target.length()) {
                return word.equals(target);
            } else {
                String word1 = word + "A";
                String word2 = reverse(word) + "B";
                ABBATask task1 = new ABBATask(word1, target);
                ABBATask task2 = new ABBATask(word2, target);
                if (word.length() + 10 >= target.length()) {
                    return isPossible(word1, target) || isPossible(word2, target);
                }
                task1.fork();
                return task2.compute() || task1.join();
            }
        }
    }
}
