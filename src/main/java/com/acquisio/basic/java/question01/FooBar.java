package com.acquisio.basic.java.question01;

/**
 * QUESTION 01: FooBar
 * You have a program that receives a value, which we assume is a positive
 * integer. You need to loop on all integer value between 0 and the value
 * received. Ex.: If you receive 50, you must loop from 0 to 50.
 * <p>
 * The program must follow those rules:
 * - Must print 'Foo' if the value can be divided by 3.
 * - Must print 'Bar' if the value can be divided by 5.
 * - Otherwise, print the current integer value in the loop.
 *
 * IMPORTANT: Add all missing javadoc and/or unit tests that you think is necessary.
 */
public class FooBar {
    public static void main(String[] args) {
        FooBar main = new FooBar();
        for (int i = 1; i <= 50; i ++) {
            System.out.println(main.fooBar(i));
        }
    }

    String fooBar(int value) {
        if (value % 15 == 0) {
        	return "FooBar";
        }
        
        if (value % 3 == 0) {
        	return "Foo";
        }
        
        if (value % 5 == 0) {
        	return "Bar";
        }
        
        return String.valueOf(value);
    }
}
