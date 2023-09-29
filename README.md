# **HTML**

*How create a new HTML Document?*

## Usage

```java

final Font titleFont = new Font("Arial");
    final Color titleFontColor = Color.fromRGB(0, 0, 0);
    final int titleFontSize = 24;

    final Font paragraphFont = new Font("Consolas");
    final Color paragraphFontColor = Color.fromRGB(0, 0, 0);
    final int paragraphFontSize = 12;

    // Light and easy PDF Generation
    final PDF pdf = PDFFactory.A4()
            .page()
                .padding(20F)
                .text("Introduction", titleFont, titleFontSize, titleFontColor, new Position(10, 10))
                .text("Welcome on this PDF Generator test.", paragraphFont, paragraphFontSize, paragraphFontColor, new Position(10, 100))
                .and()
            
            .page()
                .padding(20F)
                .text("About Us", titleFont, titleFontSize, titleFontColor, new Position(10, 10))
                .text("I'm Nicolas, I'm programming using Java language.", paragraphFont, paragraphFontSize, paragraphFontColor, new Position(10, 100))
                .and()
            .page()
                .padding(20F)
                .text("Contact Us", titleFont, titleFontSize, titleFontColor, new Position(10, 10))
                .text("My email is: nicolas.a.metivier@gmail.com", paragraphFont, paragraphFontSize, paragraphFontColor, new Position(10, 100))
            .build();
```