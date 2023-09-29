package io.nirahtech.librairies.pdf;

import java.util.Objects;

public final class Catalog extends PDFObject {
    private final Outlines outlines;
    private final Pages pages;

    Catalog(final Outlines outlines, final Pages pages) {
        this.outlines = outlines;
        this.pages = pages;
    }

    public final Outlines getOutlines() {
        return this.outlines;
    }
    public final Pages getPages() {
        return this.pages;
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
            builder.append(String.format("/Outlines %s %s %s", this.outlines.getObjectNumber(), 0, REFERENCE));
            builder.append("\n");
        }

        if (Objects.nonNull(this.pages)) {
            builder.append(String.format("/Pages %s %s %s", this.pages.getObjectNumber(), 0, REFERENCE));
            builder.append("\n");
        }
        builder.append(">>\n")
                .append("endobj\n");
        return builder.toString();
    }
}
