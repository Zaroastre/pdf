package io.nirahtech.librairies.pdf;

import java.io.Serializable;

public abstract class PDFObject implements Serializable, Comparable<PDFObject> {

    protected static final String REFERENCE = "R";
    private static int totalCreated = 0;

    private final int objectNumber;
    protected final int version;

    public static final int getTotalCreated() {
        return totalCreated;
    }

    protected PDFObject() {
        PDFObject.totalCreated ++;
        this.objectNumber = PDFObject.totalCreated;
        this.version = 0;
    }

    @Override
    public int compareTo(PDFObject other) {
        return Integer.compare(this.objectNumber, other.getObjectNumber());
    }

    public final int getObjectNumber() {
        return this.objectNumber;
    }
    public final int getVersion() {
        return this.version;
    }
}
