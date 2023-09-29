package io.nirahtech.librairies.pdf;

import java.util.Objects;

public class Page extends PDFObject {
    private final PDFObject parent;
    private final PDFObject resources;
    private final PDFObject contents;

    public Page(final PDFObject parent, final PDFObject resources, final PDFObject contents) {
        this.parent = parent;
        this.resources = resources;
        this.contents = contents;
    }

    public PDFObject getParent() {
        return parent;
    }
    public PDFObject getResources() {
        return resources;
    }
    public PDFObject getContents() {
        return contents;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(String.format("%s %s obj", super.getObjectNumber(), super.getVersion()))
                .append("\n")
                .append("<<\n")
                .append("/Type /Page\n");

        if (Objects.nonNull(this.parent)) {
            builder.append(String.format("/Parent %s %s R", this.parent.getObjectNumber(), this.parent.getVersion()))
                    .append("\n");
        }
        if (Objects.nonNull(this.resources)) {
            builder.append(this.resources.toString())
                    .append("\n");
        }
        builder.append("/MediaBox [0 0 612.0000 792.0000]\n");

        if (Objects.nonNull(this.contents)) {
            builder.append(
                    String.format("/Contents %s %s R", this.contents.getObjectNumber(), this.contents.getVersion()))
                    .append("\n");
        }
        builder.append(">>\n")
                .append("endobj\n\n");
        return builder.toString();
    }
}
