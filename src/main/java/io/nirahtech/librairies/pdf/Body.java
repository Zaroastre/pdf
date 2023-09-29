package io.nirahtech.librairies.pdf;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class Body {

    private final Set<PDFObject> pdfObjects = new HashSet<>();


    Body(final Catalog catalog) {
        this.pdfObjects.addAll(List.of(catalog, catalog.getOutlines(), catalog.getPages()));
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        this.pdfObjects.forEach(object -> {
            builder.append(object.toString());
            builder.append("\n");
        });
        return builder.toString();
    }
}
