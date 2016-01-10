package com.github.nilscoding.uselessjsptags;

/**
 * A very simple counter, not Thread-safe
 * @author NilsCoding
 */
public class Counter {
    
    protected int value;
    protected int initialValue;
    
    /**
     * Creates a new counter with start value 0
     */
    public Counter() {
        this.value = 0;
        this.initialValue = 0;
    }
    
    /**
     * Creates a new new counter with a given initial value
     * @param initialValue  initial counter value
     */
    public Counter(int initialValue) {
        this.value = initialValue;
        this.initialValue = initialValue;
    }
    
    /**
     * Returns the counter value
     * @return  counter value
     */
    public int getValue() {
        return this.value;
    }
    
    /**
     * Increases the counter by 1 and returns itself
     * @return  itself
     */
    public Counter increase() {
        this.value++;
        return this;
    }
    
    /**
     * Increases the counter by a given value and returns itself
     * @param byValue   value to increase by
     * @return  itself
     */
    public Counter increase(int byValue) {
        this.value += byValue;
        return this;
    }
    
    /**
     * Increases the counter by the value of another counter and returns itself
     * @param byCounterValue    counter increase by value
     * @return  itself
     */
    public Counter increase(Counter byCounterValue) {
        if (byCounterValue != null) {
            this.increase(byCounterValue.getValue());
        }
        return this;
    }
    
    /**
     * Decreases the counter by 1 and returns itself
     * @return  itself
     */
    public Counter decrease() {
        this.value--;
        return this;
    }
    
    /**
     * Decreases the counter by a given value and returns itself
     * @param byValue   value to decrease by
     * @return  itself
     */
    public Counter decrease(int byValue) {
        this.value -= byValue;
        return this;
    }
    
    /**
     * Resets the counter to its initial value and returns itself
     * @return  itself
     */
    public Counter reset() {
        this.value = this.initialValue;
        return this;
    }
    
    /**
     * Resets the counter to zero and returns itself
     * @return  itself
     */
    public Counter resetZero() {
        this.value = 0;
        return this;
    }
    
    @Override
    public String toString() {
        return String.format("{ \"value\": %d}", this.value);
    }
    
    /**
     * Returns a copy of this counter
     * @return  copy of this counter
     */
    public Counter duplicate() {
        Counter c = new Counter();
        c.initialValue = this.initialValue;
        c.value = this.value;
        return c;
    }
    
}
