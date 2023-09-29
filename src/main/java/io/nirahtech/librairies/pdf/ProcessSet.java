package io.nirahtech.librairies.pdf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class ProcessSet extends PDFObject {
    private final List<String> array = new ArrayList<>();

    ProcessSet(final String... data) {
        if (Objects.nonNull(data)) {
            this.array.addAll(List.of(data));
        }
    }

    public final Collection<String> getArray() {
        return Collections.unmodifiableCollection(this.array);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        builder.append(String.format("%s %s obj", super.getObjectNumber(), super.getVersion()))
                .append("\n")
                .append(String.format("[%s]",
                        this.array.stream().map(data -> "/" + data).collect(Collectors.joining(" "))))
                .append("endobj\n");
        return builder.toString();
    }
}
