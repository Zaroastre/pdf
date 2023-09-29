package io.nirahtech.librairies.pdf;

import java.util.Objects;

public class Outline extends PDFObject {
    private final String title;

    private final PDFObject parent;
    private final PDFObject previous;
    private final PDFObject next;
    private final PDFObject first;
    private final PDFObject last;

    public Outline(final String title, final PDFObject parent, final PDFObject previous, final PDFObject next, final PDFObject first, final PDFObject last) {
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
            builder.append(String.format("/Parent %s %s R", this.parent.getObjectNumber(), this.parent.getVersion()));
            builder.append("\n");
        }
        if (Objects.nonNull(this.previous)) {
            builder.append(String.format("/Prev %s %s R", this.previous.getObjectNumber(), this.previous.getVersion()));
            builder.append("\n");
        }
        if (Objects.nonNull(this.next)) {
            builder.append(String.format("/Next %s %s R", this.next.getObjectNumber(), this.next.getVersion()));
            builder.append("\n");
        }
        if (Objects.nonNull(this.first)) {
            builder.append(String.format("/First %s %s R", this.first.getObjectNumber(), this.first.getVersion()));
            builder.append("\n");
        }
        if (Objects.nonNull(this.last)) {
            builder.append(String.format("/Last %s %s R", this.last.getObjectNumber(), this.last.getVersion()));
            builder.append("\n");
        }

        if (this.title.toLowerCase().contains("section")) {
            builder.append("/Count 1\n");
            builder.append("/A 4 0 R\n");
        }

        builder.append("/D [ /Fit ]\n")
                .append(">>\n")
                .append("endobj\n\n");

        return builder.toString();
    }
}
