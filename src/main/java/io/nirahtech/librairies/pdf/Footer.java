package io.nirahtech.librairies.pdf;

import java.time.format.DateTimeFormatter;

public final class Footer extends PDFObject {
    private final Header header;

    Footer(final Header header) {
        this.header = header;
    }

    public final Header getHeader() {
        return this.header;
    }

    @Override
    public String toString() {
        final DateTimeFormatter pdfDateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssZ");
        final String pdfFormattedDate = this.header.getCreationDate().format(pdfDateTimeFormatter);

        final StringBuilder builder = new StringBuilder();
        builder.append(String.format("%s %s obj", super.getObjectNumber(), super.getVersion()))
        .append("\n")
        .append("<<\n")
        .append(String.format("/Creator (%s)", this.header.getCreator()))
        .append("\n")
        .append(String.format("/Producer (%s)", this.header.getProducer()))
        .append("\n")
        .append(String.format("/CreationDate (D:%s)", pdfFormattedDate))
        .append("\n")
        .append(">>\n")
        .append("endobj\n")
        .append("\n");

        return builder.toString();
    }
}
