package io.nirahtech.librairies.pdf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Pages extends PDFObject {
    private final List<Page> pages = new ArrayList<>();

    public Pages(final Page... pages) {
        this.pages.addAll(List.of(pages));
        Collections.sort(this.pages);
    }

    public Collection<Page> getPages() {
        return Collections.unmodifiableCollection(this.pages);
    }


    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder
                .append(String.format("%s %s obj", super.getObjectNumber(), super.getVersion()))
                .append("\n")
                .append("<<\n")
                .append("/Type /Pages\n")
                .append(String.format("/Count %s", this.pages.size()))
                .append("\n");

        builder.append(String.format("/Kids [ %s ]", this.pages.stream().map(page -> String.format("%s %s R", page.getObjectNumber(), page.getVersion())).collect(Collectors.joining(" "))));
        builder.append("\n");

        builder.append(">>\n")
                .append("endobj\n\n");
        
        this.pages.forEach(page -> {
            builder.append(page.toString()).append("\n");
        });
        return builder.toString();
    }
}
