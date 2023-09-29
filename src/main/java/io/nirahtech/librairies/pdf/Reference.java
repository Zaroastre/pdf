package io.nirahtech.librairies.pdf;

public final class Reference {
    private final int startPositionOfTheObjectInOctet;
    private final int generationNumber;
    private final boolean isFree;

    Reference(final int startPositionOfTheObjectInOctet, final int generationNumber, final boolean isFree) {
        this.startPositionOfTheObjectInOctet = startPositionOfTheObjectInOctet;
        this.generationNumber = generationNumber;
        this.isFree = isFree;
    }

    public final int getStartPositionOfTheObjectInOctet() {
        return this.startPositionOfTheObjectInOctet;
    }
    public final int getGenerationNumber() {
        return this.generationNumber;
    }
    public final boolean isFree() {
        return this.isFree;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", String.format("%010d", this.startPositionOfTheObjectInOctet), String.format("%05d", this.generationNumber), (this.isFree) ? "f" : "n");
    }
}
