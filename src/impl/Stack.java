package impl;

import common.StackEmptyException;
import common.StackOverflowException;
import interfaces.IStack;

public class Stack implements IStack {
    private Object[] array; // content of array
    private int start; // starting index in array. first stack = 0, second stack = length - 1
    private int end; // ending index in array. first stack = ascending, second stack = descending
    private int maxSize; // maximum capacity of array

    public Stack(int maxSize, int start, Object[] array){
        this.maxSize = maxSize;
        this.start = start;
        this.array = array;
        end = start; // no element has been added yet
    }

    public void push(Object element) throws StackOverflowException {
        if(start == 0){
            // first stack
            if(end == maxSize){ // if maximum capacity is reached
                throw new StackOverflowException();
            }
            array[end] = element;
            end ++;
        }else{
            // second stack
            if(end == start - maxSize){ // if maximum capacity is reached
                throw new StackOverflowException();
            }
            array[end] = element;
            end --;
        }
    }

    public Object pop() throws StackEmptyException{
        if(isEmpty()){
            // unable to pop if stack is empty
            throw new StackEmptyException();
        }
        if(start == 0){ // first stack
            end--;

        }else{ // second stack
            end++;
        }

        Object temp = array[end]; // temporarily store 'top' value
        array[end] = null; // reset 'top' value
        return temp;
    }

    public Object top() throws StackEmptyException{
        if(isEmpty()){
            // unable to return top if stack is empty
            throw new StackEmptyException();
        }

        // don't change 'end', since the value is not popped
        if(start == 0) return array[end-1]; // first stack
        return array[end+1]; // second stack

    }

    public int size(){
        if(start == 0) return end; // first stack
        else return start-end; // second stack = difference between the start to end
    }
    public boolean isEmpty(){
        return size() == 0;
    }

    public void clear(){
        end = start; // no need to reset values. instead change end index so values are rewritten
    }
}
