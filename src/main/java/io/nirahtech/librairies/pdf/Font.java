package io.nirahtech.librairies.pdf;

import java.util.concurrent.atomic.AtomicInteger;

public final class Font extends PDFObject {
    private static AtomicInteger totalCreated = new AtomicInteger(0);

    private final String subType;
    private final String alias;
    private final String baseFont;
    private final String encoding;

    public Font(final String fontName) {
        this.subType = "Type1";
        this.alias = String.format("F%s", Font.totalCreated.incrementAndGet());
        this.baseFont = fontName;
        this.encoding = "WinAnsiEncoding";
    }


    public String getBaseFont() {
        return this.baseFont;
    }
    public String getAlias() {
        return this.alias;
    }
    public String getSubType() {
        return this.subType;
    }
    public String getEncoding() {
        return this.encoding;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        
        builder
                .append(String.format("%s %s obj", super.getObjectNumber(), super.getVersion()))
                .append("\n")
                .append("<<\n")
                .append("/Type /Font\n")
                .append(String.format("/Subtype /%s", this.subType))
                .append("\n")
                .append(String.format("/Name /%s", this.alias))
                .append("\n")
                .append(String.format("/BaseFont /%s", this.baseFont))
                .append("\n")
                .append(String.format("/Encoding /%s", this.encoding))
                .append("\n")
                .append(">>\n")
                .append("endobj\n");
                ;

        return builder.toString();
    }

}
