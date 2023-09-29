package io.nirahtech.librairies.pdf;

import java.io.Serializable;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public final class Header implements Serializable {
    private float pdfVersion = 1.7F;
    private String fileFormat = "%PDF-".concat(String.valueOf(this.pdfVersion));
    private String specificCodification = "%âãÏÓ";
    private Charset charset = StandardCharsets.UTF_8;
    private String comments = null;
    private String creator = "NIRAHTECH PDF Dependancy";
    private String title = null;
    private String author = null;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;
    private Set<String> keywords;
    private String producer = System.getProperty("user.name");
    private String subject = null;
    private boolean trapped = false;
    private String security = null;
    private float version = 0.0F;
    private final String id;
    private int pageCount = 1;
    private Set<String> changes = new HashSet();
    private Locale language = Locale.getDefault();
    private String mimeType = "application/pdf";
    private String copyright = null;
    private String license = null;
    private URL source = null;
    private Base64 thumbnail = null;

    Header() {
        final UUID uuid = UUID.randomUUID();
        final String formattedUUID = uuid.toString().replace("-", "");
        final String part1 = formattedUUID.substring(0, 16);
        final String part2 = formattedUUID.substring(16, 32);
        this.id = String.format("<%s> <%s>", part1, part2);
    }

    public final float getPdfVersion() {
        return pdfVersion;
    }

    public final String getFileFormat() {
        return fileFormat;
    }

    public final String getSpecificCodification() {
        return specificCodification;
    }

    public final Charset getCharset() {
        return charset;
    }

    public final String getComments() {
        return comments;
    }

    public final String getCreator() {
        return creator;
    }

    public final String getTitle() {
        return title;
    }

    public final String getAuthor() {
        return author;
    }

    public final LocalDateTime getCreationDate() {
        return creationDate;
    }

    public final LocalDateTime getModificationDate() {
        return modificationDate;
    }

    public final Set<String> getKeywords() {
        return keywords;
    }

    public final String getProducer() {
        return producer;
    }

    public final String getSubject() {
        return subject;
    }

    public final boolean isTrapped() {
        return trapped;
    }

    public final String getSecurity() {
        return security;
    }

    public final float getVersion() {
        return version;
    }

    public final String getId() {
        return id;
    }

    public final int getPageCount() {
        return pageCount;
    }

    public final Collection<String> getChanges() {
        return Collections.unmodifiableCollection(changes);
    }

    public final Locale getLanguage() {
        return language;
    }

    public final String getMimeType() {
        return mimeType;
    }

    public final String getCopyright() {
        return copyright;
    }

    public final String getLicense() {
        return license;
    }

    public final URL getSource() {
        return source;
    }
    public final Base64 getThumbnail() {
        return this.thumbnail;
    }

    final void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(this.fileFormat);
        builder.append("\n");
        builder.append(this.specificCodification);
        builder.append("\n");

        if (Objects.nonNull(this.charset)) {
            builder.append("%Charset: ").append(this.charset.name());
            builder.append("\n");
        }
        if (Objects.nonNull(this.title)) {
            builder.append("%Title: ").append(this.title);
            builder.append("\n");
        }
        if (Objects.nonNull(this.author)) {
            builder.append("%Author: ").append(this.author);
            builder.append("\n");
        }
        if (Objects.nonNull(this.subject)) {
            builder.append("%Subject: ").append(this.subject);
            builder.append("\n");
        }
        if (Objects.nonNull(this.subject)) {
            builder.append("%Keywords: ").append(this.keywords.stream().collect(Collectors.joining(", ")));
            builder.append("\n");
        }
        if (Objects.nonNull(this.creator)) {
            builder.append("%Creator: ").append(this.creator);
            builder.append("\n");
        }
        if (Objects.nonNull(this.producer)) {
            builder.append("%Producer: ").append(this.producer);
            builder.append("\n");
        }
        if (Objects.nonNull(this.creationDate)) {
            final DateTimeFormatter pdfDateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssZ");
            final String pdfFormattedDate = this.creationDate.format(pdfDateTimeFormatter);
            builder.append("%CreationDate: D:").append(pdfFormattedDate);
            builder.append("\n");
        }
        if (Objects.nonNull(this.modificationDate)) {
            final DateTimeFormatter pdfDateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssZ");
            final String pdfFormattedDate = this.modificationDate.format(pdfDateTimeFormatter);
            builder.append("%ModDate: D:").append(pdfFormattedDate);
            builder.append("\n");
        }
        if (Objects.nonNull(this.version) && this.version > 0.0F) {
            builder.append("%Version: ").append(this.version);
            builder.append("\n");
        }
        if (Objects.nonNull(this.id)) {
            builder.append("%ID: ").append(this.id);
            builder.append("\n");
        }
        if (Objects.nonNull(this.id) && this.pageCount > 0) {
            builder.append("%PageCount: ").append(this.pageCount);
            builder.append("\n");
        }
        if (Objects.nonNull(this.changes) && !this.changes.isEmpty()) {
            builder.append("%Changes: ").append(this.changes.stream().collect(Collectors.joining(", ")));
            builder.append("\n");
        }
        if (Objects.nonNull(this.language)) {
            builder.append("%Language: ").append(this.language.toLanguageTag());
            builder.append("\n");
        }
        if (Objects.nonNull(this.thumbnail)) {
            builder.append("%Thumbnail: ").append(this.thumbnail.toString());
            builder.append("\n");
        }
        if (Objects.nonNull(this.mimeType)) {
            builder.append("%MIMEType: ").append(this.mimeType);
            builder.append("\n");
        }
        if (Objects.nonNull(this.copyright)) {
            builder.append("%Copyright: ").append(this.copyright);
            builder.append("\n");
        }
        if (Objects.nonNull(this.license)) {
            builder.append("%License: ").append(this.license);
            builder.append("\n");
        }
        if (Objects.nonNull(this.source)) {
            builder.append("%Source: ").append(this.source.toString());
            builder.append("\n");
        }
        if (Objects.nonNull(this.trapped)) {
            builder.append("%Trapped: ").append((this.trapped) ? "True" : "False");
            builder.append("\n");
        }

        return builder.toString();
    }
}
