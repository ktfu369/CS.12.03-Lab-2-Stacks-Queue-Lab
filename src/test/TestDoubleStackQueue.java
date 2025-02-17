package test;

import common.QueueEmptyException;
import common.QueueFullException;
import impl.Factory;
import org.junit.jupiter.api.Test;
import common.AbstractFactoryClient;
import interfaces.IQueue;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests double stack queue implementation.
 */
public class TestDoubleStackQueue extends AbstractFactoryClient {

    private static final int DEFAULT_MAX_SIZE = 10;
    private IQueue queue;

    /**
     * Tests that the factory constructs a non-null object.
     */
    @Test
    public void factoryReturnsNonNullDoubleStackQueue() {
        IQueue queue = getFactory().makeDoubleStackQueue(DEFAULT_MAX_SIZE);
        assertNotNull(queue, "Failure: IFactory.makeDoubleStackQueue returns null, expected non-null object");
    }

    public void setUp() {
        queue = getFactory().makeDoubleStackQueue(DEFAULT_MAX_SIZE); // Assuming a method that creates a queue using double stack
    }

    @Test
    public void testEnqueueAndDequeue() throws QueueFullException, QueueEmptyException {
        setUp();
        // enqueue and dequeue elements
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        // check if follows FIFO order
        assertEquals(1, queue.dequeue());
        assertEquals(2, queue.dequeue());
        assertEquals(3, queue.dequeue());
    }

    @Test
    public void testDequeueEmptyQueue(){
        setUp();
        // check if able to dequeue an empty class
        assertThrows(QueueEmptyException.class,()-> queue.dequeue());
    }

    @Test
    public void testIsEmpty() throws QueueFullException, QueueEmptyException {
        setUp();
        // checks if initial queue is empty
        assertTrue(queue.isEmpty());
        queue.enqueue(400);
        assertFalse(queue.isEmpty());
        queue.dequeue();
        // queue should be empty if element is enqueue-d then dequeue-d
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testSize() throws QueueFullException, QueueEmptyException {
        setUp();
        // size should increase when adding new elements
        assertEquals(0, queue.size());
        queue.enqueue("a");
        queue.enqueue("b");
        assertEquals(2, queue.size());
        queue.dequeue();
        assertEquals(1, queue.size());
    }

    @Test
    public void testQueueFull() throws QueueFullException {
        setUp();
        // adds maximum capacity
        for(int i=0;i<10;i++){
            queue.enqueue(i);
        }
        // queue full exception should be thrown when adding 11th element
        assertThrows(QueueFullException.class, ()->queue.enqueue(15));
    }

    @Test
    public void testClear() throws QueueFullException {
        setUp();
        // tests if clear method will reset queue
        queue.enqueue(10);
        queue.enqueue(20);
        queue.clear();
        assertTrue(queue.isEmpty());
        assertEquals(0,queue.size());
    }
}

