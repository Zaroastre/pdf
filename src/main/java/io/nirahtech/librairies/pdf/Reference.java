package io.nirahtech.librairies.pdf;

public class Reference {
    private final int startPositionOfTheObjectInOctet;
    private final int generationNumber;
    private final boolean isFree;

    public Reference(final int startPositionOfTheObjectInOctet, final int generationNumber, final boolean isFree) {
        this.startPositionOfTheObjectInOctet = startPositionOfTheObjectInOctet;
        this.generationNumber = generationNumber;
        this.isFree = isFree;
    }

    public int getStartPositionOfTheObjectInOctet() {
        return startPositionOfTheObjectInOctet;
    }
    public int getGenerationNumber() {
        return generationNumber;
    }
    public boolean isFree() {
        return isFree;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", String.format("%010d", this.startPositionOfTheObjectInOctet), String.format("%05d", this.generationNumber), (this.isFree) ? "f" : "n");
    }
}
