package impl;

import common.StackEmptyException;
import common.StackOverflowException;
import interfaces.IDoubleStack;
import interfaces.IFactory;
import interfaces.IQueue;

/**
 * This class implements a singleton factory.
 *
 */
public final class Factory implements IFactory {

    private static IFactory factoryInstance = null;

    private Factory() {

    }

    /**
     * Method which returns an instance of the singleton Factory class.
     * @return the instance of the Factory
     */
    public static IFactory getInstance() {
        if (factoryInstance == null) {
            factoryInstance = new Factory();
        }
        return factoryInstance;
    }

    @Override
    public IDoubleStack makeDoubleStack(int maxSize) throws StackOverflowException, StackEmptyException {
        // TODO need to implement this
        DoubleStack myDoubleStack = new DoubleStack(maxSize);
        return myDoubleStack;
    }

    @Override
    public IQueue makeDoubleStackQueue(int maxSize) {
        // TODO need to implement this
        DoubleStackQueue queue = new DoubleStackQueue(maxSize);
        return queue;
    }

}
