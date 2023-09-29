package io.nirahtech.librairies.pdf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class CrossReferenceTable {
    
    private final List<Reference> referencesTable = new ArrayList<>();

    private final int positionInOctetFromTheBegining;

    CrossReferenceTable(int positionInOctetFromTheBegining) {
        this.positionInOctetFromTheBegining = positionInOctetFromTheBegining;
    }

    public final List<Reference> getReferencesTable() {
        return Collections.unmodifiableList(referencesTable);
    }
    
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("xref\n");
        builder.append(0)
        .append(" ")
        .append(PDFObject.getTotalCreated())
        .append("\n");
        this.referencesTable.forEach(reference -> {
            builder.append(reference.toString()).append("\n");
        });
        builder.append("startxref\n");
        builder.append(this.positionInOctetFromTheBegining);
        builder.append("\n");
        return builder.toString();
    }
}
