package io.nirahtech.librairies.pdf;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws IOException {
		final PDF document = new PDF(
				new Header(),
				new Body(
						new Catalog(
								new Outlines(
										new Outline("Page 1", null, null, null, null, null)),
								new Pages(
										new Page(null, new Resources(), new Contents())))),

				new CrossReferenceTable(0),
				new Trailer());

		String content = document.toString();

		File file = new File("test.pdf");
		FileWriter writer = new FileWriter(file);
		writer.write(content);
		writer.flush();
		writer.close();
		
	}
}
