package io.nirahtech.librairies.pdf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class Outlines extends PDFObject {
    private final List<Outline> outlines = new ArrayList<>();

    Outlines(final Outline... outlines) {
        super();
        this.outlines.addAll(List.of(outlines));
        Collections.sort(this.outlines);
    }

    public final Collection<Outline> getOutlines() {
        return Collections.unmodifiableCollection(this.outlines);
    }


    @Override
    public String toString() {
        final Outline first = this.outlines.get(0);
        final Outline last = this.outlines.get(this.outlines.size() - 1);
        final StringBuilder builder = new StringBuilder();
        builder
                .append(String.format("%s %s obj", super.getObjectNumber(), super.getVersion()))
                .append("\n")
                .append("<<\n")
                .append("/Type /Outlines\n");

        if (!this.outlines.isEmpty()) {
            builder.append(String.format("/First %s %s %s", first.getObjectNumber(), first.getVersion(), REFERENCE));
            builder.append("\n");
            builder.append(String.format("/Last %s %s %s", last.getObjectNumber(), last.getVersion(), REFERENCE));
            builder.append("\n");
        }
        builder.append(String.format("/Count %s", this.outlines.size()));
        builder.append("\n");

        builder.append(">>\n")
                .append("endobj\n");
                
        return builder.toString();
    }
}
