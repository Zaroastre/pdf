package io.nirahtech.librairies.pdf;

import java.util.Objects;

public final class Page extends PDFObject {
    private final PDFObject parent;
    private final Resources resources;
    private final Contents contents;

    private final Position dimension;

    Page(final PDFObject parent, final Resources resources, final Contents contents, final Position dimension) {
        this.parent = parent;
        this.resources = resources;
        this.contents = contents;
        this.dimension = dimension;
    }

    public final PDFObject getParent() {
        return this.parent;
    }
    public final Resources getResources() {
        return this.resources;
    }
    public final Contents getContents() {
        return this.contents;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(String.format("%s %s obj", super.getObjectNumber(), super.getVersion()))
                .append("\n")
                .append("<<\n")
                .append("/Type /Page\n");

        if (Objects.nonNull(this.parent)) {
            builder.append(String.format("/Parent %s %s %s", this.parent.getObjectNumber(), this.parent.getVersion(), REFERENCE))
                    .append("\n");
        }
        if (Objects.nonNull(this.resources)) {
            builder.append(this.resources.toString());
        }
        builder.append(String.format("/MediaBox [0 0 %s %s]", this.dimension.x(), this.dimension.y()));
        builder.append("\n");

        if (Objects.nonNull(this.contents)) {
            builder.append(
                    String.format("/Contents %s %s %s", this.contents.getObjectNumber(), this.contents.getVersion(), REFERENCE))
                    .append("\n");
        }
        builder.append(">>\n")
                .append("endobj\n");

        if (Objects.nonNull(this.resources)) {
            this.resources.getFonts().forEach(font -> {
                builder.append(font.toString()).append("\n");
            });
        }
            
        builder.append(this.contents.toString()).append("\n");
        return builder.toString();
    }
}
