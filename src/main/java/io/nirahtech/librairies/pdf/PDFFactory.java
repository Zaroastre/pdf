package io.nirahtech.librairies.pdf;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public final class PDFFactory {

    private interface Builder {
        PDF build();
    }

    private PDFFactory() { }

    public static PDFBuilder A0() {
        return new PDFBuilder();
    }
    public static PDFBuilder A1() {
        return new PDFBuilder();
    }
    public static PDFBuilder A2() {
        return new PDFBuilder();
    }
    public static PDFBuilder A3() {
        return new PDFBuilder();
    }
    public static PDFBuilder A4() {
        return new PDFBuilder();
    }
    public static PDFBuilder A5() {
        return new PDFBuilder();
    }
    public static PDFBuilder A6() {
        return new PDFBuilder();
    }
    public static PDFBuilder A7() {
        return new PDFBuilder();
    }
    public static PDFBuilder A8() {
        return new PDFBuilder();
    }
    public static PDFBuilder A9() {
        return new PDFBuilder();
    }
    public static PDFBuilder A10() {
        return new PDFBuilder();
    }




    // PDF Builder
    public static final class PDFBuilder implements Builder {
        
        private final ProcessSet processSet = new ProcessSet("PDF", "Text");
        private final Header header = new Header();
        private final Footer footer = new Footer(header);
        private final Set<Page> pages = new HashSet<>();

        private PDFBuilder() {

        }

        public PDFPageBuilder page() {
            return new PDFPageBuilder(this);
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

    // Page Builder
    public static final class PDFPageBuilder implements Builder {
        private final PDFBuilder master;
        private final Set<Text> texts = new HashSet<>();

        private PDFPageBuilder(final PDFBuilder master) {
            this.master = master;
        }

        public PDFPageBuilder text(final String value, final Font font, final int fontSize, final Color color, final Position position) {
            Text text = new Text(value, font, fontSize, color, position);
            this.texts.add(text);
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
            final Page page = new Page(
                null, 
                new Resources(
                    master.processSet,
                    fonts.toArray(new Font[fonts.size()])
                ), 
                new Contents(this.texts.toArray(new Text[this.texts.size()]))
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
