package io.nirahtech.librairies.pdf;

import java.util.Objects;

public class Catalog extends PDFObject {
    private final PDFObject outlines;
    private final PDFObject pages;

    public Catalog(final PDFObject outlines, final PDFObject pages) {
        this.outlines = outlines;
        this.pages = pages;
    }

    public PDFObject getOutlines() {
        return outlines;
    }
    public PDFObject getPages() {
        return pages;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder
                .append(String.format("%s %s obj", super.getObjectNumber(), super.getVersion()))
                .append("\n")
                .append("<<\n")
                .append("/Type /Catalog\n");

        if (Objects.nonNull(this.outlines)) {
            builder.append(String.format("/Outlines %s %s R", this.outlines.getObjectNumber(), 0));
            builder.append("\n");
        }

        if (Objects.nonNull(this.pages)) {
            builder.append(String.format("/Pages %s %s R", this.pages.getObjectNumber(), 0));
            builder.append("\n");
        }
        builder.append(">>\n")
                .append("endobj\n\n");
        return builder.toString();
    }
}
