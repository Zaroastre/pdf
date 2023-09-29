package io.nirahtech.librairies.pdf;

import java.util.Objects;

public final class Outline extends PDFObject {
    private final String title;

    private final PDFObject parent;
    private final PDFObject previous;
    private final PDFObject next;
    private final PDFObject first;
    private final PDFObject last;

    Outline(final String title, final PDFObject parent, final PDFObject previous, final PDFObject next, final PDFObject first, final PDFObject last) {
        this.title = title;
        this.parent = parent;
        this.previous = previous;
        this.next = next;
        this.first = first;
        this.last = last;

    }

    public final String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(String.format("%s %s obj", super.getObjectNumber(), super.getVersion()))
                .append("\n")
                .append("<<\n")
                .append(String.format("/Title (%s)", this.title))
                .append("\n");

        if (Objects.nonNull(this.parent)) {
            builder.append(String.format("/Parent %s %s %s", this.parent.getObjectNumber(), this.parent.getVersion(), REFERENCE));
            builder.append("\n");
        }
        if (Objects.nonNull(this.previous)) {
            builder.append(String.format("/Prev %s %s %s", this.previous.getObjectNumber(), this.previous.getVersion(), REFERENCE));
            builder.append("\n");
        }
        if (Objects.nonNull(this.next)) {
            builder.append(String.format("/Next %s %s %s", this.next.getObjectNumber(), this.next.getVersion(), REFERENCE));
            builder.append("\n");
        }
        if (Objects.nonNull(this.first)) {
            builder.append(String.format("/First %s %s %s", this.first.getObjectNumber(), this.first.getVersion(), REFERENCE));
            builder.append("\n");
        }
        if (Objects.nonNull(this.last)) {
            builder.append(String.format("/Last %s %s %s", this.last.getObjectNumber(), this.last.getVersion(), REFERENCE));
            builder.append("\n");
        }

        if (this.title.toLowerCase().contains("section")) {
            builder.append("/Count 1\n");
            builder.append(String.format("/A 4 0 %s", REFERENCE));
            builder.append("\n");
        }

        builder.append("/D [ /Fit ]\n")
                .append(">>\n")
                .append("endobj\n");

        return builder.toString();
    }
}
