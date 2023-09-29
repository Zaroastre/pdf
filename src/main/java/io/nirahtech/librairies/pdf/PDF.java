package io.nirahtech.librairies.pdf;

import java.io.Serializable;

public final class PDF implements Serializable {
    private final Header header;
	private final Body body;
	private final CrossReferenceTable crossReferenceTable;
	private final Trailer trailer;

	PDF(final Header header, final Body body, final CrossReferenceTable crossReferenceTable, final Trailer trailer) {
		this.header = header;
		this.body = body;
		this.crossReferenceTable = crossReferenceTable;
		this.trailer = trailer;
	}

	public final Header getHeader() {
		return this.header;
	}
	public final Body getBody() {
		return this.body;
	}
	public CrossReferenceTable getCrossReferenceTable() {
		return crossReferenceTable;
	}
	public Trailer getTrailer() {
		return trailer;
	}

	@Override
	public String toString() {
		final StringBuilder builder= new StringBuilder();
		builder.append(this.header.toString());
		builder.append("\n");
		builder.append(this.body.toString());
		builder.append("\n");
		builder.append(this.trailer.toString());
		builder.append("\n");
		builder.append(this.crossReferenceTable.toString());
		builder.append("%%EOF");
		return builder.toString();
	}
}
