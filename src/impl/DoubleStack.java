package impl;

import interfaces.IDoubleStack;

public class DoubleStack implements IDoubleStack {
    private Stack stack1;
    private Stack stack2;
    private Object array[];

    public DoubleStack(int maxSize) {
        // if stack is attempted to be created with invalid size (negative)
        if(maxSize < 0) throw new IllegalArgumentException("Invalid size");

        // create an array to store values
        array = new Object[maxSize];
        stack1 = new Stack(maxSize/2,0,array);
        stack2 = new Stack((maxSize+1)/2,maxSize-1,array);
    }

    public Stack getFirstStack(){
        return stack1;
    }

    public Stack getSecondStack(){
        return stack2;
    }
}
