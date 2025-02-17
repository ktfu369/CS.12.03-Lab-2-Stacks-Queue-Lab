package impl;

import common.QueueEmptyException;
import common.QueueFullException;
import common.StackEmptyException;
import common.StackOverflowException;
import interfaces.IQueue;

public class DoubleStackQueue implements IQueue {
    private int maxSize;
    private DoubleStack stack;

    public DoubleStackQueue(int maxSize){
        this.maxSize = maxSize;
        // maxSize * 2 --> both input and ouptut stacks can hold maxSize lengths
        stack = new DoubleStack(maxSize*2);
    }
    public void enqueue(Object element) throws QueueFullException {
        try {
            // attempt to insert new element into the input stack
            stack.getFirstStack().push(element);
        } catch (StackOverflowException e) {
            // reached maximum capacity, throw error
            throw new QueueFullException();
        }
    }

    public Object dequeue() throws QueueEmptyException {
        try{
            if(stack.getSecondStack().isEmpty()) {
                // no more values in output stack
                while (!stack.getFirstStack().isEmpty()) {
                    // add input stack into output stack
                    Object top = stack.getFirstStack().pop();
                    stack.getSecondStack().push(top);
                }
            }
            // return the first value in the output stack
            return stack.getSecondStack().pop();
        }
        catch (StackEmptyException e) {
            // empty stacks
            throw new QueueEmptyException();
        }
        catch(StackOverflowException e){
            // reached maximum capacity in stack
            throw new RuntimeException();
        }

    }
    public int size(){
        // since first stack & second stack both store values - sum the size of both
        return stack.getFirstStack().size() + stack.getSecondStack().size();
    }
    public boolean isEmpty(){
        // checks if both input and output stacks are empty
        return stack.getFirstStack().isEmpty() && stack.getSecondStack().isEmpty();
    }

    public void clear(){
        stack.getFirstStack().clear();
        stack.getSecondStack().clear();
    }
}
