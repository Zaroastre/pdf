package io.nirahtech.librairies.pdf;

public final class Trailer extends PDFObject {

    private final Catalog catalog;
    private final Footer footer;

    Trailer(final Catalog catalog, final Footer footer) {
        this.catalog = catalog;
        this.footer = footer;
    }

    public final Catalog getCatalog() {
        return this.catalog;
    }
    public final Footer getFooter() {
        return this.footer;
    }
    
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("trailer\n")
        .append("<<\n")
        .append(String.format("/Size %s", PDFObject.getTotalCreated()))
        .append("\n")
        .append(String.format("/Root %s %s %s", this.catalog.getObjectNumber(), this.catalog.getVersion(), REFERENCE))
        .append("\n")
        .append(String.format("/Info %s %s %s", this.footer.getObjectNumber(), this.footer.getVersion(), REFERENCE))        
        .append(">>\n");
        return builder.toString();
    }
}
