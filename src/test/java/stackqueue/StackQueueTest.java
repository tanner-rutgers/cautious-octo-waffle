package stackqueue;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by trutgers on 4/1/16.
 */
public class StackQueueTest {

    @Test
    public void test() {
        StackQueue queue = new StackQueue();
        queue.enqueue(1);
        assertTrue(queue.dequeue().equals(1));
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        assertTrue(queue.dequeue().equals(1));
        assertTrue(queue.dequeue().equals(2));
        assertTrue(queue.dequeue().equals(3));
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        assertTrue(queue.dequeue().equals(1));
        assertTrue(queue.dequeue().equals(2));
        queue.enqueue(4);
        assertTrue(queue.dequeue().equals(3));
        assertTrue(queue.dequeue().equals(4));
    }

}