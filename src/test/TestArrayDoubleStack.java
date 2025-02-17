package test;

import common.StackEmptyException;
import common.StackOverflowException;
import impl.DoubleStack;
import interfaces.IStack;
import org.junit.jupiter.api.Test;
import common.AbstractFactoryClient;
import interfaces.IDoubleStack;

import java.util.Stack;
import impl.Factory;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests array collection implementation.
 */
public class TestArrayDoubleStack extends AbstractFactoryClient {

    private static final int DEFAULT_MAX_SIZE = 10;
    private IDoubleStack doubleStack;
    private IStack firstStack;
    private IStack secondStack;

    /**
     * Tests that the factory constructs a non-null double stack.
     */

    @Test
    public void factoryReturnsNonNullDoubleStackObject() throws StackOverflowException, StackEmptyException {
        IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        assertNotNull(doubleStack1, "Failure: IFactory.makeDoubleStack returns null, expected non-null object");
    }

    //
    public void setUp() throws StackOverflowException, StackEmptyException {
        // set up double stack and store first & second stacks
        // assuming a double stack of size 10
        doubleStack = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        firstStack = doubleStack.getFirstStack();
        secondStack = doubleStack.getSecondStack();

    }

    @Test
    public void invalidSize(){
        // attemtping to create stack with negative size
        assertThrows(IllegalArgumentException.class,()->getFactory().makeDoubleStack(-100));
    }

    @Test
    public void testPushAndPopFirstStack() throws StackOverflowException, StackEmptyException {
        // tests push and pop method in the first stack
        setUp();
        // pushes 2 elements
        firstStack.push(1);
        firstStack.push(2);
        // checks 2 elements popped and whether it follows FILO order
        assertEquals(2, firstStack.pop());
        assertEquals(1, firstStack.pop());
    }

    @Test
    public void testPushAndPopSecondStack() throws StackOverflowException, StackEmptyException {
        // tests push and pop method in the second stack
        setUp();
        secondStack.push("test1");
        secondStack.push("test2");
        assertEquals("test2", secondStack.pop());
        assertEquals("test1", secondStack.pop());
    }

    @Test
    public void testIsEmpty() throws StackOverflowException, StackEmptyException {
        setUp();

        // checking initial empty stacks
        assertTrue(firstStack.isEmpty());
        assertTrue(secondStack.isEmpty());

        // checking stacks with pushed elements
        firstStack.push(5);
        assertFalse(firstStack.isEmpty());
        secondStack.push(10);
        assertFalse(secondStack.isEmpty());

        // checking stacks with now empty elements
        firstStack.pop();
        assertTrue(firstStack.isEmpty());
        secondStack.pop();
        assertTrue(secondStack.isEmpty());
    }

    @Test
    public void testSize() throws StackOverflowException, StackEmptyException {
        setUp();

        // checks whether initial size is 0
        assertEquals(0, firstStack.size());
        firstStack.push(15);
        // checks whether size updates
        assertEquals(1, firstStack.size());
        secondStack.push(5);
        secondStack.push(20);
        assertEquals(2, secondStack.size());
    }

    @Test
    public void testStackOverFlow() throws StackOverflowException, StackEmptyException {
        setUp();

        // push 5 elements in firstStack
        firstStack.push(1);
        firstStack.push(2);
        firstStack.push(3);
        firstStack.push(4);
        firstStack.push(5);

        // max size = 5
        // should throw stackOverFlow if 6th element is attempted to be pushed
        assertThrows(StackOverflowException.class, () -> firstStack.push(6));
    }

    @Test
    public void testStackOverFlow2() throws StackOverflowException, StackEmptyException {
        setUp();

        // push 5 elements into secondStack
        secondStack.push(1);
        secondStack.push(2);
        secondStack.push(3);
        secondStack.push(4);
        secondStack.push(5);

        // max size = 5
        // should throw stackOverFlow if 6th element is attempted to be pushed
        assertThrows(StackOverflowException.class, () -> secondStack.push(6));
    }

    public void testEmptyStack() throws StackOverflowException, StackEmptyException {
        setUp();

        // initial stack should be empty
        // should throw StackEmpty Exception when trying to pop an empty stack
        assertThrows(StackEmptyException.class, () -> firstStack.pop());
    }

    @Test
    public void testClear() throws StackOverflowException, StackEmptyException {
        setUp();

        // check whether both stacks can be cleared
        firstStack.push(50);
        secondStack.push(60);
        firstStack.clear();
        secondStack.clear();
        // when cleared, stacks should be empty
        assertTrue(firstStack.isEmpty());
        assertTrue(secondStack.isEmpty());
    }

    @Test
    public void testTop() throws StackOverflowException, StackEmptyException {
        setUp();
        // check the empty method of stack
        firstStack.push("x");
        assertEquals("x", firstStack.top());
        secondStack.push("y");
        assertEquals("y", secondStack.top());
        secondStack.push("z");
        assertEquals("z", secondStack.top());
    }
}
