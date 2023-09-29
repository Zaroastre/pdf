package io.nirahtech;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import io.nirahtech.librairies.pdf.Color;
import io.nirahtech.librairies.pdf.Font;
import io.nirahtech.librairies.pdf.PDF;
import io.nirahtech.librairies.pdf.PDFFactory;
import io.nirahtech.librairies.pdf.Position;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    
	private static final void createPDFFile(final PDF pdf, final File destinationFile) {
		try (FileWriter writer = new FileWriter(destinationFile)) {
			writer.write(pdf.toString());
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    /**
     * Rigorous Test :-)
     */
    @Test
    public void pdfFactoryTest()
    {
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
		
        // PDF Unit tests
        assertNotNull(pdf.getHeader());
        assertNotNull(pdf.getBody());
        assertNotNull(pdf.getCrossReferenceTable());
        assertNotNull(pdf.getTrailer());

        createPDFFile(pdf, new File("test.pdf"));

    }
}
