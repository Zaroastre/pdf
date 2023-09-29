package io.nirahtech.librairies.pdf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class Resources extends PDFObject {
    private final List<PDFObject> resources = new ArrayList<>();
    private final ProcessSet processSet;

    Resources(final ProcessSet processSet, final PDFObject... resources) {
        if (Objects.nonNull(resources)) {
            this.resources.addAll(List.of(resources));
        }
        this.processSet = processSet;
    }

    public final Collection<Font> getFonts() {
        return Collections.unmodifiableCollection(this.resources.stream().filter(resource -> resource instanceof Font).map(resource -> (Font) resource).collect(Collectors.toSet()));
    }

    public final Collection<PDFObject> getResources() {
        return Collections.unmodifiableCollection(this.resources);
    }

    public final ProcessSet getProcessSet() {
        return processSet;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("/Resources <<\n");
        this.resources.forEach(resource -> {
            if (resource instanceof Font) {
                final Font font = (Font) resource;
                builder.append("/Font <<\n")
                        .append(String.format("/%s %s %s %s", font.getAlias(), font.getObjectNumber(),
                                font.getVersion(), REFERENCE))
                        .append("\n")
                        .append(">>\n");
            }
        });
        builder.append(String.format("/ProcSet %s %s %s", this.processSet.getObjectNumber(),
                this.processSet.getVersion(), REFERENCE))
                .append("\n")
                .append(">>\n");
        return builder.toString();
    }
}
