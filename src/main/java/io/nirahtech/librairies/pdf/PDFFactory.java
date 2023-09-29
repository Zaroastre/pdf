package io.nirahtech.librairies.pdf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public final class PDFFactory {

    private interface Builder {
        PDF build();
    }

    private PDFFactory() { }

    public static PDFBuilder A0() {
        return new PDFBuilder(PageFormat.A0);
    }
    public static PDFBuilder A1() {
        return new PDFBuilder(PageFormat.A1);
    }
    public static PDFBuilder A2() {
        return new PDFBuilder(PageFormat.A2);
    }
    public static PDFBuilder A3() {
        return new PDFBuilder(PageFormat.A3);
    }
    public static PDFBuilder A4() {
        return new PDFBuilder(PageFormat.A4);
    }
    public static PDFBuilder A5() {
        return new PDFBuilder(PageFormat.A5);
    }
    public static PDFBuilder A6() {
        return new PDFBuilder(PageFormat.A6);
    }
    public static PDFBuilder A7() {
        return new PDFBuilder(PageFormat.A7);
    }
    public static PDFBuilder A8() {
        return new PDFBuilder(PageFormat.A8);
    }
    public static PDFBuilder A9() {
        return new PDFBuilder(PageFormat.A9);
    }
    public static PDFBuilder A10() {
        return new PDFBuilder(PageFormat.A10);
    }




    // PDF Builder
    public static final class PDFBuilder implements Builder {
        
        private final PageFormat pageFormat;
        private final ProcessSet processSet = new ProcessSet("PDF", "Text");
        private final Header header = new Header();
        private final Footer footer = new Footer(header);
        private final Set<Page> pages = new HashSet<>();

        private PDFBuilder(PageFormat pageFormat) {
            this.pageFormat = pageFormat;
        }

        public PDFPageBuilder page() {
            return new PDFPageBuilder(this, this.pageFormat);
        }

        @Override
        public PDF build() {
            final Catalog catalog = new Catalog(
                    new Outlines(
                        new Outline(
                            "Page 1", 
                            null, 
                            null, 
                            null, 
                            null, 
                            null)
                        ),
                    new Pages(
                        this.pages.toArray(new Page[this.pages.size()])
                    ));
            header.setPageCount(this.pages.size());
            return new PDF(
				header,
				new Body(catalog),
				new CrossReferenceTable(0),
				new Trailer(catalog, footer));
        }

    }

    // Table Builder
    public static final class PDFTableBuilder implements Builder {

        private final PDFPageBuilder master;
        private final Map<String, List<Object>> table = new HashMap<>();

        private PDFTableBuilder(final PDFPageBuilder master, final String... headers) {
            this.master = master;
            if (Objects.nonNull(headers)) {
                List.of(headers).forEach(header -> {
                    if (Objects.nonNull(header)) {
                        this.table.put(header, new ArrayList<>());
                    }
                });
            }
        }

        public final PDFTableBuilder raw(final Object... cells) {
            return this;
        }

        public final PDFPageBuilder and() {
            return this.master;
        } 

        @Override
        public PDF build() {
            // TODO Auto-generated method stub
            return null;
        }
    }

    // Page Builder
    public static final class PDFPageBuilder implements Builder {
        private final PDFBuilder master;
        private final Set<Text> texts = new HashSet<>();
        private final PageFormat pageFormat;
        private PageOrientation orientation = PageOrientation.PORTRAIT;

        private PDFPageBuilder(final PDFBuilder master, final PageFormat pageFormat) {
            this.master = master;
            this.pageFormat = pageFormat;
        }

        public PDFPageBuilder text(final String value, final Font font, final int fontSize, final Color color, final Position position) {
            Text text = new Text(value, font, fontSize, color, position);
            this.texts.add(text);
            return this;
        }
        public PDFTableBuilder table(final String... headers) {
            return new PDFTableBuilder(this, headers);
        }

        public PDFPageBuilder orientation(PageOrientation orientation) {
            this.orientation = orientation;
            return this;
        }

        
        public PDFPageBuilder padding(final float margin) {
            return this;
        }
        public PDFPageBuilder padding(final float topAndBottom, final float leftAndRight) {
            return this;
        }
        public PDFPageBuilder padding(final float top, final float bottom, final float left, final float right) {
            return this;
        }

        private final void buildPage() {
            final Set<Font> fonts = this.texts.stream().map(text -> text.font()).collect(Collectors.toSet());
            Position position;
            switch (this.orientation) {
                case LANDSCAPE:
                    position = new Position(this.pageFormat.getHeightInPoints(), this.pageFormat.getWidthInPoints());
                    break;
            
                default:
                    position = new Position(this.pageFormat.getWidthInPoints(), this.pageFormat.getHeightInPoints());
                    break;
            }
            final Page page = new Page(
                null, 
                new Resources(
                    master.processSet,
                    fonts.toArray(new Font[fonts.size()])
                ), 
                new Contents(this.texts.toArray(new Text[this.texts.size()])),
                position
            );
            this.master.pages.add(page);

        }

        public PDFBuilder and() {
            this.buildPage();
            return this.master;
        }

        @Override
        public PDF build() {
            this.buildPage();
            return this.master.build();
        }
    }

}
