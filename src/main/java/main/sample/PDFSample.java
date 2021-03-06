package main.sample;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.io.IOException;


public class PDFSample {
    public static void main (String args[]) throws IOException {


        //Creating PDF document object
        PDDocument document = new PDDocument();

        PDPage blankPage = new PDPage();
        //Adding the blank page to the document
        document.addPage( blankPage );

        //Retrieving the pages of the document
        PDPage page = document.getPage(0);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        contentStream.beginText();

        //Setting the font to the Content stream
        contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);

        //Setting the leading
        contentStream.setLeading(14.5f);

        //Setting the position for the line
        contentStream.newLineAtOffset(25, 725);

        String text = "This is the main.sample document and we are adding content to it.";
        for (int i = 0; i < 1000; i++){

           contentStream.showText(text);
           contentStream.newLine();
        }

        contentStream.endText();
        System.out.println("Content added");

        //Closing the content stream
        contentStream.close();

        //Saving the document
        document.save(new File("new.pdf"));

        //Closing the document
        document.close();
    }
}
