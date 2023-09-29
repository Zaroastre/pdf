package io.nirahtech.librairies.pdf;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public final class Contents extends PDFObject {
    private final Set<Text> content = new HashSet<>();
    
    Contents(final Text... texts) {
        if (Objects.nonNull(texts)) {
            this.content.addAll(List.of(texts));
        }
    }

    public final Collection<Text> getContent() {
        return Collections.unmodifiableCollection(this.content);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(String.format("%s %s obj", super.getObjectNumber(), super.getVersion()))
        .append("\n")
        .append(String.format("<< /Length %s >>", this.content.stream().mapToInt(text -> text.value().length()).sum()))
        .append("\n")
        .append("stream\n")
        .append("2 J\n");
        this.content.forEach(text -> {
            builder.append(text.toString());
        });
        builder.append("endstream\n").append("endobj\n");
        return builder.toString();
    }
}
