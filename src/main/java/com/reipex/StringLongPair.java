package com.reipex;

/**
 * I'm not a huge fan of Java's built-in options for pairing stuff together.
 * So I wrote this. This class is primarily used by PendingTransactionContainer.
 */
public class StringLongPair {
    public String stringToHold;
    public long longToHold;

    /**
     * A StringLongPair is... well... a pair containing a String and a Long.
     *
     * @param stringToHold String to hold
     * @param longToHold   Long to hold
     */
    public StringLongPair(String stringToHold, long longToHold) {
        this.stringToHold = stringToHold;
        this.longToHold = longToHold;
    }
}